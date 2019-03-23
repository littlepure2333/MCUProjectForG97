package test;

import data.UserList;
import data.User;
import bin.UserManage;

import org.junit.Test;

import java.util.ArrayList;


public class FunctionTest {
    /*
     * 将一个默认的用户信息列表保存到本地文件:user.xml
     * 再从文件中取出用户信息列表，并列出所有用户的验证信息的完整性
     */
    @Test
    public void testSetUserData() {
        UserList list = new UserList();
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123456780,"first","aaa@a.com"));
        userList.add(new User(111111110,"second","bbb@b.cn"));
        list.setList(userList);
        for (User user: list.getList()) {
            System.out.println(user.getQmNumber());
        }
    }

    /*
     * 向用户信息列表中添加新用户（用户信息符合格式）
     * 每次添加成功（不是重复信息）后，返回一个true
     * 最后打印一次所有用户的qm号来验证用户信息是否添加
     */
    @Test
    public void testRegistration() {
        System.out.println(UserManage.registration(100000000,"aaa","ccc@se16.qmul.uk"));
        UserList userList = new UserList();
        for (User user: userList.getList()) {
            System.out.println(user.getQmNumber());
        }
    }
}
