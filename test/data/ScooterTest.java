package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScooterTest {

    @Test
    void testCreateScooter() {
        Scooter scooter = new Scooter(2333,0);
        assertEquals(2333, scooter.getId());
        assertEquals(0, scooter.getUsed());
    }

}