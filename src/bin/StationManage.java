package bin;

import data.Station;
import data.StationList;

/**
 * 站点管理操作
 */
public class StationManage {

    /**
     * 选择当前的站点
     * @param stationId 站点id
     * （未实现）检测错误的站点id
     */
    public static void stationChoose(int stationId) {
        State.setCurrentStation(findStationById(stationId));
    }

    /**
     * 根据站点id返回指定的站点数据
     * @param stationId 指定的站点id (默认 1 2 3)
     * @return 站点数据对象，包括其中的slot和scooter数据
     */
    private static Station findStationById(int stationId) {
        StationList stationList = new StationList();
        for (Station station : stationList.getList()) {
            if (station.getId() == stationId)
                return station;
        }
        return null;
    }
}