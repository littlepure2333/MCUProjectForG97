package bin;

import data.Station;
import data.StationList;
import data.User;


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


}
