package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GotoButton extends JButton implements ActionListener {
    private JPanel nextPanel;
    GotoButton(String text, JPanel nextPanel) {
        super(text);
        this.nextPanel = nextPanel;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Windows.frame.remove(Windows.stack.peek());
        Windows.stack.push(this.nextPanel);
        Windows.frame.add(this.nextPanel);
        Windows.returnPanel.setVisible(true);

        //重绘界面
        Windows.frame.validate();
        Windows.frame.repaint();
    }
}
