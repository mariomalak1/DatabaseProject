package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainRepository {
    private final Connection connection;

    public TrainRepository() {
        this.connection = MainRepository.getConnection();
    }
    public Train createTrain(Train train) throws SQLException {
        String sql = "INSERT INTO Train (TrainID, Capacity, tripID) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getTrainId());
            statement.setInt(2, train.getCapacity());
            statement.setInt(3, train.getTripId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding train failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    train.setTrainId(generatedID);
                } else {
                    throw new SQLException("Adding train failed, no ID obtained.");
                }
            }
        }
        return train;
    }
    public void UpdateTrain(Train train) throws SQLException {
        String sql = "UPDATE Train SET TrainID = ?, Capacity = ?, TripID = ? ";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getTrainId());
            statement.setInt(2, train.getCapacity());
            statement.setInt(3, train.getTripId());
            statement.executeUpdate();

        }
    }
    private Train mapTrain(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt("TrainId");
        int capacity = resultSet.getInt("Capacity");
        int tripId = resultSet.getInt("tripID");
        Train train = new Train();
        train.setTrainId(ID);
        train.setCapacity(capacity);
        train.setTripId(tripId);
        return train;
    }


}
