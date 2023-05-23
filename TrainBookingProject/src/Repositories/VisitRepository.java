package Repositories;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Models.City;
import Models.Trip;
import Models.Visit;

public class VisitRepository {
    public Visit AddCityToVisit(Visit visit, Connection connection) throws SQLException {
        String sql = "INSERT INTO Visit (tripID, cityID, visit_Time, visit_Date) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, visit.getTrip().getID());
            statement.setInt(2, visit.getCity().getID());
            statement.setTime(3,Time.valueOf(visit.getArrivingDateTime().toLocalTime()));
            //statement.setTime(4, Time.valueOf(String.valueOf((visit.getArrivingDateTime().toLocalTime()))));
            statement.setDate(4,Date.valueOf(visit.getArrivingDateTime().toLocalDate()));
            //statement.setDate(4, Date.valueOf(String.valueOf((visit.getArrivingDateTime().toLocalDate()))));
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return visit;
    }

    public List<Visit> getAllVisitsByTrip(int tripId, Connection connection) throws SQLException {
        String sql = "SELECT * FROM Visit WHERE tripID = ?";
        List<Visit> visits = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tripId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    visits.add(mapVisit(resultSet, connection));
                }
            }
        }

        return visits;
    }

    public List<Visit> getAllVisitsByCityName(String cityName, Connection connection) throws SQLException{
        String sql = "SELECT * FROM Visit" +
                "JOIN City ON Visit.cityID = City.CityID " +
                "WHERE City.city_name LIKE ?";

        List<Visit> visits = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + cityName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    visits.add(mapVisit(resultSet, connection));
                }
            }
        }

        return visits;
    }
    public Visit getVisitByCityIDAndTripID(int tripId, int cityId, Connection connection){
        String sql = "SELECT * FROM Visit WHERE tripID = ? And cityID = ?";
        Visit visit = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tripId);
            statement.setInt(2, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    visit = mapVisit(resultSet, connection);
                }
                return visit;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllVisitsForTrip(Trip trip, Connection connection) throws SQLException {
        String sql = "DELETE From Visit WHERE tripID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trip.getID());
            statement.executeUpdate();
        }
    }

    private Visit mapVisit(ResultSet resultSet, Connection connection) throws SQLException {
        Visit visit = null;
        int tripID = resultSet.getInt("tripID");
        int cityId = resultSet.getInt("cityID");
        Date date = resultSet.getDate("visit_Date");
        Time time = resultSet.getTime("visit_Time");

        //        Date ArrivingTime = resultSet.getDate("ArrivingTime");

        City city = new CityRepository().getCityByID(cityId, connection);

        if (city != null){
            LocalDateTime arrivingTime = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
            visit = new Visit(city, arrivingTime);
            return visit;
        }
        return null;
    }
}