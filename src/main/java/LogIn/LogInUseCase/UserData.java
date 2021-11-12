package LogIn.LogInUseCase;

import LogIn.LogInEntity.User;

import java.util.ArrayList;


public class UserData implements UserOperation {
    static ArrayList<User> users = new ArrayList<>();

    /**
     * Add an username to the system.
     *
     * @param user The User who want to play this game.
     */
    public void register(User user){
        users.add(user);
    }

    /**
     * return true is both username and password are right.
     *
     * @param user The User tries to log in.
     * @return If the username correspond to password or not.
     */
    public boolean logIn(User user){
        boolean flag = false;
        for (User user0 : users){
            if (user0.equal(user0)){
                flag = true;
                break;
            }
        }
        return flag;
    }


}