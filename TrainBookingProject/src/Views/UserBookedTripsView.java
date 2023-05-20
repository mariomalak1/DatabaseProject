package Views;

import Controllers.BookingController;
import Models.Booking;
import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.ArrayList;

public class UserBookedTripsView implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    JLabel welcome = new JLabel();
    MainFrame f = new MainFrame();
    User newUser = null;
    ArrayList<Booking> BookingList ;
    public UserBookedTripsView(User user){
        BookingList = new ArrayList<>();
        BookingList = BookingController.getAllBookingForUser(user);
        //System.out.println(BookingList.get(0).getID());
        newUser = user;
        backBTN.setText("Go Back");
        backBTN.addActionListener(this);
        welcome.setText("Welcome: "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        upper.add(welcome);
        upper.add(backBTN);
        loadBooking();
        f.add(upper);
        f.setVisible(true);
    }
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
            for(int i =0; i< BookingList.size();i++)
            {
                JLabel label = new JLabel();

                label.setText("Booking ID: "+BookingList.get(i).getID()
                        +" User ID: "+BookingList.get(i).getUser().getID()
                        +" TrainID: "+BookingList.get(i).getTrain().getID()+
                        " Number Of Seats: "+BookingList.get(i).getNumberOfSeats());
                label.setFont(new Font("Consolas",Font.PLAIN,20));
                label.setForeground(new Color(0,0,0));
                label.setBounds(3400,100,500,80);
                f.add(label);
            }

        }

    }
}