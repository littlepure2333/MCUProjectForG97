package views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPanel extends JPanel{
	//next state
	private JPanel registerInputPanel;

	
	ManagerPanel(JPanel registerInputPanel){
		this.registerInputPanel = registerInputPanel;
//		this.setTitle("Welcome! Dear manager!");
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());

		this.setLayout(new GridLayout(3,1));
		this.setVisible(true);
	
	}
	

	
	class MainPanel extends JPanel implements ActionListener{
		
		MainPanel(){
			GotoButton registerButton=new GotoButton("Register", registerInputPanel);
			JButton userButton=new JButton("User Information");
			JButton stationButton=new JButton("Station Information");
			this.setLayout(new GridLayout(1,3));
			registerButton.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			userButton.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			stationButton.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			this.add(registerButton);
			this.add(userButton);
			this.add(stationButton);
			
			registerButton.addActionListener(this);
			
			
		}
		
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			// do some other actions
			}
	}

	
}
