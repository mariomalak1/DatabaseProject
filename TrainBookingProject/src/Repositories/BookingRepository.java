package Repositories;

import Models.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRepository {
    private final Connection connection;

    public BookingRepository() {
        this.connection = MainRepository.getConnection();
    }

    // this function will receive booking model and get data from it

    public Booking createBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO Booking (seatID, userID, tripID) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getSeat().getID());
            statement.setInt(2, booking.getUser().getID());
            statement.setInt(3, booking.getTrip().getID());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding Booking failed, no rows affected.");
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

    private Booking mapBooking(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("BookingID");
        int seatId = resultSet.getInt("seatID");
        int userId = resultSet.getInt("userID");
        int tripId = resultSet.getInt("tripID");

        //        user = UserRepo.getUserByID(userId);
        //        trip = tripRepo.getTripByID(userId);
        //        seat = tripRepo.getSeatByID(seatId);
        //        Booking booking = new Booking(id,user, tripId, seatId);
        //        return booking;

        return null;
    }

}