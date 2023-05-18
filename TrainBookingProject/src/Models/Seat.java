package Models;

public class Seat {
    private int ID;
    private double Price;

    public double getPrice(){
        return Price;
    }
    public void setPrice(double price){
        Price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
