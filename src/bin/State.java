package bin;

import data.Station;
import data.StationList;
import data.User;

import javax.swing.*;
import java.util.HashMap;

public class State {
    private static User currentUser;
    private static Station currentStation;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Station getCurrentStation() {
        return currentStation;
    }

    public static void setCurrentUser(User currentUser) {
        State.currentUser = currentUser;
    }

    public static void setCurrentStation(Station currentStation) {
        State.currentStation = currentStation;
    }

    public static Station findStation(int stationId) {
        StationList stationList = new StationList();
        Station returnStation = null;
        for (Station station : stationList.getList()) {
            if (station.getId() == stationId)
                returnStation = station;
        }
        return returnStation;
    }
}
