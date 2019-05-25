package bin;

import data.AppData;
import data.User;

/**
 * Control Class
 * Manage the transmission, validation, queries of user data
 */
public class UserManage extends AppData {

    /**
     * Register a new user
     * @param qmNumber QM number input by user
     * @param fullName full name input by user
     * @param email    email input by user
     * @return true: successful registration, false: registration failed
     */
    public static boolean registration(int qmNumber, String fullName, String email) {
        if (!isDuplicate(qmNumber, email)) {
            users.add(new User(qmNumber, fullName, email));
            updateData();
            return true;
        }
        return false;
    }

    /**
     * Check if QM number and email address are duplicate with the database information
     * @param qmNumber the QM number
     * @param email    the email address
     * @return true - duplicate, false - not duplicate
     */
    static boolean isDuplicate(int qmNumber, String email) {
        for (User user : users) {
            if (qmNumber == user.getQmNumber())
                return true;
            if (email.equals(user.getEmail()))
                return true;
        }
        return false;
    }

    /**
     * User log in
     * @param qmNumber the QM number
     * @return true - log in success, false - log in failure
     */
    public static boolean login(int qmNumber) {
        for (User user : users) {
            if (qmNumber == user.getQmNumber()) {
                AppState.setCurrentUser(user);
                return true;
            }
        }
        return false;
    }

    /**
     * Pay the fine
     */
    public static void payTheFine() {
        AppState.getCurrentUser().setNeedToPay("false");
        updateData();
    }
}
