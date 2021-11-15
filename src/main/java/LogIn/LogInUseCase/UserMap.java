package LogIn.LogInUseCase;

import LogIn.LogInEntity.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of username -> User object.
 */
// What do we gain by defining this class and storing an instance of it
// in the LogInUseCase class, as opposed to just
// storing a Map<String, User> in the LogInUseCase class?
public class UserMap implements Serializable {
    private final Map<String, User> users = new HashMap<>();

    /**
     * Add user to this user list.
     * @param user the user to add
     */
    public void add(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Return the User associated with username.
     * @param username the username of the user to get.
     */
    public User getUser(String username) {
        return users.get(username);
    }
}