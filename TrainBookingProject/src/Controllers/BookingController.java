package Controllers;

import Models.Booking;
import Models.Train;
import Models.User;
import Repositories.BookingRepository;

import java.sql.SQLException;
import java.util.List;

public class BookingController {
    public static Booking creatBooking(User user, Train train, int NumberOfSeats) throws SQLException {
        try{
            Booking booking = new Booking(0, user, train, NumberOfSeats);
            return new BookingRepository().createBooking(booking);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Booking> getAllBookingForUser(User user) throws SQLException{
        try {
            return new BookingRepository().getBookingsForUser(user.getID());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
