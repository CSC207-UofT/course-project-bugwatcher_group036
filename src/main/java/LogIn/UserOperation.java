package LogIn;

import LogIn.User;

public interface UserOperation{

    public abstract void register(User user);

    public abstract boolean logIn(User user);

}



