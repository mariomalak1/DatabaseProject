package Models;

import java.time.LocalDateTime;

public class Visit {
    private LocalDateTime ArrivingTime;
    private Trip trip;
    private City city;

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
