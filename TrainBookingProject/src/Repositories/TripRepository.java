package Repositories;

import Models.Train;
import Models.Trip;
import Models.City;
import Models.Visit;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripRepository {
    private final Connection connection;

    TripRepository(){
        connection = MainRepository.getConnection();
    }

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

    public Trip getTripById(int id) throws SQLException {
        String sql = "SELECT * FROM Trip WHERE TripID = ?";
        Trip trip = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    trip = mapTrip(resultSet);
                }
            }
        }

        return trip;
    }

    // send it result of database set and extract from it Trip and return it
    private Trip mapTrip(ResultSet resultSet) throws SQLException {
        Trip trip = null;

        int id = resultSet.getInt("ID");
        int sourceID = resultSet.getInt("SourceID");
        int destenationID = resultSet.getInt("DestenationID");
        Date dateOftrip = resultSet.getDate("DateOftrip");
        Time startTime = resultSet.getTime("StartTime");
        Time endTime = resultSet.getTime("EndTime");

        // to get the city object
        City sourceCity = new CityRepository().getCityByID(sourceID);
        City destenationCity = new CityRepository().getCityByID(destenationID);

        Visit SourceVisit = new Visit(sourceCity);
        Visit DestinationVisit = new Visit(destenationCity);

        if (sourceCity != null && destenationCity != null) {
            Visit sourceVisit = new Visit(sourceCity);
            Visit destinationVisit = new Visit(destenationCity);

            trip = new Trip(id, destinationVisit, sourceVisit);
            destinationVisit.setTrip(trip);
            sourceVisit.setTrip(trip);

            LocalDateTime arrivingTime = LocalDateTime.of(dateOftrip.toLocalDate(), startTime.toLocalTime());
            sourceVisit.setArrivingTime(arrivingTime);

            List<Train> trainsInTrip = new TrainRepository().getAllTrainsInTrip(trip.getID());
            trip.setTrains(trainsInTrip);

            List<Visit> VisitsInTrain = new VisitRepository().getAllVisitsByTrip(trip.getID());
            trip.setVisits(VisitsInTrain);

            return trip;
        }


        return null;
    }
}
