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

//            LocalDateTime arrivingTime = LocalDateTime.of(dateOftrip.toLocalDate(), startTime.toLocalTime());
//                    sourceVisit.setArrivingTime(arrivingTime);
//
//                    List<Train> trainsInTrip = new TrainRepository().getAllTrainsInTrip(trip.getID());
//        trip.setTrains(trainsInTrip);
//
//        List<Visit> VisitsInTrain = new VisitRepository().getAllVisitsByTrip(trip.getID());
//        trip.setVisits(VisitsInTrain);


public class TripController {
    public static List<Trip> getAllTrips(){
        try {
            List<Trip> trips = new ArrayList<>();
            TripRepository tripRepository = new TripRepository();
            trips = tripRepository.getAllTrips();
            return trips;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Trip> getAllTripsForSpecificCriteria(Time stratTime, java.util.Date date, String destinationCityName, String sourceCityName, int capacityOfTrain) {
        try {
            List<Trip> trips = new ArrayList<>();
            TripRepository tripRepository = new TripRepository();
            trips = tripRepository.getAllTripsInSpecificCriteria(
                    stratTime, date, destinationCityName, sourceCityName, capacityOfTrain);
            return trips;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Trip createTrip(String Source, String Destination, Time StartTime, Date StartDate) throws SQLException {
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

            trip = tripRepository.createTrip(trip);

            return trip;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Trip> getAllTripsGoToCityByCityName(String cityName){
        try {
            return new TripRepository().getAllTripsGoToCityByCityName(cityName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
