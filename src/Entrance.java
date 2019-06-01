import data.AppData;
import mcu.RxTx;
import views.Windows;

public class Entrance {

    public static void main(String[] args) {
        //Database initialization
        new AppData();
//        //RxTx initialization
//        new RxTx("COM8");
        //GUI
        new Windows();
    }
}