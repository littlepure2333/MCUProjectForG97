package views.components;

import javax.swing.*;
import java.awt.*;

/**
 * Boundary Class.
 * Empty slot displayed in station GUI
 */
public class EmptySlot extends JPanel {
    /**
     * Paint the empty slot
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        ImageIcon image = new ImageIcon("./media/null.jpg");
        image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(),
                Image.SCALE_AREA_AVERAGING));
        g.drawImage(image.getImage(), 0, 0, this);
    }

}