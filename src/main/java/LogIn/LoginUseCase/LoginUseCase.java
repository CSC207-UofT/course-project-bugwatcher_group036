package LogIn.LoginUseCase;

import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserReadWriter;

import java.io.IOException;

public class LoginUseCase implements LoginInputBoundary {
    private final UserList users;
    UserReadWriter readWriter = new UserReadWriter();

    /**
     * The result of login.
     */
    public enum LoginResult {
        SUCCESS, FAILURE // Should we do NO_SUCH_USER as well as SUCCESS and FAILURE?
    }

    /**
     * The result of register.
     */
    public enum RegisterResult{
        SUCCESS, FAILURE
    }

    /**
     * Saving the list into the file.
     * @param users The user list need to save.
     */
    public LoginUseCase(UserList users) {
        this.users = users;
        try {
            readWriter.saveToFile("src\\main\\java\\DataSet\\users.ser", users);
        } catch (IOException e) {
            System.out.println("User list did not save.");
        }
    }

    /**
     * Read the list from the file.
     * @param register The user list need to save.
     */
    public LoginUseCase(boolean register){
        UserList temp = new UserList();
        try{
            temp = readWriter.readFromFile("src\\main\\java\\DataSet\\users.ser");
        } catch (ClassNotFoundException | IOException e){
            System.out.println("No data found!");
        }
        this.users = temp;
    }

    /**
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Whether the login is successful.
     */
    public LoginResult login(String username, String password) {
        User user = users.getUser(username);
        if (user != null && user.passwordMatches(password)) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.FAILURE;
        }
    }

    /**
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Whether the register is successful.
     */
    public RegisterResult register(String username, String password){
        User newUser;
        if (this.users.getUser(username) == null) {
                newUser = new User(username, password);
                this.users.add(newUser);
            try {
                readWriter.saveToFile("src\\main\\java\\DataSet\\users.ser", this.users);
            } catch (IOException e) {
                System.out.println("User list did not save.");
            }
                return RegisterResult.SUCCESS;
            } else {
                System.out.println("You've already registered, info is " + this.users.getUser(username));
                return RegisterResult.FAILURE;
            }
    }

    /**
     *
     * @return Get the users.
     */
    public UserList getUsers() {
        return users;
    }
}
