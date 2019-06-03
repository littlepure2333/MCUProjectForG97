import data.AppData;
import views.Windows;

/**
 * The main class of the program
 */
public class ScooterSystem {

    /**
     * The main method of the program.
     * First load data from local files, then initiate the GUI
     *
     * @param args input arguments for opening program
     */
    public static void main(String[] args) {
        new AppData();
        new Windows();
    }
}