package LogIn;

import java.util.ArrayList;

public class UserData implements UserOperation{
    static ArrayList<User> users = new ArrayList<User>();

    public void register(User user){
        users.add(user);
    }

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