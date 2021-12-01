package UseCase;

import LogIn.LogInEntity.UserStatistics;

public interface IGameInput {

    void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest);

///////////////////////////////////////////////
    //Command Line Methods

    EachRound getEachRound();
    void runGameforGUI(String text, UserStatistics stats);
}
