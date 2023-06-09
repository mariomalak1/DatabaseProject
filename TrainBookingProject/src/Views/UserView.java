package Views;

import Models.Trip;
import Models.User;
import Repositories.MainRepository;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserView extends MainFrame implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    JLabel welcome = new JLabel();
    JButton search = new JButton();
    JButton showBooked = new JButton();
    JButton editProfile = new JButton();
    MainFrame f = new MainFrame();
    User newUser = null;
    Connection connection;
    public UserView(User user,Connection conn){
        newUser = user;
        connection = conn;
        backBTN.setText("Log Out");
        backBTN.addActionListener(this);
        welcome.setText("Welcome, "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        search.addActionListener(this);
        search.setText("Search For Trip");
        search.setFocusable(false);
        search.setFont(new Font("Consolas",Font.PLAIN,20));
        search.setForeground(Color.white);
        search.setBackground(new Color(0x212A3E));
        search.setBounds(490,100,300,80);
        //-----------Show All Button
        showBooked.addActionListener(this);
        showBooked.setText("Show Booked Trips");
        showBooked.setFocusable(false);
        showBooked.setFont(new Font("Consolas",Font.PLAIN,20));
        showBooked.setForeground(Color.white);
        showBooked.setBackground(new Color(0x212A3E));
        showBooked.setBounds(490,250,300,80);
        //---------Edit Profile Button
        editProfile.addActionListener(this);
        editProfile.setText("Edit Profile");
        editProfile.setFocusable(false);
        editProfile.setFont(new Font("Consolas",Font.PLAIN,20));
        editProfile.setForeground(Color.white);
        editProfile.setBackground(new Color(0x212A3E));
        editProfile.setBounds(490,400,300,80);
        upper.add(welcome);
        upper.add(backBTN);
        f.add(upper);
        f.add(search);
        f.add(showBooked);
        f.add(editProfile);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBTN)
        {
            logInPage l = new logInPage(connection);
            f.dispose();
        }
        else if(e.getSource()== editProfile)
        {

            editProfile n = new editProfile(newUser,connection);
            f.dispose();
        }
        else if(e.getSource()==showBooked)
        {

            UserBookedTripsView n = new UserBookedTripsView(newUser,connection);
            f.dispose();
        }
        else if(e.getSource() == search)
        {
            List<Trip> l= null;
            SearchForTripView s = new SearchForTripView(newUser,connection,l);
            f.dispose();
        }
    }
}
