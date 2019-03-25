package views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.*; 

public class ManagerFrame extends JFrame{
	
	JPanel mainPanel;
	JPanel emptyPanel1;
	JPanel emptyPanel2;
	
	ManagerFrame(){
		
		this.setTitle("Welcome! Dear manager!");
		mainPanel=new MainPanel();
		emptyPanel1=new EmptyPanel();
		emptyPanel2=new EmptyPanel();
		
		this.add(emptyPanel1);
		this.add(mainPanel);
		this.add(emptyPanel2);
		
		
		this.setLayout(new GridLayout(3,1));
		this.setSize(1200, 1000);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	
	}
	
	class EmptyPanel extends JPanel{
		
	}
	
	class MainPanel extends JPanel implements ActionListener{
		
		MainPanel(){
			JButton registerButton=new JButton("Register");
			JButton userButton=new JButton("User Information");
			JButton stationButton=new JButton("Station Information");
			this.setLayout(new GridLayout(1,3));
			registerButton.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			userButton.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			stationButton.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
			this.add(registerButton);
			this.add(userButton);
			this.add(stationButton);
			
			registerButton.addActionListener(this);
			
			
		}
		
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			if(actionCommand=="Register") {
				JFrame registerInputFrame = new RegisterInputFrame();
				//registerInputFrame.setSize(1000, 800);
				//registerInputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				registerInputFrame.setVisible(true);
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		ManagerFrame my=new ManagerFrame();

	}
	
}
