package views;

import views.components.ReturnButton;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Boundary Class
 * The entrance of opening GUI
 * Monitor the state of the frame and every panels
 */
public class Windows {
    public static JFrame frame = new JFrame("QM scooter system");
    public static JPanel upperPanel = new JPanel();
    public static Stack<JPanel> stack = new Stack<>();
    private static IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel();

    /**
     * Create the frame, adding the first panel into the frame as the menu
     */
    public Windows() {
        init();
        frame.add(identityChoosePanel, BorderLayout.CENTER);
        stack.push(identityChoosePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Frame settings
     */
    private static void init() {
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(new ReturnButton("return"), BorderLayout.WEST);
        upperPanel.setVisible(false);
        frame.add(upperPanel, BorderLayout.NORTH);
    }

    /**
     * Reset the current panel to the menu
     */
    static void backToMenu() {
        Windows.frame.remove(Windows.stack.peek());
        Windows.stack = new Stack<>();

        Windows.frame.add(identityChoosePanel);
        Windows.stack.push(identityChoosePanel);
        Windows.upperPanel.setVisible(false);

        Windows.frame.validate();
        Windows.frame.repaint();
    }

    /**
     * Change the current panel to the selected panel
     *
     * @param nextPanel the panel that program will change to
     * @param <T>       panel class
     */
    public static <T extends JPanel> void goToPanel(T nextPanel) {
        Windows.frame.remove(Windows.stack.peek());
        Windows.stack.push(nextPanel);
        Windows.frame.add(nextPanel);
        Windows.upperPanel.setVisible(true);

        Windows.frame.validate();
        Windows.frame.repaint();
    }
}
