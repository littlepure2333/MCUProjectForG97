package data;

import bin.UserManage;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Vector;


class UserListTest extends AppData {
    /*
     * 向用户信息列表中添加新用户（用户信息符合格式）
     * 每次添加成功（不是重复信息）后，返回一个true
     * 最后打印一次所有用户的qm号来验证用户信息是否添加
     */
    @Test
    void testRegistration() {
        System.out.println(UserManage.registration(100000000,"aaa","ccc@se16.qmul.uk"));
        for (User user: users) {
            System.out.println(user.getQmNumber());
        }
    }
}
