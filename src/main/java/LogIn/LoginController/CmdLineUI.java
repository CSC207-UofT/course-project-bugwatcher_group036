package LogIn.LoginController;

import java.util.Scanner;

public class CmdLineUI {
    public boolean runLogin(LoginController controller) {
        System.out.println("Username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        return controller.runLogin(username, password);
    }
}
