package data;


import org.junit.jupiter.api.Test;

class testStation {

    @Test
    void testAddingStation() {
        StationList stationList = new StationList();
        Station station = new Station(123, 8);
        station.addScooter(new Scooter(12345,0));
        stationList.addStation(station);
    }
}
