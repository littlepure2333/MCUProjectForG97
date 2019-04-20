package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BorrowAndReturnPanel extends JPanel implements ComponentState {


	JLabel findlabel;
	private JPanel slotpanel1;
	private JPanel slotpanel2;
	private JPanel slotpanel3;
	private JPanel slotpanel4;
	private JPanel slotpanel5;
	private JPanel slotpanel6;
	private JPanel slotpanel7;
	private JPanel slotpanel8;
	JPanel flashpanel;

	JLabel emptylabel;
	
	BorrowAndReturnPanel(){
		//The general frame
		JFrame myFrame = new JFrame("Station");

		//findlabel=new ;
		JPanel mypanel = new MyPanel();
		JPanel subpanel = new SubPanel();
		JPanel submitpanel = new SubmitPanel();
		//subpanel=new SubPanel();
		
		
		this.add(mypanel);
		this.add(subpanel);
		this.add(submitpanel);
		
		this.setLayout(new GridLayout(3,1));
		this.setVisible(true);
		
	}

	public void stateChanged() {

	}


	public void update() {

	}

	class MyPanel extends JPanel{
		MyPanel(){
			JLabel mylabel = new JLabel("Preparing for your scooter......\r\n");
			mylabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			JLabel selectlabel = new JLabel("Please use the one with flashing......");
			selectlabel.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
		
			this.setLayout(new GridLayout(3,1));
			this.add(mylabel);
			this.add(selectlabel);

		}
	}
	
	class SlotPanel extends JPanel{
		int state=1;
		public void paintComponent(Graphics g) {
			ImageIcon image =new ImageIcon("./media/scooter.jpg");
			image.setImage(image.getImage().getScaledInstance(this.getWidth(),this.getHeight(), 
					Image.SCALE_AREA_AVERAGING));
			g.drawImage(image.getImage(),0,0,this);
		}
		
	}
	class EmptyPanel extends JPanel{
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
			slotpanel1=new SlotPanel();
			slotpanel2=new SlotPanel();
			slotpanel3=new SlotPanel();
			slotpanel4=new EmptyPanel();
			slotpanel5=new SlotPanel();
			slotpanel6=new SlotPanel();
			slotpanel7=new EmptyPanel();
			slotpanel8=new EmptyPanel();
			
			this.setLayout(new GridLayout(1,8));
			
			this.add(slotpanel1);
			this.add(slotpanel2);
			this.add(slotpanel3);
			this.add(slotpanel4);
			this.add(slotpanel5);
			this.add(slotpanel6);
			this.add(slotpanel7);
			this.add(slotpanel8);
		}
	}
	
	class SubmitPanel extends JPanel implements ActionListener{
		JButton helpbutton;
		SubmitPanel(){
			helpbutton=new JButton("Help me pick one");
			this.setLayout(new GridLayout(2,1));
			emptylabel=new JLabel("");
			JButton pickbutton=new JButton("Pick");
			helpbutton.setFont(new Font("Times New Roman", Font.PLAIN, 40)); 
			this.add(emptylabel);			
			this.add(helpbutton);
			//this.add(pickbutton);
			helpbutton.addActionListener(this);
			//pickbutton.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			helpbutton.setText("Pick");
			if(actionCommand=="Help me pick one") {
				int i=0;
				//int check;
				for(i=0;i<20;i++) {
					if(i%2==0) {
						Graphics g=slotpanel1.getGraphics();
						ImageIcon image =new ImageIcon("./media/scooterflash.jpg");
						image.setImage(image.getImage().getScaledInstance(slotpanel1.getWidth(),slotpanel1.getHeight(),
						Image.SCALE_AREA_AVERAGING));
						g.drawImage(image.getImage(),0,0,slotpanel1);
						
						//System.out.println("0");
						long y=0x10008000l;
						for(;y>=0;y--) {
							
						}
					}
					else {
						Graphics g=slotpanel1.getGraphics();
						ImageIcon image =new ImageIcon("./media/scooter.jpg");
						image.setImage(image.getImage().getScaledInstance(slotpanel1.getWidth(),slotpanel1.getHeight(),
						Image.SCALE_AREA_AVERAGING));
						g.drawImage(image.getImage(),0,0,slotpanel1);
						
						//System.out.println("1");
						long y=0x10008000l;
						for(;y>=0;y--) {
							
						}
					}
						
				}
			}
			else if(actionCommand.equals("Pick")) {
				Graphics g=slotpanel1.getGraphics();
				ImageIcon image =new ImageIcon("./media/null.jpg");
				image.setImage(image.getImage().getScaledInstance(slotpanel1.getWidth(),slotpanel1.getHeight(),
				Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(),0,0,slotpanel1);
			}
			
		}
	}

}
