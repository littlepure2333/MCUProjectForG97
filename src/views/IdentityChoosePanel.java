package views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;


class IdentityChoosePanel extends JPanel {
	//next state
	private JPanel managerPanel;
	private JPanel borrowAndReturnPanel;

	IdentityChoosePanel(JPanel managerPanel, JPanel borrowAndReturnPanel) {
//		JFrame initialInterface = new JFrame("Welcome to use QM scooter system");
		this.managerPanel = managerPanel;
		this.borrowAndReturnPanel = borrowAndReturnPanel;
		this.setLayout(new GridLayout(3,1));
		MainPanel mainPanel = new MainPanel();
		this.add(new JPanel());
		this.add(mainPanel);
		this.add(new JPanel());
	
	}
	class MainPanel extends JPanel {
		MainPanel(){          
		this.setLayout(new GridLayout(1,2));
		GotoButton manager = new GotoButton("Manager", managerPanel);
		GotoButton user = new GotoButton("User", borrowAndReturnPanel);
		manager.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		user.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		this.add(manager);
		this.add(user);
		}
    }

}
