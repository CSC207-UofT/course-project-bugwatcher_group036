package LogIn;

import LogIn.LoginController.CmdLineUI;
import LogIn.LoginController.LoginController;
import LogIn.LoginGUI.LoginUI;
import LogIn.LoginUseCase.LoginUseCase;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class LoginStarter {

    public static @NotNull String login() {
        LoginUseCase useCase;
        String choice = loginInput();
        if (choice.equals("1")) {
            useCase = new LoginUseCase(false);
        } else if (choice.equals("2")) {
            useCase = new LoginUseCase(true);
        } else {
            return "Q";
        }
        LoginController controller = new LoginController(useCase); //controller
        CmdLineUI ui = new CmdLineUI(); //UI
        // Note how this differs from a previous example we saw, where
        // we "injected" the UI into the controller. Here, we are doing it
        // the other way. Are both ways consistent with the Dependency Rule?
        if (ui.runLogin(controller)) {
            return "T";
        }
        return "F";
    }

    public static String loginInput() {
        System.out.println("Welcome to the game center, enter \"1\" to login, " +
                "\"2\" to register, and \"3\" to leave");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.println("Invalid input, please enter again.");
            System.out.println("Enter \"1\" to login, \"2\" to register, and \"3\" to leave");
            choice = input.nextLine();
        }
        return choice;
    }

    public static void main(String[] args) {
        LoginUI ui = new LoginUI();

    }
}
