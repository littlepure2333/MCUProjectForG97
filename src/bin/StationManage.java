package bin;

import data.AppData;
import data.Station;

import java.util.ArrayList;

/**
 * Control Class
 * Manage the transmission, text output and queries
 */
public class StationManage extends AppData {
    /**
     * Set the station as the current station, then save it into the program state.
     * @param stationName name of the station
     */
    public static void chooseStation(String stationName) {
        AppState.setCurrentStation(findStationByName(stationName));
    }

    /**
     * Choose which slot of the current station will start to flash
     * @param slotId the chosen slot
     */
    public static void chooseFlashSlot(int slotId) {
        AppState.setCurrentSlot(slotId);
    }

    /**
     * Acquire the information of the station by its name
     * @param stationName name of the station
     * @return the information of the station including scooters in it
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