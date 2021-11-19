package LogIn.LogInUseCase;

import java.io.IOException;
import LogIn.LogInController.UserDataReader;
import LogIn.LogInEntity.User;

public class LoginUseCase implements LoginInterface{
    private final UserMap users;
    UserDataReader DataReader = new UserDataReader();
    public enum LoginResult{
        S, F
    }
    public LoginUseCase(UserMap users) {
        this.users = users;
        try {
            DataReader.saveToFile("users.ser", users);
        } catch (IOException e) {
            System.out.println("User map did not save.");
        }
    }

    /**
     * Run the login use case.
     * @param username the username
     * @param password the password attempt
     * @return whether the attempt matches the password associated with username
     */
    public LoginResult logIn(String username, String password) {
        User user = users.getUser(username);
        if (user.match(password)) {
            return LoginResult.S;
        } else {
            return LoginResult.F;
        }
    }
}
