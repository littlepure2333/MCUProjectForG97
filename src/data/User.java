package data;

public class User {
    public int qmNumber;
    public String fullName;
    public String email;
    public String needToPay;
    public Scooter scooter;

    public User() { }

    public User(int qmNumber, String fullName, String email) {
        this.qmNumber = qmNumber;
        this.fullName = fullName;
        this.email = email;
        this.needToPay = "false";
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

    /* this user borrow a scooter from one station */
    public void borrowScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    /* this user return the scooter to one station */
    public Scooter returnScooter() {
        Scooter scooter = this.scooter;
        this.scooter = null;
        return scooter;
    }

}
