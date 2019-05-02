package bin;

import data.Scooter;
import data.ScooterList;
import data.Station;
import data.User;

import static bin.StationManage.stationList;
import static bin.UserManage.userList;

/**
 * 单车管理操作
 */
public class ScooterManage {
    /* 静态加载scooterList */
    public static ScooterList scooterList = new ScooterList();

    /**
     * 借车操作，车从station拿出来，放到user里
     * @param stationId 指定的站点id
     * @param scooterId 指定的单车id
     * @param qmNumber  指定的用户id
     * @return true-成功 false-失败
     */
    public static boolean takeScooter(int stationId, int scooterId, int qmNumber) {
        Station station = StationManage.findStationById(stationId);
        Scooter scooter = station.removeScooter(scooterId);

        if (scooter == null) {
            return false;
        }

        User user = UserManage.findUserByQm(qmNumber);
        user.takeScooter(scooter);
        scooterList.updateList();
        stationList.updateList();
        userList.updateList();

        return true;
    }

    /**
     * 还车操作，车从user拿出来，放到station里
     * @param qmNumber  指定的用户id
     * @param scooterId 指定的单车id
     * @param stationId 指定的站点id
     * @return true-成功 false-失败
     */
    public static boolean returnScooter(int qmNumber, int scooterId, int stationId) {
        User user = UserManage.findUserByQm(qmNumber);
        Scooter scooter = user.returnScooter();

        if (scooter == null) {
            return false;
        }

        Station station = StationManage.findStationById(stationId);
        station.addScooter(scooter);
        scooterList.updateList();
        stationList.updateList();
        userList.updateList();

        return true;
    }

    /**
     * 根据单车id返回指定的单车数据
     * @param scooterId 指定的单车id
     * @return 单车数据对象
     */
    public static Scooter findScooterById(int scooterId) {
        for (Scooter scooter : scooterList.getList()) {
            if (scooter.getId() == scooterId)
                return scooter;
        }
        return null;
    }

}
