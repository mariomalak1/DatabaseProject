package Views;

import Controllers.UserController;
import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editProfile implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    JLabel welcome = new JLabel();
    JLabel fnameL = new JLabel();
    JLabel lnameL = new JLabel();
    JLabel emailL = new JLabel();
    JLabel passL = new JLabel();
    JLabel confirmpassL = new JLabel();
    JTextField fnameT = new JTextField();
    JTextField lnameT = new JTextField();
    JTextField emailT = new JTextField();
    JTextField passT = new JTextField();
    JTextField confirmpassT = new JTextField();
    JButton editBTN = new JButton();
    MainFrame f = new MainFrame();
    User newUser = null;
    public editProfile(User user){
        newUser = user;
        backBTN.setText("Log Out");
        backBTN.addActionListener(this);
        welcome.setText("Welcome: "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        fnameL.setText("First Name:");
        fnameL.setForeground(Color.BLACK);
        fnameL.setFont(new Font("Consolas",Font.PLAIN,30));
        fnameL.setBounds(165,100,200,80);
        fnameT.setBackground(new Color(241,246,249));
        fnameT.setFont(new Font("Consolas",Font.PLAIN,30));
        fnameT.setBounds(360,110,200,50);
        lnameL.setText("Last Name:");
        lnameL.setForeground(Color.BLACK);
        lnameL.setFont(new Font("Consolas",Font.PLAIN,30));
        lnameL.setBounds(600,100,200,80);
        lnameT.setBackground(new Color(241,246,249));
        lnameT.setFont(new Font("Consolas",Font.PLAIN,30));
        lnameT.setBounds(780,110,200,50);
        emailL.setText("Email:");
        emailL.setForeground(Color.BLACK);
        emailL.setFont(new Font("Consolas",Font.PLAIN,30));
        emailL.setBounds(250,180,150,80);
        emailT.setBounds(360,190,620,50);
        emailT.setFont(new Font("Consolas",Font.PLAIN,30));
        emailT.setBackground(new Color(241,246,249));
        passL.setText("Password:");
        passL.setForeground(Color.BLACK);
        passL.setFont(new Font("Consolas",Font.PLAIN,30));
        passL.setBounds(200,250,200,80);
        passT.setBounds(360,260,620,50);
        passT.setFont(new Font("Consolas",Font.PLAIN,30));
        passT.setBackground(new Color(241,246,249));
        //--------------------
        confirmpassL.setText("Confirm Password:");
        confirmpassL.setForeground(Color.BLACK);
        confirmpassL.setFont(new Font("Consolas",Font.PLAIN,30));
        confirmpassL.setBounds(63,320,300,80);
        confirmpassT.setBounds(360,330,620,50);
        confirmpassT.setFont(new Font("Consolas",Font.PLAIN,30));
        confirmpassT.setBackground(new Color(241,246,249));
        // ----------------------------
        editBTN.setText("Edit");
        editBTN.addActionListener(this);
        editBTN.setBounds(590,400,100,50);
        upper.add(welcome);
        upper.add(backBTN);
        f.add(fnameL);
        f.add(fnameT);
        f.add(lnameL);
        f.add(lnameT);
        f.add(emailL);
        f.add(emailT);
        f.add(passL);
        f.add(passT);
        f.add(confirmpassL);
        f.add(confirmpassT);
        f.add(editBTN);
        f.add(upper);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBTN)
        {
            f.dispose();
            logInPage l = new logInPage();
        }
        else if(e.getSource()==editBTN)
        {
            String fname = fnameT.getText();
            String lname = lnameT.getText();
            String pass = passT.getText();
            String confirmPass = confirmpassT.getText();
            String email = emailT.getText();
            if(fname.equals("") || lname.equals("") || pass.equals("") || confirmPass.equals("") || email.equals(""))
            {
                JOptionPane.showMessageDialog(null,"You Should Enter all you data","Sign Up Failed",JOptionPane.ERROR_MESSAGE);
            }
            else if(!pass.equals(confirmPass) )
            {
                JOptionPane.showMessageDialog(null,"Password Don't Match ","Sign Up Failed",JOptionPane.ERROR_MESSAGE);
            }else {
                User n = null;
                n = UserController.updateUser(newUser.getID(), fname, lname, pass, email, "User");
                if (newUser != null) {
                    JOptionPane.showMessageDialog(null, "User Edited Successfully", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    UserView u = new UserView(newUser);
                }
            }
        }
    }
}

