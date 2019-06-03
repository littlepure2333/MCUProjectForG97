package views;

import views.components.GotoButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boundary Class
 * It is displayed after the manager logged in successfully.
 * There will be three function for the manager to choose: Register, User information and Station Information.
 */
class ManagerPanel extends JPanel {
	private RegisterInputPanel registerInputPanel = new RegisterInputPanel();
	private StationInformationPanel stationInformationPanel = new StationInformationPanel();
	private UserInformationPanel userInformationPanel = new UserInformationPanel();

	/**
	 * The constructor of ManagerPanel
	 */
	ManagerPanel() {
		this.setLayout(new GridLayout(3, 1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());

		this.setVisible(true);
	}

	class MainPanel extends JPanel implements ActionListener {
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

			registerButton.addActionListener(this);
			userButton.addActionListener(this);
			stationButton.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("User Information")) {
				userInformationPanel.update();
				Windows.goToPanel(userInformationPanel);
			}
			if (actionCommand.equals("Station Information")) {
				stationInformationPanel.update();
				Windows.goToPanel(stationInformationPanel);
			}
		}
	}
}
