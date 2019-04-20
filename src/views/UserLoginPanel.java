package views;

import bin.UserManage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserLoginPanel extends JPanel{
	private JPanel borrowAndReturnPanel;
	private JTextField answertext;
	private JLabel feedbackLabel;

	UserLoginPanel(JPanel borrowAndReturnPanel){
		this.borrowAndReturnPanel = borrowAndReturnPanel;
		JPanel myPanel = new MyPanel();
		JPanel feedbackPanel = new FeedbackPanel();
		
		this.add(feedbackPanel);
		this.add(myPanel);
		this.add(new JPanel());
		
		this.setLayout(new GridLayout(3,1));
		this.setVisible(true);
		
	}

    
    class MyPanel extends JPanel implements ActionListener{
    	MyPanel(){
    		JButton submitButton=new JButton("Submit");
        	submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 40)); 
        	answertext=new JTextField(15);
        	answertext.setFont(new Font("Times New Roman", Font.PLAIN, 40));
     
        	
        	submitButton.addActionListener(this);
        	
    		this.add(answertext);
        	this.add(submitButton);
        	this.setLayout(new GridLayout(2,1));
        	
        	
    	}
    	

    	public void actionPerformed(ActionEvent e){
    		String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Submit")) {
				if (answertext.getText().length()==0) {
					feedbackLabel.setText("You have to type in your QM ID now.");
				} 
				else if((FormatCheck.isID(answertext.getText())==0)) {
					feedbackLabel.setText("Not in correct format!");
				}
				else if(!UserManage.qmIsExist(Integer.parseInt(answertext.getText()))) {
					feedbackLabel.setText("You haven't registered yet!");
				}
				else {

				}
			}
    	}
    }
    
    class FeedbackPanel extends JPanel{
    	FeedbackPanel(){
    		feedbackLabel=new JLabel("Please type in your QM ID.");
    		this.add(feedbackLabel);
    		feedbackLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        	this.setLayout(new GridLayout(2,1));
    		
    	}
    }
    

}
