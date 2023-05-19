package Controllers;

import Models.City;
import Repositories.CityRepository;

import java.sql.SQLException;
import java.util.List;

public class CityController {
    public static City addCity(String cityName) throws SQLException {
        CityRepository cityRepository = new CityRepository();
        return cityRepository.addCity(cityName);
    }

    public static List<City> getAllCitiesLikeName(String cityName) throws SQLException {
        CityRepository cityRepository =new CityRepository();
        return cityRepository.GetAllCitiesLikeName(cityName);
    }

    public static City getCityByID(int id) throws SQLException {
        return new CityRepository().getCityByID(id);
    }
}
