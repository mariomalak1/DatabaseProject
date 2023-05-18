package Models;

public class Train {
    private int TrainID;
    private int Capacity;
    private Trip tripID;
    private int Price;

    public void setTrainId(int TrainID) {
        this.TrainID = TrainID;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }

    public void setTripId(Trip TripID) {
        this.tripID = TripID;
    }

    public int getTrainId() {
        return TrainID;
    }
    public int getCapacity() {
        return Capacity;
    }
    public Trip getTripId(){
        return tripID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
