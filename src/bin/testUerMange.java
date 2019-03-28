package bin;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.User;
import data.UserList;

class testUerMange {

	@BeforeEach
	void init() {
        UserList list = new UserList();
        ArrayList<User> userList = new ArrayList<>();
        list.setList(userList);
	}

    @Test
    void testRegistration() {
    	assertTrue(UserManage.registration(123456700,"first","aaafdas@qmul.ac.uk"));
    }
    
    @Test
    void testIfDuplicate() {
    	UserManage.registration(123456700,"first","aaafdas@qmul.ac.uk");
    	assertTrue(UserManage.ifDuplicate(123456700,"aaafdas@qmul.ac.uk"));
    }

}
