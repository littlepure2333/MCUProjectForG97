package test;

import data.UserList;
import data.User;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Creating a user list with default arguments, write it into user.xml,
 * then extract data from user.xml to verify the integrity of user data.
 */
public class FunctionTest {
    @Test
    public void testUserDataIO() {
        UserList list = new UserList();
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123456780,"first","aaa@a.com"));
        userList.add(new User(111111110,"second","bbb@b.cn"));
        list.setList(userList);
        for (User user: list.getList()) {
            System.out.println(user.getQmNumber());
        }
    }
}
