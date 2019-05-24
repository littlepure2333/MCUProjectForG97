package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void testCreateStation() {
        Station station = new Station("B",8);
        assertEquals(2333, station.getName());
        assertEquals(8, station.getSlotSize());
    }

}