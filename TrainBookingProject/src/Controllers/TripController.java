package Controllers;

import Models.City;
import Models.Trip;
import Models.Visit;
import Repositories.CityRepository;
import Repositories.TripRepository;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripController {
    public static List<Trip> getAllTrips() throws SQLException {
        List<Trip>trips = new ArrayList<>();
        TripRepository tripRepository = new TripRepository();
        trips = tripRepository.getAllTrips();
        return trips;
    }

    public static List<Trip> getAllTripsForSpecificCriteria(Time stratTime, java.util.Date date, String destinationCityName, String sourceCityName, int capacityOfTrain) throws SQLException {
        List<Trip> trips = new ArrayList<>();
        TripRepository tripRepository = new TripRepository();
        trips = tripRepository.getAllTripsInSpecificCriteria(
                stratTime, date, destinationCityName, sourceCityName, capacityOfTrain);
        return trips;
    }

    public static Trip createTrip(String Source, String Destination, Time StartTime, Date StartDate) throws SQLException {
        Trip trip = null;
        CityRepository cityRepository = new CityRepository();

        City sourceCity = cityRepository.getCityByName(Source);
        if (sourceCity == null){
            cityRepository.addCity(Source);
        }

        City destinationCity = cityRepository.getCityByName(Destination);
        if (destinationCity == null){
            cityRepository.addCity(Destination);
        }

        Visit visitDestination = new Visit(destinationCity);
        Visit visitSource = new Visit(sourceCity);

        trip = new Trip(0, visitDestination, visitSource);
        trip.setStartDateTime(LocalDateTime.of(LocalDate.parse(StartDate.toString()), StartTime.toLocalTime()));

        TripRepository tripRepository = new TripRepository();

        trip = tripRepository.createTrip(trip);

        return trip;
    }

}
