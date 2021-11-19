package LogIn.LogInController;

import LogIn.LogInUseCase.UserMap;
import LogIn.LogInUseCase.LoginUseCase;
import LogIn.LogInEntity.User;
import LogIn.LogInUI.CmdLineUI;

public class Main {

    public static void main(String[] args) {
        // Note how we are "cheating" here and directly creating objects from all of our
        // layers of Clean Architecture in main. This is fine for the purposes
        // of this little demo, but your main method shouldn't generally look like this.

        // Set up a list of users to store in the LoginUseCase so we can try things out
        // for our demo.
        UserMap users = new UserMap();
        users.add(new User("paul", "password")); //entity
        LoginUseCase useCase = new LoginUseCase(users); //use case
        LoginController controller = new LoginController(useCase); //controller
        CmdLineUI ui = new CmdLineUI(); //UI
        // Note how this differs from a previous example we saw, where
        // we "injected" the UI into the controller. Here, we are doing it
        // the other way. Are both ways consistent with the Dependency Rule?
        ui.runLogin(controller);
    }
}