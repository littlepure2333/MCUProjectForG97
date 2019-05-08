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
//        Windows.frame.remove(Windows.stack.peek());
//        Windows.stack.push(this.nextPanel);
//        Windows.frame.add(this.nextPanel);
//        Windows.upperPanel.setVisible(true);
//
//        //重绘界面
//        Windows.frame.validate();
//        Windows.frame.repaint();
    }
}
