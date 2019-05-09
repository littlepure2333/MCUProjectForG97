package bin;

import data.Transaction;
import data.TransactionList;


import java.util.Date;

/**
 * 事务管理操作
 */
public class TransactionManage {
    //静态加载 transactionList
    private static TransactionList transactionList = new TransactionList();

    /**
     * 根据现有的用户，scooter，当前时间生成一条transaction
     * 需要scooter还在用户手上
     */
    static void generateTransaction(String type) {
        Date time = new Date();
        Transaction transaction = new Transaction(AppState.getCurrentUser().getQmNumber(), type, AppState.getCurrentUser().getScooter().getId(), time);
        transactionList.addTransaction(transaction);
    }

    /**
     * （未实现）根据transaction检查当前用户是否超时
     */
    public static boolean checkIfExpired() {
        return false;
    }


}
