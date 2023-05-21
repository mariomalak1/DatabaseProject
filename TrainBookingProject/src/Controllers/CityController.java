package Controllers;

import Models.City;
import Repositories.CityRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CityController {
    public static City addCity(String cityName, Connection connection){
        try{
            CityRepository cityRepository = new CityRepository();
            return cityRepository.addCity(cityName, connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<City> getAllCitiesLikeName(String cityName, Connection connection) {
        try {
            CityRepository cityRepository = new CityRepository();
            return cityRepository.GetAllCitiesLikeName(cityName, connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static City getCityByID(int id, Connection connection) {
        try {
            return new CityRepository().getCityByID(id, connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
