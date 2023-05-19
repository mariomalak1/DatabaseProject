package Models;

public class City {
    private int ID;
    private String Name;

    City(int Id, String name){
        this.Name = name;
        this.ID = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}