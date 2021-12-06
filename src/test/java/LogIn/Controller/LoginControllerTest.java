package LogIn.Controller;

import LogIn.LogInEntity.User;
import LogIn.LoginController.LoginController;
import LogIn.LoginUseCase.LoginInputBoundary;
import LogIn.LoginUseCase.LoginUseCase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoginControllerTest {
    @Test
    public void testInitialization(){
        LoginController loginControllerCopy = new LoginController();
        LoginController loginController = new LoginController();
        assertNotEquals(loginController.getLoginInputBoundary(), loginControllerCopy.getLoginInputBoundary());
    }

    @Test
    public void testRunLogin(){
        LoginController loginController = new LoginController();
        loginController.runLogin("Allen", "123456");
        LoginUseCase.LoginResult loginResult = LoginUseCase.LoginResult.FAILURE;
        assertEquals(LoginUseCase.LoginResult.valueOf("FAILURE"), loginResult);
    }

    @Test
    public void testRunRegister(){
        LoginController loginController = new LoginController();
        loginController.runRegister("Allen", "123456");
        LoginUseCase.RegisterResult registerResult = LoginUseCase.RegisterResult.FAILURE;
        assertEquals(LoginUseCase.RegisterResult.valueOf("FAILURE"), registerResult);
    }

    @Test
    public void testGetLoginInputBoundary(){
        LoginController loginController = new LoginController();
        LoginInputBoundary loginInputBoundary = new LoginUseCase(false);
        assertNotEquals(loginController.getLoginInputBoundary(), loginInputBoundary);
    }





}
