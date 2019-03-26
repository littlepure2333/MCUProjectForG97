package views;

import bin.UserManage;
import views.components.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterInputFrame extends JFrame{
	JFrame myFrame2;//The general frame

	/* ！
	 * 从组件库中获取没有事件监听器的普通组件，
	 * 现在已经拥有了单独的类名，
	 * 它们都位于views.components中，
	 * 如果需要创建新的组件，可以参照API文档在组件库中
	 * 创建并继承相应的原型
	*/
	IdPanel idPanel;
	NamePanel namePanel;
	AddPanel addPanel;

	//！checkPanel也没有监听器
	//  可能需要大量复用（没有改动）
	JPanel checkPanel;
	JPanel emptyPanel1;
//	JPanel emptyPanel2;
	JPanel submitPanel;
	JPanel buttonPanel;
	
	JTextField idText;
	JTextField nameText;
	JTextField addText;

	JLabel checkLabel;
	
	
	RegisterInputFrame(){
		this.setTitle("Please register a new user.");
		
		emptyPanel1=new EmptyPanel();

		/* ！
		 * 这是原来的三个输入Panel
		 * 对应的class被移出该类
		 * new ??Panel(text) -> text为提示标签的文字
		 * xxPanel.bindTextField() -> 将Panel里面的文本框的引用传给该界面，便于Listener控制
		 */
		idPanel = new IdPanel("ID:                 ");
		idText = idPanel.bindTextField();
		namePanel = new NamePanel("Full Name:     ");
		nameText = namePanel.bindTextField();
		addPanel = new AddPanel("Email Add:     ");
		addText = addPanel.bindTextField();

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
		this.setSize(1200, 1000);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
		
	}

	class SubmitPanel extends JPanel implements ActionListener {
		SubmitPanel() {
			JButton submitButton = new JButton("Submit");
			submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 50));

			this.add(submitButton);
			submitButton.addActionListener(this);
		}

		/*	！
		 * 	这里对于数据格式的检查方法已经写入一个了单独的类
		 *	views.FormatCheck
		 *	其中的方法均是静态类型，不需要创建实例
		 *	检查格式的功能不变
		 */
		public void actionPerformed(ActionEvent e) {
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
				// Login Successful
				if (UserManage.registration(Integer.parseInt(idText.getText()), nameText.getText(), addText.getText())) {
					this.registerSuccess();
				}
				// Duplicate
				else {
					checkLabel.setText("Duplicate qmNumber or Email!");
				}
			}
		}

		/*	！
		 *	这里是第一次迭代界面与后台交互的部分（注册成功）
		 */
		void registerSuccess() {
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
	
	class CheckPanel extends JPanel {
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
			if(actionCommand.equals("Clear")) {
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
