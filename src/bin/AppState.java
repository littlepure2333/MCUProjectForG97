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

    /**
     * Get the current user data
     *
     * @return current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Get the current station data
     *
     * @return current station
     */
    public static Station getCurrentStation() {
        return currentStation;
    }

    /**
     * Get the current flashing slot data
     *
     * @return current flashing slot
     */
    public static int getCurrentSlot() {
        return currentSlot;
    }

    /**
     * Get the current transaction data
     *
     * @return current transaction
     */
    public static Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    /**
     * Set the current user
     *
     * @param currentUser current user
     */
    static void setCurrentUser(User currentUser) {
        AppState.currentUser = currentUser;
    }

    /**
     * Set the current station
     *
     * @param currentStation current station
     */
    static void setCurrentStation(Station currentStation) {
        AppState.currentStation = currentStation;
    }

    /**
     * Set the current flashing slot
     *
     * @param currentSlot current flashing slot
     */
    static void setCurrentSlot(int currentSlot) {
        AppState.currentSlot = currentSlot;
    }

    /**
     * Set the current transaction
     *
     * @param currentTransaction current transaction
     */
    static void setCurrentTransaction(Transaction currentTransaction) {
        AppState.currentTransaction = currentTransaction;
    }
}
