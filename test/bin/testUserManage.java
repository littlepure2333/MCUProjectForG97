package bin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Vector;

import data.AppData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testUserManage extends AppData {

	@BeforeEach
	void init() {
        users = new Vector<>();
        updateData();
	}

    @Test
    void testRegistration() {
    	assertTrue(UserManage.registration(123456700,"first","aaafdas@qmul.ac.uk"));
    }

    @Test
    void testIfDuplicate() {
    	UserManage.registration(123456700,"first","aaafdas@qmul.ac.uk");
    	assertTrue(UserManage.isDuplicate(123456700,"aaafdas@qmul.ac.uk"));
    }

}
