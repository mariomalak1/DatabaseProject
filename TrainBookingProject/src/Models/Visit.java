package Models;

import java.time.LocalDateTime;

public class Visit {
    private LocalDateTime ArrivingTime;
    private Trip trip;
    private City city;

    public Visit(City city){
        this.city = city;
    }

    public Visit(City city, LocalDateTime arrivingTime){
        this.city = city;
        this.ArrivingTime = arrivingTime;
    }

    public Visit(Trip trip, City city, LocalDateTime arrivingTime){
        ArrivingTime = arrivingTime;
        this.trip = trip;
        this.city = city;
    }

    public Visit(Trip trip, City city){
        this.city = city;
        this.trip = trip;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDateTime getArrivingDateTime() {
        return ArrivingTime;
    }

    public void setArrivingTime(LocalDateTime arrivingTime) {
        ArrivingTime = arrivingTime;
    }
}
