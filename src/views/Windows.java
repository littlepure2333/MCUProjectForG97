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

    //return
    static JPanel returnPanel = new JPanel();
    //stack
    static Stack<JPanel> stack = new Stack<>();



    public Windows() {

        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        returnPanel.setLayout(new BorderLayout());
        returnPanel.add(new ReturnButton("return"), BorderLayout.WEST);
        returnPanel.setVisible(false);
        frame.add(returnPanel, BorderLayout.NORTH);

        frame.add(identityChoosePanel, BorderLayout.CENTER);
        stack.push(identityChoosePanel);

    }
}
