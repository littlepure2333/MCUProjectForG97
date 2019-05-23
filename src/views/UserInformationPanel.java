package views;

import views.components.PanelStateMonitor;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.*;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

class UserInformationPanel extends JPanel implements PanelStateMonitor {
	/**
	 * Create the panel.
	 */
	UserInformationPanel() {
	}

	/**
	 * 界面组件的重新渲染
	 */
	@Override
	public void update() {
		this.removeAll();
		String[] columnNames = {"Name", "Take Time", "Take Station", "Return Time", "Return Station"};
		String[][] data = {{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"},
				{"1", "2", "3", "4", "4"}};
		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(10, 263, 310, -300);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));
		this.add(scrollPane);
	}
}
