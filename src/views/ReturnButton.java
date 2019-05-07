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

        Windows.frame.remove(Windows.stack.peek());
        Windows.stack.pop();
        Windows.frame.add(Windows.stack.peek());
        if(Windows.stack.size()==1)
            Windows.upperPanel.setVisible(false);

        //重绘界面
        Windows.frame.validate();
        Windows.frame.repaint();
    }
}