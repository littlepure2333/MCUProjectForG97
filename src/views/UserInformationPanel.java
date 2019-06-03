package views;

import bin.TransactionManage;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;

/**
 * Boundary Class
 * This is the panel for displaying the user information.
 * It will display all the event created by the system and its detail information.
 */
class 	UserInformationPanel extends JPanel implements PanelStateMonitor {
	/**
	 * Create the panel.
	 */
	UserInformationPanel() {
	}

	/**
	 * Load the data and refresh the view
	 */
	@Override
	public void update() {
		this.removeAll();
		String[] columnNames = {"Time", "UserId", "Name", "Type", "ScooterID", "StationName"};
		String[][] data = TransactionManage.getAllTransactions();
		JTable table = new JTable(data, columnNames);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(10, 263, 310, -300);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));

		this.add(scrollPane);
	}
}
