package Models;

public class Train {
    private int ID;
    private int Capacity;
    private Trip trip;
    private double Price;

    public Train(int id, int capacity, double price){
        this.ID = id;
        this.Capacity = capacity;
        this.Price = price;
    }

    public Train(int id, int capacity, Trip trip, double price){
        this.ID = id;
        this.Capacity = capacity;
        this.trip = trip;
        this.Price = price;
    }

    public void setID(int TrainID) {
        this.ID = TrainID;
    }

    public int getID() {
        return ID;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setTrip(Trip Trip) {
        this.trip = Trip;
    }

    public Trip getTrip(){
        return trip;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
