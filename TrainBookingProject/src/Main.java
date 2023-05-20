import Controllers.BookingController;
import Controllers.UserController;
import Models.*;
import Repositories.*;
import Views.HomePageView;
import Views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //HomePageView f = new HomePageView();
        User u = UserController.getUserById(6);
        ArrayList<Booking> n = BookingController.getAllBookingForUser(u);
        System.out.println( n.get(0).getID());
    }
}