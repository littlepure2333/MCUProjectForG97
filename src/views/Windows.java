package views;

import bin.State;

import javax.swing.*;
import java.awt.*;

import java.util.Stack;

public class Windows {
    static final JFrame frame = new JFrame("QM scooter system");

    //panels
    //所有界面添加到此处
    private static RegisterInputPanel registerInputPanel = new RegisterInputPanel();
    private static BorrowAndReturnPanel borrowAndReturnPanel = new BorrowAndReturnPanel();
    private static UserLoginPanel userLoginPanel = new UserLoginPanel(borrowAndReturnPanel);
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

    static void stationChoose() {
        borrowAndReturnPanel.stateChanged();
    }

}
