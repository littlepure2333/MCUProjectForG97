package views;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterInputPanel extends JPanel {
	JFrame myFrame2;//The general frame
	JPanel idPanel;
	JPanel namePanel;
	JPanel addPanel;
	JPanel emptyPanel1;
//	JPanel emptyPanel2;
	JPanel submitPanel;
	JPanel checkPanel;
	JPanel buttonPanel;
	
	JTextField idText;
	JTextField nameText;
	JTextField addText;
	JLabel checkLabel;
	
	
	RegisterInputPanel(){
//		this.setTitle("Please register a new user.");
		
		emptyPanel1=new EmptyPanel();
		idPanel=new IdPanel();
		namePanel=new NamePanel();
		addPanel=new AddPanel();
//		emptyPanel2=new EmptyPanel();
		submitPanel=new SubmitPanel();
		checkPanel=new CheckPanel();
		buttonPanel=new ButtonPanel();
		
		this.add(emptyPanel1);
		this.add(idPanel);
		this.add(namePanel);
		this.add(addPanel);
		this.add(submitPanel);
//		this.add(emptyPanel2);
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
				JFrame littleFrame =new JFrame("Successful");
				JLabel littleLabel=new JLabel("Successful Input.");
				littleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
				littleFrame.add(littleLabel);
				
				checkLabel.setText("Successful Input.");
				
				littleFrame.setSize(500, 500);
				//littleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				littleFrame.setVisible(true);
			}
		}

			
		
		
	}
	
	class CheckPanel extends JPanel{
		CheckPanel(){
			checkLabel=new JLabel("Please enter info.");
			this.add(checkLabel);
			//checkLabel.setSize(5, 100);
			checkLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50)); 
			
		}
	}
	
	class ButtonPanel extends JPanel implements ActionListener{
		ButtonPanel(){
			JButton clearButton=new JButton("Clear");
			clearButton.setFont(new Font("Times New Roman", Font.PLAIN, 50)); 
			this.add(clearButton);
			clearButton.addActionListener(this);
			
//			JButton backButton=new JButton("Back");
//			backButton.setFont(new Font("Times New Roman", Font.PLAIN, 50)); 
//			this.add(backButton);
//			backButton.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			if(actionCommand=="Clear") {
    			idText.setText("");
    			nameText.setText("");
    			addText.setText("");
    		}
			
//			if(actionCommand=="Back") {
//				
//    		}
		}
	}
	
	class EmptyPanel extends JPanel {
		
	}

}
