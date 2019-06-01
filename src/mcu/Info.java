package mcu;

import bin.AppState;

import static java.lang.Math.pow;

public class Info {
    /**
     * Get the station slots, which indicated as 8 byte array
     * @return a byte that represents slots
     */
    public static byte[] getSlots() { // have tested
        RxTx rxTx = new RxTx("COM3");
        byte[] slots = new byte[10];
        slots[0] = rxTx.LED_SEND_INIT;
        for (int i = 0; i <= 7; i++) {
            if (AppState.getCurrentStation().getSlot()[7-i] != null) {
//            if (station[7-i] != null) {
                slots[i+1] = (byte)pow(2, i);
            }
            else {
                slots[i+1] = 0x00;
            }
        }
        slots[9] = rxTx.DATA_END;
        System.out.println(slots);
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
