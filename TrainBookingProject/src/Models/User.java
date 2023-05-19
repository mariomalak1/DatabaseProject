package Models;

public class User {
    private int UserID;
    private String firstName;
    private String lastName;
    private String fullName;
    private String Password;
    private String Role;
    private String Email;
    private static int lastID = 0;

    public User(String fname,String lname,String pass,String role,String email)
    {
        lastID++;
        UserID = lastID;
        firstName = fname;
        lastName= lname;
        fullName = firstName + lastName;
        Password = pass;
        Email = email;
        Role = role;
    }
    public int getID() {
        return UserID;
    }
    public void setID(int ID) {
        this.UserID = ID;
    }
    public void setFirstName(String fname)
    {
        this.firstName = fname;
        this.fullName = firstName + lastName;
    }
    public void setLastName(String lname)
    {
        this.lastName = lname;
        this.fullName = firstName + lastName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getPassword() {
        return Password;
    }
    public void setRole(String role) {
        Role = role;
    }
    public String getRole() {
        return Role;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getEmail() {
        return Email;
    }
    public boolean isAdmin()
    {
        return Role == "Admin";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
