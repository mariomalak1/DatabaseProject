package Repositories;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Models.City;
import Models.Trip;
import Models.Visit;

public class VisitRepository {
    private final Connection connection;

    public VisitRepository() {
        this.connection = MainRepository.getConnection();
    }

    public Visit AddCityToVisit(Visit visit) throws SQLException {
        String sql = "INSERT INTO Visit (tripID, cityID, visit_Time, visit_Date) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, visit.getTrip().getID());
            statement.setInt(2, visit.getCity().getID());
            statement.setDate(3, Date.valueOf(String.valueOf((visit.getArrivingDateTime().toLocalDate()))));
            statement.setTime(4, Time.valueOf(String.valueOf((visit.getArrivingDateTime().toLocalTime()))));
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return visit;
    }

    public List<Visit> getAllVisitsByTrip(int tripId) throws SQLException {
        String sql = "SELECT * FROM Visit WHERE tripID = ?";
        List<Visit> visits = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tripId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    visits.add(mapVisit(resultSet));
                }
            }
        }

        return visits;
    }

    public List<Visit> getAllVisitsByCityName(String cityName) throws SQLException{
        String sql = "SELECT * From Visit Where cityID in" +
                " (select CityID from City where city_name Like ?)";

        List<Visit> visits = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + cityName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    visits.add(mapVisit(resultSet));
                }
            }
        }

        return visits;
    }

    private Visit mapVisit(ResultSet resultSet) throws SQLException {
        Visit visit = null;
        int tripID = resultSet.getInt("tripID");
        int cityId = resultSet.getInt("cityID");
        Date date = resultSet.getDate("visit_Date");
        Time time = resultSet.getTime("visit_Time");

        //        Date ArrivingTime = resultSet.getDate("ArrivingTime");

        Trip trip = new TripRepository().getTripById(tripID,connection);
        City city = new CityRepository().getCityByID(cityId);

        if (city != null && trip != null){
            LocalDateTime arrivingTime = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
            visit = new Visit(trip, city, arrivingTime);
            visit.setTrip(trip);
            return visit;
        }
        return null;
    }
}

