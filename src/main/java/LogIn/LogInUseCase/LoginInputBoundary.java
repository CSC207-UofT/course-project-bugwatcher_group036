package LogIn.LogInUseCase;

public interface LoginInputBoundary {
    LoginUseCase.LoginResult logIn(String username, String password);
}
