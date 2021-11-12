package LogIn.LogInUseCase;

import LogIn.LogInEntity.User;

public interface UserOperation {

    void register(User user);

    boolean logIn(User user);

}



