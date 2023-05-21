package Views;

import javax.swing.*;

public class backBtn extends JButton{

    ImageIcon backIcon = new ImageIcon("src\\back.png");
    public backBtn(){
        this.setIcon(backIcon);
        this.setText("Go Back");
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setFocusable(false);
        //backBTN.setBackground(Color.WHITE);
        this.setBounds(0,0,100,50);
    }
}
