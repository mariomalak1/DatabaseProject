package Models;

public class Booking {
    private int ID;
    private User user;
    private Trip trip;
    private Seat seat;

    public Booking(int id, User user, Trip trip, Seat seat){
        this.ID = id;
        this.seat = seat;
        this.user = user;
        this.trip = trip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getBookingID() {
        return ID;
    }

    public void setBookingID(int bookingID) {
        ID = bookingID;
    }
}
