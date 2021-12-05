package LogIn.LoginUseCase;

import LogIn.LogInEntity.UserList;

public interface LoginInputBoundary {
    LoginUseCase.LoginResult login(String username, String password);
    LoginUseCase.RegisterResult register(String username, String password);
    UserList getUsers();
}
