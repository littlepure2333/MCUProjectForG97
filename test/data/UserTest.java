package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testCreateUser() {
        User user = new User(161188896,"Shizun Wang", "111@163.com");
        assertEquals(161188896, user.getQmNumber());
        assertEquals("Shizun Wang", user.getFullName());
        assertEquals("111@163.com", user.getEmail());
    }
}