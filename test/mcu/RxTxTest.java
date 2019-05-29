package mcu;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.junit.jupiter.api.Test;

class RxTxTest {

    @Test
    void test() {
        RxTx.init("COM3");
        RxTx.wait(1000);
        byte[] data = null;
        data[0] = RxTx.KEY_RECEIVE_ID;
        RxTx.send(data);
        //while(true);
    }

    @Test
    void getSystemPort() {
        RxTx.getSystemPort();
    }
}