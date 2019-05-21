package views;

import bin.FormatCheck;
import bin.UserManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ManagerLoginPanel extends JPanel {
	private ManagerPanel managerPanel = new ManagerPanel();

	private JTextField answerText;
	private JLabel feedbackLabel;

	ManagerLoginPanel() {
		JPanel myPanel = new MyPanel();
		JPanel feedbackPanel = new FeedbackPanel();

		this.setLayout(new GridLayout(3, 1));
		this.add(feedbackPanel);
		this.add(myPanel);
		this.add(new JPanel());

		this.setVisible(true);
	}

	class MyPanel extends JPanel implements ActionListener {
		@SuppressWarnings("Duplicates")
		MyPanel() {
			JButton submitButton = new JButton("Submit");
			submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			submitButton.addActionListener(this);

			answerText = new JTextField(15);
			answerText.setFont(new Font("Times New Roman", Font.PLAIN, 40));

			this.setLayout(new GridLayout(2, 1));
			this.add(answerText);
			this.add(submitButton);
		}


		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("Submit")) {
				if (answerText.getText().length() == 0) {
					answerText.setText("");
					feedbackLabel.setText("You have to type in your manager ID now.");
				} else if ((FormatCheck.isID(answerText.getText()) == 0)) {
					answerText.setText("");
					feedbackLabel.setText("Not in correct format!");
				} else if (!UserManage.login(Integer.parseInt(answerText.getText()))) {
					answerText.setText("");
					feedbackLabel.setText("You haven't registered yet!");
				} else {
					//登录成功
					answerText.setText("");
					feedbackLabel.setText("Please type in your manager ID.");
					Windows.goToPanel(managerPanel);
				}
			}
		}
	}

	class FeedbackPanel extends JPanel {
		FeedbackPanel() {
			feedbackLabel = new JLabel("Please type in your manager ID.");
			feedbackLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));

			this.setLayout(new GridLayout(2, 1));
			this.add(feedbackLabel);
		}
	}


}
