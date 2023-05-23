package Repositories;
import Models.*;

import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BookingRepository {
    
    public Booking createBooking(Booking booking,Connection connection) throws SQLException {
        String sqlquery = "INSERT INTO Booking (trainID, userID,NumberOfSeats) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlquery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, booking.getTrain().getID());
            statement.setInt(2, booking.getUser().getID());
            statement.setInt(3, booking.getNumberOfSeats());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Adding Booking failed");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    booking.setID(generatedID);
                } else {
                    throw new SQLException("Adding Booking failed, no ID obtained.");
                }
            }
        }
        return booking;
    }

    public void deleteBooking(int bookID,Connection connection) throws SQLException {
        String sqlquery = "DELETE From Booking WHERE BookingID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlquery)) {
            statement.setInt(1, bookID);
            statement.executeUpdate();
        }
    }

    public Booking getBookingByID(int bookID,Connection connection) throws SQLException {
        String sqlquery = "SELECT * FROM Booking WHERE BookingID = ?";
        Booking booking = null;
        try (PreparedStatement statement = connection.prepareStatement(sqlquery)) {
            statement.setInt(1, bookID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    booking = extractBookingFromResultSet(resultSet,connection);
                }
            }
        }
        return booking;
    }

    public ArrayList<Booking> getBookingsForUser(int userId,Connection connection) throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        String query = "SELECT * FROM Booking WHERE userID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Booking booking = extractBookingFromResultSet(resultSet,connection);
            bookings.add(booking);

        }
        return bookings;
    }

    public void deleteAllBookingForTrip(Trip trip, Connection connection) throws SQLException {
        String sql = "DELETE From Booking WHERE trainID = (select TrainID from Train Where tripID = ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trip.getID());
            statement.executeUpdate();
        }
    }

    private Booking extractBookingFromResultSet(ResultSet resultSet,Connection connection) throws SQLException {
        int bookingId = resultSet.getInt("BookingID");
        int trainId = resultSet.getInt("trainID");
        int userId = resultSet.getInt("userID");
        int numberOfSeats = resultSet.getInt("NumberOfSeats");

        User user = new UserRepository().getUserById(userId,connection);
        Train train = new TrainRepository().getTrainById(trainId,connection);

        return new Booking(bookingId, user, train, numberOfSeats);
    }
}