package bin;

import data.User;
import data.UserList;

/**
 * 用户管理操作
 */
public class UserManage {
    /* 静态加载userList */
    public static UserList userList = new UserList();

/* 注册相关函数 */

    /**
     * 用户注册实现
     * @param qmNumber 填入的qm号码
     * @param fullName 填入的名称
     * @param email 填入的email地址
     * @return true-注册成功 false-注册失败
     */
    public static boolean registration(int qmNumber, String fullName, String email) {
        if (!isDuplicate(qmNumber, email)) {
            UserList userList = new UserList();
            userList.addUser(new User(qmNumber, fullName, email));
            return true;
        }
        return false;
    }

    /**
     * 检查qm号和邮箱是否与数据库信息重复
     * @param qmNumber 填入的qm号码
     * @param email 填入的email地址
     * @return true-信息重复 false-不重复
     */
    static boolean isDuplicate(int qmNumber, String email) {
        UserList userList = new UserList();
        for (User user:userList.getList()) {
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
     * @param qmNumber 填入的qm号码
     * @return true-登录成功 false-登录失败
     */
    public static boolean login(int qmNumber) {
        if (isExist(qmNumber)) {
            State.setCurrentUser(UserManage.findUserByQm(qmNumber));
            return true;
        }
        return false;
    }

    /**
     * 检查qm号是否存在与数据库中
     * @param qmNumber 填入的qm号码
     * @return true-存在 false-不存在
     */
    static boolean isExist(int qmNumber) {
        UserList userList = new UserList();
        for (User user:userList.getList()) {
            if (qmNumber == user.getQmNumber())
                return true;
        }
        return false;
    }

    /**
     * 根据用户qm号返回指定的用户信息
     * @param qmNumber 指定的qm号
     * @return 目标用户信息
     */
    public static User findUserByQm(int qmNumber) {
        for (User user:userList.getList()) {
            if (qmNumber == user.getQmNumber())
                return user;
        }
        return null;
    }
}

//
//