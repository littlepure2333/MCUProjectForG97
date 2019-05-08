package views;

import bin.StationManage;
import views.components.ReturnButton;

import javax.swing.*;
import java.awt.*;

import java.util.Stack;

public class Windows {
    public static JFrame frame = new JFrame("QM scooter system");

    //第一个界面
    private static IdentityChoosePanel identityChoosePanel = new IdentityChoosePanel();

    //return panel - 应用于所有的界面
    public static JPanel upperPanel = new JPanel();

    //stack - 实现返回键功能
    public static Stack<JPanel> stack = new Stack<>();

    public Windows() {
        init();
        frame.add(identityChoosePanel, BorderLayout.CENTER);
        stack.push(identityChoosePanel);
    }

    /**
     * 窗口布局初始化
     */
    private static void init() {
        /*
        frame的样式，不要修改
         */
        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        /*
        界面上方返回按钮的样式，不要修改
         */
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(new ReturnButton("return"), BorderLayout.WEST);
        upperPanel.setVisible(false);
        frame.add(upperPanel, BorderLayout.NORTH);

    }

//    /**
//     * 刷新借/还车的界面信息
//     */
//    static void updateStationView() {
//        returnPanel.update();
//        borrowPanel.update();
//    }

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
        Windows.upperPanel.setVisible(false);

        //重绘界面
        Windows.frame.validate();
        Windows.frame.repaint();
    }

    /**
     * 界面测试类修改到此处
     *
     */
    static class PanelTest {
        static BorrowPanel testPanel = new BorrowPanel();             //测试用Panel写在这里
        public static void main(String[] args) {
            init();
            upperPanel.setVisible(true);
            upperPanel.add(new JLabel("单界面测试中,不要使用返回键"));
            setState();


            frame.add(testPanel, BorderLayout.CENTER);
            stack.push(testPanel);
        }

        /**
         * 借/还页测试专用方法
         * 1.选定站点
         * 2.初始化视图
         */
        private static void setState() {
            StationManage.chooseStation(1);
            testPanel.update();
        }
    }

}
