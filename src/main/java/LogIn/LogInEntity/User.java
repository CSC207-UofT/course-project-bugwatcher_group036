package LogIn.LogInEntity;
import java.io.Serializable;

public class User implements Serializable {

    private final String username;
    private final String password;
    private UserStatistics userStatistics;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userStatistics = new UserStatistics(username);
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

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }
}
