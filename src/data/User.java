package data;

/**
 * Entity class used to present users
 */
public class User {
    /**
     * user number
     */
    public int qmNumber;
    /**
     * user's full name
     */
    public String fullName;
    /**
     * user email
     */
    public String email;
    /**
     * if user need to pay
     */
    public String needToPay;
    /**
     * scooter data (if user holds one)
     */
    public Scooter scooter;

    public User() {
    }

    /**
     * Create a user with data
     *
     * @param qmNumber user number
     * @param fullName user's full name
     * @param email    user email
     */
    public User(int qmNumber, String fullName, String email) {
        this.qmNumber = qmNumber;
        this.fullName = fullName;
        this.email = email;
        this.needToPay = "false";
        this.scooter = null;
    }

    /**
     * Get user number
     *
     * @return user number
     */
    public int getQmNumber() {
        return this.qmNumber;
    }

    /**
     * Get user's full name
     *
     * @return user's full name
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Get user email
     *
     * @return user email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get user's scooter
     *
     * @return user's scooter
     */
    public Scooter getScooter() {
        return this.scooter;
    }

    /**
     * Check if user need to pay
     *
     * @return true: need to pay, false: do not need
     */
    public String isNeedToPay() {
        return this.needToPay;
    }

    /**
     * Change user's payment state
     *
     * @param needToPay true: need to pay, false: do not need
     */
    public void setNeedToPay(String needToPay) {
        this.needToPay = needToPay;
    }

    /**
     * the user take a scooter from a station
     *
     * @param scooter the scooter
     */
    public void takeScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    /**
     * the user return a scooter to a station
     *
     * @return the scooter
     */
    public Scooter returnScooter() {
        Scooter scooter = this.scooter;
        this.scooter = null;
        return scooter;
    }

}
