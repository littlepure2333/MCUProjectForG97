package bin;

import data.Station;
import data.StationList;

public class StationManage {
    public static Station findStationById(int stationId) {
        StationList stationList = new StationList();
        for (Station station : stationList.getList()) {
            if (station.getId() == stationId)
                return station;
        }
        return null;
    }
}