package views;

import bin.UserManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserLoginPanel extends JPanel{
	private JTextField answerText;
	private JLabel feedbackLabel;
	private JPanel borrowAndReturnPanel;

	UserLoginPanel(JPanel borrowAndReturnPanel) {
		this.borrowAndReturnPanel = borrowAndReturnPanel;

		JPanel myPanel = new MyPanel();
		JPanel feedbackPanel = new FeedbackPanel();

		this.setLayout(new GridLayout(3,1));
		this.add(feedbackPanel);
		this.add(myPanel);
		this.add(new JPanel());

		this.setVisible(true);
	}

    
    class MyPanel extends JPanel implements ActionListener {
    	MyPanel() {
    		JButton submitButton=new JButton("Submit");
        	submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			submitButton.addActionListener(this);

			answerText=new JTextField(15);
			answerText.setFont(new Font("Times New Roman", Font.PLAIN, 40));

			this.setLayout(new GridLayout(2,1));
    		this.add(answerText);
        	this.add(submitButton);
    	}
    	

    	public void actionPerformed(ActionEvent e){
    		String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Submit")) {
				if (answerText.getText().length()==0) {
					answerText.setText("");
					feedbackLabel.setText("You have to type in your QM ID now.");
				} 
				else if((FormatCheck.isID(answerText.getText())==0)) {
					answerText.setText("");
					feedbackLabel.setText("Not in correct format!");
				}
				else if(!UserManage.login(Integer.parseInt(answerText.getText()))) {
					answerText.setText("");
					feedbackLabel.setText("You haven't registered yet!");
				}
				else {
					//登录成功

					/*
					 * temporary 修改站点mode并且跳转
					 * 需要进行修改
					 */
					Windows.changeStationView("borrow");
					answerText.setText("");
					feedbackLabel.setText("Please type in your QM ID.");
					jumpToNext();
				}
			}
    	}

		/**
		 * 修改站点的mode并跳转
		 * 临时方法 功能修改后需要删除
		 */
		private void jumpToNext() {
			Windows.frame.remove(Windows.stack.peek());
			Windows.stack.push(borrowAndReturnPanel);
			Windows.frame.add(borrowAndReturnPanel);
			Windows.returnPanel.setVisible(true);

			//重绘界面
			Windows.frame.validate();
			Windows.frame.repaint();
		}
    }
    
    class FeedbackPanel extends JPanel{
    	FeedbackPanel(){
    		feedbackLabel=new JLabel("Please type in your QM ID.");
    		feedbackLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));

        	this.setLayout(new GridLayout(2,1));
			this.add(feedbackLabel);
		}
    }
    

}
