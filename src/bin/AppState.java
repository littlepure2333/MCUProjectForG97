package bin;

import data.Station;
import data.Transaction;
import data.User;

/**
 * Control Class.
 * Background state in parallel with the front GUI.
 * Keep temporary information to be used in front GUI.
 * Front GUI can only access this class, can not modify this class.
 */
public class AppState {
    /**
     * The current login user
     */
    private static User currentUser;
    /**
     * The current located station
     */
    private static Station currentStation;
    /**
     * The current flashing slot, numbered from 0.
     * Can be used for take or return a scooter
     */
    private static int currentSlot;
    /**
     * the transaction currently used for checking if time expired
     */
    private static Transaction currentTransaction;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Station getCurrentStation() {
        return currentStation;
    }

    public static int getCurrentSlot() {
        return currentSlot;
    }

    public static Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    static void setCurrentUser(User currentUser) {
        AppState.currentUser = currentUser;
    }

    static void setCurrentStation(Station currentStation) {
        AppState.currentStation = currentStation;
    }

    static void setCurrentSlot(int currentSlot) {
        AppState.currentSlot = currentSlot;
    }

    static void setCurrentTransaction(Transaction currentTransaction) {
        AppState.currentTransaction = currentTransaction;
    }
}
