package Views;

import Controllers.TrainController;
import Models.Train;
import Models.Trip;
import Models.User;
import Repositories.TrainRepository;
import Repositories.TripRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class editDataForAdmin implements ActionListener {
    Connection connection ;
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    MainFrame f = new MainFrame();
    JLabel welcome = new JLabel();
    User newUser;
    JComboBox<Integer> trainIdC;
    JTextField capacityT ;
    JTextField tripIdT;
    JTextField pricePerSeatT;
    JButton editTrainBTN ;
    JButton deleteTrainBTN;
    public editDataForAdmin(User user,Connection conn){
        connection = conn;
        newUser = user;
        backBTN.setText("Go Back");
        backBTN.addActionListener(this);
        welcome.setText("Welcome, "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        upper.add(backBTN);
        upper.add(welcome);
        f.add(upper);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backBTN)
        {
            showAllToAdmin s = new showAllToAdmin(newUser ,connection);
            s.showAllTrains();
            f.dispose();
        }else if(e.getSource() == editTrainBTN)
        {
            TripRepository tr = new TripRepository();
            Integer trainID = Integer.parseInt(trainIdC.getSelectedItem().toString());
            Train old = TrainController.getTrainById(trainID,connection);
            Integer tripID  = old.getTrip().getID();
            if(!tripIdT.getText().equals(""))
            {
                tripID = Integer.parseInt(tripIdT.getText());
            }
            Integer cap = old.getCapacity();
            if(!capacityT.getText().equals(""))
            {
                cap = Integer.parseInt(capacityT.getText());
            }
            double price = old.getPrice();
            if(!pricePerSeatT.getText().equals(""))
            {
                price = Double.parseDouble(pricePerSeatT.getText());
            }
            Trip trip;
            try {
                trip = tr.getTripById(tripID,connection);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Train t = new Train(trainID,cap,trip,price);
            if (TrainController.updateTrain(t,connection))
            {
                JOptionPane.showMessageDialog(null,"Train Updated Successfully","Train Update",JOptionPane.INFORMATION_MESSAGE);
                AdminView a =new AdminView(newUser , connection);
                f.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Train Updated Failed","Train Update",JOptionPane.ERROR_MESSAGE);

            }


        }
        else if(e.getSource() == deleteTrainBTN) {
            Integer trainID = Integer.parseInt(trainIdC.getSelectedItem().toString());
            TrainRepository trepo = new TrainRepository();
            try {
                trepo.deleteTrain(trainID,connection);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null,"Train Deleted Successfully","Train Delete",JOptionPane.INFORMATION_MESSAGE);
            AdminView a =new AdminView(newUser , connection);
            f.dispose();

        }
    }
    public void editTrain(){
        JLabel trainIdL = new JLabel("Train ID:");
        trainIdL.setFont(new Font("Consolas",Font.PLAIN,30));
        trainIdL.setForeground(Color.BLACK);
        trainIdL.setBounds(200,100,200,80);
        List<Integer> listofIds = getAllTrainIDs();
        Integer [] l = new Integer[listofIds.size()];
        for(int i = 0 ; i < listofIds.size();i++)
        {
            l[i] = listofIds.get(i);
        }
        trainIdC = new JComboBox<>(l);
        trainIdC.setBounds(380,110,150,50);
        JLabel capacity = new JLabel("Capacity: ");
        capacity.setFont(new Font("Consolas",Font.PLAIN,30));
        capacity.setForeground(Color.BLACK);
        capacity.setBounds(600,100,200,80);
        capacityT = new JTextField();
        capacityT.setFont(new Font("Consolas",Font.PLAIN,30));
        capacityT.setBounds(770,110,150,50);
        JLabel tripidL = new JLabel("Trip ID:");
        tripidL.setFont(new Font("Consolas",Font.PLAIN,30));
        tripidL.setForeground(Color.BLACK);
        tripidL.setBounds(200,200,200,80);
        tripIdT = new JTextField();
        tripIdT.setFont(new Font("Consolas",Font.PLAIN,30));
        tripIdT.setBounds(380,210,150,50);
        JLabel priceL = new JLabel("PricePerSeat:");
        priceL.setFont(new Font("Consolas",Font.PLAIN,30));
        priceL.setForeground(Color.BLACK);
        priceL.setBounds(550,200,250,80);
        pricePerSeatT = new JTextField();
        pricePerSeatT.setFont(new Font("Consolas",Font.PLAIN,30));
        pricePerSeatT.setBounds(770,210,150,50);
        editTrainBTN = new JButton("Edit");
        editTrainBTN.setFocusable(false);
        editTrainBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        editTrainBTN.setBounds(670,450,200,50);
        editTrainBTN.setBackground(new Color(0x212A3E));
        editTrainBTN.addActionListener(this);
        editTrainBTN.setForeground(Color.WHITE);
        deleteTrainBTN = new JButton("Delete");
        deleteTrainBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        deleteTrainBTN.setBounds(420,450,200,50);
        deleteTrainBTN.setBackground(new Color(0x212A3E));
        deleteTrainBTN.addActionListener(this);
        deleteTrainBTN.setForeground(Color.WHITE);
        f.add(capacity);
        f.add(capacityT);
        f.add(trainIdL);
        f.add(trainIdC);
        f.add(tripidL);
        f.add(tripIdT);
        f.add(priceL);
        f.add(pricePerSeatT);
        f.add(editTrainBTN);
        f.add(deleteTrainBTN);
        f.setVisible(true);
        //trainIdL.
    }
    private List<Integer> getAllTrainIDs(){
        List<Train> l;
        TrainRepository trepo = new TrainRepository();
        try {
            l = trepo.getAllTrains(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Integer> ids = new ArrayList<>();
        if(l != null)
        {
            for (int i = 0 ; i < l.size();i++)
            {
                ids.add(l.get(i).getID());
            }
        }
        return  ids;

    }
}
