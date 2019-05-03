package bin;

import data.Station;
import data.StationList;

/**
 * 站点管理操作
 */
public class StationManage {
    /* 静态加载 stationList */
    public static StationList stationList = new StationList();

    /**
     * 选择当前的站点
     * @param stationId 站点id
     * （未实现）检测错误的站点id
     */
    public static void chooseStation(int stationId) {
        State.setCurrentStation(findStationById(stationId));
    }

    /**
     * 选择当前站点闪烁的slot编号
     * @param slotId
     */
    public static void chooseFlashSlot(int slotId) {
        State.setCurrentSlot(slotId);
    }

    /**
     * 根据站点id返回指定的站点数据
     * @param stationId 指定的站点id (默认 1 2 3)
     * @return 站点数据对象，包括其中的slot和scooter数据
     */
    public static Station findStationById(int stationId) {
        for (Station station : stationList.getList()) {
            if (station.getId() == stationId)
                return station;
        }
        return null;
    }
}