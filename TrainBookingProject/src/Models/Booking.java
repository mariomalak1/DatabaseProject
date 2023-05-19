package Models;

public class Booking {
    private int ID;
    private User user;
    private Train train;
    private int NumberOfSeats;

    public Booking(int id, User user, Train train, int seats){
        this.ID = id;
        this.NumberOfSeats = seats;
        this.user = user;
        this.train = train;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return train.getTrip();
    }

    public void setTrip(Trip trip) {
        this.train.setTrip(trip);
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
