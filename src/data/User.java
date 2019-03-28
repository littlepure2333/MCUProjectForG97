package data;

public class User {

    public int qmNumber;

    public String fullName;

    public String email;

    public String needToPay;

    public int getQmNumber() {
        return qmNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String isNeedToPay() {
        return needToPay;
    }

   public User(int qmNumber, String fullName, String email) {
        this.qmNumber = qmNumber;
        this.fullName = fullName;
        this.email = email;
        this.needToPay = "false";
    }

   public User() { }
}
