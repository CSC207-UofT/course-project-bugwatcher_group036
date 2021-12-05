package LogIn.LoginUseCase;

import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserReadWriter;

import java.io.IOException;

public class LoginUseCase implements LoginInputBoundary {

    private final UserList users;

    UserReadWriter readWriter = new UserReadWriter();

    public enum LoginResult {
        SUCCESS, FAILURE // Should we do NO_SUCH_USER as well as SUCCESS and FAILURE?
    }

    public enum RegisterResult{
        SUCCESS, FAILURE
    }

    public LoginUseCase(UserList users) {
        this.users = users;
        try {
            readWriter.saveToFile("src\\main\\java\\DataSet\\users.ser", users);
        } catch (IOException e) {
            System.out.println("User list did not save.");
        }
    }

    public LoginUseCase(boolean register){
        UserList temp = new UserList();
        try{
            temp = readWriter.readFromFile("src\\main\\java\\DataSet\\users.ser");
        } catch (ClassNotFoundException | IOException e){
            System.out.println("No data found!");
        }
//        if (register) {
//            Scanner input = new Scanner(System.in);
//            System.out.println("Please decide your userName and password");
//            System.out.print("User Name: ");
//            String userName = input.nextLine();
//            System.out.print("Password: ");
//            String password = input.nextLine();
//            User newUser;
//            if (temp.getUser(userName) == null) {
//                newUser = new User(userName, password);
//                temp.add(newUser);
//            } else {
//                System.out.println("You've already registered, info is " + temp.getUser(userName));
//            }
//            try {
//                readWriter.saveToFile("src\\main\\java\\DataSet\\users.ser", temp);
//            } catch (IOException e) {
//                System.out.println("User list did not save.");
//            }
//
//        }
        this.users = temp;
    }

    public LoginResult login(String username, String password) {
        User user = users.getUser(username);
        if (user != null && user.passwordMatches(password)) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.FAILURE;
        }
    }

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

    public UserList getUsers() {
        return users;
    }
}
