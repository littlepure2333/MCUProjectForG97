package views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ManagerPanel extends JPanel {
	//next state
	private JPanel registerInputPanel;

	
	ManagerPanel(JPanel registerInputPanel){
		this.registerInputPanel = registerInputPanel;

		this.setLayout(new GridLayout(3,1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());

		this.setVisible(true);
	}

	class MainPanel extends JPanel implements ActionListener{
		
		MainPanel() {
			GotoButton registerButton=new GotoButton("Register", registerInputPanel);
			JButton userButton=new JButton("User Information");
			JButton stationButton=new JButton("Station Information");

			registerButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			userButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			stationButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			registerButton.addActionListener(this);

			this.setLayout(new GridLayout(1,3));
			this.add(registerButton);
			this.add(userButton);
			this.add(stationButton);
		}
		
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			// do some other actions
			}
	}

	
}
