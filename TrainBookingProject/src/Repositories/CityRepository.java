package Repositories;

import Models.City;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRepository {
    private final Connection connection;

    CityRepository() {
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


    private City mapCity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        return new City(id, name);
    }
}