package LogIn.LogInEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of username -> User object.
 */
public class UserList implements Serializable {

    /**
     * Initialize a new user list.
     */
    private final Map<String, User> users = new HashMap<>();

    /**
     *
     * @param user Add a new user to the user list.
     */
    public void add(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     *
     * @param username The username of the user.
     * @return Get the user according to the username.
     */
    public User getUser(String username) {
        return users.get(username);
    }
}