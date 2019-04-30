package views;

import javax.swing.*;
import java.awt.*;

class IdentityChoosePanel extends JPanel {
	private JPanel managerPanel;
	private JPanel borrowAndReturnPanel;

	IdentityChoosePanel(JPanel managerPanel, JPanel borrowAndReturnPanel) {
		this.managerPanel = managerPanel;
		this.borrowAndReturnPanel = borrowAndReturnPanel;

		this.setLayout(new GridLayout(3,1));
		this.add(new JPanel());
		this.add(new MainPanel());
		this.add(new JPanel());
	
	}
	class MainPanel extends JPanel {
		MainPanel() {
			GotoButton manager = new GotoButton("Manager", managerPanel);
			manager.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			GotoButton user = new GotoButton("User", borrowAndReturnPanel);
			user.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.setLayout(new GridLayout(1,2));
			this.add(manager);
			this.add(user);
		}
    }

}
