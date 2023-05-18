import Models.*;
import Repositories.BookingRepository;
import Repositories.MainRepository;

import java.awt.print.Book;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MainRepository.getConnection();
    }
}