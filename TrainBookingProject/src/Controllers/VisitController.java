package Controllers;

import Models.City;
import Models.Trip;
import Models.Visit;
import Repositories.VisitRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class VisitController {
    public static Visit createVisit(LocalDateTime arriveDateTime, City city, Trip trip) {
        try {
            VisitRepository visitRepository = new VisitRepository();
            Visit visit = new Visit(trip, city, arriveDateTime);
            visitRepository.AddCityToVisit(visit);
            return visit;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Visit> getAllVisitsInTrip(Trip trip) {
        try {
            return new VisitRepository().getAllVisitsByTrip(trip.getID());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Visit> getAllVisitsByCityName(String cityName) {
        try {
            return new VisitRepository().getAllVisitsByCityName(cityName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
