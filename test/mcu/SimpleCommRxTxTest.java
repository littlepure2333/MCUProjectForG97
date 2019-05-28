package mcu;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.junit.jupiter.api.Test;
import sun.awt.im.SimpleInputMethodWindow;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCommRxTxTest {

    @Test
    void test() {
        SimpleCommRxTx.init("COM8");
        SimpleCommRxTx.wait(1000);
        byte[] data = new byte[2];
        data[0] = 'a';
        data[1] = '2';
        SimpleCommRxTx.send(data);
        SimpleCommRxTx.setListener(new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    byte[] data = SimpleCommRxTx.receive();
                    System.out.println("Receive data length: " + data.length);
                    System.out.println("Receive data content: " + new String(data));
                }
            }
        });
        //while(true);
    }

    @Test
    void getSystemPort() {
        SimpleCommRxTx.getSystemPort();
    }
}