package data;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class TransactionListTest {

    /**
     * 将transaction重置为初始状态（什么都没有）
     */
    @Test
    void testResetTransaction() {
        TransactionList transactionList = new TransactionList();
        transactionList.resetList(new ArrayList<>());
    }
}
