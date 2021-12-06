package UI;

import Controller.Controller;
import Controller.Presenter;
import LogIn.LogInEntity.UserStatistics;

public interface ModeBuilder {

    Controller buildController(int playerNum, Presenter presenter);

    Presenter buildPresenter();

    void buildGameFrame(UserStatistics stats);

}
