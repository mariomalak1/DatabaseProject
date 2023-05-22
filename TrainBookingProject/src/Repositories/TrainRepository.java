package Repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Train;
import Models.Trip;

public class TrainRepository {

    public Train createTrain(Train train,Connection connection) throws SQLException {
        String sql = "INSERT INTO Train (Capacity, tripID, PricePerSeat) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    public void UpdateTrain(Train train,Connection connection) throws SQLException {
        String sql = "UPDATE Train SET capacity = ?, tripID = ? , PricePerSeat = ? where TrainID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getCapacity());
            statement.setInt(2, train.getTrip().getID());
            statement.setDouble(3, train.getPrice());
            statement.setInt(4, train.getID());
            statement.executeUpdate();
        }
    }

    public Train getTrainById(int id,Connection connection) throws SQLException {
        String sql = "SELECT * FROM Train WHERE TrainID = ?";
        Train train = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    train = mapTrain(resultSet,connection);
                }
            }
        }

        return train;
    }

    public List<Train> getAllTrainsInTrip(int tripId,Connection connection) throws SQLException {
        String sql = "SELECT * FROM Train WHERE tripID = ?";
        List<Train> trains = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tripId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trains.add(mapTrain(resultSet,connection));
                }
            }
        }

        return trains;
    }
    public List<Train> getAllTrains(Connection connection) throws SQLException{
        String sql = "SELECT * FROM Train ";
        List<Train> trains = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trains.add(mapTrain(resultSet,connection));
                }
            }
        }

        return trains;
    }
    private Train mapTrain(ResultSet resultSet,Connection connection) throws SQLException {
        Train train;
        int ID = resultSet.getInt("TrainID");
        int capacity = resultSet.getInt("capacity");
        int tripId = resultSet.getInt("tripID");
        double pricePerSeat = resultSet.getDouble("PricePerSeat");

        train = new Train(ID, capacity, pricePerSeat);

        Trip trip  = new TripRepository().getTripById(tripId,connection);

        train.setTrip(trip);


        return train;
    }
    public void deleteTrain(int trainId,Connection connection) throws SQLException{
        String sql = "DELETE From Train WHERE TrainID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trainId);
            statement.executeUpdate();
        }

    }

}
