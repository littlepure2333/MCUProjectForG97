package views;

import bin.AppState;
import bin.ScooterManage;
import bin.StationManage;
import bin.TransactionManage;
import views.components.EmptySlot;
import views.components.OccupiedSlot;
import views.components.PanelStateMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnPanel extends JPanel implements PanelStateMonitor {
    private static JPanel[] slotPanel;
    private static JLabel myLabel = new JLabel();
    private static JLabel selectLabel = new JLabel();
    private static JButton helpButton;
    private static JPanel subPanel;

    ReturnPanel() {
        JPanel upperPanel = new UpperPanel();
        slotPanel = new JPanel[8];
        for (int i = 0; i <= 7; i++)
            slotPanel[i] = new OccupiedSlot();
        subPanel = new SubPanel();

        this.setLayout(new GridLayout(3, 1));
        this.add(upperPanel);
        this.add(subPanel);
        JPanel submitPanel = new SubmitPanel();
        this.add(submitPanel);

        this.setVisible(true);
    }

    /**
     * 界面后台信息的改动
     * 直接影响界面内槽位的显示
     * 站点必须在之前已经指定好，本类不要调用该方法
     * 更新文本信息
     * 适用于初次/再次进入该界面
     */
    @Override
    public void update() {
        /*
		从后台读取slot数据并设置图片
		 */
        refresh();
        /*
		预判断slot整体情况
		 */
        if (!checkIsFull()) {
            myLabel.setText("Ready for return your scooter......\r\n");
            selectLabel.setText("Please use the one with flashing......");
            helpButton.setText("Help me pick a empty slot");
        } else {
            myLabel.setText("No available slot in this station!\r\n");
            selectLabel.setText("Please check other station!");
            helpButton.setText("");
        }
    }

    private static void refresh() {
        for (int i = 0; i <= 7; i++) {
            if (AppState.getCurrentStation().getSlot()[i] != null)
                slotPanel[i] = new OccupiedSlot();
            else slotPanel[i] = new EmptySlot();
        }
        subPanel.removeAll();
        for (int i = 0; i <= 7; i++)
            subPanel.add(slotPanel[i]);
        Windows.frame.validate();
        Windows.frame.repaint();
    }

    /**
     * 检查站点是否为满
     *
     * @return true-全满，false-有空位
     */
    public static boolean checkIsFull() {
        for (int i = 0; i <= 7; i++) {
            if (AppState.getCurrentStation().getSlot()[i] == null) {
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
            helpButton = new JButton("Help me pick a empty slot");
            this.setLayout(new GridLayout(2, 1));
            helpButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            this.add(new JLabel(""));
            this.add(helpButton);
            helpButton.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            /*
			提示阶段
			 */
            if (actionCommand.equals("Help me pick a empty slot")) {
                //创建线程
                Thread thread = new Thread(new WaitForReturn());
                helpButton.setText("Return");
                //从左到右找到一个空车位
                for (site = 0; site <= 7; site++) {
                    if (AppState.getCurrentStation().getSlot()[site] == null) {
                        StationManage.chooseFlashSlot(site);
                        break;
                    }
                }
                thread.start();
            }
            //return the scooter
            if (actionCommand.equals("Return")) {
                ScooterManage.returnScooter();
                myLabel.setText("You have returned your scooter!\r\n");
                helpButton.setText("Click here to log out");
                WaitForReturn.abort();
                switch (TransactionManage.checkIfExpired()) {
                    case 0:
                        selectLabel.setText("Thank you for using!");
                        break;
                    case 1:
                        selectLabel.setText("You have expired the usage time! (30min per using)");
                        break;
                    case 2:
                        selectLabel.setText("You have expired the usage time! (2h per day)");
                        break;
                }
            }
            //complete the return
            if (actionCommand.equals("Click here to log out")) {
                Windows.backToMenu();
            }
            //return time expired
        }

        /*
        闪光线程
         */
        static class WaitForReturn implements Runnable {
            private static int i;

            @Override
            public void run() {
                waitForSec(5);
            }

            @SuppressWarnings("Duplicates")
            private void waitForSec(int sec) {
                for (i = 0; i < 100; i++) {
                    setSlotViewEmpty();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setSlotViewEmptyFlash();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == (sec-1)) {
                        myLabel.setText("Time expired\r\n");
                        selectLabel.setText("Please return to previous page");
                        helpButton.setText("Time expired");

                        Windows.frame.validate();
                        Windows.frame.repaint();
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
         * 将目标槽位的图片修改为：null-无灯
         */
        private static void setSlotViewEmpty() {
            JPanel slot = slotPanel[AppState.getCurrentSlot()];
            ImageIcon image = new ImageIcon("./media/null.jpg");
            image.setImage(image.getImage().getScaledInstance(slot.getWidth(), slot.getHeight(), Image.SCALE_AREA_AVERAGING));
            slot.getGraphics().drawImage(image.getImage(), 0, 0, slot);
        }

        /**
         * 将目标槽位的图片修改为：无车-有灯
         */
        private static void setSlotViewEmptyFlash() {
            JPanel slot = slotPanel[AppState.getCurrentSlot()];
            ImageIcon image = new ImageIcon("./media/nullflash.jpg");
            image.setImage(image.getImage().getScaledInstance(slot.getWidth(), slot.getHeight(), Image.SCALE_AREA_AVERAGING));
            slot.getGraphics().drawImage(image.getImage(), 0, 0, slot);
        }
    }
}
