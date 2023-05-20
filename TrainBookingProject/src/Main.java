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
        User user = UserController.getUserById(2);
        BookingRepository bookrepo = new BookingRepository();
        try {
            System.out.println(bookrepo.getBookingByID(6));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        ArrayList<Booking> bookings;
//        try {
//            bookings = bookrepo.getBookingsForUser(2);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        if (bookings != null) {
//            for (Booking booking : bookings) {
//                System.out.println(booking.getUser().getID());
//                System.out.println(booking.getTrip().getID());
//                System.out.println(booking.getTrain().getID());
//                System.out.println(booking.getNumberOfSeats());
//            }
//        }else{
//            System.out.println("no thing");
//        }
        //HomePageView f = new HomePageView();

    }
}