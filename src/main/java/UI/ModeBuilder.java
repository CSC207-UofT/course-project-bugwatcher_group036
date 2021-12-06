package UI;

import Controller.Controller;
import Controller.Presenter;
import LogIn.LogInEntity.UserStatistics;

/**
 * The Interface for Mode Builder.
 */
public interface ModeBuilder {

    Controller buildController(int playerNum, Presenter presenter);

    Presenter buildPresenter();

    void buildGameFrame(UserStatistics stats);

}
