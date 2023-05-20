package Repositories;
import Models.Booking;
import Models.Train;
import Models.Trip;
import Models.User;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BookingRepository {
    private final Connection connection;

    public BookingRepository() {
        connection = MainRepository.getConnection();
    }

    public Booking createBooking(Booking booking) throws SQLException {
        String sqlquery = "INSERT INTO Booking (trainID, userID) " +
                "VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlquery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, booking.getTrain().getID());
            statement.setInt(2, booking.getUser().getID());

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

    public void deleteBooking(int bookID) throws SQLException {
        String sqlquery = "DELETE From Booking WHERE BookingID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlquery)) {
            statement.setInt(1, bookID);
            statement.executeUpdate();
        }
    }

    public Booking getBookingByID(int bookID) throws SQLException {
        String sqlquery = "SELECT * FROM Booking WHERE BookingID = ?";
        Booking booking = null;
        try (PreparedStatement statement = connection.prepareStatement(sqlquery)) {
            statement.setInt(1, bookID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    booking = extractBookingFromResultSet(resultSet);
                }
            }
        }
        return booking;
    }

    public List<Booking> getBookingsForUser(int userId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        String query = "SELECT * FROM Booking WHERE userID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Booking booking = extractBookingFromResultSet(resultSet);
            bookings.add(booking);
        }
        return bookings;
    }

    private Booking extractBookingFromResultSet(ResultSet resultSet) throws SQLException {
        int bookingId = resultSet.getInt("BookingID");
        int trainId = resultSet.getInt("trainID");
        int userId = resultSet.getInt("userID");
        int numberOfSeats = resultSet.getInt("NumberOfSeats");


        User user = new UserRepository().getUserById(userId);
        Train train = new TrainRepository().getTrainById(trainId);

        return new Booking(bookingId, user, train, numberOfSeats);
    }
}


