package Views;

import Controllers.TripController;
import Models.User;
import Models.Trip;
import Repositories.CityRepository;
import Repositories.TripRepository;
import cambodia.raven.Time;
import com.raven.swing.TimePicker;
import com.raven.swing.TimePickerMenu;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

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
    List<Trip> l;
    JComboBox minC;
    JDateChooser cal;
    JButton searchBTN;
    JTextField cap ;
    JComboBox hoursC;
    JComboBox listOfTrips;
    public SearchForTripView(User user,Connection conn){
        connection = conn;
        l = TripController.getAllTrips(connection);
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
        loadAllCities("SourceCity:",400,110,260,100);
        loadAllCities("Destination:",800,110,650,100);
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
        hoursC.insertItemAt("",0);
        hoursC.setBounds(800,200,50,30);
        hoursC.setSelectedIndex(0);
        String[] min = new String[59];
        for(int i = 1 ; i <= 59;i++)
        {
            min[i-1] = String.valueOf(i);
        }
        minC = new JComboBox(min);
        minC.insertItemAt("",0);
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
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBTN)
        {
            logInPage l = new logInPage();
            f.dispose();
        }
        else if(e.getSource() == searchBTN)
        {
            String sname = listOfTrips.getSelectedItem().toString();
            String dname = listOfTrips.getSelectedItem().toString();
            String hours = hoursC.getSelectedItem().toString();
            String mins = minC.getSelectedItem().toString();
            LocalDateTime time = LocalDateTime.parse(hours+mins);
            LocalDate date = LocalDate.parse(cal.getDate().toString());
            int capacity = Integer.parseInt(cap.getText());
            l = TripController.getAllTripsForSpecificCriteria(time.toLocalTime(),date,dname,sname,capacity,connection);
        }

    }
    //private void loadAllSources()

    private void loadAllCities(String t,int x,int y,int xl,int yl){
        List<String> l = new ArrayList<>();
        CityRepository trepo = new CityRepository();
        l = trepo.getAllCities();
        String[] loaded = new String[l.size()];

        for( int i = 0 ; i < l.size() ; i++)
        {
            loaded[i]=(l.get(i));
        }
        listOfTrips = new JComboBox(loaded);
        listOfTrips.setBounds(x,y,150,50);
        listOfTrips.insertItemAt("",0);
        listOfTrips.setSelectedIndex(0);
        JLabel label = new JLabel(t);
        label.setFont(new Font("Consolas",Font.PLAIN,20));
        label.setForeground(Color.BLACK);
        label.setBounds(xl,yl,200,80);

        f.add(listOfTrips);
        f.add(label);
    }
    Border blackline = BorderFactory.createLineBorder(Color.black,2);
    private void loadAllTrips(List<Trip> l){
        int y = 320;
        for (int i = 0 ; i < l.size() ; i++)
        {

            JLabel label = new JLabel();
            label.setText("TripID: "+l.get(i).getID()+" -- SourceCity: "+l.get(1).getSource().getCity().getName()+
                    " -- DestinationCity: "+l.get(1).getDestination().getCity().getName()+
                    " -- Date: ");
            label.setBorder(blackline);
            label.setFont(new Font("Consolas",Font.PLAIN,15));
            label.setBounds(100,y,900,50);
            f.add(label);
            y+=60;
        }
    }

}
