package views.components;

import views.Windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GotoButton extends JButton implements ActionListener {
    private JPanel nextPanel;

    public GotoButton(String text, JPanel nextPanel) {
        super(text);
        this.nextPanel = nextPanel;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Windows.goToPanel(this.nextPanel);
    }
}
