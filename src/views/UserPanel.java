package views;

import bin.AppState;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boundary Class
 * The Panel to be displayed after a user logged in successfully.
 * The user can choose to borrow, return a scooter or to check his account.
 */
class UserPanel extends JPanel implements PanelStateMonitor {
    private BorrowPanel borrowPanel = new BorrowPanel();
    private ReturnPanel returnPanel = new ReturnPanel();
    private MyAccountPanel myAccountPanel = new MyAccountPanel();
    private AlreadyHaveOnePanel alreadyHaveOnePanel = new AlreadyHaveOnePanel();
    private NotHaveOnePanel notHaveOnePanel = new NotHaveOnePanel();
    private HaveFinePanel haveFinePanel = new HaveFinePanel();

    /**
     * The constructor of UserPanel
     */
    UserPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.add(new JPanel());
        this.add(new MainPanel());
        this.add(new JPanel());

        this.setVisible(true);
    }

    @Override
    public void update() {

    }

    class MainPanel extends JPanel implements ActionListener {
        MainPanel() {
            JButton takeButton = new JButton("TAKE");
            JButton returnButton = new JButton("RETURN");
            JButton accountButton = new JButton("MY ACCOUNT");

            takeButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            accountButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));

            takeButton.addActionListener(this);
            returnButton.addActionListener(this);
            accountButton.addActionListener(this);

            this.setLayout(new GridLayout(1, 3));
            this.add(takeButton);
            this.add(returnButton);
            this.add(accountButton);
        }

        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals("TAKE")) {
                if (AppState.getCurrentUser().getScooter() == null) {
                    if (AppState.getCurrentUser().isNeedToPay().equals("true")) {
                        Windows.goToPanel(haveFinePanel);
                    } else {
                        borrowPanel.update();
                        Windows.goToPanel(borrowPanel);
                    }
                } else {
                    Windows.goToPanel(alreadyHaveOnePanel);
                }
            }
            if (actionCommand.equals("RETURN")) {
                if (AppState.getCurrentUser().getScooter() == null) {
                    Windows.goToPanel(notHaveOnePanel);
                } else {
                    returnPanel.update();
                    Windows.goToPanel(returnPanel);
                }
            }
            if (actionCommand.equals("MY ACCOUNT")) {
                myAccountPanel.update();
                Windows.goToPanel(myAccountPanel);
            }
        }
    }
}