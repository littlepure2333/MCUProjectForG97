package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void testCreateStation() {
        Station station = new Station(2333,8);
        assertEquals(2333, station.getId());
        assertEquals(8, station.getSlotSize());
    }

}