package data;

import java.util.Date;

public class Transaction {
    public int qmNumber;
    public int scooterId;
    public Date borrowDate;
    public Date returnDate;

    public Transaction() { }

    public Transaction(int qmNumber, int scooterId, Date borrowDate) {
        this.qmNumber = qmNumber;
        this.scooterId = scooterId;
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getQmNumber() {
        return qmNumber;
    }

    public int getScooterId() {
        return scooterId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
