package views;

import javax.swing.*;
import java.awt.*;

class TestPanel {

    private RegisterInputPanel registerInputPanel = new RegisterInputPanel();
    private BorrowAndReturnPanel borrowAndReturnPanel = new BorrowAndReturnPanel();
    private ManagerPanel managerPanel = new ManagerPanel(registerInputPanel);
    private IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel(managerPanel, borrowAndReturnPanel);

    private JFrame frame = new JFrame();

    public static void main(String[] args) {
        TestPanel t = new TestPanel();
        t.test();
    }
    void test() {
        //style
        frame.setSize(1200, 1000);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JPanel testPanel = borrowAndReturnPanel;     //在这里填入测试用的panel
        frame.add(testPanel,BorderLayout.CENTER);
    }
}
