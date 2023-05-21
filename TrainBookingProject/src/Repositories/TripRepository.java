package Repositories;

import Models.Trip;
import Models.City;
import Models.Visit;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripRepository {
//    private final Connection connection;
//
//    public TripRepository(){
//        connection = MainRepository.getConnection();
//    }

    public Trip createTrip(Trip trip,Connection connection) throws SQLException {
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

    public Trip updateTrip(Trip trip,Connection connection) throws SQLException{
        String sql = "update Trip set SourceID = ?, DestenationID = ?, DateOftrip = ?, StartTime = ?, EndTime = ?" +
                "where TripID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, trip.getSource().getCity().getID());
            statement.setInt(2, trip.getDestination().getCity().getID());
            statement.setDate(3, Date.valueOf(trip.getStartDateTime().toLocalDate()));
            statement.setTime(4, Time.valueOf(trip.getStartDateTime().toLocalTime()));
            statement.setTime(5, Time.valueOf(trip.EndTime().toLocalTime()));
            statement.setInt(6, trip.getID());
            statement.executeUpdate();
        }
        return trip;
    }

    public List<Trip> getAllTrips(Connection connection) throws SQLException{
        String sql = "Select * From Trip";
        List<Trip> trips = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Trip trip = mapTrip(resultSet,connection);
                trips.add(trip);
            }
        }
        return trips;
    }

    public Trip getTripById(int id,Connection connection) throws SQLException {
        String sql = "SELECT * FROM Trip WHERE TripID = ?";
        Trip trip = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    trip = mapTrip(resultSet,connection);
                }
            }
        }

        return trip;
    }

    // return list of trips that satisfied specific criteria
    public List<Trip> getAllTripsInSpecificCriteria(Time stratTime, java.util.Date date, String destinationCityName, String sourceCityName, int capacityOfTrain,Connection connection) throws SQLException {
        String sql = "select * from Trip Where 1 = 1";

        if (stratTime != null) {
            sql += "And StartTime = ?";
        }

        if (date != null) {
            sql += "And DateOftrip = ?";
        }

        if (capacityOfTrain != 0) {
            sql += "And TripID In (select tripID from Train where capacity = ?)";
        }

        if (sourceCityName != null) {
            sql += "And TripID In (select tripID from Visit where cityID In(select CityID from City where city_name = ?))";
        }

        if (destinationCityName != null) {
            sql += "And TripID In (select tripID from Visit where cityID In(select CityID from City where city_name = ?))";
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameter values based on the provided parameters
            int parameterIndex = 1;
            if (stratTime != null) {
                statement.setTime(parameterIndex++, stratTime);
            }

            if (date != null) {
                statement.setDate(parameterIndex++, (Date) date);
            }
            if (sourceCityName != null) {
                statement.setString(parameterIndex++, "%" + sourceCityName + "%");
            }
            if (destinationCityName != null) {
                statement.setString(parameterIndex++, "%" + destinationCityName + "%");
            }
            if (capacityOfTrain != 0) {
                statement.setInt(parameterIndex, capacityOfTrain);
            }

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                Trip trip = mapTrip(resultSet,connection);
                trips.add(trip);
            }

            return trips;
        }
    }

    public List<Trip> getAllTripsGoToCityByCityName(String cityName,Connection connection){
        String sql = "Select * From Trip where TripID In " +
                "(Select tripID from Visit where cityID in (Select CityID from City where city_name LIKE ?))";

        List<Trip> trips = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            // to get all cities like this name
            statement.setString(1, "%" + cityName + "%");

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Trip trip = mapTrip(resultSet,connection);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return trips;
    }

    // send it result of database set and extract from it Trip and return it
<<<<<<< HEAD
    private Trip mapTrip(ResultSet resultSet) throws SQLException {
=======
    private Trip mapTrip(ResultSet resultSet,Connection connection) throws SQLException {

>>>>>>> 30cfc4802cf164b637d7012d69625d2a7d9e227d
        Trip trip = null;

        int id = resultSet.getInt("TripID");
        int sourceID = resultSet.getInt("SourceID");
        int destenationID = resultSet.getInt("DestenationID");
        Date dateOftrip = resultSet.getDate("DateOftrip");
        Time startTime = resultSet.getTime("StartTime");

        LocalDateTime arrivingTime = LocalDateTime.of(dateOftrip.toLocalDate(), startTime.toLocalTime());
        trip = new Trip(id, arrivingTime);
        // to get the city object
        City sourceCity = new CityRepository().getCityByID(sourceID);
        City destenationCity = new CityRepository().getCityByID(destenationID);

        if (sourceCity != null && destenationCity != null) {

            Visit sourceVisit = new VisitRepository().getVisitByCityIDAndTripID(id , sourceCity.getID());
            Visit destinationVisit = new VisitRepository().getVisitByCityIDAndTripID(id , destenationCity.getID());

            trip.setSource(sourceVisit);
            trip.setDestination(destinationVisit);

            return trip;
        }

        trip.setVisits(new VisitRepository().getAllVisitsByTrip(trip.getID()));

        return trip;
    }
}


