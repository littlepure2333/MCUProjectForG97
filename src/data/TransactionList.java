package data;

import java.util.ArrayList;

public class TransactionList extends DataIO {
    public ArrayList<Transaction> transactionList;
    private static final String FILE_LOCATION = "./statics/transaction.xml";

    public TransactionList() {
        //noinspection unchecked
        this.transactionList = (ArrayList<Transaction>)read(FILE_LOCATION);
    }

    public ArrayList<Transaction> getList() {
        return transactionList;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
        save(transactionList, FILE_LOCATION);
    }

    public void resetList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
        save(transactionList, FILE_LOCATION);
    }

    public void updateList() {
        save(transactionList, FILE_LOCATION);
    }

}
