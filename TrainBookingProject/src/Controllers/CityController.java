package Controllers;

import Models.City;
import Repositories.CityRepository;

import java.sql.SQLException;
import java.util.List;

public class CityController {
    public static City addCity(String cityName){
        try{
            CityRepository cityRepository = new CityRepository();
            return cityRepository.addCity(cityName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<City> getAllCitiesLikeName(String cityName) {
        try {
            CityRepository cityRepository = new CityRepository();
            return cityRepository.GetAllCitiesLikeName(cityName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static City getCityByID(int id) {
        try {
            return new CityRepository().getCityByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
