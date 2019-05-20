package data;


import org.junit.jupiter.api.Test;

import java.util.Vector;

class ScooterListTest extends AppData {
    /**
     * 测试是否能读取车的信息
     */
    @Test
    void testGetScooter() {
        for (Station station : stations) {
            if (station.getId() == 1) {
                if (station.getSlot()[7] == null)
                    System.out.println("no 7");
            }
        }
    }
}
