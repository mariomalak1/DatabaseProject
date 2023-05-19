package Views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TrainBooking");
//        this.setBackground(new Color(7,10,8,2));
        this.setSize(1280,720);
        //this.setVisible(true);
        ImageIcon icon = new ImageIcon("src\\train.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(236,219,186));
        this.setLayout(null);

    }
}
