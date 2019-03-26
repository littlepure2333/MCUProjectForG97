package data;

public class User {
    @SuppressWarnings("WeakerAccess")
    public int qmNumber;
    @SuppressWarnings("WeakerAccess")
    public String fullName;
    @SuppressWarnings("WeakerAccess")
    public String email;
    @SuppressWarnings("WeakerAccess")
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
