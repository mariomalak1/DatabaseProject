package Repositories;

import Models.City;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRepository {
    private final Connection connection;

    public CityRepository() {
        connection = MainRepository.getConnection();
    }

    public City getCityByID(int id) throws SQLException {
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

    public City addCity(City city) throws SQLException {
        String sql = "Insert Into City (city_name) values(?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, city.getName());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding City failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    city.setID(generatedID);
                    return city;
                } else {
                    throw new SQLException("Adding City failed, no ID obtained.");
                }
            }
        }
    }

    private City mapCity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        return new City(id, name);
    }
}