package views;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Windows {
    static final JFrame fr = new JFrame("aa");
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JButton goto2 = new GotoButton("go2", p2);
    JButton goto3 = new GotoButton("go3", p3);

    static Stack<JPanel> stack = new Stack<>();

    public static void main(String[] args) {
        Windows win = new Windows();
    }

    private Windows() {
        fr.setSize(500,500);
        p1.setLayout(new GridLayout(3,1));
        p2.setLayout(new GridLayout(3,1));
        p3.setLayout(new GridLayout(3,1));
        fr.add(p1);
        p1.add(goto2);
        p1.add(new JPanel());
        p1.add(new JPanel());

        p2.add(new ReturnButton("ret"));
        p2.add(goto3);
        p2.add(new JPanel());

        p3.add(new ReturnButton("ret"));
        p3.add(new JPanel());
        p3.add(new JPanel());

        fr.setVisible(true);
        stack.push(p1);
    }
}
