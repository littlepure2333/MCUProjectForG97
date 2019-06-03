package mcu;

import static java.lang.Math.pow;

class Communication {

    /**
     * Data received from mcu
     */
    private static final int RECEIVE_BUFF_LENGTH = 20;
    private int RECEIVE_BUFF_INDEX = 0;
    private byte[] RECEIVE_BUFF = new byte[RECEIVE_BUFF_LENGTH];
    /**
     * A flag to denote if the receive buffer is check by java program
     */
    private static boolean RECEIVE_BUFF_IS_CHECKED = true;

    private static final int IS_NOT_QM_NUMBER = -1;
    private static final int BROKEN_QM_NUMBER = -2;
    private static final int NOT_THIS_TYPE = -1;
    static final int TAKE_OPTION = 1;
    static final int RETURN_OPTION = 2;

    void addReceiveBuff(byte[] data) {
        if (RECEIVE_BUFF_INDEX != 0) {
            if (RECEIVE_BUFF[RECEIVE_BUFF_INDEX - 1] == RxTx.DATA_END) {
                RECEIVE_BUFF_INDEX = 0;
            }
        }
        for (byte datum : data) {
            RECEIVE_BUFF[RECEIVE_BUFF_INDEX] = datum;
            RECEIVE_BUFF_INDEX++;
        }
        //只有收齐了数据才将其重置为unchecked
        if (RECEIVE_BUFF[RECEIVE_BUFF_INDEX -1] == RxTx.DATA_END) {
            setReceiveBuffIsChecked(false);
        }
    }

    void setReceiveBuffIsChecked(boolean flag) {
        RECEIVE_BUFF_IS_CHECKED = flag;
    }

    boolean getReceiveBuffIsChecked() {
        return RECEIVE_BUFF_IS_CHECKED;
    }


    /**
     * Send station initial slots information to mcu
     */
    void sendStationSlots() {
        byte[] data = Info.getSlots();
        RxTx.send(data);
    }

    /**
     * Send station flash slot information to mcu
     * @param i slot number, index from 0
     */
    void sendTakeFlashSlot(int i) {
        byte[] data = new byte[3];
        data[0] = RxTx.LED_TAKE_FLASH;
        data[1] = Info.getFlashSlot(i);
        data[2] = RxTx.DATA_END;
        RxTx.send(data);
    }

    void sendReturnFlashSlot(int i) {
        byte[] data = new byte[3];
        data[0] = RxTx.LED_RET_FLASH;
        data[1] = Info.getFlashSlot(i);
        data[2] = RxTx.DATA_END;
        RxTx.send(data);
    }

    /**
     * get the QM number from mcu
     * @return QM number,
     * or -1 if receive content is not QM number,
     * or -2 if the data is incomplete
     */
    int receiveQmNumber() {
        // Check if receive data type is ID
        if (RECEIVE_BUFF[0] == RxTx.KEY_RECEIVE_ID) {
            // Check if the data is incomplete
            if (RECEIVE_BUFF[10] == RxTx.DATA_END) {
                int QMNumber = 0;
                //byte[] qm = Arrays.copyOfRange(RECEIVE_BUFF, 1,9);
                for (int i = 1; i <= 9; i++) {
                    // Check if the byte is valid
                    if (RECEIVE_BUFF[i] != 0x00 && (int)RECEIVE_BUFF[i] != 0xFF) {
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

    /**
     * receive take or return option
     * only input 1: 1, only input 2: 2, else: -1
     * @return 1 take, 2 return, -1 not this type
     */
    int receiveTakeOrReturn() {
        // Check if receive data type is ID
        if (RECEIVE_BUFF[1] == 1 && onlyOneByte(RECEIVE_BUFF)) { //RxTx.KEY_RECEIVE_TAKE
            return TAKE_OPTION;
        }
        else if (RECEIVE_BUFF[1] == 2 && onlyOneByte(RECEIVE_BUFF)) { //RxTx.KEY_RECEIVE_RETURN
            return RETURN_OPTION;
        }
        else {
            return NOT_THIS_TYPE;
        }
    }

    private boolean onlyOneByte(byte[] receive) {
        for (int i = 2; i < 9; i++) {
            if (receive[i] != 0)
                return false;
        }
        return true;
    }


}
