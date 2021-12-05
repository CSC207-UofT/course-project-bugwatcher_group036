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
        UserStatistics userStat = user.getUserStatistics();
        int[] stat = userStat.getStats();
        assertEquals(stat.length, 7);


    }

    @Test
    public void testSetUserStatics() {
        UserStatistics userStat = new UserStatistics("Allen");
        assertEquals(userStat.getPlayerId(), "Allen");
        UserStatistics userStat0 = new UserStatistics(("Jame"));
        assertEquals(userStat0.getPlayerId(), "Jame");

    }
}
