package bin;

import data.AppData;
import data.Transaction;

import java.util.Date;

/**
 * 事务管理操作
 */
public class TransactionManage extends AppData {
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
     */
    public static boolean checkIfExpired() {
        return false;
    }

    /**
     * （未实现）输出所有的transaction
     */
    public static void outputAllTransactions() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).toString());
        }
    }
}
