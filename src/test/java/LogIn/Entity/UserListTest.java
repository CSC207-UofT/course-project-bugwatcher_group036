package LogIn.Entity;

import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class UserListTest {
    UserList userList = new UserList();
    User user = new User("Allen", "1007224576");

    @Test
    public void testAdd() {
        userList.add(user);
        User user0 = userList.getUser("Allen");
        assertEquals(user0.getUsername(), "Allen");
    }

    @Test
    public void testGetUser() {
        userList.add(user);
        User user0 = new User("Jame", "123456");
        User user1 = userList.getUser("Jame");
        assertEquals(user0.getUsername(), "Jame");
    }
}
