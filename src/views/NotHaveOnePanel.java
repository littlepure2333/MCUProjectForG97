package views;

import views.IdentityChoosePanel.MainPanel;
import views.components.ReturnButton;

import javax.swing.*;
import java.awt.*;

public class NotHaveOnePanel extends JPanel{
	
	NotHaveOnePanel() {

		this.setLayout(new GridLayout(3,1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());
	
	}
	class MainPanel extends JPanel {
		MainPanel() {
			ReturnButton warning = new ReturnButton("You don't owe me, my darling");
			warning.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			this.setLayout(new GridLayout(1,1));
			this.add(warning);
		}
    }
}
