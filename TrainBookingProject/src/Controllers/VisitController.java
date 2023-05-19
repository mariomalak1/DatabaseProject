package Controllers;

import Models.City;
import Models.Trip;
import Models.Visit;
import Repositories.VisitRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class VisitController {
    public static Visit createVisit(LocalDateTime arriveDateTime, City city, Trip trip) throws SQLException {
        VisitRepository visitRepository = new VisitRepository();
        Visit visit = new Visit(trip, city, arriveDateTime);
        visitRepository.AddCityToVisit(visit);
        return visit;
    }

    public static List<Visit> getAllVisitsInTrip(Trip trip) throws SQLException {
        return new VisitRepository().getAllVisitsByTrip(trip.getID());
    }

    public static List<Visit> getAllVisitsByCityName(String cityName) throws SQLException {
        return new VisitRepository().getAllVisitsByCityName(cityName);
    }
}
