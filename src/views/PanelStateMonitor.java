package views;

/**
 * 实现了这个接口的界面需要在一定的条件下更新界面前台/后台的内容
 *
 * 本类不要访问接口的方法
 */
interface PanelStateMonitor {
    /**
     * 界面后台信息（状态）的修改
     */
    void stateChanged();

    /**
     * 界面组件的重新渲染
     */
    void update();
}
