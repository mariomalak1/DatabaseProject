import Models.*;
import Repositories.BookingRepository;

import java.awt.print.Book;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Trip trip = new Trip();
        Seat seat = new Seat();
        Booking book = new Booking(user,trip,seat);
        BookingRepository a = new BookingRepository();
        try {
            a.createBooking(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}