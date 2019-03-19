package test;

import org.junit.jupiter.api.Test;
import data.DataModel;
import data.User;
import java.util.ArrayList;

class FunctionTest {

    @Test
    private void testUserDataIO() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(123456789,"first","aaa@a.com"));
        userList.add(new User(111111111,"second","bbb@b.cn"));
        DataModel.saveUserData(userList);
        DataModel.readUserData();
    }
}
