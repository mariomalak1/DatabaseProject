package Controllers;

import Models.Booking;
import Models.Train;
import Models.User;
import Repositories.BookingRepository;
import Repositories.MainRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class BookingController {

    public static Booking creatBooking(User user, Train train, int NumberOfSeats,Connection conn) {
        try{
            Booking booking = new Booking(0, user, train, NumberOfSeats);
            return new BookingRepository().createBooking(booking,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Booking> getAllBookingForUser(int userId,Connection conn) {
        try {
            return new BookingRepository().getBookingsForUser(userId,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Booking getBookingByID(int bookingId,Connection conn){
        try{
            return new BookingRepository().getBookingByID(bookingId,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean deleteBooking(int bookingId,Connection conn){
        try{
            new BookingRepository().deleteBooking(bookingId,conn);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
