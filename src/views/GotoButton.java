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
        Windows.fr.remove(Windows.stack.peek());
        Windows.fr.add(this.nextPanel);
        Windows.stack.push(this.nextPanel);

        //重绘界面
        Windows.fr.validate();
        Windows.fr.repaint();
    }
}
