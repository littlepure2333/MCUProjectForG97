package mcu;

import bin.AppState;
import bin.StationManage;
import data.AppData;

import java.util.Vector;

import static java.lang.Math.pow;

public class Info {
    /**
     * Get the station slots, which indicated as 8 byte array
     * @return a byte that represents slots
     */
    public static byte[] getSlots() { // have tested
        Vector<Byte> slotList = new Vector<>();
        slotList.add(RxTx.LED_SEND_INIT);
        for (int i = 0; i <= 7; i++) {
            if (AppState.getCurrentStation().getSlot()[7-i] != null) {
                slotList.add((byte)pow(2, i));
            }
        }
        slotList.add(RxTx.DATA_END);
        byte[] slots = new byte[slotList.size()];

        for (int i = 0; i < slotList.size(); i++) {
            slots[i] = slotList.get(i);
            System.out.println(slots[i]);
        }
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
