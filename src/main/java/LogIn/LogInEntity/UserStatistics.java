package LogIn.LogInEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class UserStatistics implements Serializable {
    private final String playerId;
    private final int[] stats;

    public UserStatistics(String playerId) {
        // 0: PVP win count
        // 1: PVE win count
        // 2: total card played
        // 3: total function card played
        // 4: total card drawn
        // 5: exp
        // 6: player level
        stats = new int[7];
        this.playerId = playerId;
    }

    public int[] getStats() {
        return stats;
    }

    public void PVPWin() {
        stats[0]++;
        stats[5] += 100;
        checkExp();
    }

    public void PVEWin() {
        stats[1]++;
        stats[5] += 70;
        checkExp();
    }

    public void playCard(String played) {
        if (played == null) return;
        stats[2]++; stats[5] += 2;
        ArrayList<String> nums = new ArrayList<>();
        Collections.addAll(nums, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        if (!nums.contains(played.split(" ")[1])) {
            stats[3]++; stats[5] += 3;
        }
        checkExp();
    }

    public void drawCard(int num) {
        stats[4] += num;
        stats[5] += num;
        checkExp();
    }

    public void checkExp() {
        if (stats[5] < 100) {
            stats[6] = 1;
            return;
        }

        stats[6] = 1 + (int) (Math.log((stats[5] + 1) / 100.0) / Math.log(2));
    }

    public String getPlayerId() {
        return playerId;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        String[] keywords = new String[]{"PVP win count", "PVE win count", "total card played",
                "total function card played", "total card drawn", "exp", "player level"};
        for (int i = 0; i < keywords.length; i++) {
            res.append(keywords[i]).append(": ").append(stats[i]).append("\n");
        }
        return res.toString();
    }
}
