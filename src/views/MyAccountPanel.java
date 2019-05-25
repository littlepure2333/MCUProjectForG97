package views;

import bin.AppState;
import bin.UserManage;
import views.components.GotoButton;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyAccountPanel extends JPanel implements PanelStateMonitor {
	private ReportPanel reportPanel = new ReportPanel();
	private JLabel fineText = new JLabel();
	private JLabel idText = new JLabel();
	private JLabel nameText = new JLabel();
	private JLabel addText = new JLabel();

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

	@Override
	public void update() {
		fineText.setText(AppState.getCurrentUser().isNeedToPay().equals("true") ? "Be fined" : "Not be fined");
		idText.setText(String.valueOf(AppState.getCurrentUser().getQmNumber()));
		nameText.setText(AppState.getCurrentUser().getFullName());
		addText.setText(AppState.getCurrentUser().getEmail());
	}

	class IdPanel extends JPanel {
		IdPanel() {
			JLabel idLabel = new JLabel("ID:             ");
			idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			idText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(idLabel);
			this.add(idText);
		}
	}

	class NamePanel extends JPanel {
		NamePanel() {
			JLabel nameLabel = new JLabel("Name:        ");
			nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			nameText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(nameLabel);
			this.add(nameText);
		}
	}

	class AddPanel extends JPanel {
		AddPanel() {
			JLabel addLabel = new JLabel("Email：      ");
			addLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			addText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(addLabel);
			this.add(addText);
		}
	}

	class FinePanel extends JPanel {
		FinePanel() {
			JLabel fineLabel = new JLabel("Fine Status: ");
			fineLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
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
				fineText.setText(AppState.getCurrentUser().isNeedToPay().equals("true") ? "Be fined" : "Not be fined");
			}
		}
	}

}
