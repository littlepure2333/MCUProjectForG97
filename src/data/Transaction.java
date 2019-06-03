package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entity class used to present transactions
 * Transaction is used to record who did what in where at when
 */
public class Transaction {

    /**
     * user's number
     */
    public int qmNumber;
    /**
     * user name
     */
    public String name;
    /**
     * type of transaction (take/return)
     */
    public String type;
    /**
     * station name
     */
    public String stationName;
    /**
     * scooter id
     */
    public int scooterId;
    /**
     * transaction time
     */
    public Date time;

    public Transaction() {
    }

    /**
     * Create a transaction with user and station data
     *
     * @param qmNumber    user's number
     * @param name        user name
     * @param type        type of transaction (take/return)
     * @param stationName station name
     * @param scooterId   scooter id
     * @param time        transaction time
     */
    public Transaction(int qmNumber, String name, String type, String stationName, int scooterId, Date time) {
        this.qmNumber = qmNumber;
        this.name = name;
        this.type = type;
        this.stationName = stationName;
        this.scooterId = scooterId;
        this.time = time;
    }

    /**
     * Get transaction's user number
     *
     * @return user number
     */
    public int getQmNumber() {
        return qmNumber;
    }

    /**
     * Get transaction's type
     *
     * @return transaction type
     */
    public String getType() {
        return type;
    }

    /**
     * Get transaction's time
     *
     * @return transaction data
     */
    public Date getTime() {
        return time;
    }

    /**
     * Output the transaction data
     *
     * @return transaction data
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String time = dateFormat.format(this.time);
        return time + " " + qmNumber + " " + name + " " + type + " " + scooterId + " " + stationName;
    }

    /**
     * get the actual date
     *
     * @return the actual date
     */
    public String getActualDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(getTime());
    }
}
