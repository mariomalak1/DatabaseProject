package Views;

import Controllers.TrainController;
import Models.Train;
import Models.Trip;
import Models.User;
import Repositories.TrainRepository;
import Repositories.TripRepository;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class showAllToAdmin implements ActionListener {
    Connection connection ;
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    MainFrame f = new MainFrame();
    JLabel welcome = new JLabel();
    User newUser;
    JButton editTrain;
    Border blackline = BorderFactory.createLineBorder(Color.black,2);
    public showAllToAdmin(User user,Connection conn){
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
        if (e.getSource() == backBTN)
        {
            AdminView a = new AdminView(newUser , connection);
            f.dispose();
        }else if(e.getSource() == editTrain){
            editDataForAdmin edit  = new editDataForAdmin(newUser , connection);
            edit.editTrain();
            f.dispose();
        }
    }
    public void showAllTrains(){
        TrainRepository trepo = new TrainRepository();
        List<Train> trainList;
        try {
            trainList = trepo.getAllTrains(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int y = 100;
        for (int i = 0 ; i < trainList.size();i++){
            JLabel label = new JLabel();
            label.setText("Train ID: "+trainList.get(i).getID()+" -- Capacity: "+trainList.get(i).getCapacity()+
                    " -- Trip ID: "+trainList.get(i).getTrip().getID()+" -- Price Per Seat: "
                    + trainList.get(i).getPrice());
            label.setFont(new Font("Consolas",Font.PLAIN,20));
            label.setForeground(Color.BLACK);
            label.setBorder(blackline);
            label.setBounds(180,y,850,50);
            f.add(label);
            y+=60;
        }
        editTrain = new JButton("Edit Train");
        editTrain.setBackground(new Color(0x212A3E));
        editTrain.setFont(new Font("Consolas", Font.PLAIN,20));
        editTrain.setForeground(Color.WHITE);
        editTrain.setBounds(1000,600,200,50);
        editTrain.setFocusable(false);
        editTrain.addActionListener(this);
        f.add(editTrain);
        f.setVisible(true);
    }
    public void showAllTrips(){
        TripRepository trepo = new TripRepository();
        List<Trip> tripList;
        try {
            tripList = trepo.getAllTrips(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int y = 100;
        for (int i = 0 ; i < tripList.size();i++){
            JLabel label = new JLabel();
            label.setText("TripID: "+tripList.get(i).getID()+
                    " -- SourceCity: "+ tripList.get(i).getSource().getCity().getName()+
                    " -- DestinationCity: "+tripList.get(i).getDestination().getCity().getName()+
                    " -- StartTime: "+tripList.get(i).getStartDateTime().toString()+
                    " -- EndTime: "+tripList.get(i).EndTime().toString());
            label.setFont(new Font("Consolas",Font.PLAIN,15));
            label.setForeground(Color.BLACK);
            label.setBorder(blackline);
            label.setBounds(180,y,1000,50);
            f.add(label);
            y+=60;
        }
        editTrain = new JButton("Edit Trip");
        editTrain.setBackground(new Color(0x212A3E));
        editTrain.setFont(new Font("Consolas", Font.PLAIN,20));
        editTrain.setForeground(Color.WHITE);
        editTrain.setBounds(1000,600,200,50);
        editTrain.setFocusable(false);
        editTrain.addActionListener(this);
        f.add(editTrain);
        f.setVisible(true);
    }
}
