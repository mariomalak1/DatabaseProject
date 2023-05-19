package Repositories;


import java.sql.*;

import Models.City;
import Models.Trip;
import Models.Visit;

public class VisitRepository {
    private final Connection connection;

    public VisitRepository() {
        this.connection = MainRepository.getConnection();
    }

    public Visit AddCityToVisit(Visit visit) throws SQLException {
        String sql = "INSERT INTO Visit (tripID, cityID) " +
                "VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, visit.getTrip().getID());
            statement.setInt(2, visit.getCity().getID());

//            statement.setDate(3, Date.valueOf(String.valueOf((visit.getArrivingDateTime()))));

            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return visit;
    }

    private Visit mapVisit(ResultSet resultSet) throws SQLException {
        Visit visit = null;
        int tripID = resultSet.getInt("tripID");
        int cityId = resultSet.getInt("cityID");

        //        Date ArrivingTime = resultSet.getDate("ArrivingTime");

        Trip trip = new TripRepository().getTripById(tripID);
        City city = new CityRepository().getCityByID(cityId);

        if (city != null && trip != null){
            visit = new Visit(trip, city);
            return visit;
        }

        return null;
    }
}

