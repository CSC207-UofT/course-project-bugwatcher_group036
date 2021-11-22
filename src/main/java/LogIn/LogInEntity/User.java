package LogIn.LogInEntity;
import java.io.Serializable;

public class User implements Serializable {

    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
}
