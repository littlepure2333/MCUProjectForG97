package views;

import javax.swing.*;
import java.awt.*;

class OccupiedSlot extends JPanel {
    public void paintComponent(Graphics g) {
        ImageIcon image =new ImageIcon("./media/scooter.jpg");
        image.setImage(image.getImage().getScaledInstance(this.getWidth(),this.getHeight(),
                Image.SCALE_AREA_AVERAGING));
        g.drawImage(image.getImage(),0,0,this);
    }

}