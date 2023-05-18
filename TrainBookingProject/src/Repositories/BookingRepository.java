package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final Connection connection;

    public BookingRepository() {
        this.connection = MainRepository.getConnection();
    }

    // this function will receive booking model and get data from it

    public void createBooking() throws SQLException {
        String sql = "INSERT INTO Booking (seatID, userID, tripID) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, candy.getName());
        }
    }
}