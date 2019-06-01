package views;

import bin.FormatCheck;
import bin.UserManage;
import mcu.CommunicationEvent;
import mcu.CommunicationListener;
import mcu.RxTx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserLoginPanel extends JPanel {
	//next state
	private UserPanel userPanel = new UserPanel();

	private JTextField answerText;
	private JLabel feedbackLabel;

	UserLoginPanel() {
		JPanel myPanel = new MyPanel();
		JPanel feedbackPanel = new FeedbackPanel();

		this.setLayout(new GridLayout(3, 1));
		this.add(feedbackPanel);
		this.add(myPanel);
		this.add(new JPanel());

		this.setVisible(true);
//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
		RxTx.communication.registerListener(new CommunicationListener() {
			@Override
			public void doReceiveQmNumber(CommunicationEvent event) {
				answerText.setText(Integer.toString(RxTx.communication.receiveQmNumber()));
			}
		});

		byte[] data = new byte[5];
		data[0] = RxTx.KEY_RECEIVE_ID;
		data[1] = 0x01;
		data[2] = 0x02;
		data[3] = 0x03;
		data[4] = 0x04;
		RxTx.communication.addReceiveBuff(data);
		data = new byte[6];
		data[0] = 0x05;
		data[1] = 0x06;
		data[2] = 0x07;
		data[3] = 0x08;
		data[4] = 0x09;
		data[5] = RxTx.DATA_END;
		RxTx.communication.addReceiveBuff(data);
//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
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
					feedbackLabel.setText("You have to type in your QM ID now.");
				} else if ((FormatCheck.isID(answerText.getText()) == 0)) {
					answerText.setText("");
					feedbackLabel.setText("Not in correct format!");
				} else if (!UserManage.login(Integer.parseInt(answerText.getText()))) {
					answerText.setText("");
					feedbackLabel.setText("You haven't registered yet!");
				} else {
					//登录成功
					answerText.setText("");
					feedbackLabel.setText("Please type in your QM ID.");
					Windows.goToPanel(userPanel);
				}
			}
		}
	}

	class FeedbackPanel extends JPanel {
		FeedbackPanel() {
			feedbackLabel = new JLabel("Please type in your QM ID.");
			feedbackLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));

			this.setLayout(new GridLayout(2, 1));
			this.add(feedbackLabel);
		}
	}


}
