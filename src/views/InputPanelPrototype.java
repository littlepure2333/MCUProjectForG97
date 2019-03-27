package views;

import javax.swing.*;
import java.awt.*;

/*
 *  将指定panel变为{输入}专用panel,
 *  并向内部添加一个指示标签，和一个文本框，
 *  文本框需要被绑定才可以被外部访问
 */
abstract class InputPanelPrototype extends JPanel {
    private JTextField jTextField = new JTextField(15);
    InputPanelPrototype(String text) {
        JLabel jLabel=new JLabel(text);
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        jTextField.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        this.add(jLabel);
        this.add(jTextField);
    }

    JTextField bindTextField() {
        return this.jTextField;
    }
}
