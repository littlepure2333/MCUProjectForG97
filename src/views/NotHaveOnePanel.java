package views;

import views.components.ReturnButton;

import javax.swing.*;
import java.awt.*;

/**
 * Boundary Class
 * If the user clicked return and he haven't borrowed one, he will be taken to the here.
 * which remind that the user hasn't borrowed one.
 */
class NotHaveOnePanel extends JPanel {
	NotHaveOnePanel() {
		this.setLayout(new GridLayout(3, 1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());
	}

	class MainPanel extends JPanel {
		MainPanel() {
			ReturnButton warning = new ReturnButton("You don't owe me, my darling");
			warning.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			this.setLayout(new GridLayout(1, 1));
			this.add(warning);
		}
	}
}
