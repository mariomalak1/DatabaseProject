package Controllers;

import Models.Booking;
import Models.Train;
import Models.User;
import Repositories.BookingRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingController {
    public static Booking creatBooking(User user, Train train, int NumberOfSeats) {
        try{
            Booking booking = new Booking(0, user, train, NumberOfSeats);
            return new BookingRepository().createBooking(booking);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Booking> getAllBookingForUser(User user) {
        try {
            return new BookingRepository().getBookingsForUser(user.getID());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Booking getBookingByID(int bookingId){
        try{
            return new BookingRepository().getBookingByID(bookingId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean deleteBooking(int bookingId){
        try{
            new BookingRepository().deleteBooking(bookingId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
