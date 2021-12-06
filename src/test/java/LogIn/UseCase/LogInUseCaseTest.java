package LogIn.UseCase;

import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserReadWriter;
import LogIn.LoginUseCase.LoginUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogInUseCaseTest {
    UserList userList;
    UserReadWriter readWriter = new UserReadWriter();

    @Test
    public void testLogInResult() {
        LoginUseCase.LoginResult loginResult = LoginUseCase.LoginResult.FAILURE;
        assertEquals(LoginUseCase.LoginResult.valueOf("FAILURE"), loginResult);
    }

    @Test
    public void testRegisterResult() {
        LoginUseCase.RegisterResult registerResult = LoginUseCase.RegisterResult.FAILURE;
        assertEquals(LoginUseCase.RegisterResult.valueOf("FAILURE"), registerResult);
    }
}
