package DBconnection;

import java.sql.*;

public class connection {
    public static void connect()
    {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TrainBooking;encrypt=true;trustServerCertificate=true";
        String username = "admin";
        String pass = "1234";
        try {
            Connection c = DriverManager.getConnection(url,username,pass);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }
}
