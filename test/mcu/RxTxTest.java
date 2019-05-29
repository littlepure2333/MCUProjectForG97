package mcu;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.junit.jupiter.api.Test;

class RxTxTest {

    @Test
    void test() {
        RxTx.init("COM8");
        RxTx.wait(1000);
        byte[] data = new byte[1];
        data[0] = RxTx.KEY_RECEIVE_ID;
        RxTx.send(data);
        //while(true);
    }

    @Test
    void getSystemPort() {
        RxTx.getSystemPort();
    }
}