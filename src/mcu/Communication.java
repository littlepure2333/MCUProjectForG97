package mcu;

public class Communication {
    /**
     * Data received from mcu
     */
    private static byte[] RECEIVE_BUFF;
    /**
     * A flag to denote if the receive buffer is check by java program
     */
    private static boolean RECEIVE_BUFF_IS_CHECKED;

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





}
