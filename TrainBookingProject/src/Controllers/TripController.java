package Controllers;

import Models.City;
import Models.Trip;
import Models.Visit;
import Repositories.CityRepository;
import Repositories.TrainRepository;
import Repositories.TripRepository;
import Repositories.VisitRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//        List<Train> trainsInTrip = new TrainRepository().getAllTrainsInTrip(trip.getID());
//        trip.setTrains(trainsInTrip);
//
//        List<Visit> VisitsInTrain = new VisitRepository().getAllVisitsByTrip(trip.getID());
//        trip.setVisits(VisitsInTrain);


public class TripController {
    public static List<Trip> getAllTrips(Connection connection){
        try {
            List<Trip> trips = new ArrayList<>();
            TripRepository tripRepository = new TripRepository();
            trips = tripRepository.getAllTrips(connection);
            return trips;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Trip> getAllTripsForSpecificCriteria(Time stratTime, java.util.Date date, String destinationCityName, String sourceCityName, int capacityOfTrain,Connection connection) {
        try {
            List<Trip> trips = new ArrayList<>();
            TripRepository tripRepository = new TripRepository();
            trips = tripRepository.getAllTripsInSpecificCriteria(
                    stratTime, date, destinationCityName, sourceCityName, capacityOfTrain,connection);
            return trips;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Trip createTrip(String Source, String Destination, Time StartTime, Date StartDate,Connection connection) throws SQLException {
        try {
            Trip trip = null;
            CityRepository cityRepository = new CityRepository();

            City sourceCity = cityRepository.GetAllCitiesLikeName(Source).get(0);
            if (sourceCity == null) {
                cityRepository.addCity(Source);
            }

            City destinationCity = cityRepository.GetAllCitiesLikeName(Destination).get(0);
            if (destinationCity == null) {
                cityRepository.addCity(Destination);
            }

            Visit visitDestination = new Visit(destinationCity);
            Visit visitSource = new Visit(sourceCity);

            trip = new Trip(0, visitDestination, visitSource);
            trip.setStartDateTime(LocalDateTime.of(LocalDate.parse(StartDate.toString()), StartTime.toLocalTime()));

            TripRepository tripRepository = new TripRepository();

            trip = tripRepository.createTrip(trip,connection);

            return trip;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Trip> getAllTripsGoToCityByCityName(String cityName,Connection connection){
        try {
            return new TripRepository().getAllTripsGoToCityByCityName(cityName,connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Trip getTripByID(int tripID,Connection connection){
        try {
<<<<<<< HEAD
            Trip trip = new TripRepository().getTripById(tripID);
            trip.setTrains(new TrainRepository().getAllTrainsInTrip(trip.getID()));

=======

            Trip trip = new TripRepository().getTripById(tripID,connection);
            trip.setTrains(new TrainRepository().getAllTrainsInTrip(trip.getID(),connection));
>>>>>>> 30cfc4802cf164b637d7012d69625d2a7d9e227d
            trip.setVisits(new VisitRepository().getAllVisitsByTrip(trip.getID()));
            return trip;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
