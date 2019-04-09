package views;

import javax.swing.*;


import java.awt.*;


class IdentityChoosePanel extends JPanel {
	//next state
	private JPanel managerPanel;
	private JPanel borrowPanel;

	IdentityChoosePanel(JPanel managerPanel, JPanel borrowPanel) {
//		JFrame initialInterface = new JFrame("Welcome to use QM scooter system");
		this.managerPanel = managerPanel;
		this.borrowPanel = borrowPanel;
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
		GotoButton user = new GotoButton("User", borrowPanel);
		manager.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		user.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		this.add(manager);
		this.add(user);
		}
    }
}
