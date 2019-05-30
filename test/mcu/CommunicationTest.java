package mcu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommunicationTest {

    @Test
    void testReceiveQMNumber() {
        byte[] data = new byte[10];
        data[0] = RxTx.KEY_RECEIVE_ID;
        data[1] = 0x01;
        data[2] = 0x02;
        data[3] = 0x03;
        data[4] = 0x03;
        data[5] = 0x03;
        data[6] = 0x03;
        data[7] = 0x03;
        data[8] = 0x03;
        data[9] = 0x03;
        Communication.setReceiveBuff(data);
        int QMNumber = Communication.receiveQMNumber();
        assertEquals(123333333, QMNumber);
        System.out.println(QMNumber);

        data[9] = 0x00;
        Communication.setReceiveBuff(data);
        QMNumber = Communication.receiveQMNumber();
        assertEquals(Communication.BROKEN_QM_NUMBER, QMNumber);

        data[0] = RxTx.LED_SEND_FLASH;
        Communication.setReceiveBuff(data);
        QMNumber = Communication.receiveQMNumber();
        assertEquals(Communication.IS_NOT_QM_NUMBER, QMNumber);

        data = new byte[3];
        data[0] = RxTx.KEY_RECEIVE_ID;
        data[1] = 0x01;
        data[2] = 0x02;
        Communication.setReceiveBuff(data);
        QMNumber = Communication.receiveQMNumber();
        assertEquals(Communication.BROKEN_QM_NUMBER, QMNumber);

    }




}