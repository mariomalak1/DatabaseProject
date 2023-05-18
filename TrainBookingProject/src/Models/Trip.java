package Models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Trip {
    private int ID;
    private City Destination;
    private City Source;
    private LocalDateTime StartDateTime;
    private List<Train> Trains;
    private List<City> Cities;

    public Duration EndTime(){
        return Duration.between(StartDateTime, Destination.getArrivingDateTime());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public City getDestination() {
        return Destination;
    }

    public void setDestination(City destination) {
        Destination = destination;
    }

    public City getSource() {
        return Source;
    }

    public void setSource(City source) {
        Source = source;
    }

    public LocalDateTime getStartDateTime() {
        return StartDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        StartDateTime = startDateTime;
    }

    public List<Train> getTrains() {
        return Trains;
    }

    public void setTrains(List<Train> trains) {
        Trains = trains;
    }

    public List<City> getCities() {
        return Cities;
    }

    public void setCities(List<City> cities) {
        Cities = cities;
    }
}