package bin;

import data.AppData;
import data.User;

/**
 * Control Class
 * Manage the transmission, validation, queries of user data
 */
public class UserManage extends AppData {

    /**
     * 用户注册实现
     *
     * @param qmNumber QM number input by user
     * @param fullName full name input by user
     * @param email    email input by user
     * @return true: successful registration, false: registration failed
     */
    public static boolean registration(int qmNumber, String fullName, String email) {
        if (!isDuplicate(qmNumber, email)) {
            users.add(new User(qmNumber, fullName, email));
            updateData();
            return true;
        }
        return false;
    }

    /**
     * 检查qm号和邮箱是否与数据库信息重复
     *
     * @param qmNumber 填入的qm号码
     * @param email    填入的email地址
     * @return true-信息重复 false-不重复
     */
    static boolean isDuplicate(int qmNumber, String email) {
        for (User user : users) {
            if (qmNumber == user.getQmNumber())
                return true;
            if (email.equals(user.getEmail()))
                return true;
        }
        return false;
    }

    /* 登陆相关函数 */

    /**
     * 用户登录实现
     *
     * @param qmNumber 填入的qm号码
     * @return true-登录成功 false-登录失败
     */
    public static boolean login(int qmNumber) {
        for (User user : users) {
            if (qmNumber == user.getQmNumber()) {
                AppState.setCurrentUser(user);
                return true;
            }
        }
        return false;
    }

    public static void payTheFine() {
        AppState.getCurrentUser().setNeedToPay("false");
        updateData();
    }
}
