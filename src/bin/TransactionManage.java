package bin;

import data.AppData;
import data.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 事务管理操作
 */
public class TransactionManage extends AppData {
    // how long should the user return the scooter at most (in seconds)
    private static final long SINGLE_TIME = 10;
    private static final int NOT_EXPIRED = 0;
    private static final int SINGLE_EXPIRED = 1;
    private static final int TOTAL_EXPIRED = 2;

    /**
     * 根据现有的用户，scooter，当前时间生成一条transaction
     * 需要scooter还在用户手上
     */
    static void generateTransaction(String type) {
        Date time = new Date();
        Transaction transaction = new Transaction(AppState.getCurrentUser().getQmNumber(), type, AppState.getCurrentUser().getScooter().getId(), time);
        transactions.add(transaction);
        if (transaction.getType().equals("return"))
            AppState.setCurrentTransaction(transaction);
        updateData();
    }

    /**
     * 根据transaction检查当前用户是否超时
     * 先判断单次使用是否超时
     */
    public static int checkIfExpired() {
        if (!ifSingleTimeExpired()) {
            AppState.getCurrentUser().setNeedToPay("true");
            updateData();
            return SINGLE_EXPIRED;
        }
        if (!ifTotalExpired()) {
            AppState.getCurrentUser().setNeedToPay("true");
            updateData();
            return TOTAL_EXPIRED;
        }
        return NOT_EXPIRED;
    }

    /**
     * （未实现）输出所有的transaction
     */
    public static void allTransactions() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).toString());
        }
    }

    private static boolean ifSingleTimeExpired() {
        ArrayList<Transaction> usageList = findTransactionsByUser();
        Transaction lastTakeTransaction = usageList.get(usageList.size() - 1);
        Date lastTakeTime = lastTakeTransaction.time;
        // Calculate how long does the user return the scooter this time (in minutes)
        long singleTime = (AppState.getCurrentTransaction().time.getTime() - lastTakeTime.getTime())/1000;
        return singleTime <= SINGLE_TIME;
    }

    private static ArrayList<Transaction> findTransactionsByUser() {
        ArrayList<Transaction> usageList = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (transaction.getQmNumber() == AppState.getCurrentUser().getQmNumber())
                usageList.add(transaction);
        }
        return usageList;
    }

    private static boolean ifTotalExpired() {
        ArrayList<Transaction> usageList = findTransactionsByUser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        long totalDiff = 0;
        Transaction takeTransaction = null;

        for (Transaction transaction: usageList) {
            if (dateFormat.format(transaction.getTime()).equals(dateFormat.format(new Date()))) {
                if (transaction.getType().equals("return"))
                    return false;
                else {
                    if (transaction.getType().equals("take")) {
                        takeTransaction = transaction;
                        continue;
                    }
                    if (transaction.getType().equals("return")) {
                        assert takeTransaction != null;
                        long diff = (transaction.getTime().getTime() - takeTransaction.getTime().getTime())/1000;
                        totalDiff += diff;
                    }
                }
            }
        }
        return totalDiff > 30;
    }


}
