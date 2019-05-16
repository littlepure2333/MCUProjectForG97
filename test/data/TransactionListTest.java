package data;

import org.junit.jupiter.api.Test;
import java.util.Vector;

class TransactionListTest extends AppData {

    /**
     * 将transaction重置为初始状态（什么都没有）
     */
    @Test
    void testResetTransaction() {
        transactions = new Vector<>();
        updateData();
    }
}
