package Models;

import java.time.LocalDateTime;
import java.util.List;

public class Trip {
    private int ID;
    private Visit Destination;
    private Visit Source;
    private LocalDateTime StartDateTime;
    private List<Train> Trains;
    private List<Visit> Visits;

    public Trip(int id){
        this.ID = id;
    }

    public Trip(int id, LocalDateTime localDateTime){
        this.ID = id;
        this.StartDateTime = localDateTime;
    }

    public Trip(int id, Visit destination, Visit source){
        this.ID = id;
        this.Destination = destination;
        this.Source = source;

        this.Destination.setTrip(this);
        this.Source.setTrip(this);
    }

    public LocalDateTime EndTime(){
        return  Destination.getArrivingDateTime();
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
        this.Destination.setTrip(this);
    }

    public Visit getSource() {
        return Source;
    }

    public void setSource(Visit source) {
        Source = source;
        this.Source.setTrip(this);
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

    public List<Visit> getCities() {
        return Visits;
    }

    public void setVisits(List<Visit> visits) {
        for (Visit visit: visits) {
            visit.setTrip(this);
        }
        Visits = visits;
    }
}