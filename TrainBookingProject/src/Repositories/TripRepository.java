package Repositories;

import Models.Trip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripRepository {
    private final Connection connection;

    TripRepository(){
        connection = MainRepository.getConnection();
    }

    // create new trip in database based on data in trip model
    public Trip createTrip(Trip trip) throws SQLException {
        String sql = "INSERT INTO Trip (SourceID, DestenationID, DateOftrip, StartTime) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trip.getSource().getCity().getID());
            statement.setInt(2, trip.getDestination().getCity().getID());
            statement.setDate(3, Date.valueOf(trip.getStartDateTime().toLocalDate()));
            statement.setTime(4, Time.valueOf(trip.getStartDateTime().toLocalTime()));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding Trip failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    trip.setID(generatedID);
                } else {
                    throw new SQLException("Adding Trip failed, no ID obtained.");
                }
            }
        }
        return trip;
    }

    // update data in trip that in trip model
    public Trip updateTrip(Trip trip) throws SQLException{
        String sql = "update Trip set SourceID = ?, DestenationID = ?, DateOftrip = ?, StartTime = ?, EndTime = ?" +
                "where TripID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, trip.getSource().getCity().getID());
            statement.setInt(2, trip.getDestination().getCity().getID());
            statement.setDate(3, Date.valueOf(trip.getStartDateTime().toLocalDate()));
            statement.setTime(4, Time.valueOf(trip.getStartDateTime().toLocalTime()));
            statement.setTime(5, Time.valueOf(trip.EndTime()));
            statement.setInt(6, trip.getID());
            statement.executeUpdate();
        }
        return trip;
    }

    public List<Trip> getAllTrips() throws SQLException{
        String sql = "Select * From Trip";
        List<Trip> trips = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Trip trip = mapTrip(resultSet);
                trips.add(trip);
            }
        }
        return trips;
    }



    private Trip mapTrip(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        // get destination city
        // get source city
        // LocalDateTime
        // all Trains that With This Trip'
        // all cities that this trip will visit it
//        return trip;
        return null;
    }

}
