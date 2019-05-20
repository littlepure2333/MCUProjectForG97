package views;

import views.components.GotoButton;

import javax.swing.*;

import bin.AppState;
import views.components.PanelStateMonitor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserPanel extends JPanel implements PanelStateMonitor {
    private BorrowPanel borrowPanel = new BorrowPanel();
    private ReturnPanel returnPanel = new ReturnPanel();
    private MyAccountPanel myAccountPanel = new MyAccountPanel();
    private AlreadyHaveOnePanel alreadyHaveOnePanel = new AlreadyHaveOnePanel();
    private NotHaveOnePanel notHaveOnePanel = new NotHaveOnePanel();
    private HaveFinePanel haveFinePanel = new HaveFinePanel();

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

    class MainPanel extends JPanel implements ActionListener{

        MainPanel(){
            JButton takeButton = new JButton("TAKE");
            JButton returnButton = new JButton("RETURN");
            GotoButton accountButton = new GotoButton("MY ACCOUNT", myAccountPanel);
            JButton checkStationButton = new JButton("CHECK STATION");

            takeButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            accountButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            checkStationButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));

            takeButton.addActionListener(this);
            returnButton.addActionListener(this);

            this.setLayout(new GridLayout(1,3));
            this.add(takeButton);
            this.add(returnButton);
            this.add(accountButton);
            this.add(checkStationButton);
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
        }
       
    }
}