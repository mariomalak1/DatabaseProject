package Views;

import Controllers.*;
import Models.City;
import Models.Train;
import Models.Trip;
import Models.User;
import Repositories.CityRepository;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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
    JComboBox listOfTripsS;
    JComboBox listOfTripsD;
    JComboBox minC;
    JDateChooser cal;
    JComboBox hoursC;
    JButton createTripBTN;
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
        }else if(e.getSource() == createTripBTN)
        {
            String sname = null;
            String dname = null;
            if(listOfTripsS.getSelectedItem().toString() != " " && listOfTripsS.getSelectedItem().toString() != null)
            {

                sname = listOfTripsS.getSelectedItem().toString();

            }
            if(listOfTripsD.getSelectedItem().toString() != " "&& listOfTripsD.getSelectedItem().toString() != null)
            {

                dname = listOfTripsD.getSelectedItem().toString();

            }
            String hours = hoursC.getSelectedItem().toString();
            String mins = minC.getSelectedItem().toString();
            String sdate = " ";
            Time time = null;
            java.util.Date date = null;
            LocalDate local;
            Date sqlDate = null;
            LocalDateTime stime = null;
            if(cal.getDate() != null)
            {
                //date = cal.getDate();
                date =cal.getDate();
                local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                sqlDate = Date.valueOf(local);
                if(!hours.equals(" ") && !mins.equals(" "))
                {
                    time = Time.valueOf(hours+":"+mins+":0");
                    stime = LocalDateTime.of(local,time.toLocalTime());

                }

                City city = CityController.getCityLikeName(dname,connection);
                City city2 = CityController.getCityLikeName(sname,connection);
                Trip n = null;
                try {
                    n = TripController.createTrip(sname,dname,time,sqlDate,connection);
                    VisitController.createVisit(stime,city,n,connection);
                    VisitController.createVisit(stime,city2,n,connection);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(n != null)
                {
                    JOptionPane.showMessageDialog(null,"Trip Created Successfully", "Creating Trip",JOptionPane.INFORMATION_MESSAGE);
                    AdminView a = new AdminView(newUser,connection);
                    f.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Trip Creation Failed", "Creating Trip",JOptionPane.ERROR_MESSAGE);

                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Your Have To enter a start Date","Create Trip",JOptionPane.ERROR_MESSAGE);
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
    public void createTrip(){
        List<String> nS;
        CityRepository trepo = new CityRepository();
        nS = trepo.getAllCities(connection);
        String[] loadedS = new String[nS.size()];
        for( int i = 0 ; i < nS.size() ; i++)
        {
            loadedS[i]=(nS.get(i));
        }
        listOfTripsS = new JComboBox(loadedS);
        listOfTripsS.setBounds(400,110,150,50);
        listOfTripsS.setSelectedIndex(0);
        JLabel labelS = new JLabel("SourceCity:");
        labelS.setFont(new Font("Consolas",Font.PLAIN,20));
        labelS.setForeground(Color.BLACK);
        labelS.setBounds(260,100,200,80);

        f.add(listOfTripsS);
        f.add(labelS);
        //---------------------------
        listOfTripsD = new JComboBox(loadedS);
        listOfTripsD.setBounds(800,110,150,50);
        listOfTripsD.setSelectedIndex(0);
        JLabel labelD = new JLabel("DestinationCity:");
        labelD.setFont(new Font("Consolas",Font.PLAIN,20));
        labelD.setForeground(Color.BLACK);
        labelD.setBounds(620,100,200,80);
        f.add(labelD);
        f.add(listOfTripsD);
        cal =new JDateChooser();
        cal.setForeground(Color.BLACK);
        cal.setBounds(400,200,150,30);
        JLabel calenderL = new JLabel("Date:");
        calenderL.setFont(new Font("Consolas",Font.PLAIN,20));
        calenderL.setForeground(Color.BLACK);
        calenderL.setBounds(325,177,300,80);
        String[] hours = new String[24];
        for(int i = 1 ; i <= 24;i++)
        {
            hours[i-1] = String.valueOf(i);
        }
        hoursC = new JComboBox(hours);
        hoursC.setBounds(800,200,50,30);
        hoursC.setSelectedIndex(0);
        String[] min = new String[59];
        for(int i = 1 ; i <= 59;i++)
        {
            min[i-1] = String.valueOf(i);
        }
        minC = new JComboBox(min);
        minC.setBounds(860,200,50,30);
        minC.setSelectedIndex(0);
        JLabel timeL = new JLabel("StartTime (h-m):");
        timeL.setFont(new Font("Consolas",Font.PLAIN,20));
        timeL.setForeground(Color.BLACK);
        timeL.setBounds(620,177,300,80);
        createTripBTN = new JButton("Create");
        createTripBTN.setFont(new Font("Consolas",Font.PLAIN,20));
        createTripBTN.setForeground(Color.WHITE);
        createTripBTN.addActionListener(this);
        createTripBTN.setBounds(530,350,200,50);
        createTripBTN.setBackground(new Color(0x212A3E));
        f.add(cal);
        f.add(calenderL);
        f.add(hoursC);
        f.add(minC);
        f.add(timeL);
        f.add(createTripBTN);
        f.setVisible(true);
    }
}
