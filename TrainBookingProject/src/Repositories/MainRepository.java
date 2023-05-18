package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainRepository {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=TrainBooking;encrypt=true;trustServerCertificate=true";
    private static final String username = "admin";
    private static final String pass = "12345";

    public static Connection getConnection(){
        try {
            System.out.println("Before Connected");
            Connection c = DriverManager.getConnection(url, username, pass);
            System.out.println("Connected");
            return c;
        }
        catch(SQLException e){
            System.out.println("Error While Connecting to DataBase");
            e.printStackTrace();
            return null;
        }
    }
}
