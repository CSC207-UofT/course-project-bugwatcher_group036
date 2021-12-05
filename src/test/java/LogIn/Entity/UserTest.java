package LogIn.Entity;

import LogIn.LogInEntity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user = new User("Allen", "123456");
    User user0 = new User("Jack", "010814");

    @Test
    public void testInitialization() {
        String username = user0.getUsername();
        assertEquals(username, "Allen");
    }

    @Test
    public void testPasswordMatches() {
        assertFalse(user.passwordMatches("010814"));
        assertTrue(user.passwordMatches("123456"));
    }

    @Test
    public void testGetUsername() {
        assertEquals(user.getUsername(), "Allen");
        assertEquals(user0.getUsername(), "Jack");
    }

    @Test
    public void testGetUserStatics() {

    }

    @Test
    public void testSetUserStatics() {

    }
}
