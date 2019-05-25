package bin;

import data.AppData;
import data.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Control Class
 *
 */
public class TransactionManage extends AppData {
    // how long should the user return the scooter at most (in seconds)
    /**
     * the single use time of a user (in seconds)
     */
    private static final int SINGLE_TIME = 10;
    /**
     * the total use time of a user (in seconds)
     */
    private static final int TOTAL_TIME = 30;
    /**
     * the value transmit to the interface which means the user's usage did not expired
     */
    private static final int NOT_EXPIRED = 0;
    /**
     * the value transmit to the interface which means the user's usage got a single expired
     */
    private static final int SINGLE_EXPIRED = 1;
    /**
     * the value transmit to the interface which means the user's usage got a total expired
     */
    private static final int TOTAL_EXPIRED = 2;

    /**
     * Generate a transaction based on current user, scooter and time
     * The transaction can only created when the user is holding the scooter
     */
    static void generateTransaction(String type) {
        Date time = new Date();
        Transaction transaction =
                new Transaction(
                        AppState.getCurrentUser().getQmNumber(),
                        AppState.getCurrentUser().getFullName(),
                        type,
                        AppState.getCurrentStation().getName(),
                        AppState.getCurrentUser().getScooter().getId(),
                        time
                );
        transactions.add(transaction);
        if (transaction.getType().equals("return"))
            AppState.setCurrentTransaction(transaction);
        updateData();
    }

    /**
     * Check if the user's usage has expired by analyzing it's transactions
     * @return 0: not expired, 1: single usage expired, 2: total usage expired
     */
    public static int checkIfExpired() {
        if (ifSingleTimeExpired()) {
            AppState.getCurrentUser().setNeedToPay("true");
            updateData();
            return SINGLE_EXPIRED;
        }
        if (ifTotalExpired()) {
            AppState.getCurrentUser().setNeedToPay("true");
            updateData();
            return TOTAL_EXPIRED;
        }
        return NOT_EXPIRED;
    }

    /**
     * Check if the used have got a single time expiry
     * @return true: single time expired, false: not single time expired
     */
    private static boolean ifSingleTimeExpired() {
        ArrayList<Transaction> usageList = findTransactionsByUser();
        Transaction lastTakeTransaction = usageList.get(usageList.size() - 2);
        Date lastTakeTime = lastTakeTransaction.time;
        long singleTime = (AppState.getCurrentTransaction().time.getTime() - lastTakeTime.getTime()) / 1000;
        return singleTime > SINGLE_TIME;
    }

    /**
     * Check if the used have got a total time expiry
     * @return true: total time expired, false: not total time expired
     */
    private static boolean ifTotalExpired() {
        ArrayList<Transaction> usageList = findTransactionsByUser();
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        long totalDiff = 0;
        Transaction takeTransaction = null;

        //if a "take" not on today and the next "return" is today
        for (int i = 0; i < usageList.size(); i ++) {
            if (!usageList.get(i).getActualDate().equals(today)&& usageList.get(i+1).getActualDate().equals(today)) {
                long diff = (usageList.get(i+1).getTime().getTime() - usageList.get(i).getTime().getTime());
                totalDiff += diff;
            }
        }
        //normal condition
        for (Transaction transaction : usageList) {
            if (transaction.getActualDate().equals(today)) {
                if (transaction.getType().equals("take")) {
                    takeTransaction = transaction;
                    continue;
                }
                if (transaction.getType().equals("return")) {
                    assert takeTransaction != null;
                    long diff = (transaction.getTime().getTime() - takeTransaction.getTime().getTime()) / 1000;
                    totalDiff += diff;
                }
            }
        }
        return totalDiff > TOTAL_TIME;
    }

    /**
     * Output all transactions of the current user
     * @return All the transactions of the current user, ordered by transaction time
     */
    private static ArrayList<Transaction> findTransactionsByUser() {
        ArrayList<Transaction> usageList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getQmNumber() == AppState.getCurrentUser().getQmNumber())
                usageList.add(transaction);
        }
        return usageList;
    }

    /**
     * Output all transactions
     * @return All transaction information to be displayed on the interface
     */
    public static String[][] getAllTransactions() {
        ArrayList<String[]> column = new ArrayList<>();
        int rowSize = 0;
        for (Transaction transaction : transactions) {
            String[] row = transaction.toString().split(" ");
            column.add(row);
            rowSize = row.length;
        }
        String[][] allTransactions = new String[column.size()][rowSize];
        for (int i = 0; i < column.size(); i++) {
            allTransactions[i] = column.get(i);
        }
        return allTransactions;
    }

}
