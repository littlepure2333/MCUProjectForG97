package test;

import data.UserList;
import data.User;
import bin.UserManage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class FunctionTest {
    /*
     * 将一个默认的用户信息列表保存到本地文件:user.xml
     * 再从文件中取出用户信息列表，并列出所有用户的验证信息的完整性
     */
    @Test
    void testSetUserData() {
        UserList list = new UserList();
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123456780,"first","aaa@qmul.ac.uk"));
        userList.add(new User(111111110,"second","bbb@qmul.ac.uk"));
        list.setList(userList);
        for (User user: list.getUserList()) {
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
        System.out.println(UserManage.registration(100000000,"aaa","ccc@qmul.ac.uk"));
        UserList userList = new UserList();
        for (User user: userList.getUserList()) {
            System.out.println(user.getQmNumber());
        }
    }
}
