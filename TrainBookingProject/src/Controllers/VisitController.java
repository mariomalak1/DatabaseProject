package Controllers;

import Models.City;
import Models.Trip;
import Models.Visit;
import Repositories.VisitRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class VisitController {
    public static Visit createVisit(LocalDateTime arriveDateTime, City city, Trip trip, Connection connection) {
        try {
            VisitRepository visitRepository = new VisitRepository();
            Visit visit = new Visit(trip, city, arriveDateTime);
            visitRepository.AddCityToVisit(visit, connection);
            return visit;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Visit> getAllVisitsInTrip(Trip trip, Connection connection) {
        try {
            return new VisitRepository().getAllVisitsByTrip(trip.getID(), connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Visit> getAllVisitsByCityName(String cityName, Connection connection) {
        try {
            return new VisitRepository().getAllVisitsByCityName(cityName, connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
