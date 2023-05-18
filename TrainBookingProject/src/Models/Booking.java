package Models;

public class Booking {
    private int ID;
    private User user;
    private Trip trip;
    private Train train;
    private int NumberOfSeats;

    public Booking(User user, Trip trip, int seats){
        this(0, user, trip, seats);
    }

    public Booking(int id, User user, Trip trip, int seats){
        this.ID = id;
        this.NumberOfSeats = seats;
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

    public int getNumberOfSeats() {
        return NumberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.NumberOfSeats = numberOfSeats;
    }

    public int getID() {
        return ID;
    }

    public void setID(int bookingID) {
        ID = bookingID;
    }

    public double costOfBooking(){
        return NumberOfSeats * train.getPrice();
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
