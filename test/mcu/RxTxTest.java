package mcu;

import org.junit.jupiter.api.Test;

class RxTxTest {

    @Test
    void test() {
        byte[] data = new byte[1];
        data[0] = RxTx.KEY_RECEIVE_ID;
        RxTx.send(data);
        //while(true);
    }

    @Test
    void getSystemPort() {
        RxTx.getSystemPort();
    }

    @Test
    void testGetUnsignedByte() {
        int data = 0xFE;
        System.out.println(data);
        System.out.println(RxTx.getUnsignedByte(data));
    }
}