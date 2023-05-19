import Models.*;
import Repositories.BookingRepository;
import Repositories.MainRepository;
import Repositories.UserRepository;

import java.awt.print.Book;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        User user = new User("Joe","Refaat","joe12324","Admin","joe22@gmail.com");
        UserRepository u = new UserRepository();
        try {
            user = u.insertUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        try {
//            u.deleteUser(3);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            User user1 = u.getUserById(2);
//            System.out.println(user1.getFirstName()+" "+user1.getLastName());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}