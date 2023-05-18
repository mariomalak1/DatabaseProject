package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainRepository {
    private static final String DATABASE_URL = "";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(DATABASE_URL);
        }
        catch(SQLException e){
            System.out.println("Error While Connecting to DataBase");
            e.printStackTrace();
            return null;
        }
    }
}
