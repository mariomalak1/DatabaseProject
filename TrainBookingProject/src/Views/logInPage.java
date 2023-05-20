package Views;

import Controllers.UserController;
import Models.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logInPage extends MainFrame implements ActionListener {
    JLabel userL = new JLabel();
    JLabel userP = new JLabel();
    JButton loginBTN;
    JButton backBTN = new JButton();
    JPanel upper = new JPanel();
    JTextField userIdIN;
    JTextField userPassIN;
    MainFrame LoginFrame = new MainFrame();
    ImageIcon backIcon = new ImageIcon("src\\back.png");
    public logInPage(){
        backBTN.setIcon(backIcon);
        backBTN.setText("Go Back");
        backBTN.setHorizontalTextPosition(JButton.CENTER);
        backBTN.setFocusable(false);
        backBTN.addActionListener(this);
        //backBTN.setBackground(Color.WHITE);
        backBTN.setBounds(0,0,100,50);
        upper.setLayout(null);
        upper.add(backBTN);
        upper.setBackground(new Color(0x212A3E));
        upper.setPreferredSize(new Dimension(80,50));
        upper.setBounds(0,0,100*100,50);

        userL.setText("User ID");
        userL.setForeground(Color.BLACK);
        userL.setBounds(405,203,150,50);
        userL.setFont(new Font("Consolas",Font.PLAIN,30));
        userP.setText("Password");
        userP.setForeground(Color.BLACK);
        userP.setBounds(390,303,150,50);
        userP.setFont(new Font("Consolas",Font.PLAIN,30));
        userIdIN = new JTextField();
        userPassIN = new JTextField();
        userIdIN.setBounds(540,200,300,50);
        userIdIN.setBackground(new Color(241,246,249));
        userIdIN.setFont(new Font("Consolas",Font.PLAIN,30));
        userPassIN.setBounds(540,300,300,50);
        userPassIN.setBackground(new Color(241,246,249));
        userPassIN.setFont(new Font("Consolas",Font.PLAIN,30));
        loginBTN = new JButton();
        loginBTN.addActionListener(this);
        loginBTN.setText("Log In");
        loginBTN.setBounds(615,400,100,50);
        LoginFrame.add(userIdIN);
        LoginFrame.add(userL);
        LoginFrame.add(userP);
        LoginFrame.add(userPassIN);
        LoginFrame.add(loginBTN);
        LoginFrame.add(upper);
        LoginFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginBTN)
        {
            int userid = Integer.parseInt(userIdIN.getText());
            String pass = userPassIN.getText();
            User user = UserController.getUserById(userid);
            if(user == null)
            {
                JOptionPane.showMessageDialog(null,"Login Failed, UserId or Password is is wrong","Login Failed",JOptionPane.ERROR_MESSAGE);

            }
            else {
                System.out.println("Login Done");
            }
        }
        else if(e.getSource() == backBTN)
        {
            LoginFrame.dispose();
            HomePageView h = new HomePageView();
        }
    }
}
