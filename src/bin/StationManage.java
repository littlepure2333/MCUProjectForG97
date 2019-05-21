package bin;

import data.AppData;
import data.Station;

/**
 * 站点管理操作
 */
public class StationManage extends AppData {
    /**
     * 选择当前的站点
     *
     * @param stationId 站点id
     */
    public static void chooseStation(int stationId) {
        AppState.setCurrentStation(findStationById(stationId));
    }

    /**
     * 选择当前站点闪烁的slot编号
     *
     * @param slotId 目标slot编号
     */
    public static void chooseFlashSlot(int slotId) {
        AppState.setCurrentSlot(slotId);
    }

    /**
     * 根据站点id返回指定的站点数据
     *
     * @param stationId 指定的站点id (默认 1 2 3)
     * @return 站点数据对象，包括其中的slot和scooter数据
     */
    private static Station findStationById(int stationId) {
        for (Station station : stations) {
            if (station.getId() == stationId)
                return station;
        }
        return null;
    }
}