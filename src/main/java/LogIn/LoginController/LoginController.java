package LogIn.LoginController;

import LogIn.LoginUseCase.LoginInputBoundary;
import LogIn.LoginUseCase.LoginUseCase;

public class LoginController {

    private final LoginInputBoundary loginInputBoundary;



    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public boolean runLogin(String username, String password) {
        // Note: hands off the work to the use case class.
        LoginUseCase.LoginResult result = loginInputBoundary.login(username, password);
        return result == LoginUseCase.LoginResult.SUCCESS;
    }
    public boolean runRegister(String username, String password){
        LoginUseCase.RegisterResult result = loginInputBoundary.register(username, password);
        return result == LoginUseCase.RegisterResult.SUCCESS;
    }

    public LoginInputBoundary getLoginInputBoundary() {
        return loginInputBoundary;
    }
}
