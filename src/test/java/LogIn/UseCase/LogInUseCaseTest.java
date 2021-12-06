package LogIn.UseCase;

import LogIn.LogInEntity.UserList;
import LogIn.LoginUseCase.LoginUseCase;
import org.junit.jupiter.api.Test;
import LogIn.LogInEntity.User;

import static org.junit.jupiter.api.Assertions.*;

public class LogInUseCaseTest {

    @Test
    public void testLogInResult() {
        UserList userList = new UserList();
        LoginUseCase.LoginResult loginResult = LoginUseCase.LoginResult.FAILURE;
        assertEquals(LoginUseCase.LoginResult.valueOf("FAILURE"), loginResult);
        User user = new User("york","123");
        userList.add(user);
        LoginUseCase loginUseCase = new LoginUseCase(userList);
        assertNotEquals(loginUseCase.login("abc","123"),
                LoginUseCase.LoginResult.SUCCESS);
        assertEquals(loginUseCase.login("york","123"),
                LoginUseCase.LoginResult.SUCCESS);
    }

    @Test
    public void testRegisterResult() {
        LoginUseCase.RegisterResult registerResult = LoginUseCase.RegisterResult.FAILURE;
        assertEquals(LoginUseCase.RegisterResult.valueOf("FAILURE"), registerResult);
        UserList userList = new UserList();
        User user = new User("york","123");
        userList.add(user);
        LoginUseCase loginUseCase = new LoginUseCase(userList);
        assertEquals(loginUseCase.register("abc","123"),
                LoginUseCase.RegisterResult.SUCCESS);
        assertEquals(loginUseCase.register("york","123"),
                LoginUseCase.RegisterResult.FAILURE);
    }

    @Test
    public void testLoginUseCase(){
        UserList userList = new UserList();
        LoginUseCase loginUseCase = new LoginUseCase(true);
        assertNotEquals(userList, loginUseCase.getUsers());
    }

    @Test
    public void testOtherLoginUseCase(){
        UserList userList = new UserList();
        UserList userListCopy = new UserList();
        LoginUseCase loginUseCase = new LoginUseCase(userList);
        assertNotEquals(userListCopy, loginUseCase.getUsers());
        assertEquals(userList, loginUseCase.getUsers());
    }

    @Test
    public void testGetUsers(){
        UserList userList = new UserList();
        LoginUseCase loginUseCase = new LoginUseCase(userList);
        UserList userListCopy = new UserList();
        assertEquals(userList, loginUseCase.getUsers());
        assertNotEquals(userListCopy, loginUseCase.getUsers());
    }

    @Test
    public void testLogin(){
        UserList userList = new UserList();
        User user = new User("york","123");
        userList.add(user);
        LoginUseCase loginUseCase = new LoginUseCase(userList);
        assertNotEquals(loginUseCase.login("abc","123"),
                LoginUseCase.LoginResult.SUCCESS);
        assertEquals(loginUseCase.login("york","123"),
                LoginUseCase.LoginResult.SUCCESS);
    }

    @Test
    public void testRegister(){
        UserList userList = new UserList();
        User user = new User("york","123");
        userList.add(user);
        LoginUseCase loginUseCase = new LoginUseCase(userList);
        assertEquals(loginUseCase.register("abc","123"),
                LoginUseCase.RegisterResult.SUCCESS);
        assertEquals(loginUseCase.register("york","123"),
                LoginUseCase.RegisterResult.FAILURE);
    }
}
