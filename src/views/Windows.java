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
    private static UserLoginPanel userLoginPanel = new UserLoginPanel(borrowAndReturnPanel);    //临时路径（需要修改）
    private static StationPanel stationPanel = new StationPanel(userLoginPanel);
    private static ManagerPanel managerPanel = new ManagerPanel(registerInputPanel);
    private static IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel(managerPanel, stationPanel);
    //


    //return panel - 应用于所有的界面
    static JPanel returnPanel = new JPanel();

    //stack - 实现返回键功能
    static Stack<JPanel> stack = new Stack<>();

    public Windows() {

        /*
        frame的样式，不要修改
         */
        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        /*
        界面上方返回按钮的样式，不要修改
         */
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
    static void changeStationView(String mode) {
        borrowAndReturnPanel.mode = mode;
        borrowAndReturnPanel.stateChanged();
        borrowAndReturnPanel.update();
    }

    /**
     * 回到主页
     * 注意对于没有实现 ComponentState 的界面
     * 在调用这个方法前，需要将标签和输入框重置
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
