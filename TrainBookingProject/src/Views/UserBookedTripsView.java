package Views;

import Controllers.BookingController;
import Models.Booking;
import Models.User;
import Repositories.MainRepository;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.Connection;
import java.util.ArrayList;

public class UserBookedTripsView implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    JLabel welcome = new JLabel();
    MainFrame f = new MainFrame();
    User newUser = null;
    ArrayList<Booking> BookingList ;
    Connection connection ;
    public UserBookedTripsView(User user,Connection conn){
        connection = conn;
        BookingList = new ArrayList<>();
        BookingList = BookingController.getAllBookingForUser(user.getID(),connection);
        //System.out.println(BookingList.get(0).getID());
        newUser = user;
        backBTN.setText("Go Back");
        backBTN.addActionListener(this);
        welcome.setText("Welcome, "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        upper.add(welcome);
        upper.add(backBTN);
        loadBooking();
        f.add(upper);
        f.setVisible(true);

    }
    Border blackline = BorderFactory.createLineBorder(Color.black,2);
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBTN)
        {
            f.dispose();
            UserView l = new UserView(newUser);
        }
    }
    private void loadBooking(){
        if(BookingList.isEmpty())
        {
            JLabel label = new JLabel();
            label.setText("No Booking To Show");

            label.setFont(new Font("Consolas",Font.PLAIN,50));
            label.setForeground(new Color(0,0,0));
            label.setBounds(400,100,500,80);
            f.add(label);
        }
        else {
            int h = 100;
            for(int i =0; i< BookingList.size();i++)
            {
                JLabel label = new JLabel();
                label.setBorder(blackline);
                label.setText("Booking ID: "+BookingList.get(i).getID()
                        +" -- User ID: "+BookingList.get(i).getUser().getID()
                        +" -- TrainID: "+BookingList.get(i).getTrain().getID()+
                        " -- Number Of Seats: "+BookingList.get(i).getNumberOfSeats());
                label.setFont(new Font("Consolas",Font.PLAIN,20));
                label.setForeground(new Color(0,0,0));
                label.setBounds(300,h,700,80);
                h+=100;
                f.add(label);
            }

        }

    }
}
