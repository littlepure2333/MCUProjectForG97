package bin;

import data.AppData;
import data.Transaction;
import data.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * 事务管理操作
 */
public class TransactionManage extends AppData {
    // how long should the user return the scooter at most (in seconds)
    public static final long SINGLE_TIME = 10;
    public static final int NOT_EXPIRED = 0;
    public static final int SINGLE_EXPIRED = 1;
    public static final int TOTAL_EXPIRED = 2;

    /**
     * 根据现有的用户，scooter，当前时间生成一条transaction
     * 需要scooter还在用户手上
     */
    static void generateTransaction(String type) {
        Date time = new Date();
        Transaction transaction = new Transaction(AppState.getCurrentUser().getQmNumber(), type, AppState.getCurrentUser().getScooter().getId(), time);
        transactions.add(transaction);
        updateData();
    }

    /**
     * （未实现）根据transaction检查当前用户是否超时
     * 先判断单次使用是否超时
     */
    public static int checkIfExpired() {
        if (checkIfExpiredSingleTime() == false) {
            return SINGLE_EXPIRED;
        }
        if (oneDayExpired() == false) {
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

    public static boolean checkIfExpiredSingleTime() {
        ArrayList<Transaction> usageList = findTransactionsByUser();
        Transaction lastTakeTransaction = usageList.get(usageList.size() - 1);
        Date lastTakeTime = lastTakeTransaction.time;
        // Calculate how long does the user return the scooter this time (in minutes)
        long singleTime = (AppState.getCurrentTransaction().time.getTime() - lastTakeTime.getTime())/1000;
        if (singleTime > SINGLE_TIME) {
            return false;
        }
        else return true;
    }

    public static ArrayList<Transaction> findTransactionsByUser () {
        ArrayList<Transaction> usageList = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (transaction.getQmNumber() == AppState.getCurrentUser().getQmNumber())
                usageList.add(transaction);
        }
        return usageList;
    }

    public static boolean oneDayExpired() {
        //todo not finish
        return false;
    }

}
