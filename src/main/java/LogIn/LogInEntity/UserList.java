package LogIn.LogInEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of username —> User object.
 */
public class UserList implements Serializable {

    private final Map<String, User> users = new HashMap<>();

    public void add(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}