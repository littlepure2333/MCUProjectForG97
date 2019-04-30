package views;

import bin.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BorrowAndReturnPanel extends JPanel implements ComponentState {
	/**
	 * 用于表示界面目前的模式（借/还）
	 */
	String mode;

	private JPanel[] slotPanel;
	private JLabel myLabel = new JLabel();
	private JLabel selectLabel = new JLabel();
	private JPanel subPanel;

	BorrowAndReturnPanel(){
		JPanel upperPanel = new UpperPanel();
		slotPanel = new JPanel[8];
		for(int i=0;i<=7;i++)
			slotPanel[i] = new EmptySlotPanel();
		JPanel submitPanel = new SubmitPanel();
		subPanel = new SubPanel();

		this.setLayout(new GridLayout(3,1));
		this.add(upperPanel);
		this.add(subPanel);
		this.add(submitPanel);
		
		this.setVisible(true);
	}

	/**
	 * 借/还车界面后台信息的改动
	 * 直接影响界面内槽位的显示
	 * 站点必须在之前已经指定好，本类不要调用该方法
	 */
	@Override
	public void stateChanged() {
		for (int i=0;i<=7;i++) {
			if (State.getCurrentStation().slot[i] == null)
				slotPanel[i] = new EmptySlotPanel();
			else slotPanel[i] = new OccupiedSlotPanel();
		}
	}

	/**
	 * 根据借/还切换文本信息
	 *
	 * （未实现）还需要根据借/还替换按钮功能（或是替换按钮本身）
	 */
	@Override
	public void update() {
		subPanel.removeAll();
		for (int i=0;i<=7;i++)
			subPanel.add(slotPanel[i]);

		if (this.mode.equals("borrow")) {
			if (!checkIsEmpty()) {
				myLabel.setText("Preparing for your scooter......\r\n");
				selectLabel.setText("Please use the one with flashing......");
			}
			else {
				myLabel.setText("No scooter in this station!\r\n");
				selectLabel.setText("Please check other station!");
			}
		}
		else if (this.mode.equals("return")) {
			if (!checkIsFull()) {
				myLabel.setText("Ready for return your scooter......\r\n");
				selectLabel.setText("Please use the one with flashing......");
			}
			else {
				myLabel.setText("No available slot in this station!\r\n");
				selectLabel.setText("Please check other station!");
			}
		}
	}

	/**
	 * （未实现）
	 * 检查站点是否为空
	 * @return true-空，false-非空
	 */
	private boolean checkIsEmpty() {
		return false;
	}

	/**
	 * （未实现）
	 * 检查站点是否为满
	 * @return true-全满，false-有空位
	 */
	private boolean checkIsFull() {
		return false;
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
		SubPanel() {
			this.setLayout(new GridLayout(1,8));

			for (int i=0;i<=7;i++)
				this.add(slotPanel[i]);
		}
	}
	
	class SubmitPanel extends JPanel implements ActionListener{
		JButton helpButton;
		SubmitPanel() {
			helpButton=new JButton("Help me pick one");
			this.setLayout(new GridLayout(2,1));
			helpButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			this.add(new JLabel(""));
			this.add(helpButton);
			helpButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e){
			String actionCommand = e.getActionCommand();
			helpButton.setText("Pick");
			if(actionCommand.equals("Help me pick one")) {
				int i=0;
				//int check;
				for(i=0;i<20;i++) {
					if(i%2==0) {
						setSlotViewOccupiedFlash(slotPanel[0]);
						
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

		/**
		 * 将目标槽位的图片修改为：null-无灯
		 * @param slotPanel 目标槽位
		 */
		private void setSlotViewEmpty(JPanel slotPanel) {
			ImageIcon image = new ImageIcon("./media/null.jpg");
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			slotPanel.getGraphics().drawImage(image.getImage(),0,0,slotPanel);
		}

		/**
		 * 将目标槽位的图片修改为：有车-无灯
		 * @param slotPanel 目标槽位
		 */
		private void setSlotViewOccupied(JPanel slotPanel) {
			ImageIcon image = new ImageIcon("./media/scooter.jpg");
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			slotPanel.getGraphics().drawImage(image.getImage(),0,0,slotPanel);
		}

		/**
		 * 将目标槽位的图片修改为：有车-有灯
		 * @param slotPanel 目标槽位
		 */
		private void setSlotViewOccupiedFlash(JPanel slotPanel) {
			ImageIcon image = new ImageIcon("./media/scooterflash.jpg");
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			slotPanel.getGraphics().drawImage(image.getImage(),0,0,slotPanel);
		}

		/*
		缺少null-无灯的显示图片
		 */
		private void setSlotViewEmptyFlash(JPanel slotPanel) {
			ImageIcon image = new ImageIcon();
			image.setImage(image.getImage().getScaledInstance(slotPanel.getWidth(), slotPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
			slotPanel.getGraphics().drawImage(image.getImage(),0,0,slotPanel);
		}
	}

}
