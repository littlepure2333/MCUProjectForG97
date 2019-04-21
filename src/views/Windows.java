package views;

import javax.swing.*;
import java.awt.*;

import java.util.Stack;

public class Windows {
    static final JFrame frame = new JFrame("QM scooter system");

    //panels
    //所有界面添加到此处
    private static RegisterInputPanel registerInputPanel = new RegisterInputPanel();
    private static BorrowAndReturnPanel borrowAndReturnPanel = new BorrowAndReturnPanel();
    private static UserLoginPanel userLoginPanel = new UserLoginPanel();
    private static StationPanel stationPanel = new StationPanel(userLoginPanel);
    private static ManagerPanel managerPanel = new ManagerPanel(registerInputPanel);
    private static IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel(managerPanel, stationPanel);
    //


    //return panel - 应用于所有的界面
    static JPanel returnPanel = new JPanel();

    //stack - 实现返回键功能
    static Stack<JPanel> stack = new Stack<>();

    public Windows() {

        //frame style
        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //return panel style
        returnPanel.setLayout(new BorderLayout());
        returnPanel.add(new ReturnButton("return"), BorderLayout.WEST);
        returnPanel.setVisible(false);
        frame.add(returnPanel, BorderLayout.NORTH);

        frame.add(identityChoosePanel, BorderLayout.CENTER);
        stack.push(identityChoosePanel);
    }

    /**
     * 修改借车还车界面的内容和功能
     * @param mode 界面模式（借/还）
     */
    static void stationView(String mode) {
        borrowAndReturnPanel.mode = mode;
        borrowAndReturnPanel.stateChanged();
        borrowAndReturnPanel.update();
    }

    /**
     * 回到主页
     */
    static void backToMenu() {
        Windows.frame.remove(Windows.stack.peek());
        Windows.stack.empty();

        Windows.frame.add(identityChoosePanel);
        Windows.stack.push(identityChoosePanel);
        Windows.returnPanel.setVisible(false);

        //重绘界面
        Windows.frame.validate();
        Windows.frame.repaint();
    }

}
