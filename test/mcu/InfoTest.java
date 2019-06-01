package mcu;

import bin.AppState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoTest {

    @Test
    void testGetSlots() {

    }

    @Test
    void testGetFlashSlot() {
        System.out.println(Info.getFlashSlot(2));
    }
}