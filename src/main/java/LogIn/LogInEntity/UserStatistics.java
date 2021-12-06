package LogIn.LogInEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class UserStatistics implements Serializable {
    private final String playerId;
    private final int[] stats;

    /**
     * Initialize a new statistics according to player.
     * @param playerId
     */
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

    /**
     *
     * @return Get the stats of the user.
     */
    public int[] getStats() {
        return stats;
    }

    /**
     * Change the stats of the user after winning a PVP game.
     */
    public void PVPWin() {
        stats[0]++;
        stats[5] += 100;
        checkExp();
    }

    /**
     * Change the stats of the user after winning a PVE game.
     */
    public void PVEWin() {
        stats[1]++;
        stats[5] += 70;
        checkExp();
    }

    /**
     *
     * @param played The card the player play. Add 2 exp if number cards, 3 exp if function card.
     */
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

    /**
     *
     * @param num The number of card the player draw.
     */
    public void drawCard(int num) {
        stats[4] += num;
        stats[5] += num;
        checkExp();
    }

    /**
     * Check whether the exp is enough to level up.
     */
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

    /**
     *
     * @return Convert the stats to string.
     */
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
