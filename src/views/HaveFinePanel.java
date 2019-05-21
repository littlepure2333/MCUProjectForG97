package views;

import views.components.ReturnButton;
import javax.swing.*;
import java.awt.*;

class HaveFinePanel extends JPanel {
	HaveFinePanel() {
		this.setLayout(new GridLayout(3, 1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());
	}

	class MainPanel extends JPanel {
		MainPanel() {
			ReturnButton warning = new ReturnButton("You owe me! Plz pay your fine through my account");
			warning.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.setLayout(new GridLayout(1, 1));
			this.add(warning);
		}
	}
}
