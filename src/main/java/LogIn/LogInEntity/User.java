package LogIn.LogInEntity;
import java.io.Serializable;

public class User implements Serializable {

    private final String username;
    private final String password;
    private int PVPWinCount;
    private int PVEWinCount;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.PVPWinCount = 0;
        this.PVEWinCount = 0;
    }

    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString(){
        return "Username: " + this.username + ", Password: " + this.password;
    }

    public int getPVPWinCount() {
        return PVPWinCount;
    }

    public void setPVPWinCount(int PVPWinCount) {
        this.PVPWinCount = PVPWinCount;
    }

    public int getPVEWinCount() {
        return PVEWinCount;
    }

    public void setPVEWinCount(int PVEWinCount) {
        this.PVEWinCount = PVEWinCount;
    }
}
