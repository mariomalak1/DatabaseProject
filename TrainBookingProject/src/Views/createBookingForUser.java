package Views;

import Controllers.BookingController;
import Controllers.TrainController;
import Controllers.UserController;
import Models.Booking;
import Models.Train;
import Models.User;
import Repositories.TrainRepository;
import Repositories.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class createBookingForUser implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    MainFrame f = new MainFrame();
    Connection connection ;
    User newUser;
    JLabel welcome = new JLabel();
    JComboBox<Integer> trainid ;
    JTextField userIdT ;
    JTextField numberOfseats;
    JButton createBookingBTN ;
    public createBookingForUser(User user , Connection conn){
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
        List<Integer> listofIDs = getAllTrainIDs();
        Integer[] ids = new Integer[listofIDs.size()];
        for (int i = 0 ; i < listofIDs.size();i++)
        {
            ids[i] = listofIDs.get(i);
        }
        trainid = new JComboBox<>(ids);
        trainid.setSelectedIndex(0);
        trainid.setBounds(400,100,200,30);
        JLabel trainidL = new JLabel("Train ID:");
        trainidL.setFont(new Font("Consolas",Font.PLAIN,20));
        trainidL.setForeground(Color.BLACK);
        trainidL.setBounds(275,78,200,80);
        JLabel userIdL = new JLabel("User ID:");
        userIdL.setFont(new Font("Consolas",Font.PLAIN,20));
        userIdL.setForeground(Color.BLACK);
        userIdL.setBounds(280,178,200,80);
        userIdT = new JTextField();
        userIdT.setFont(new Font("Consolas",Font.PLAIN,20));
        userIdT.setBounds(400,200,200,30);
        JLabel NofseatsL = new JLabel("Number Of Seats:");
        NofseatsL.setFont(new Font("Consolas",Font.PLAIN,20));
        NofseatsL.setForeground(Color.BLACK);
        NofseatsL.setBounds(200,278,200,80);
        numberOfseats = new JTextField();
        numberOfseats.setFont(new Font("Consolas",Font.PLAIN,20));
        numberOfseats.setBounds(400,300,200,30);
        createBookingBTN = new JButton("Create");
        createBookingBTN.setFocusable(false);
        createBookingBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        createBookingBTN.setBounds(900,600,300,50);
        createBookingBTN.setBackground(new Color(0x212A3E));
        createBookingBTN.addActionListener(this);
        createBookingBTN.setForeground(Color.WHITE);
        f.add(userIdL);
        f.add(userIdT);
        f.add(trainid);
        f.add(trainidL);
        f.add(upper);
        f.add(NofseatsL);
        f.add(numberOfseats);
        f.add(createBookingBTN);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBTN)
        {
            SearchForTripView v  = new SearchForTripView(newUser,connection,null);
            f.dispose();
        }else if(e.getSource() == createBookingBTN) {
            Integer trainId= Integer.parseInt(trainid.getSelectedItem().toString());
            Integer userId ;
            Integer numberofseats;
            User user;
            Train train = TrainController.getTrainById(trainId,connection);
            if(Number.isNumeric(userIdT.getText()) && Number.isNumeric(numberOfseats.getText()))
            {
                userId = Integer.parseInt(userIdT.getText());
                numberofseats = Integer.parseInt(numberOfseats.getText());
                user = UserController.getUserById(userId,connection);
                if(user != null)
                {
                    System.out.println(numberofseats);
                    Booking book = BookingController.creatBooking(user,train,numberofseats,connection);
                    if(book != null)
                    {
                        JOptionPane.showMessageDialog(null, "Booking Created Successfully, BookingID: "+book.getID(), "CreateBooking", JOptionPane.INFORMATION_MESSAGE);
                        UserView u = new UserView(newUser,connection);
                        f.dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "Error While Creating Booking", "CreateBooking", JOptionPane.ERROR_MESSAGE);

                    }
                }else {
                    JOptionPane.showMessageDialog(null, "User ID in not valid, No User With that id", "CreateBooking", JOptionPane.ERROR_MESSAGE);

                }
            }else {
                JOptionPane.showMessageDialog(null, "Please Enter Only Number", "CreateBooking", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    private java.util.List<Integer> getAllTrainIDs(){
        java.util.List<Train> l;
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
