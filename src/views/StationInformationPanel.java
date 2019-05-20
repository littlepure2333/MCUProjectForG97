package views;

import views.components.PanelStateMonitor;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class StationInformationPanel extends JPanel implements PanelStateMonitor {

	/**
	 * Create the panel.
	 */
	StationInformationPanel() {
		this.setLayout(new GridLayout(2,1));
	}

	/**
	 * Load the data and refresh the view
	 */
	@Override
	public void update() {
		String[] columnNames = {"Station","Occupied","Unoccupied","Total"};
		Object[][] data={ {"A","1","2","3"},
				{"B","1","2","3"},
				{"C","1","2","3"},};
		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(10, 263, 310, -300);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(5000, 5000));

		JLabel lblNewLabel = new JLabel("8 scooters in use.",JLabel.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		add(scrollPane,BorderLayout.CENTER);
		add(lblNewLabel);
	}
}
