package Controllers;

import Models.Train;
import Models.Trip;
import Repositories.MainRepository;
import Repositories.TrainRepository;

import java.sql.Connection;
import java.util.List;

public class TrainController {

    public static Train createTrain(int capacity, Trip trip, double price,Connection connection){
        try{
            Train train = new Train(0, capacity, trip, price);
            train = new TrainRepository().createTrain(train,connection);
            return train;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateTrain(Train UpdatedTrain,Connection connection){
        try{
            new TrainRepository().UpdateTrain(UpdatedTrain,connection);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Train getTrainById(int trainId,Connection connection){
        try{
            return new TrainRepository().getTrainById(trainId,connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Train> getAllTrainsInTrip(int tripId,Connection connection){
        try{
            return new TrainRepository().getAllTrainsInTrip(tripId,connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
