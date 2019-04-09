package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ReturnButton extends JButton implements ActionListener {
    ReturnButton(String text) {
        super(text);
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {

        Windows.fr.remove(Windows.stack.peek());
        Windows.stack.pop();
        Windows.fr.add(Windows.stack.peek());

        //重绘界面
        Windows.fr.validate();
        Windows.fr.repaint();
    }
}