package views;

import bin.UserManage;
import views.components.GotoButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyAccountPanel extends JPanel {
	private ReportPanel reportPanel = new ReportPanel();
	JLabel fineText;

	MyAccountPanel() {
		JPanel idPanel = new IdPanel();
		JPanel namePanel = new NamePanel();
		JPanel addPanel = new AddPanel();
		JPanel finePanel = new FinePanel();
		JPanel buttonPanel = new ButtonPanel();

		this.setLayout(new GridLayout(7, 1));
		this.add(new JPanel());
		this.add(idPanel);
		this.add(namePanel);
		this.add(addPanel);
		this.add(finePanel);
		this.add(buttonPanel);

		this.setVisible(true);
	}

	class IdPanel extends JPanel {
		IdPanel() {
			JLabel idLabel = new JLabel("ID:             ");
			idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JLabel idText = new JLabel(UserManage.getCurrentUserId());
			idText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(idLabel);
			this.add(idText);
		}
	}

	class NamePanel extends JPanel {
		NamePanel() {
			JLabel nameLabel = new JLabel("Name:        ");
			nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JLabel nameText = new JLabel(UserManage.getCurrentUserName());
			nameText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(nameLabel);
			this.add(nameText);
		}
	}

	class AddPanel extends JPanel {
		AddPanel() {
			JLabel addLabel = new JLabel("Email：      ");
			addLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JLabel addText = new JLabel(UserManage.getCurrentUserEmail());
			addText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(addLabel);
			this.add(addText);
		}
	}

	class FinePanel extends JPanel {
		FinePanel() {
			JLabel fineLabel = new JLabel("Fine Status: ");
			fineLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			fineText = new JLabel(UserManage.getCurrentUserFineState());
			fineText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(fineLabel);
			this.add(fineText);
		}
	}

	class ButtonPanel extends JPanel implements ActionListener {
		ButtonPanel() {
			JButton payButton = new JButton("Pay My Fine");
			payButton.setFont(new Font("Times New Roman", Font.PLAIN, 50));
			GotoButton reportButton = new GotoButton("Check Report", reportPanel);
			reportButton.setFont(new Font("Times New Roman", Font.PLAIN, 50));

			this.add(payButton);
			this.add(reportButton);
			payButton.addActionListener(this);
			reportButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("Pay My Fine")) {
				UserManage.payTheFine();
				fineText.setText(UserManage.getCurrentUserFineState());
			}
		}
	}

}
