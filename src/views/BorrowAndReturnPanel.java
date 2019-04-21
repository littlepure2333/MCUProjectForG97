package views;

import bin.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BorrowAndReturnPanel extends JPanel implements ComponentState {
	//state
	String mode;

	private JPanel[] slotPanel = new JPanel[8];
	private JLabel myLabel = new JLabel();
	private JLabel selectLabel = new JLabel();
	private JPanel subPanel;

	BorrowAndReturnPanel(){
		for(int i=0;i<=7;i++) {
			slotPanel[i] = new EmptySlotPanel();
		}
		JPanel upperPanel = new UpperPanel();

		JPanel submitPanel = new SubmitPanel();

		subPanel = new SubPanel();
		this.add(upperPanel);
		this.add(subPanel);
		this.add(submitPanel);
		
		this.setLayout(new GridLayout(3,1));
		this.setVisible(true);
		
	}

	@Override
	public void stateChanged() {
		for(int i=0;i<=7;i++) {
			if (State.getCurrentStation().slot[i]==null)
				slotPanel[i] = new EmptySlotPanel();
			else slotPanel[i] = new OccupiedSlotPanel();
		}
	}

	@Override
	public void update() {
		if(this.mode.equals("borrow")) {
			myLabel.setText("Preparing for your scooter......\r\n");
			selectLabel.setText("Please use the one with flashing......");
		}
		else if(this.mode.equals("return")) {
			myLabel.setText("Ready for return your scooter......\r\n");
			selectLabel.setText("Please use the one with flashing......");
		}

		subPanel.removeAll();
		for (int i=0;i<=7;i++)
			subPanel.add(slotPanel[i]);
	}

	class UpperPanel extends JPanel{
		UpperPanel() {
			myLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			selectLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.setLayout(new GridLayout(3,1));
			this.add(myLabel);
			this.add(selectLabel);

		}
	}
	
	class OccupiedSlotPanel extends JPanel{
		int state=1;
		public void paintComponent(Graphics g) {
			ImageIcon image =new ImageIcon("./media/scooter.jpg");
			image.setImage(image.getImage().getScaledInstance(this.getWidth(),this.getHeight(), 
					Image.SCALE_AREA_AVERAGING));
			g.drawImage(image.getImage(),0,0,this);
		}
		
	}
	class EmptySlotPanel extends JPanel{
		int state=0;
		public void paintComponent(Graphics g) {
			ImageIcon image =new ImageIcon("./media/null.jpg");
			image.setImage(image.getImage().getScaledInstance(this.getWidth(),this.getHeight(), 
					Image.SCALE_AREA_AVERAGING));
			g.drawImage(image.getImage(),0,0,this);
		}
		
	}
	
	class SubPanel extends JPanel{
		SubPanel(){
			this.setLayout(new GridLayout(1,8));

			for (int i=0;i<=7;i++)
				this.add(slotPanel[i]);
		}
	}
	
	class SubmitPanel extends JPanel implements ActionListener{
		JButton helpbutton;
		SubmitPanel(){
			helpbutton=new JButton("Help me pick one");
			this.setLayout(new GridLayout(2,1));
			helpbutton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			this.add(new JLabel(""));
			this.add(helpbutton);
			helpbutton.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			helpbutton.setText("Pick");
			if(actionCommand.equals("Help me pick one")) {
				int i=0;
				//int check;
				for(i=0;i<20;i++) {
					if(i%2==0) {
						setSlotViewFlash(slotPanel[0]);
						
						//System.out.println("0");
						long y=0x10008000l;
						for(;y>=0;y--) {
							
						}
					}
					else {
						setSlotViewOccupied(slotPanel[0]);
						
						//System.out.println("1");
						long y=0x10008000l;
						for(;y>=0;y--) {
							
						}
					}
				}
			}
			else if(actionCommand.equals("Pick")) {
				setSlotViewEmpty(slotPanel[0]);
			}
			
		}

		private void setSlotViewEmpty(JPanel slotPanel) {
			Graphics g=slotPanel.getGraphics();
			ImageIcon image =new ImageIcon("./media/null.jpg");
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			g.drawImage(image.getImage(),0,0,slotPanel);
		}

		private void setSlotViewOccupied(JPanel slotPanel) {
			Graphics g=slotPanel.getGraphics();
			ImageIcon image =new ImageIcon("./media/scooter.jpg");
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			g.drawImage(image.getImage(),0,0,slotPanel);
		}

		private void setSlotViewFlash(JPanel slotPanel) {
			Graphics g=slotPanel.getGraphics();
			ImageIcon image =new ImageIcon("./media/scooterflash.jpg");
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			g.drawImage(image.getImage(),0,0,slotPanel);
		}
	}

}
