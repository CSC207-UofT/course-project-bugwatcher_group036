package LogIn.Entity;

import LogIn.LogInEntity.UserStatistics;
import org.junit.jupiter.api.Test;

import java.net.PortUnreachableException;

import static org.junit.jupiter.api.Assertions.*;


public class UserStaticsTest {
    UserStatistics statistics = new UserStatistics("Allen");

    @Test
    public void testInitialization() {
        assertEquals(statistics.getPlayerId(), "Allen");

    }

    @Test
    public void testGetStats() {
        int[] stats = statistics.getStats();
        assertEquals(stats[0], 0);
        assertEquals(stats.length, 7);
    }

    @Test
    public void testPVPWin() {
        statistics.PVPWin();
        int[] stats = statistics.getStats();
        assertEquals(stats[0], 1);
        assertEquals(stats[1], 0);
        assertEquals(stats[5], 100);
        assertEquals(stats.length, 7);

    }

    @Test
    public void testPVEWin() {
        statistics.PVEWin();
        int[] stats = statistics.getStats();
        assertEquals(stats[0], 0);
        assertEquals(stats[1], 1);
        assertEquals(stats[5], 70);
        assertEquals(stats.length, 7);

    }

    @Test
    public void testDrawCard() {
        statistics.drawCard(10);
        int[] stats = statistics.getStats();
        assertEquals(stats[0], 0);
        assertEquals(stats[0], 0);
        assertEquals(stats[5], 10);
        assertEquals(stats.length, 7);
    }

    @Test
    public void testPlayCard() {
        int[] stats = statistics.getStats();
        assertEquals(stats[6], 0);
        statistics.playCard("red 5");
        assertEquals(stats[2], 1);
        assertEquals(stats[3], 0);
        assertEquals(stats[5], 2);
        assertEquals(stats[6], 1);
        assertEquals(stats.length, 7);
        statistics.playCard("red +2");
        assertEquals(stats[2], 2);
        assertEquals(stats[3], 1);
        assertEquals(stats[5], 7);
        assertEquals(stats[6], 1);
        assertEquals(stats.length, 7);

    }

    @Test
    public void testCheckExp() {
        statistics.checkExp();
        int[] stats = statistics.getStats();
        assertEquals(stats[0], 0);
        assertEquals(stats[6], 1);
        assertEquals(stats.length, 7);
    }

    @Test
    public void testGetPlayerId() {
        String id = statistics.getPlayerId();
        assertEquals(id, "Allen");
    }

    @Test
    public void testToString() {
        String s = statistics.toString();
        assertEquals(s, "PVP win count: 0\nPVE win count: 0\ntotal card played: 0\n" +
                "total function card played: 0\ntotal card drawn: 0\nexp: 0\nplayer level: 0\n");
    }
}
