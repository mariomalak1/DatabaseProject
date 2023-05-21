import Repositories.MainRepository;
import Views.HomePageView;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = MainRepository.getConnection();
        HomePageView f = new HomePageView(connection);
    }
}