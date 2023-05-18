package Models;

import java.time.LocalDateTime;

public class City {
    private LocalDateTime ArrivingTime;
    private Trip TripOn;
    private String Name;

    public LocalDateTime getArrivingDateTime() {
        return ArrivingTime;
    }

    public void setArrivingTime(LocalDateTime arrivingTime) {
        ArrivingTime = arrivingTime;
    }

    public Trip getTripOn() {
        return TripOn;
    }

    public void setTripOn(Trip tripOn) {
        TripOn = tripOn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
