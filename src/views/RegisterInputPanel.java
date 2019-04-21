package views;

import bin.UserManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class RegisterInputPanel extends JPanel {

	private JTextField idText;
	private JTextField nameText;
	private JTextField addText;
	private JLabel checkLabel;
	
	
	RegisterInputPanel() {

		JPanel idPanel = new IdPanel();
		JPanel namePanel = new NamePanel();
		JPanel addPanel = new AddPanel();
		JPanel submitPanel = new SubmitPanel();
		JPanel checkPanel = new CheckPanel();
		JPanel buttonPanel = new ButtonPanel();
		
		this.add(new JPanel());
		this.add(idPanel);
		this.add(namePanel);
		this.add(addPanel);
		this.add(submitPanel);
		this.add(checkPanel);
		this.add(buttonPanel);
		
		this.setLayout(new GridLayout(7,1));
		this.setVisible(true);
		
	}
	
	class IdPanel extends JPanel{
		IdPanel(){
			JLabel idLabel=new JLabel("ID:                 ");
			idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			idText=new JTextField(15);
			idText.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
		
			this.add(idLabel);
			this.add(idText);
		}
	}
	
	class NamePanel extends JPanel{
		NamePanel(){
			JLabel nameLabel=new JLabel("Full Name:     ");
			nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			nameText=new JTextField(15);
			nameText.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			
			this.add(nameLabel);
			this.add(nameText);
		}
	}
	
	class AddPanel extends JPanel{
		AddPanel(){
			JLabel addLabel=new JLabel("Email Add:     ");
			addLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			addText=new JTextField(15);
			addText.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			
			this.add(addLabel);
			this.add(addText);
		}
	}
	
	class SubmitPanel extends JPanel implements ActionListener{
		SubmitPanel(){
			JButton submitButton=new JButton("Submit");
			submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 50)); 
			
			this.add(submitButton);
			submitButton.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e){
			if (idText.getText().length()==0) {
				checkLabel.setText("You haven't entered the ID!");
			} 
			else if(FormatCheck.isID(idText.getText())==0) {
				checkLabel.setText("Invalid ID. You must enter 9 digits!");
			}
			else if(nameText.getText().length()==0) {
				checkLabel.setText("You haven't entered the full name!");
			}
			else if(FormatCheck.isName(nameText.getText())==0) {
				checkLabel.setText("<html>Invalid name.<br/> Example: Xiaoming Wang</html>");
			}
			else if(addText.getText().length()==0) {
				checkLabel.setText("You haven't entered the email address!");
			}
			else if(FormatCheck.isAddress(addText.getText())==0) {
				checkLabel.setText("<html>Invalid email address. <br/>Example:qmul123_uk@qmul.ac.uk.</html>");
			}
			else {
				if(!UserManage.registration(Integer.parseInt(idText.getText()), nameText.getText(), addText.getText())) {
					checkLabel.setText("Duplicate ID or email address!");
				}
				else {
					clear();
					Windows.backToMenu();
					registerReminder();
				}
			}
		}

		private void registerReminder() {
			JFrame littleFrame =new JFrame("Successful");
			JLabel littleLabel=new JLabel("Successful Input!");
			littleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			littleFrame.add(littleLabel);
			littleFrame.setSize(500, 500);
			littleFrame.setVisible(true);
		}

	}
	
	class CheckPanel extends JPanel{
		CheckPanel(){
			checkLabel=new JLabel("Please enter info.");
			this.add(checkLabel);
			checkLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
			
		}
	}
	
	class ButtonPanel extends JPanel implements ActionListener{
		ButtonPanel(){
			JButton clearButton=new JButton("Clear");
			clearButton.setFont(new Font("Times New Roman", Font.PLAIN, 50)); 
			this.add(clearButton);
			clearButton.addActionListener(this);

		}
		
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Clear")) {
    			clear();
    		}
		}
	}
	
	private void clear() {
		idText.setText("");
		nameText.setText("");
		addText.setText("");
		checkLabel.setText("");
	}

}
