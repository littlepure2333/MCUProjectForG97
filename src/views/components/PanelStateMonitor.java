package views.components;

/**
 * Boundary Class.
 * Allow the implemented classes to update their content
 */
public interface PanelStateMonitor {
    /**
     * Before entering the stateful panel, load the data and refresh the panel
     */
    void update();
}
