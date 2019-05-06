package views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserPanel extends JPanel{
    private JPanel borrowAndReturnPanel;
    private JPanel myAccountPanel;

    UserPanel(JPanel borrowAndReturnPanel,JPanel myAccountPanel){
        this.borrowAndReturnPanel = borrowAndReturnPanel;
        this.myAccountPanel = myAccountPanel;

        this.setLayout(new GridLayout(3,1));
        this.add(new JPanel());
        this.add(new MainPanel());
        this.add(new JPanel());

        this.setVisible(true);
    }
    class MainPanel extends JPanel implements ActionListener{
        MainPanel(){
            GotoButton takeButton=new GotoButton("TAKE", borrowAndReturnPanel);
            GotoButton returnButton=new GotoButton("RETURN",borrowAndReturnPanel);
            GotoButton accountButton=new GotoButton("MY ACCOUNT",myAccountPanel);
            JButton checkstationButton=new JButton("CHECK STATION");

            takeButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            accountButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            checkstationButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));

            takeButton.addActionListener(this);
            returnButton.addActionListener(this);

            this.setLayout(new GridLayout(1,3));
            this.add(takeButton);
            this.add(returnButton);
            this.add(accountButton);
            this.add(checkstationButton);
        }

        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand();
            // do some other actions
        }
    }
}