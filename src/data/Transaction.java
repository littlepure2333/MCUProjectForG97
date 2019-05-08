package data;

import java.util.Date;

public class Transaction {
    public int qmNumber;
    public String type;
    public int scooterId;
    public Date time;

    public Transaction() { }

    public Transaction(int qmNumber, String type,int scooterId, Date time) {
        this.qmNumber = qmNumber;
        this.type = type;
        this.scooterId = scooterId;
        this.time = time;
    }

    public int getQmNumber() {
        return qmNumber;
    }

    public String getType() {
        return type;
    }

    public int getScooterId() {
        return scooterId;
    }

    public Date getTime() {
        return time;
    }

}
