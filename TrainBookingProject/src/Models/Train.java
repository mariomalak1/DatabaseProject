package Models;

public class Train {
    private int TrainID;
    private int Capacity;
    private Trip tripID;

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
}
