package LogIn.LogInEntity;
import java.io.Serializable;

public class User implements Serializable {

    private final String username;
    private final String password;
    private UserStatistics userStatistics;

    /**
     * Initialize a new user.
     * @param username The username of user.
     * @param password The password of user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userStatistics = new UserStatistics(username);
    }

    /**
     *
     * @param password The password of user.
     * @return Whether the password matches the username.
     */
    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    /**
     *
     * @return Get the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return Convert the user to string.
     */
    @Override
    public String toString(){
        return "Username: " + this.username + ", Password: " + this.password;
    }

    /**
     *
     * @return Get the statistics of user.
     */
    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    /**
     *
     * @param userStatistics The statistics to set.
     */
    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }
}
