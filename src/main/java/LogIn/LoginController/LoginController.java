package LogIn.LoginController;

import LogIn.LoginUseCase.LoginInputBoundary;
import LogIn.LoginUseCase.LoginUseCase;

public class LoginController {

    private final LoginInputBoundary loginInputBoundary;

    /**
     * Initialize the Login Controller.
     */
    public LoginController() {
        LoginInputBoundary loginInputBoundary = new LoginUseCase(false);
        this.loginInputBoundary = loginInputBoundary;
    }

    /**
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Whether the username and password match.
     */
    public boolean runLogin(String username, String password) {
        // Note: hands off the work to the use case class.
        LoginUseCase.LoginResult result = loginInputBoundary.login(username, password);
        return result == LoginUseCase.LoginResult.SUCCESS;
    }

    /**
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Whether the username is available.
     */
    public boolean runRegister(String username, String password){
        LoginUseCase.RegisterResult result = loginInputBoundary.register(username, password);
        return result == LoginUseCase.RegisterResult.SUCCESS;
    }

    /**
     *
     * @return Get the interface to deal with the username and password.
     */
    public LoginInputBoundary getLoginInputBoundary() {
        return loginInputBoundary;
    }
}
