//package mcu;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CommunicationTest {
//
//    @Test
//    void testReceiveQMNumber() {
//        Communication communication = new Communication();
//        byte[] data = new byte[10];
//        data[0] = RxTx.KEY_RECEIVE_ID;
//        data[1] = 0x01;
//        data[2] = 0x02;
//        data[3] = 0x03;
//        data[4] = 0x03;
//        data[5] = 0x03;
//        data[6] = 0x03;
//        data[7] = 0x03;
//        data[8] = 0x03;
//        data[9] = 0x03;
//        Communication.setReceiveBuff(data);
//        int QMNumber = communication.receiveQmNumber();
//        assertEquals(123333333, QMNumber);
//        System.out.println(QMNumber);
//
//        data[9] = 0x00;
//        Communication.setReceiveBuff(data);
//        QMNumber = communication.receiveQmNumber();
//        assertEquals(Communication.BROKEN_QM_NUMBER, QMNumber);
//
//        data[0] = RxTx.LED_SEND_FLASH;
//        Communication.setReceiveBuff(data);
//        QMNumber = communication.receiveQmNumber();
//        assertEquals(Communication.IS_NOT_QM_NUMBER, QMNumber);
//
//        data = new byte[3];
//        data[0] = RxTx.KEY_RECEIVE_ID;
//        data[1] = 0x01;
//        data[2] = 0x02;
//        Communication.setReceiveBuff(data);
//        QMNumber = communication.receiveQmNumber();
//        assertEquals(Communication.BROKEN_QM_NUMBER, QMNumber);
//
//    }
//
//
//    @Test
//    void testAddReceiveBuff() {
//        Communication communication = new Communication();
//        byte[] data = new byte[5];
//        data[0] = RxTx.KEY_RECEIVE_ID;
//        data[1] = 0x01;
//        data[2] = 0x02;
//        data[3] = 0x03;
//        data[4] = 0x04;
//        communication.addReceiveBuff(data);
//        data = new byte[6];
//        data[0] = 0x05;
//        data[1] = 0x06;
//        data[2] = 0x07;
//        data[3] = 0x08;
//        data[4] = 0x09;
//        data[5] = RxTx.DATA_END;
//        communication.addReceiveBuff(data);
//        int QMNumber = communication.receiveQmNumber();
//        System.out.println(QMNumber);
//        data = new byte[5];
//        data[0] = RxTx.KEY_RECEIVE_ID;
//        data[1] = 0x09;
//        data[2] = 0x02;
//        data[3] = 0x03;
//        data[4] = 0x04;
//        communication.addReceiveBuff(data);
//        data = new byte[6];
//        data[0] = 0x05;
//        data[1] = 0x06;
//        data[2] = 0x07;
//        data[3] = 0x08;
//        data[4] = 0x09;
//        data[5] = RxTx.DATA_END;
//        communication.addReceiveBuff(data);
//        QMNumber = communication.receiveQmNumber();
//        System.out.println(QMNumber);
//    }
//}