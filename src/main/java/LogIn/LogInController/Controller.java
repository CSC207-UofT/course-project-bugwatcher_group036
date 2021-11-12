package LogIn.LogInController;

import java.util.Scanner;
import LogIn.LogInUseCase.UserData;
import LogIn.LogInEntity.User;

public class Controller {
    public static void main(String[] args){
        System.out.println("Welcome to the registration and login interface!");
        while (true) {
            System.out.println("Press '1' for register, '2' for log in, '3' for exit");
            Scanner scanner = new Scanner(System.in);

            UserData userOperation = new UserData();
            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    System.out.println("Welcome to register system!");
                    System.out.println("Please enter your username.");
                    String username = scanner.nextLine();
                    System.out.println("Please enter your password.");
                    String password = scanner.nextLine();
                    User user = new User(username, password);
                    userOperation.register(user);
                    System.out.println("Register Succeed!");
                    break;
                case "2":
                    System.out.println("Welcome to log in system!");
                    System.out.println("username:");
                    String username0 = scanner.nextLine();
                    System.out.println("password:");
                    String password0 = scanner.nextLine();
                    User user1 = new User(username0, password0);
                    boolean flag = userOperation.logIn(user1);
                    if (flag) {
                        System.out.println("Log in Succeed!");
                        // add code here for playing the game
                    } else {
                        System.out.println("Log in failed!");
                    }
                    break;
                case "3":
                    System.exit(0);
                default:
                    break;
            }

        }
    }
}
