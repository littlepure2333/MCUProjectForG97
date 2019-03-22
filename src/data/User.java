package data;

public class User implements java.io.Serializable{
    @SuppressWarnings("WeakerAccess")
    public int qmNumber;
    @SuppressWarnings("WeakerAccess")
    public String fullName;
    @SuppressWarnings("WeakerAccess")
    public String emailAddress;
    @SuppressWarnings("WeakerAccess")
    public boolean needToPay;

    public int getQmNumber() {
        return qmNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isNeedToPay() {
        return needToPay;
    }

    void setQmNumber(int qmNumber) {
        this.qmNumber = qmNumber;
    }

    void setFullName(String fullName) {
        this.fullName = fullName;
    }

    void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setNeedToPay(boolean needToPay) {
        this.needToPay = needToPay;
    }

   public User(int qmNumber, String fullName, String emailAddress) {
        this.qmNumber = qmNumber;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.needToPay = false;
    }

   public User() { }
}
