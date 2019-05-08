package views;

import views.components.GotoButton;

import javax.swing.*;
import java.awt.*;

class ManagerPanel extends JPanel {
	//next state
	private RegisterInputPanel registerInputPanel = new RegisterInputPanel();

	
	ManagerPanel() {

		this.setLayout(new GridLayout(3,1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());

		this.setVisible(true);
	}

	class MainPanel extends JPanel {

		MainPanel() {
			GotoButton registerButton = new GotoButton("Register", registerInputPanel);
			JButton userButton = new JButton("User Information");
			JButton stationButton = new JButton("Station Information");

			registerButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			userButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			stationButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.setLayout(new GridLayout(1, 3));
			this.add(registerButton);
			this.add(userButton);
			this.add(stationButton);
		}

	}
}
