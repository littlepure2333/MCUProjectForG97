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
    private static ScooterList scooterList = new ScooterList();

    /**
     * 直接从State里面获取当前用户，当前站点，当前车槽
     * 进行借车操作，车从station拿出来，放到user里
     * 没有检查State数据是否正确，所以使用时一定确保数据正确
     */
    public static void takeScooter() {
        Station station = AppState.getCurrentStation();
        Scooter scooter = station.removeScooter(AppState.getCurrentSlot());
        User user = AppState.getCurrentUser();
        user.takeScooter(scooter);

        // 对数据进行修改后，立即更新XML
        scooterList.updateList();
        stationList.updateList();
        userList.updateList();
    }

    /**
     * 直接从State里面获取当前用户，当前站点，当前车槽
     * 进行还车操作，车从user拿出来，放到station里
     * 没有检查State数据是否正确，所以使用时一定确保数据正确
     */
    public static void returnScooter() {
        User user = AppState.getCurrentUser();
        Scooter scooter = user.returnScooter();
        Station station = AppState.getCurrentStation();
        station.addScooter(scooter);

        // 对数据进行更改后，立即更新XML
        scooterList.updateList();
        stationList.updateList();
        userList.updateList();
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
