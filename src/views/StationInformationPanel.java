package views;

import bin.ScooterManage;
import bin.StationManage;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;

/**
 * Boundary Class
 * Class StationInformationPanel.
 * It is the panel for displaying the station info.
 */
public class StationInformationPanel extends JPanel implements PanelStateMonitor {
	/**
	 * Create the panel.
	 */
	StationInformationPanel() {
		this.setLayout(new GridLayout(2, 1));
	}

	/**
	 * Load the data and refresh the view
	 */
	@Override
	public void update() {
		this.removeAll();
		String[] columnNames = {"Station", "Occupied", "Unoccupied", "Total Slots"};
		String[][] data = StationManage.outputAllStations();
		JTable table = new JTable(data, columnNames);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(10, 263, 310, -300);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(5000, 5000));

		JLabel lblNewLabel = new JLabel("In use: " + ScooterManage.getUsedCount() +
				((ScooterManage.getUsedCount()==1|| ScooterManage.getUsedCount()==0)?" scooter":" scooters")
				, JLabel.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		add(scrollPane, BorderLayout.CENTER);
		add(lblNewLabel);
	}
}
