package Views;

import Repositories.MainRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class HomePageView implements ActionListener {
    JButton logInBTN;
    JButton signUpBTN;
    JPanel btnPanel;
    Connection connection;
    MainFrame f = new MainFrame();
    public HomePageView(Connection conn){
        connection = conn;
        logInBTN = new JButton();
        logInBTN.setText("Log In");
        logInBTN.setFont(new Font("Arail",Font.BOLD,30));
        logInBTN.setForeground(Color.BLACK);
        logInBTN.setBackground(new Color(200,75,49));
        logInBTN.setFocusable(false);
        logInBTN.setPreferredSize(new Dimension(150,60));
        logInBTN.setBounds(500,200,250,60);
        logInBTN.addActionListener(this);
        signUpBTN = new JButton();
        signUpBTN.setText("Sign Up");
        signUpBTN.setFont(new Font("Arail",Font.BOLD,30));
        signUpBTN.setForeground(Color.BLACK);
        signUpBTN.setBackground(new Color(200,75,49));
        signUpBTN.setFocusable(false);
        signUpBTN.setPreferredSize(new Dimension(150,60));
        signUpBTN.setBounds(500,300,250,60);
        signUpBTN.addActionListener(this);
        JLabel label = new JLabel();
        label.setText("Welcome To Train Booking System");
        label.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        label.setForeground(new Color(45,66,99));
        label.setPreferredSize(new Dimension(200,200));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(380,50,500,200);

        f.setLayout(null);
        f.add(label);
        f.add(logInBTN);
        f.add(signUpBTN);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logInBTN)
        {
            f.dispose();
            logInPage l = new logInPage(connection);
        }
        else if(e.getSource()==signUpBTN)
        {
            f.dispose();
            signUpPage s = new signUpPage(connection);
        }

    }

}
