package data;

import org.junit.jupiter.api.Test;

import java.util.Vector;

class ResetAll extends AppData {
    @Test
    void resetAll() {
        testResetTransaction();
        testResetStationAndScooter();
        testResetUserData();
    }

    /**
     * 将transaction重置为初始状态（什么都没有）
     */
    @Test
    void testResetTransaction() {
        transactions = new Vector<>();
        updateData();
    }

    /**
     * 初始化站点信息
     * （每个站点5辆车，占前五个槽位）
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
            Scooter scooter = new Scooter(i,0);
            station1.addScooter(scooter);
            scooters.add(scooter);
        }
        for(;i<=10;i++) {
            Scooter scooter = new Scooter(i,0);
            station2.addScooter(scooter);
            scooters.add(scooter);
        }
        for(;i<=15;i++) {
            Scooter scooter = new Scooter(i,0);
            station3.addScooter(scooter);
            scooters.add(scooter);
        }
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        updateData();
    }

    /**
     * 重置用户信息
     * 注意只在需要重新导入数据时使用
     * 需要同时重置站点信息
     */
    @Test
    void testResetUserData() {
        users = new Vector<>();
        users.add(new User(123456789,"first","aaa@qmul.ac.uk"));
        users.add(new User(111111111,"second","bbb@qmul.ac.uk"));
        users.add(new User(222222222,"third","ccc@qmul.ac.uk"));
        users.add(new User(333333333,"second","ddd@qmul.ac.uk"));
        users.add(new User(444444444,"second","eee@qmul.ac.uk"));
        updateData();

    }
}
