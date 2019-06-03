package views;

import views.components.ReturnButton;

import javax.swing.*;
import java.awt.*;

/**
 * Boundary Class
 * If the user clicked take and he has borrowed one, he will be taken here.
 * which remind that the user has already borrowed one and have to return first.
 */
class AlreadyHaveOnePanel extends JPanel {
	AlreadyHaveOnePanel() {
		this.setLayout(new GridLayout(3, 1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());

	}

	class MainPanel extends JPanel {
		MainPanel() {
			ReturnButton warning = new ReturnButton("You've already borrowed one, plz return first!");
			warning.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.setLayout(new GridLayout(1, 1));
			this.add(warning);
		}
	}
}
