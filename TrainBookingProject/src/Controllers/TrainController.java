package Controllers;

import Models.Train;
import Models.Trip;
import Repositories.TrainRepository;

import java.util.List;

public class TrainController {
    public static Train createTrain(int capacity, Trip trip, double price){
        try{
            Train train = new Train(0, capacity, trip, price);
            train = new TrainRepository().createTrain(train);
            return train;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateTrain(Train UpdatedTrain){
        try{
            new TrainRepository().UpdateTrain(UpdatedTrain);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Train getTrainById(int trainId){
        try{
            return new TrainRepository().getTrainById(trainId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Train> getAllTrainsInTrip(int tripId){
        try{
            return new TrainRepository().getAllTrainsInTrip(tripId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
