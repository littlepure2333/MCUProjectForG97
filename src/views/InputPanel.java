package views;

import javax.swing.*;
import java.awt.*;

abstract class InputPanel extends JPanel {
    InputPanel(String text) {
        JLabel idLabel=new JLabel(text);
        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JTextField idText=new JTextField(15);
        idText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        this.add(idLabel);
        this.add(idText);
    }
}
