package LogIn.LogInUseCase;

    /**
     * The input boundary for the login use case.
     */
// Note: The interface that the LogInUseCase implements
// Note how it specifies what the input (arguments) and output (return type) are.
public interface LoginInterface {
    LoginUseCase.LoginResult logIn(String username, String password);
}

