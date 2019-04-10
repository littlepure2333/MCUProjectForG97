package views;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Windows {
    static final JFrame frame = new JFrame("QM scooter system");

    //panels
    //所有界面添加到此处
    private RegisterInputPanel registerInputPanel = new RegisterInputPanel();
    private BorrowPanel borrowPanel = new BorrowPanel();
    private ManagerPanel managerPanel = new ManagerPanel(registerInputPanel);
    private IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel(managerPanel, borrowPanel);
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
}
