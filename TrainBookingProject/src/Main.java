import Controllers.CityController;
import Controllers.TripController;
import Models.City;
import Models.Trip;
import Repositories.MainRepository;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = MainRepository.getConnection();
        City source = CityController.getCityLikeName("Cairo", connection);
        City destination = CityController.getCityLikeName("Alexandria", connection);

        List<Trip> trips = TripController.getAllTripsForSpecificCriteria(null, null, destination, source, 0, connection);
        if (trips != null) {
            for (Trip trip : trips) {
                System.out.println("------------------");
                System.out.println(trip.getID());
                System.out.println(trip.getSource());
                System.out.println(trip.getDestination());
                System.out.println(trip.getStartDateTime());
            }
        }else{
            System.out.println("No Trips");
        }
//        HomePageView f = new HomePageView(connection);
    }
}