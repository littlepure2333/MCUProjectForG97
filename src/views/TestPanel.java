package views;

import bin.State;
import bin.StationManage;

import javax.swing.*;
import java.awt.*;

class TestPanel {

    private static RegisterInputPanel registerInputPanel = new RegisterInputPanel();
    private static BorrowAndReturnPanel borrowAndReturnPanel = new BorrowAndReturnPanel();
    private static UserLoginPanel userLoginPanel = new UserLoginPanel();
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
        JPanel testPanel = borrowAndReturnPanel;     //在这里填入测试用的panel
        frame.add(testPanel,BorderLayout.CENTER);
    }

    /**
     * 借/还页的条件
     * 1.选定站点
     * 2.mode(借/还)
     */
    private void setState() {
        State.setCurrentStation(StationManage.findStationById(1));
        Windows.stationView("borrow");

    }
}
