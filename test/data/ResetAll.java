package data;

import org.junit.jupiter.api.Test;

import java.util.Vector;

/**
 * Reset all the program's data
 */
public class ResetAll extends AppData {
    /**
     * reset transaction, station, scooter and user information
     */
    @Test
    public void resetAll() {
        testResetTransaction();
        testResetStationAndScooter();
        testResetUserData();
    }

    /**
     * Reset transaction to init state (says nothing)
     */
    @Test
    void testResetTransaction() {
        transactions = new Vector<>();
        updateData();
    }

    /**
     * Initialize Station information
     * (Every station has 5 scooters, which occupy the first 5 slots)
     */
    @Test
    void testResetStationAndScooter() {
        int i = 1;
        stations = new Vector<>();
        scooters = new Vector<>();
        updateData();

        Station station1 = new Station("A", 8);
        Station station2 = new Station("B", 8);
        Station station3 = new Station("C", 8);
        for(;i<=5;i++) {
            Scooter scooter = new Scooter(i);
            station1.addScooter(scooter);
            scooters.add(scooter);
        }
        for(;i<=10;i++) {
            Scooter scooter = new Scooter(i);
            station2.addScooter(scooter);
            scooters.add(scooter);
        }
        for(;i<=15;i++) {
            Scooter scooter = new Scooter(i);
            station3.addScooter(scooter);
            scooters.add(scooter);
        }
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        updateData();
    }

    /**
     * Reset user information, Only be used when need to import data again
     * Need to reset station information simultaneously
     */
    @Test
    void testResetUserData() {
        users = new Vector<>();
        users.add(new User(161188896, "ShizunWang", "jp2016213584@qmul.ac.uk"));
        users.add(new User(161189332,"MeiyuChen","jp2016213628@qmul.ac.uk"));
        users.add(new User(151007257,"TianqiZhu","jp2015213451@qmul.ac.uk"));
        users.add(new User(333333333,"RuyangLiu","jp2016213460@qmul.ac.uk"));
        users.add(new User(161188623,"WeipengShen","jp2016213556@qmul.ac.uk"));
        users.add(new User(161187888, "YufanWang", "jp2016213479@qmul.ac.uk"));
        updateData();
    }
}
