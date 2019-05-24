package bin;

import data.AppData;
import data.Station;

import java.util.ArrayList;

/**
 * 站点管理操作
 */
public class StationManage extends AppData {
    /**
     * 选择当前的站点
     *
     * @param stationName name of the station
     */
    public static void chooseStation(String stationName) {
        AppState.setCurrentStation(findStationByName(stationName));
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
     * @param stationName 指定的站点name (A B C)
     * @return 站点数据对象，包括其中的slot和scooter数据
     */
    private static Station findStationByName(String stationName) {
        for (Station station : stations) {
            if (station.getName().equals(stationName))
                return station;
        }
        return null;
    }

    public static String[][] outputAllStations() {
        ArrayList<String[]> column = new ArrayList<>();
        int rowSize = 0;
        for (Station station: stations){
            String[] row = station.toString().split(" ");
            column.add(row);
            rowSize = row.length;
        }
        String[][] allStations = new String[column.size()][rowSize];
        for (int i = 0; i < column.size(); i++) {
            allStations[i] = column.get(i);
        }
        return allStations;
    }
}