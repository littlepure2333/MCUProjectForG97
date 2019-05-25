package views;

import bin.AppState;
import bin.TransactionManage;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;

class ReportPanel extends JPanel implements PanelStateMonitor {

	private UsagePanel usagePanel = new UsagePanel();
	private JLabel greetingLabel = new JLabel();

	ReportPanel() {
		JPanel emailPanel = new EmailPanel();

		this.setLayout(new BorderLayout());
		this.add(emailPanel, BorderLayout.NORTH);
		this.add(usagePanel, BorderLayout.CENTER);

		this.setVisible(true);
	}


	class EmailPanel extends JPanel {
		EmailPanel() {
			greetingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			this.add(greetingLabel);
		}
	}

	class UsagePanel extends JPanel {
		UsagePanel() {

		}
	}

	@Override
	public void update() {
		greetingLabel.setText("<html><body>Dear Mr/Mrs: " +
				AppState.getCurrentUser().getFullName()+ "<br><br>" +
				"Here is your scooter weekly usage report. Thank you for supporting us.<br>" +
				"If you have any questions,please contact us by 9348-3527.<br></body></html>");

		usagePanel.removeAll();
		String[] columnNames = {"Time", "Qm number", "Name", "Type", "ScooterID", "StationName"};
		String[][] data = TransactionManage.getUserTransactions(AppState.getCurrentUser().getQmNumber());

		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(10, 263, 300, -200);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 500));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		usagePanel.add(scrollPane);

	}

}
