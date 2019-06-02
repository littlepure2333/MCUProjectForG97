package mcu;

import bin.AppState;
import bin.StationManage;

import static java.lang.Math.pow;

public class Info {
    /**
     * Get the station slots, which indicated as 8 byte array
     * @return a byte that represents slots
     */
    public static byte[] getSlots() { // have tested
        byte[] slots = new byte[10];
        slots[0] = RxTx.LED_SEND_INIT;
        for (int i = 0; i <= 7; i++) {
            if (AppState.getCurrentStation().getSlot()[7-i] != null) {
//            if (station[7-i] != null) {
                slots[i+1] = (byte)pow(2, i);
            }
            else {
                slots[i+1] = 0x00;
            }
        }
        slots[9] = RxTx.DATA_END;
        for (byte slot: slots)
            System.out.println(slot);
        return slots;
    }

    /**
     * Get the flash slot, which indicated as 8 binary numbers
     * i.e. 00001000 means [slot][slot][slot][slot][flash][slot][slot][slot]
     * @return a byte that represents flash slot
     */
    public static byte getFlashSlot(int i) {
        // i index from 0, and count from left to right
        byte slots = (byte)pow(2,7 - i);
        return slots;
    }

}
