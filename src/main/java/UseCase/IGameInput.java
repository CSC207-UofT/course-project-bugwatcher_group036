package UseCase;

import LogIn.LogInEntity.UserStatistics;

public interface IGameInput {

    void buildIEachRound(IPresenter iPresenter, GameRequest gameRequest);

    EachRound getEachRound();

    void runGameforGUI(String text, UserStatistics stats);
}
