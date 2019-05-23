package bin;

import data.AppData;
import data.User;

/**
 * 用户管理操作
 */
public class UserManage extends AppData {
    /* 注册相关函数 */

    /**
     * 用户注册实现
     *
     * @param qmNumber 填入的qm号码
     * @param fullName 填入的名称
     * @param email    填入的email地址
     * @return true-注册成功 false-注册失败
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
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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

    public static String getCurrentUserId() {
        return Integer.toString(AppState.getCurrentUser().getQmNumber());
    }

    public static String getCurrentUserName() {
        return AppState.getCurrentUser().getFullName();
    }

    public static String getCurrentUserEmail() {
        return AppState.getCurrentUser().getEmail();
    }

    public static String getCurrentUserFineState() {
        if (AppState.getCurrentUser().isNeedToPay() == "true") {
            return "Be fined";
        }
        else {
            return "Not be fined";
        }
    }

}
