package Repositories;

import Models.City;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    public City getCityByID(int id, Connection connection) throws SQLException {
        String sql = "Select * from City where CityID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCity(resultSet);
                }
            }
        }
        return null;
    }

    public City addCity(String cityName, Connection connection) throws SQLException {
        String sql = "Insert Into City (city_name) values(?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cityName);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding City failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    return new City(generatedID, cityName);
                } else {
                    throw new SQLException("Adding City failed, no ID obtained.");
                }
            }
        }
    }

    public City getFirstCityLikeName(String cityName, Connection connection) throws SQLException{
        City city = null;
        String sql = "Select * from City where city_name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,  cityName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = mapCity(resultSet);
                }
                return city;
            }
        }
    }

    public List<City> GetAllCitiesLikeName(String cityName, Connection connection) throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "Select * from City where city_name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + cityName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(mapCity(resultSet));
                }
                return cities;
            }
        }
    }

    private City mapCity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("CityID");
        String name = resultSet.getString("city_name");
        return new City(id, name);
    }
    public List<String> getAllCities(Connection connection){
        List<String> cities = new ArrayList<>();
        String sql = "Select city_name from City";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(resultSet.getString("city_name"));
                }
                return cities;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}