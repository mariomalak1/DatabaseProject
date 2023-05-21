package Repositories;
import Models.User;
import java.sql.*;

public class UserRepository {
    //private final Connection connection;
//    public UserRepository() {
//        this.connection = MainRepository.getConnection();
//    }
    public User insertUser(User user,Connection connection) throws SQLException {
        String sql = "INSERT INTO [User](FirstName,LastName,UserPassword,Email,Role)"+
                "VALUES(?,?,?,?,?)";
        try(PreparedStatement statement =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getPassword());
            statement.setString(4,user.getEmail());
            statement.setString(5,user.getRole());
            int rowAffected = statement.executeUpdate();
            if(rowAffected == 0)
            {
                throw new SQLException("Insert Failed, No Row Affected ");
            }
            try(ResultSet generatedKey = statement.getGeneratedKeys())
            {
                if(generatedKey.next())
                {
                    int genID = generatedKey.getInt(1);
                    user.setID(genID);
                }
                else
                {
                    throw new SQLException("Insert Failed, No ID obtained");
                }
            }
        }
        return user;
    }
    public void updateUser(User user,Connection connection)throws SQLException{
        String sql = "UPDATE [User] SET FirstName = ?, LastName = ?, UserPassword = ?, Email = ?, Role = ? WHERE UserID = ?";
        try (PreparedStatement statement =connection.prepareStatement(sql)){
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getPassword());
            statement.setString(4,user.getEmail());
            statement.setString(5,user.getRole());
            statement.setInt(6,user.getID());
            statement.executeUpdate();
        }
    }
    public void deleteUser(int userId,Connection connection) throws SQLException{
        String sql = "DELETE From [User] WHERE UserID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.executeUpdate();
        }
    }
    public User getUserById(int userId,Connection connection) throws SQLException{
        String sql = "SELECT * FROM [User] WHERE UserID = ?";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next())
                {
                    user = extractUserFromResultSet(resultSet,connection);
                }
            }
        }
        return user;
    }
    private User extractUserFromResultSet(ResultSet r,Connection connection) throws SQLException{
        int userId = r.getInt("UserID");
        String fname = r.getString("FirstName");
        String lname = r.getString("LastName");
        String email = r.getString("Email");
        String pass = r.getString("UserPassword");
        String role = r.getString("Role");
        User user = new User(fname,lname,pass,role,email);
        user.setID(userId);
        return user;
    }
}
