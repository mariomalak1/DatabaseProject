package Views;

import Controllers.UserController;
import Models.User;
import Repositories.MainRepository;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Objects;

class Number {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}

public class logInPage extends MainFrame implements ActionListener {
    JLabel userL = new JLabel();
    JLabel userP = new JLabel();
    JButton loginBTN;
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    JTextField userIdIN;
    JTextField userPassIN;
    MainFrame LoginFrame = new MainFrame();
    Connection connection;

    //ImageIcon backIcon = new ImageIcon("src\\back.png");

    public logInPage(Connection conn){
        connection = conn;
        backBTN.addActionListener(this);
        upper.add(backBTN);
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
        if(e.getSource()==loginBTN) {
            int userid;
            // to check if id he enters it is numeric or not, if not, it will send error message -> put valid id
            if (!Number.isNumeric(userIdIN.getText())){
                JOptionPane.showMessageDialog(null, "Please Enter Valid ID Number", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

            else{
                userid = Integer.parseInt(userIdIN.getText());

                String pass = userPassIN.getText();
                User user = UserController.getUserById(userid,connection);

                // check if user is found, else it will send error message -> id not found in system
                if(user == null) {
                    JOptionPane.showMessageDialog(null,"Login Failed, User Id Is Not Found, You can Put one other or Make new Register","Login Failed",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // check if this valid password, else it will send message User id nor your password incorrect
                    if (!user.getPassword().equals(pass)){
                        JOptionPane.showMessageDialog(null,"Login Failed, User Id or Password is is wrong","Login Failed",JOptionPane.ERROR_MESSAGE);
                    }

                    else{
                        if (Objects.equals(user.getRole(), "Admin")) {
                            AdminView a = new AdminView(user, connection);
                            LoginFrame.dispose();
                        }

                        else {
                            UserView u = new UserView(user, connection);
                            LoginFrame.dispose();
                        }
                    }
                }
            }
        }

        else if(e.getSource() == backBTN) {
            HomePageView h = new HomePageView(connection);
            LoginFrame.dispose();
        }
    }
}
