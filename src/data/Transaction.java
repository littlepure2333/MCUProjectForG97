package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    public int qmNumber;
    public String name;
    public String type;
    public String stationName;
    public int scooterId;
    public Date time;

    public Transaction() {
    }

    public Transaction(int qmNumber, String name, String type, String stationName, int scooterId, Date time) {
        this.qmNumber = qmNumber;
        this.name = name;
        this.type = type;
        this.stationName = stationName;
        this.scooterId = scooterId;
        this.time = time;
    }

    public int getQmNumber() {
        return qmNumber;
    }


    public String getType() {
        return type;
    }


    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String time = dateFormat.format(this.time);
        return time + " " + name + " " + type + " " + scooterId + " " + stationName;
    }

    public String getActualDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(getTime());
    }
}
