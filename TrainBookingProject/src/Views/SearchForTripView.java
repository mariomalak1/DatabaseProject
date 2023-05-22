package Views;

import Controllers.CityController;
import Controllers.TripController;
import Models.City;
import Models.User;
import Models.Trip;
import Repositories.CityRepository;
//import com.raven.swing.TimePicker;
//import com.raven.swing.TimePickerMenu;
import com.toedter.calendar.JDateChooser;
import java.sql.Time;
import java.sql.Date;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchForTripView implements ActionListener {
    backBtn backBTN = new backBtn();

    upperPanel upper = new upperPanel();
    JLabel welcome = new JLabel();
    MainFrame f = new MainFrame();
    User newUser = null;
    Connection connection;
    JComboBox minC;
    JDateChooser cal;
    JButton searchBTN;
    JTextField cap ;
    JComboBox hoursC;
    JComboBox listOfTripsS;
    JComboBox listOfTripsD;
    JButton createBookingBTN ;
    public SearchForTripView(User user,Connection conn,List<Trip> l ){
        connection = conn;
        newUser = user ;
        backBTN.setText("Go Back");
        backBTN.addActionListener(this);
        welcome.setText("Welcome, "+user.getFullName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Consolas",Font.PLAIN,30));
        welcome.setBounds(450,5,400,50);
        JLabel label = new JLabel("Search For Trips.");
        label.setFont(new Font("Consolas",Font.PLAIN,30));
        label.setForeground(Color.BLACK);
        label.setBounds(500,50,300,80);
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
        listOfTripsS.insertItemAt(" ",0);
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
        listOfTripsD.insertItemAt(" ",0);
        listOfTripsD.setSelectedIndex(0);
        JLabel labelD = new JLabel("DestinationCity:");
        labelD.setFont(new Font("Consolas",Font.PLAIN,20));
        labelD.setForeground(Color.BLACK);
        labelD.setBounds(650,100,200,80);

        f.add(listOfTripsD);
        f.add(labelS);
//        loadAllCities("SourceCity:",400,110,260,100);
//        loadAllCities("Destination:",800,110,650,100);
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
        hoursC.insertItemAt(" ",0);
        hoursC.setBounds(800,200,50,30);
        hoursC.setSelectedIndex(0);
        String[] min = new String[59];
        for(int i = 1 ; i <= 59;i++)
        {
            min[i-1] = String.valueOf(i);
        }
        minC = new JComboBox(min);
        minC.insertItemAt(" ",0);
        minC.setBounds(860,200,50,30);
        minC.setSelectedIndex(0);
        JLabel timeL = new JLabel("Time (h-m):");
        timeL.setFont(new Font("Consolas",Font.PLAIN,20));
        timeL.setForeground(Color.BLACK);
        timeL.setBounds(660,177,300,80);
        searchBTN = new JButton("search");
        searchBTN.setFont(new Font("Consolas",Font.PLAIN,20));
        searchBTN.setForeground(Color.WHITE);
        searchBTN.addActionListener(this);
        searchBTN.setBounds(800,250,200,50);
        searchBTN.setBackground(new Color(0x212A3E));
        JLabel divider = new JLabel("--------------------------------------------------------------------------------------------------------------");
        divider.setBounds(100,310,1080,10);
        divider.setFont(new Font("Consolas",Font.PLAIN,20));
        divider.setForeground(Color.BLACK);
        cap = new JTextField();
        cap.setBounds(400,260,150,30);
        cap.setFont(new Font("Consolas",Font.PLAIN,20));
        JLabel capL = new JLabel("No. of seats: ");
        capL.setFont(new Font("Consolas",Font.PLAIN,20));
        capL.setForeground(Color.BLACK);
        capL.setBounds(250,240,300,80);
        loadAllTrips(l);
        createBookingBTN = new JButton("CreateBooking");
        createBookingBTN.setFocusable(false);
        createBookingBTN.setFont(new Font("Consolas",Font.PLAIN,30));
        createBookingBTN.setBounds(900,600,300,50);
        createBookingBTN.setBackground(new Color(0x212A3E));
        createBookingBTN.addActionListener(this);
        createBookingBTN.setForeground(Color.WHITE);
        upper.add(welcome);
        upper.add(backBTN);
        f.add(upper);
        f.add(label);
        f.add(cal);
        f.add(calenderL);
        f.add(hoursC);
        f.add(minC);
        f.add(timeL);
        f.add(searchBTN);
        f.add(divider);
        f.add(cap);
        f.add(capL);
        f.add(createBookingBTN);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBTN)
        {
            UserView u = new UserView(newUser,connection);
            f.dispose();
        }
        else if(e.getSource() == searchBTN)
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
            int capacity = 0;
            if(cal.getDate() != null)
            {
                date =cal.getDate();
                local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                sqlDate = Date.valueOf(local);
            }


            if(hours != " " && mins != " ")
            {
                time = Time.valueOf(hours+":"+mins+":0");

            }
            //System.out.println(hours + " "+ mins);
//            if(sdate != " ")
//            {
//                date = Date.valueOf(sdate);
//
//            }
            if(!cap.getText().isEmpty())
            {
                capacity = Integer.parseInt(cap.getText());

            }

            City destinationCity = CityController.getCityLikeName(dname, connection);
            City sourceCity = CityController.getCityLikeName(sname, connection);
            List<Trip> n = TripController.getAllTripsForSpecificCriteria(time, sqlDate, destinationCity, sourceCity, capacity, connection);
            SearchForTripView newS = new SearchForTripView(newUser,connection,n);
            f.dispose();

        }
        else if(e.getSource() == createBookingBTN)
        {
            createBookingForUser createB = new createBookingForUser(newUser, connection);
            f.dispose();
        }

    }
    //private void loadAllSources()

