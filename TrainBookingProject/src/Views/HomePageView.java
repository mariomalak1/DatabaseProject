package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView implements ActionListener {
    JButton logInBTN;
    JButton signUpBTN;
    public HomePageView(){
        logInBTN = new JButton();
        logInBTN.setText("Log In");
        logInBTN.setFont(new Font("Arail",Font.BOLD,30));
        logInBTN.setForeground(Color.BLACK);
        logInBTN.setBackground(new Color(200,75,49));
        logInBTN.setFocusable(false);
        logInBTN.setBounds(500,200,250,60);
        logInBTN.addActionListener(this);
        signUpBTN = new JButton();
        signUpBTN.setText("Sign Up");
        signUpBTN.setFont(new Font("Arail",Font.BOLD,30));
        signUpBTN.setForeground(Color.BLACK);
        signUpBTN.setBackground(new Color(200,75,49));
        signUpBTN.setFocusable(false);
        signUpBTN.setBounds(500,300,250,60);
        signUpBTN.addActionListener(this);
        JLabel label = new JLabel();
        label.setText("Welcome To Train Booking System");
        label.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        label.setForeground(new Color(45,66,99));
        label.setBounds(410,50,430,200);
        MainFrame f = new MainFrame();
        f.add(label);
        f.add(logInBTN);
        f.add(signUpBTN);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logInBTN)
        {
            System.out.println("Login");
        }
        else if(e.getSource()==signUpBTN)
        {
            System.out.println("SignUp");
        }
    }
}
