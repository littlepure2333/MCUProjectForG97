package views;

import bin.ScooterManage;
import bin.StationManage;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

class TestPanel {

    private static RegisterInputPanel registerInputPanel = new RegisterInputPanel();
    private static BorrowPanel borrowPanel = new BorrowPanel();
    private static UserLoginPanel userLoginPanel = new UserLoginPanel(borrowPanel);    //临时路径（需要修改）
    private static StationPanel stationPanel = new StationPanel(userLoginPanel);
    private static ManagerPanel managerPanel = new ManagerPanel(registerInputPanel);
    private static IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel(managerPanel, stationPanel);

    private JFrame frame = new JFrame();

    public static void main(String[] args) {
        TestPanel t = new TestPanel();
        t.test();
    }

    private void test() {
        //style
        frame.setSize(1200, 1000);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        setState();
        JPanel testPanel = borrowPanel;     //在这里填入测试用的panel
        frame.add(testPanel,BorderLayout.CENTER);
    }

    /**
     * 借/还页的条件
     * 1.选定站点
     * 2.初始化视图
     */
    private void setState() {
        StationManage.chooseStation(1);
        borrowPanel.update();
    }


}
