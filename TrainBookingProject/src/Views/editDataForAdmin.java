package Views;

import Controllers.TrainController;
import Controllers.TripController;
import Models.Train;
import Models.Trip;
import Models.User;
import Repositories.CityRepository;
import Repositories.TrainRepository;
import Repositories.TripRepository;
import Repositories.VisitRepository;
import com.toedter.calendar.JDateChooser;

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
    JComboBox<Integer> tripIdC;
    JTextField capacityT ;
    JTextField tripIdT;
    JTextField pricePerSeatT;
    JButton editTrainBTN ;
    JButton deleteTrainBTN;
    JButton editTripBTN ;
    JButton deleteTripBTN;
    JComboBox listOfTripsS;
    JComboBox listOfTripsD;
    JComboBox minC;
    JDateChooser cal;
    JComboBox hoursC;
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

        }else if(e.getSource() == deleteTripBTN)
        {
            Integer tripId = Integer.parseInt(tripIdC.getSelectedItem().toString());
            TripRepository tripRepo = new TripRepository();
            VisitRepository visitRepo = new VisitRepository();

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
    public void editTrip(){
        JLabel tripIdL = new JLabel("Train ID:");
        tripIdL.setFont(new Font("Consolas",Font.PLAIN,20));
        tripIdL.setForeground(Color.BLACK);
        tripIdL.setBounds(220,100,200,80);
        List<Integer> listofIds = getAllTripsIDs();
        Integer [] l = new Integer[listofIds.size()];
        for(int i = 0 ; i < listofIds.size();i++)
        {
            l[i] = listofIds.get(i);
        }
        tripIdC = new JComboBox<>(l);
        tripIdC.setBounds(330,110,150,50);
        List<String> nS;
        CityRepository trepo = new CityRepository();
        nS = trepo.getAllCities(connection);
        String[] loadedS = new String[nS.size()];
        for( int i = 0 ; i < nS.size() ; i++)
        {
            loadedS[i]=(nS.get(i));
        }
        listOfTripsS = new JComboBox(loadedS);
        listOfTripsS.setBounds(600,110,200,50);
        listOfTripsS.setSelectedIndex(0);
        JLabel labelS = new JLabel("SourceCity:");
        labelS.setFont(new Font("Consolas",Font.PLAIN,20));
        labelS.setForeground(Color.BLACK);
        labelS.setBounds(610,50,200,80);

        f.add(listOfTripsS);
        f.add(labelS);
        //---------------------------
        listOfTripsD = new JComboBox(loadedS);
        listOfTripsD.setBounds(850,110,200,50);
        listOfTripsD.setSelectedIndex(0);
        JLabel labelD = new JLabel("DestinationCity:");
        labelD.setFont(new Font("Consolas",Font.PLAIN,20));
        labelD.setForeground(Color.BLACK);
        labelD.setBounds(860,50,200,80);
        f.add(labelD);
        f.add(listOfTripsD);
        cal =new JDateChooser();
        cal.setForeground(Color.BLACK);
        cal.setBounds(330,250,150,30);
        JLabel calenderL = new JLabel("Date:");
        calenderL.setFont(new Font("Consolas",Font.PLAIN,20));
        calenderL.setForeground(Color.BLACK);
        calenderL.setBounds(250,227,300,80);
        String[] hours = new String[25];
        for(int i = 0 ; i <= 24;i++)
        {
            hours[i] = String.valueOf(i);
        }
        hoursC = new JComboBox(hours);
        hoursC.setBounds(800,250,50,30);
        hoursC.setSelectedIndex(0);
        String[] min = new String[60];
        for(int i = 0 ; i <= 59;i++)
        {
            min[i] = String.valueOf(i);
        }
        minC = new JComboBox(min);
        minC.setBounds(860,250,50,30);
        minC.setSelectedIndex(0);
        JLabel timeL = new JLabel("StartTime (h-m):");
        timeL.setFont(new Font("Consolas",Font.PLAIN,20));
        timeL.setForeground(Color.BLACK);
        timeL.setBounds(620,227,300,80);
        editTripBTN = new JButton("Edit");
        editTripBTN.setFocusable(false);
        editTripBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        editTripBTN.setBounds(670,550,200,50);
        editTripBTN.setBackground(new Color(0x212A3E));
        editTripBTN.addActionListener(this);
        editTripBTN.setForeground(Color.WHITE);
        deleteTripBTN = new JButton("Delete");
        deleteTripBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        deleteTripBTN.setBounds(420,550,200,50);
        deleteTripBTN.setBackground(new Color(0x212A3E));
        deleteTripBTN.addActionListener(this);
        deleteTripBTN.setForeground(Color.WHITE);
        f.add(tripIdL);
        f.add(tripIdC);
        f.add(editTripBTN);
        f.add(deleteTripBTN);
        f.add(calenderL);
        f.add(cal);
        f.add(hoursC);
        f.add(timeL);
        f.add(minC);
        f.setVisible(true);
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
    private List<Integer> getAllTripsIDs(){
        List<Trip> l ;
        TripRepository trepo = new TripRepository();
        try {
            l = trepo.getAllTrips(connection);
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
