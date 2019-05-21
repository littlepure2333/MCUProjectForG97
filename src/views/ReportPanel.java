package views;

import bin.UserManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ReportPanel extends JPanel {
	ReportPanel() {
		JPanel emailPanel = new EmailPanel();
		JPanel taketimePanel = new TaketimePanel();
		JPanel takestationPanel = new TakestationPanel();
		JPanel returntimePanel = new ReturntimePanel();
		JPanel returnstationPanel = new ReturnstationPanel();

		this.setLayout(new GridLayout(7, 1));
		this.add(new JPanel());
		this.add(emailPanel);
		this.add(taketimePanel);
		this.add(takestationPanel);
		this.add(returntimePanel);
		this.add(returnstationPanel);

		this.setVisible(true);
	}


	class EmailPanel extends JPanel {
		EmailPanel() {
			JLabel emailLabel1 = new JLabel("Dear Mr/Mrs:");
			emailLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			JTextField emailText = new JTextField(5);
			emailText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			JLabel emailLabel = new JLabel("    Here is your scooter weekly usage report.Thank you for supporting us.If you have any questions,please contact us by 9348-3527. ");
			emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(emailLabel1);
			this.add(emailLabel);
			this.add(emailText);
		}
	}

	class TaketimePanel extends JPanel {
		TaketimePanel() {
			JLabel taketimeLabel = new JLabel("Take time");
			taketimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JTextField taketimeText = new JTextField(15);
			taketimeText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(taketimeLabel);
			this.add(taketimeText);
		}
	}

	class TakestationPanel extends JPanel {
		TakestationPanel() {
			JLabel takeStationLabel = new JLabel("Take Station");
			takeStationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JTextField takestationText = new JTextField(15);
			takestationText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(takeStationLabel);
			this.add(takestationText);
		}
	}

	class ReturntimePanel extends JPanel {
		ReturntimePanel() {
			JLabel returntimeLabel = new JLabel("Return time");
			returntimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JTextField returntimeText = new JTextField(15);
			returntimeText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(returntimeLabel);
			this.add(returntimeText);
		}
	}

	class ReturnstationPanel extends JPanel {
		ReturnstationPanel() {
			JLabel returnStationLabel = new JLabel("Return Station");
			returnStationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JTextField returnstationText = new JTextField(15);
			returnstationText.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.add(returnStationLabel);
			this.add(returnstationText);
		}
	}
}
