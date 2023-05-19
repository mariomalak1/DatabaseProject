package Models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Trip {
    private int ID;
    private Visit Destination;
    private Visit Source;
    private LocalDateTime StartDateTime;
    private List<Train> Trains;
    private List<City> Cities;

    public Trip(int id, Visit destination, Visit source){
        this.ID = id;
        this.Destination = destination;
        this.Source = source;
    }

    public String EndTime(){
        return String.valueOf(Duration.between(StartDateTime, Destination.getArrivingDateTime()));
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Visit getDestination() {
        return Destination;
    }

    public void setDestination(Visit destination) {
        Destination = destination;
    }

    public Visit getSource() {
        return Source;
    }

    public void setSource(Visit source) {
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