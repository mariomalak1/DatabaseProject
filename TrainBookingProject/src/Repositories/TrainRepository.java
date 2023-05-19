package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Train;
import Models.Trip;

public class TrainRepository {
    private final Connection connection;

    public TrainRepository() {
        this.connection = MainRepository.getConnection();
    }

    public Train createTrain(Train train) throws SQLException {
        String sql = "INSERT INTO Train (Capacity, tripID, PricePerSeat) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getCapacity());
            statement.setInt(2, train.getTrip().getID());
            statement.setDouble(3, train.getPrice());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding train failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    train.setID(generatedID);
                    return train;
                } else {
                    throw new SQLException("Adding train failed, no ID obtained.");
                }
            }
        }
    }

    public void UpdateTrain(Train train) throws SQLException {
        String sql = "UPDATE Train SET capacity = ?, tripID = ? , PricePerSeat = ? where TrainID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getCapacity());
            statement.setInt(2, train.getTrip().getID());
            statement.setDouble(3, train.getPrice());
            statement.setInt(4, train.getID());
            statement.executeUpdate();
        }
    }

    public Train getTrainById(int id) throws SQLException {
        String sql = "SELECT * FROM Train WHERE TrainID = ?";
        Train train = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    train = mapTrain(resultSet);
                }
            }
        }

        return train;
    }

    public List<Train> getAllTrainsInTrip(int tripId) throws SQLException {
        String sql = "SELECT * FROM Train WHERE tripID = ?";
        List<Train> trains = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tripId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trains.add(mapTrain(resultSet));
                }
            }
        }

        return trains;
    }



    private Train mapTrain(ResultSet resultSet) throws SQLException {
        Train train = null;
        int ID = resultSet.getInt("TrainID");
        int capacity = resultSet.getInt("capacity");
        int tripId = resultSet.getInt("tripID");
        double pricePerSeat = resultSet.getDouble("PricePerSeat");

        Trip trip = new TripRepository().getTripById(tripId);
        train = new Train(ID, capacity, trip, pricePerSeat);
        return train;
    }


}
