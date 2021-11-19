package LogIn.LogInUI;

import java.util.Scanner;
import LogIn.LogInController.LoginController;

public class CmdLineUI {
    public void runLogin(LoginController controller) {
        System.out.println("Username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        controller.runLogin(username, password);
    }
}
