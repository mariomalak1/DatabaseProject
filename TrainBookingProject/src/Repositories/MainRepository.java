package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainRepository {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=TrainBooking;encrypt=true;trustServerCertificate=true";
    private static final String username = "admin";
    private static final String pass = "1234";
    private static final String conn = "jdbc:sqlserver://trainbookingdb.database.windows.net:1433;database=TrainBooking;user=Joe@trainbookingdb;password=J0e123456789;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public static Connection getConnection(){
        try {

            Connection c = DriverManager.getConnection(conn);
            return c;

        }
        catch(SQLException e){
            System.out.println("Error While Connecting to DataBase");
            e.printStackTrace();
            return null;
        }
    }
}
