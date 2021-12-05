package LogIn.LoginUseCase;

import LogIn.LogInEntity.UserList;

/**
 * The interface to deal with data.
 */
public interface LoginInputBoundary {
    LoginUseCase.LoginResult login(String username, String password);
    LoginUseCase.RegisterResult register(String username, String password);
    UserList getUsers();
}
