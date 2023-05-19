package Controllers;

import Models.User;
import Repositories.UserRepository;

import java.sql.SQLException;

public class UserController {
    private final UserRepository userRepo;
    public UserController(){
        this.userRepo = new UserRepository();
    }
    public User addUser(String fname,String lname,String pass,String Email,String role){
        try {
            User user = new User(fname,lname,pass,role,Email);
            userRepo.insertUser(user);
            return user;
        }catch ( SQLException e)
        {
            System.out.println("Failed to Create New User");
        }
        return null;
    }
    public Boolean deleteUser(int userId){
        try {
            userRepo.deleteUser(userId);
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to Delete User");
        }
        return false;
    }
    public User updateUser(int userId,String fname,String lname,String pass,String Email,String role){
        try {
            User user = userRepo.getUserById(userId);
            if (user != null)
            {
                user.setFirstName(fname);
                user.setLastName(lname);
                user.setPassword(pass);
                user.setEmail(Email);
                user.setRole(role);
                return user;
            }else
            {
                System.out.println("User Not Found");
            }
        } catch (SQLException e) {
            System.out.println("Failed to Update User");
        }
        return null;
    }
    public User getUserById(int userId)
    {
        try {
            User user = userRepo.getUserById(userId);
            return user;
        } catch (SQLException e) {
            System.out.println("User Not Found");
        }
        return null;
    }

}
