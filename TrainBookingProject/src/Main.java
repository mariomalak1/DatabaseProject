import Controllers.BookingController;
import Controllers.TrainController;
import Controllers.TripController;
import Controllers.UserController;
import Models.*;
import Repositories.*;
import Views.HomePageView;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trip trip = TripController.getTripByID(1);
        if (trip != null) {
            System.out.println("-------------------------------------");
            System.out.println(trip.getID());
            System.out.println(trip.getTrains().size());
            System.out.println(trip.getCities().size());
            System.out.println(trip.getStartDateTime());
            System.out.println(trip.getSource().getCity().getName());
            System.out.println(trip.getDestination().getCity().getName());
            System.out.println(trip.EndTime());
            System.out.println("-------------------------------------");
            //        HomePageView f = new HomePageView();
        }
        else {
            System.out.println("null");
        }
    }
}