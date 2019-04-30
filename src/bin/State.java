package bin;

import data.Station;
import data.User;

/**
 * 与前端并行的后台状态
 * 保存程序中的临时信息便于前端调用
 * 原则上界面对state只能访问数据，只有bin中的类可以修改state中的数据
 */
public class State {
    /**
     * 目前登录中的user
     */
    private static User currentUser;
    /**
     * 目前正在访问的站点
     */
    private static Station currentStation;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Station getCurrentStation() {
        return currentStation;
    }

    static void setCurrentUser(User currentUser) {
        State.currentUser = currentUser;
    }

    static void setCurrentStation(Station currentStation) {
        State.currentStation = currentStation;
    }


}
