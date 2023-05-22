package Views;

import Controllers.TrainController;
import Controllers.TripController;
import Controllers.UserController;
import Models.Train;
import Models.Trip;
import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class createDataForAdmin implements ActionListener {
    backBtn backBTN = new backBtn();
    upperPanel upper = new upperPanel();
    MainFrame f = new MainFrame();
    Connection connection ;
    User newUser;
    JLabel welcome = new JLabel();
    JTextField capacityT ;
    JTextField tripIdT;
    JTextField pricePerSeatT;
    JButton insertTrainBTN ;
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
    JButton createAdminBTN = new JButton();
    public createDataForAdmin(User user,Connection conn){
        newUser = user;
        connection =conn;
        backBTN.setText("Go Back");
        backBTN.addActionListener(this);
        welcome.setText("Welcome, "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        upper.add(backBTN);
        upper.add(welcome);
        f.add(upper);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBTN)
        {
            AdminView a = new AdminView(newUser,connection);
            f.dispose();
        }
        else if(e.getSource() == insertTrainBTN) {
            Integer cap = Integer.parseInt(capacityT.getText());
            Integer tripId = Integer.parseInt(tripIdT.getText());
            double price = Double.parseDouble(pricePerSeatT.getText());
            Trip trip = TripController.getTripByID(tripId,connection);
            Train train = TrainController.createTrain(cap,trip,price,connection);
            if (train != null){
                JOptionPane.showMessageDialog(null,"Train Created Successfully","Train Creation",JOptionPane.INFORMATION_MESSAGE);
                AdminView a =new AdminView(newUser , connection);
                f.dispose();
            }else {
                JOptionPane.showMessageDialog(null,"Train Creation Failed ","Train Creation",JOptionPane.ERROR_MESSAGE);

            }
        }else if(e.getSource() == createAdminBTN)
        {
            String fname = fnameT.getText();
            String lname = lnameT.getText();
            String pass = passT.getText();
            String confirmPass = confirmpassT.getText();
            String email = emailT.getText();
            if(fname.equals("") || lname.equals("") || pass.equals("") || confirmPass.equals("") || email.equals(""))
            {
                JOptionPane.showMessageDialog(null,"You Should Enter all you data","Creation Failed",JOptionPane.ERROR_MESSAGE);
            }
            else if(!pass.equals(confirmPass) )
            {
                JOptionPane.showMessageDialog(null,"Password Don't Match ","Creation Failed",JOptionPane.ERROR_MESSAGE);
            }else {
                User user = null;
                user = UserController.addUser(fname,lname,pass,email,"Admin",connection);
                if(user != null){
                    JOptionPane.showMessageDialog(null,"Admin Created Successfully, Your Id to login with is:"+user.getID(),"Sign Up",JOptionPane.INFORMATION_MESSAGE);
                    AdminView a =new AdminView(newUser , connection);
                    f.dispose();
                }
            }
        }
    }
    public void createTrain(){
        JLabel capacity = new JLabel("Capacity: ");
        capacity.setFont(new Font("Consolas",Font.PLAIN,30));
        capacity.setForeground(Color.BLACK);
        capacity.setBounds(400,100,200,80);
        capacityT = new JTextField();
        capacityT.setFont(new Font("Consolas",Font.PLAIN,30));
        capacityT.setBounds(600,110,150,50);
        JLabel tripidL = new JLabel("Trip ID:");
        tripidL.setFont(new Font("Consolas",Font.PLAIN,30));
        tripidL.setForeground(Color.BLACK);
        tripidL.setBounds(200,200,200,80);
        tripIdT = new JTextField();
        tripIdT.setFont(new Font("Consolas",Font.PLAIN,30));
        tripIdT.setBounds(380,210,150,50);
        JLabel priceL = new JLabel("PricePerSeat:");
        priceL.setFont(new Font("Consolas",Font.PLAIN,30));
        priceL.setForeground(Color.BLACK);
        priceL.setBounds(550,200,250,80);
        pricePerSeatT = new JTextField();
        pricePerSeatT.setFont(new Font("Consolas",Font.PLAIN,30));
        pricePerSeatT.setBounds(770,210,150,50);
        insertTrainBTN = new JButton("Insert");
        insertTrainBTN.setFocusable(false);
        insertTrainBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        insertTrainBTN.setBounds(540,450,200,50);
        insertTrainBTN.setBackground(new Color(0x212A3E));
        insertTrainBTN.addActionListener(this);
        insertTrainBTN.setForeground(Color.WHITE);
        f.add(capacity);
        f.add(capacityT);
        f.add(tripidL);
        f.add(tripIdT);
        f.add(priceL);
        f.add(pricePerSeatT);
        f.add(insertTrainBTN);
        f.setVisible(true);
    }
    public void createAdmin(){
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
        createAdminBTN.setText("Create");
        createAdminBTN.addActionListener(this);
        createAdminBTN.setBounds(590,400,100,50);
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
        f.add(createAdminBTN);
        f.setVisible(true);
    }
}
