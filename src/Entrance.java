import data.AppData;
import views.Windows;

public class Entrance {

    public static void main(String[] args) {
        //Database initialization
        new AppData();
        //GUI
        new Windows();
    }
}