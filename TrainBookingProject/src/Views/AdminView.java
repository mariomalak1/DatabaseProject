package Views;

import Models.User;
import Repositories.MainRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AdminView implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    MainFrame LoginFrame = new MainFrame();
    Connection connection ;
    User newUser;
    JLabel welcome = new JLabel();
    Button createTrainBTN = new Button("Create Train",300,150,200);
    Button createTripBTN = new Button("Create Trip",510,150,200);
    Button createAdminBTN = new Button("Create Admin",720,150,200);
    Button showAllTrains = new Button("Show Trains",300,250,300);
    Button showAllTrips = new Button("Show Trips",620,250,300);

    public AdminView(User user, Connection conn)
    {
        newUser = user;
        connection =conn;
        backBTN.setText("Log Out");
        backBTN.addActionListener(this);
        welcome.setText("Welcome, "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        createTrainBTN.addActionListener(this);
        createTripBTN.addActionListener(this);
        createAdminBTN.addActionListener(this);
        showAllTrains.addActionListener(this);
        showAllTrips.addActionListener(this);
        LoginFrame.add(showAllTrains);
        LoginFrame.add(createAdminBTN);
        LoginFrame.add(createTrainBTN);
        LoginFrame.add(createTripBTN);
        LoginFrame.add(showAllTrips);
        upper.add(backBTN);
        upper.add(welcome);
        LoginFrame.add(upper);
        LoginFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBTN)
        {
            logInPage l = new logInPage(connection);
            LoginFrame.dispose();
        }else if(e.getSource() == showAllTrains){
            showAllToAdmin a = new showAllToAdmin(newUser,connection);
            a.showAllTrains();
            LoginFrame.dispose();
        }
    }
    private static class Button extends JButton{
        public Button(String text,int x,int y,int w){
            this.setText(text);
            this.setFont(new Font("Consolas", Font.PLAIN,20));
            this.setBounds(x,y,w,50);
            this.setBackground(new Color(0x212A3E));
            this.setForeground(Color.WHITE);
            this.setFocusable(false);
            this.setHorizontalTextPosition(JButton.CENTER);

        }
    }

}

