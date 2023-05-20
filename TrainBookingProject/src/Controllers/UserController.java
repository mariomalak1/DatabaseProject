package Controllers;

import Models.User;
import Repositories.UserRepository;

import javax.swing.*;
import java.sql.SQLException;

public class UserController {

    public static User addUser(String fname,String lname,String pass,String Email,String role){
        try {
            UserRepository userRepo = new UserRepository();
            User user = new User(fname,lname,pass,role,Email);
            userRepo.insertUser(user);
            return user;
        }catch ( SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Failed to Create User ","Sign Up Failed",JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }

    public static Boolean deleteUser(int userId){
        try {
            UserRepository userRepo = new UserRepository();
            userRepo.deleteUser(userId);
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to Delete User");
        }
        return false;
    }

    public static User updateUser(int userId,String fname,String lname,String pass,String Email,String role){
        try {
            UserRepository userRepo = new UserRepository();
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

    public static User getUserById(int userId) {
        try {
            UserRepository userRepo = new UserRepository();
            return userRepo.getUserById(userId);
        } catch (SQLException e) {
            System.out.println("User Not Found");
        }
        return null;
    }
}
