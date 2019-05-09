package data;

import bin.UserManage;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


class UserListTest {
    /**
     * 重置用户信息
     * 注意只在需要重新导入数据时使用
     * 需要同时重置站点信息
     */
    @Test
    void testResetUserData() {
        UserList userList = new UserList();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(123456789,"first","aaa@qmul.ac.uk"));
        list.add(new User(111111111,"second","bbb@qmul.ac.uk"));
        list.add(new User(222222222,"third","ccc@qmul.ac.uk"));
        list.add(new User(333333333,"second","ddd@qmul.ac.uk"));
        list.add(new User(444444444,"second","eee@qmul.ac.uk"));
        userList.resetList(list);
        for (User user: userList.getList()) {
            System.out.println(user.getQmNumber());
        }
    }

    /*
     * 向用户信息列表中添加新用户（用户信息符合格式）
     * 每次添加成功（不是重复信息）后，返回一个true
     * 最后打印一次所有用户的qm号来验证用户信息是否添加
     */
    @Test
    void testRegistration() {
        System.out.println(UserManage.registration(100000000,"aaa","ccc@se16.qmul.uk"));
        UserList userList = new UserList();
        for (User user: userList.getList()) {
            System.out.println(user.getQmNumber());
        }
    }
}
