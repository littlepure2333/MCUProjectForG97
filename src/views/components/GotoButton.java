package views.components;

import views.Windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boundary Class.
 * A button that go to another panel once clicked
 */
public class GotoButton extends JButton implements ActionListener {

    private JPanel nextPanel;
    /**
     * Generate a GOTO button, if it pressed, the frame will change the current panel
     *
     * @param text      the button content text
     * @param nextPanel the panel want to go to
     */
    public GotoButton(String text, JPanel nextPanel) {
        super(text);
        this.nextPanel = nextPanel;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Windows.goToPanel(this.nextPanel);
    }
}
