package Views;

import Controllers.UserController;
import com.sun.tools.javac.Main;
import Models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signUpPage extends MainFrame implements ActionListener {
    JLabel fnameL = new JLabel();
    JLabel lnameL = new JLabel();
    JLabel emailL = new JLabel();
    JLabel passL = new JLabel();
    JButton backBTN = new JButton();
    JPanel upper = new JPanel();
    JLabel confirmpassL = new JLabel();
    JTextField fnameT = new JTextField();
    JTextField lnameT = new JTextField();
    JTextField emailT = new JTextField();
    JTextField passT = new JTextField();
    JTextField confirmpassT = new JTextField();
    JButton signUpBTN = new JButton();
    MainFrame f = new MainFrame();
    ImageIcon backIcon = new ImageIcon("src\\back.png");
    signUpPage(){
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
        signUpBTN.setText("Sing Up");
        signUpBTN.addActionListener(this);
        signUpBTN.setBounds(590,400,100,50);
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
        f.add(signUpBTN);
        f.add(upper);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpBTN)
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
                User user = null;
                user = UserController.addUser(fname,lname,pass,email,"User");
                if(user != null){
                    JOptionPane.showMessageDialog(null,"User Created Successfully, Your Id to login with is:"+user.getID(),"Sign Up",JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    HomePageView newHome = new HomePageView();
                }
            }
        }
        else if(e.getSource() == backBTN)
        {
            f.dispose();
            HomePageView h = new HomePageView();
        }
    }
}
