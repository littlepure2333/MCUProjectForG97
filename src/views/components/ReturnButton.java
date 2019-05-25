package views.components;

import views.Windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boundary Class.
 * A button that go to another panel once clicked
 */
public class ReturnButton extends JButton implements ActionListener {
    /**
     * Generate a RETURN button
     * @param text the button content text
     */
    public ReturnButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Windows.frame.remove(Windows.stack.peek());
        Windows.stack.pop();
        Windows.frame.add(Windows.stack.peek());
        if (Windows.stack.size() == 1)
            Windows.upperPanel.setVisible(false);

        Windows.frame.validate();
        Windows.frame.repaint();
    }
}