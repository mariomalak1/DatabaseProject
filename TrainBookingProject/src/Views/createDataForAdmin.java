package Views;

import Controllers.TrainController;
import Controllers.TripController;
import Models.Train;
import Models.Trip;
import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class createDataForAdmin implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    MainFrame f = new MainFrame();
    Connection connection ;
    User newUser;
    JLabel welcome = new JLabel();
    JTextField capacityT ;
    JTextField tripIdT;
    JTextField pricePerSeatT;
    JButton insertTrainBTN ;
    public createDataForAdmin(User user,Connection conn){
        newUser = user;
        connection =conn;
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
        if(e.getSource() == backBTN)
        {
            AdminView a = new AdminView(newUser,connection);
            f.dispose();
        }
        else if(e.getSource() == insertTrainBTN) {
            Integer cap = Integer.parseInt(capacityT.getText());
            Integer tripId = Integer.parseInt(tripIdT.getText());
            double price = Double.parseDouble(pricePerSeatT.getText());
            Trip trip = TripController.getTripByID(tripId,connection);
            Train train = TrainController.createTrain(cap,trip,price,connection);
            if (train != null){
                JOptionPane.showMessageDialog(null,"Train Created Successfully","Train Creation",JOptionPane.INFORMATION_MESSAGE);
                AdminView a =new AdminView(newUser , connection);
                f.dispose();
            }else {
                JOptionPane.showMessageDialog(null,"Train Creation Failed ","Train Creation",JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    public void createTrain(){
        JLabel capacity = new JLabel("Capacity: ");
        capacity.setFont(new Font("Consolas",Font.PLAIN,30));
        capacity.setForeground(Color.BLACK);
        capacity.setBounds(400,100,200,80);
        capacityT = new JTextField();
        capacityT.setFont(new Font("Consolas",Font.PLAIN,30));
        capacityT.setBounds(600,110,150,50);
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
        insertTrainBTN = new JButton("Insert");
        insertTrainBTN.setFocusable(false);
        insertTrainBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        insertTrainBTN.setBounds(540,450,200,50);
        insertTrainBTN.setBackground(new Color(0x212A3E));
        insertTrainBTN.addActionListener(this);
        insertTrainBTN.setForeground(Color.WHITE);
        f.add(capacity);
        f.add(capacityT);
        f.add(tripidL);
        f.add(tripIdT);
        f.add(priceL);
        f.add(pricePerSeatT);
        f.add(insertTrainBTN);
        f.setVisible(true);
    }
}
