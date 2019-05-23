package views.components;

/**
 * 实现了这个接口的界面需要在一定的条件下更新界面前台/后台的内容
 */
public interface PanelStateMonitor {
    /**
     * For entering the stateful panel, load the data and refresh the panel
     */
    void update();
}
