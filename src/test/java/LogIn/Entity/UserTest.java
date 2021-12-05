package LogIn.Entity;

import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserStatistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user = new User("Allen", "123456");
    User user0 = new User("Jack", "010814");

    @Test
    public void testInitialization() {
        String username = user.getUsername();
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
    public void testToString() {
        String s = user.toString();
        assertEquals(s, "Username: Allen, Password: 123456");
    }

    @Test
    public void testGetUserStatics() {
        UserStatistics userStat = user.getUserStatistics();
        int[] stat = userStat.getStats();
        assertEquals(stat.length, 7);

    }

    @Test
    public void testSetUserStatics() {
        UserStatistics userStat = new UserStatistics("Allen");
        user.setUserStatistics(userStat);
        assertEquals(user.getUserStatistics(), userStat);

    }
}