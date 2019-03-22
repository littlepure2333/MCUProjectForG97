package test;

import data.UserList;
import data.User;
import org.junit.Test;

import java.util.ArrayList;

public class FunctionTest {
    @Test
    public void testUserDataIO2() {
        UserList list = new UserList();
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123456781,"first","aaa@a.com"));
        userList.add(new User(111111110,"second","bbb@b.cn"));
        list.setList(userList);
        for (User user: list.getList()) {
            System.out.println(user.getQmNumber());
        }
    }
}
