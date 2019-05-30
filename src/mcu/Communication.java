package mcu;

import static java.lang.Math.pow;

public class Communication {
    /**
     * Data received from mcu
     */
    private static byte[] RECEIVE_BUFF;
    /**
     * A flag to denote if the receive buffer is check by java program
     */
    private static boolean RECEIVE_BUFF_IS_CHECKED;

    public static final int IS_NOT_QM_NUMBER = -1;
    public static final int BROKEN_QM_NUMBER = -2;


    public static void setReceiveBuff(byte[] data) {
        RECEIVE_BUFF = data;
    }

    public static byte[] getReceiveBuff() {
        return RECEIVE_BUFF;
    }

    public static void setReceiveBuffIsChecked(boolean flag) {
        RECEIVE_BUFF_IS_CHECKED = flag;
    }

    public static boolean getReceiveBuffIsChecked() {
        return RECEIVE_BUFF_IS_CHECKED;
    }


    /**
     * Send station initial slots information to mcu
     * @return success or not
     */
    public static boolean sendStationSlots() {
        byte[] data = new byte[2];
        data[0] = RxTx.LED_SEND_INIT;
        data[1] = Info.getSlots();
        return RxTx.send(data);
    }

    /**
     * Send station flash slot information to mcu
     * @param i slot number, index from 0
     * @return success or not
     */
    public static boolean sendStationFlashSlot(int i) {
        byte[] data = new byte[2];
        data[0] = RxTx.LED_SEND_FLASH;
        data[1] = Info.getFlashSlot(i);
        return RxTx.send(data);
    }

    /**
     * get the QM number from mcu
     * @return QM number,
     * or -1 if receive content is not QM number,
     * or -2 if the data is incomplete
     */
    public static int receiveQMNumber() {
        // Check if receive data type is ID
        if (RECEIVE_BUFF[0] == RxTx.KEY_RECEIVE_ID) {
            // Check if the data is incomplete
            if (RECEIVE_BUFF.length == 10) {
                int QMNumber = 0;
                for (int i = 1; i <= 9; i++) {
                    // Check if the byte is valid
                    if (RECEIVE_BUFF[i] != 0x00 && RECEIVE_BUFF[i] != 0xFF) {
                        // Check if the byte is 0
                        if(RECEIVE_BUFF[i] != 0x0A) {
                            //876543210
                            QMNumber += (int)RECEIVE_BUFF[i]*(int)pow(10,9-i);
                        }
                    }
                    else {
                        return BROKEN_QM_NUMBER;
                    }
                }
                return QMNumber;
            }
            else {
                return BROKEN_QM_NUMBER;
            }
        }
        else {
            return IS_NOT_QM_NUMBER;
        }
    }




}
