package data;

/**
 * Entity class used to present users
 */
public class User {
    public int qmNumber;
    public String fullName;
    public String email;
    public String needToPay; // "true" - need to pay a fine, "false" - no fine
    public Scooter scooter;

    public User() {
    }

    public User(int qmNumber, String fullName, String email) {
        this.qmNumber = qmNumber;
        this.fullName = fullName;
        this.email = email;
        this.needToPay = "false";
        this.scooter = null;
    }

    public int getQmNumber() {
        return this.qmNumber;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public Scooter getScooter() {
        return this.scooter;
    }

    public String isNeedToPay() {
        return this.needToPay;
    }

    public void setNeedToPay(String needToPay) {
        this.needToPay = needToPay;
    }

    /**
     * the user take a scooter from a station
     * @param scooter the scooter
     */
    public void takeScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    /**
     * the user return a scooter to a station
     * @return the scooter
     */
    public Scooter returnScooter() {
        Scooter scooter = this.scooter;
        this.scooter = null;
        return scooter;
    }

}