//    private void loadAllCities(String t,int x,int y,int xl,int yl){
//        List<String> l = new ArrayList<>();
//        CityRepository trepo = new CityRepository();
//        l = trepo.getAllCities();
//        String[] loaded = new String[l.size()];
//
//        for( int i = 0 ; i < l.size() ; i++)
//        {
//            loaded[i]=(l.get(i));
//        }
//        listOfTrips = new JComboBox(loaded);
//        listOfTrips.setBounds(x,y,150,50);
//        listOfTrips.insertItemAt("",0);
//        listOfTrips.setSelectedIndex(0);
//        JLabel label = new JLabel(t);
//        label.setFont(new Font("Consolas",Font.PLAIN,20));
//        label.setForeground(Color.BLACK);
//        label.setBounds(xl,yl,200,80);
//
//        f.add(listOfTrips);
//        f.add(label);
//    }
    Border blackline = BorderFactory.createLineBorder(Color.black,2);
    private void loadAllTrips(List<Trip> l){
        if(l == null)
        {
            l = TripController.getAllTrips(connection);
        }
        if(l.isEmpty()){
            JLabel label = new JLabel();
            label.setText("No Result Found");
            label.setBorder(blackline);
            label.setFont(new Font("Consolas",Font.PLAIN,25));
            label.setBounds(100,320,900,50);
            f.add(label);
        }else {

            int y = 320;
            for (int i = 0 ; i < l.size() ; i++)
            {
                JLabel label = new JLabel();
                label.setText("TripID: "+l.get(i).getID()+" -- SourceCity: "+l.get(i).getSource().getCity().getName()+
                        " -- DestinationCity: "+l.get(i).getDestination().getCity().getName()+
                        " -- Date: ");
                label.setBorder(blackline);
                label.setFont(new Font("Consolas",Font.PLAIN,15));
                label.setBounds(100,y,900,50);
                f.add(label);
                y+=60;
            }
        }

    }

}
