package LogIn.LogInController;

import LogIn.LogInUseCase.LoginInputBoundary;
import LogIn.LogInUseCase.LoginUseCase;

public class LoginController {

    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public boolean runLogin(String username, String password) {
        // Note: hands off the work to the use case class.
        LoginUseCase.LoginResult result = loginInputBoundary.logIn(username, password);
        return result == LoginUseCase.LoginResult.SUCCESS;
    }
}
