package views;

import bin.AppState;
import bin.ScooterManage;
import bin.StationManage;
import views.components.EmptySlot;
import views.components.OccupiedSlot;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boundary Class
 * It is the panel for user to borrow a scooter.
 * It will display 8 pic in representation of the 8 slot.
 * And a button will be displayed to help the user pick one.
 */
public class BorrowPanel extends JPanel implements PanelStateMonitor {
	private static JPanel[] slotPanel;
	private static JLabel myLabel = new JLabel();
	private static JLabel selectLabel = new JLabel();
	private static JButton helpButton;
	private JPanel submitPanel = new SubmitPanel();
	private static JPanel subPanel;

	/**
	 * The constructor of BorrowPanel.
	 */
	BorrowPanel() {
		JPanel upperPanel = new UpperPanel();
		slotPanel = new JPanel[8];
		for (int i = 0; i <= 7; i++)
			slotPanel[i] = new EmptySlot();
		subPanel = new SubPanel();
		this.setLayout(new GridLayout(3, 1));
		this.add(upperPanel);
		this.add(subPanel);
		this.add(submitPanel);

		this.setVisible(true);
	}

	/**
	 * The modification of the background information of the interface will directly influence the display of slot in the interface.
	 * The station must be determined in advance, this class will not call the method.
	 * Update the text information.
	 * Apply to the initial visit to this interface or visit again.
	 */
	@Override
	public void update() {
		refresh();

		if (!checkIsEmpty()) {
			myLabel.setText("Preparing for your scooter......\r\n");
			selectLabel.setText("Please use the one with flashing......");
			helpButton.setText("Help me pick one");
		} else {
			myLabel.setText("No scooter in this station!\r\n");
			selectLabel.setText("Please check other station!");
			helpButton.setText("");
		}
	}

	/**
	 * Read the slot data from the background and set the picture.
	 */
	private static void refresh() {
		for (int i = 0; i <= 7; i++) {
			if (AppState.getCurrentStation().getSlot()[i] == null)
				slotPanel[i] = new EmptySlot();
			else slotPanel[i] = new OccupiedSlot();
		}
		subPanel.removeAll();
		for (int i = 0; i <= 7; i++)
			subPanel.add(slotPanel[i]);
		Windows.frame.validate();
		Windows.frame.repaint();
	}

	/**
	 * check if the station is empty
	 *
	 * @return true-empty，false-not empty
	 */
	private boolean checkIsEmpty() {
		for (int i = 0; i <= 7; i++) {
			if (AppState.getCurrentStation().getSlot()[i] != null) {
				return false;
			}
		}
		return true;
	}

	class UpperPanel extends JPanel {
		UpperPanel() {
			myLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			selectLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

			this.setLayout(new GridLayout(3, 1));
			this.add(myLabel);
			this.add(selectLabel);

		}
	}


	class SubPanel extends JPanel {
		SubPanel() {
			this.setLayout(new GridLayout(1, 8));

			for (int i = 0; i <= 7; i++)
				this.add(slotPanel[i]);
		}
	}

	static class SubmitPanel extends JPanel implements ActionListener {
		int site;

		SubmitPanel() {
			helpButton = new JButton("Help me pick one");
			this.setLayout(new GridLayout(2, 1));
			helpButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			this.add(new JLabel(""));
			this.add(helpButton);
			helpButton.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			/*
			remind
			 */
			if (actionCommand.equals("Help me pick one")) {
				//creating thread
				Thread thread = new Thread(new WaitForBorrow());
				helpButton.setText("Pick");
				//find a scooter
				for (site = 0; site <= 7; site++) {
					if (AppState.getCurrentStation().getSlot()[site] != null) {
						StationManage.chooseFlashSlot(site);
						break;
					}
				}
				thread.start();
			}
			/*
			take
			 */
			if (actionCommand.equals("Pick")) {
				ScooterManage.takeScooter();
				myLabel.setText("You have borrowed a scooter!\r\n");
				selectLabel.setText("Enjoy your scoo-life!");
				helpButton.setText("Click here to log out");
				WaitForBorrow.abort();
			}
			/*
			finish
			 */
			if (actionCommand.equals("Click here to log out")) {
				Windows.backToMenu();
			}
			/*
			expired
			 */
		}

		static class WaitForBorrow implements Runnable {
			private static int i;
			private static final int WAIT_TIME = 60;

			@Override
			public void run() {
				waitForSec(WAIT_TIME);
			}

			private void waitForSec(int sec) {
				for (i = 0; i < 100; i++) {
					setSlotViewOccupied();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setSlotViewOccupiedFlash();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == (sec - 1)) {
						myLabel.setText("Time expired\r\n");
						selectLabel.setText("Please return to previous page ");
						helpButton.setText("Time expired");

						return;
					}
				}
				refresh();
			}

			static void abort() {
				i = 100;
			}
		}


		/**
		 * Modify the target slot's pic to have-car-no-light
		 */
		private static void setSlotViewOccupied() {
			JPanel slot = slotPanel[AppState.getCurrentSlot()];
			ImageIcon image = new ImageIcon("./media/scooter.jpg");
			image.setImage(image.getImage().getScaledInstance(slot.getWidth(), slot.getHeight(), Image.SCALE_AREA_AVERAGING));
			slot.getGraphics().drawImage(image.getImage(), 0, 0, slot);
		}

		/**
		 * Modify the target slot's pic to have-car-have-light
		 */
		private static void setSlotViewOccupiedFlash() {
			JPanel slot = slotPanel[AppState.getCurrentSlot()];
			ImageIcon image = new ImageIcon("./media/scooterflash.jpg");
			image.setImage(image.getImage().getScaledInstance(slot.getWidth(), slot.getHeight(), Image.SCALE_AREA_AVERAGING));
			slot.getGraphics().drawImage(image.getImage(), 0, 0, slot);
		}
	}
}


