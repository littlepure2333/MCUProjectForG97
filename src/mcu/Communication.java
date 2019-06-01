package mcu;

import static java.lang.Math.pow;

public class Communication {
    /**
     * listener
     */
    private CommunicationListener listener;

    /**
     * Data received from mcu
     */
    private static final int RECEIVE_BUFF_LENGTH = 20;
    private static int RECEIVE_BUFF_INDEX = 0;
    private static byte[] RECEIVE_BUFF = new byte[RECEIVE_BUFF_LENGTH];
    /**
     * A flag to denote if the receive buffer is check by java program
     */
    private static boolean RECEIVE_BUFF_IS_CHECKED;

    public static final int IS_NOT_QM_NUMBER = -1;
    public static final int BROKEN_QM_NUMBER = -2;

    public static final byte DISPLAY1 = 0x01; // Please type in your QM ID


    public void registerListener(CommunicationListener communicationListener) {
        this.listener = communicationListener;
    }




    public static void setReceiveBuff(byte[] data) {
        RECEIVE_BUFF = data;
    }

    public void addReceiveBuff(byte[] data) {
        if (RECEIVE_BUFF_INDEX != 0) {
            if (RECEIVE_BUFF[RECEIVE_BUFF_INDEX - 1] == RxTx.DATA_END) {
                RECEIVE_BUFF_INDEX = 0;
            }
        }
        for (int i = 0; i < data.length; i++) {
            RECEIVE_BUFF[RECEIVE_BUFF_INDEX] = data[i];
            RECEIVE_BUFF_INDEX++;
        }
        if (RECEIVE_BUFF[RECEIVE_BUFF_INDEX -1] == RxTx.DATA_END) {
            checkType();
        }
    }

    public void checkType() {
        if(listener != null) {
            CommunicationEvent event = new CommunicationEvent(this);
            if (receiveQmNumber() > 0) {
                this.listener.doReceiveQmNumber(event);
            }
        }
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
        byte[] data = Info.getSlots();
        return RxTx.send(data);
    }

    /**
     * Send station flash slot information to mcu
     * @param i slot number, index from 0
     * @return success or not
     */
    public static boolean sendStationFlashSlot(int i) {
        byte[] data = new byte[3];
        data[0] = RxTx.LED_SEND_FLASH;
        data[1] = Info.getFlashSlot(i);
        data[2] = RxTx.DATA_END;
        return RxTx.send(data);
    }

    /**
     * get the QM number from mcu
     * @return QM number,
     * or -1 if receive content is not QM number,
     * or -2 if the data is incomplete
     */
    public int receiveQmNumber() {
        // Check if receive data type is ID
        if (RECEIVE_BUFF[0] == RxTx.KEY_RECEIVE_ID) {
            // Check if the data is incomplete
            if (RECEIVE_BUFF[10] == RxTx.DATA_END) {
                int QMNumber = 0;
                //byte[] qm = Arrays.copyOfRange(RECEIVE_BUFF, 1,9);
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

    public static boolean sendLCDInformation(byte information) {
        byte[] data = new byte[3];
        data[0] = RxTx.LCD_SEND_DISPLAY;
        data[1] = information;
        data[2] = RxTx.DATA_END;
        return RxTx.send(data);
    }


}
