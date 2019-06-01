package mcu;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.junit.jupiter.api.Test;

class RxTxTest {

    @Test
    void test() {
        RxTx rxTx = new RxTx("COM3");
        rxTx.wait(1000);
        byte[] data = new byte[1];
        data[0] = rxTx.KEY_RECEIVE_ID;
        rxTx.send(data);
        //while(true);
    }

    @Test
    void getSystemPort() {
        RxTx rxTx = new RxTx("COM3");
        rxTx.getSystemPort();
    }

    @Test
    void testGetUnsignedByte() {
        RxTx rxTx = new RxTx("COM3");
        int data = 0xFE;
        System.out.println(data);
        System.out.println(rxTx.getUnsignedByte(data));
    }
}