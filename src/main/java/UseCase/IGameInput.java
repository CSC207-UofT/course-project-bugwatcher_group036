package UseCase;

import LogIn.LogInEntity.UserStatistics;

/**
 * The Interface of IGameInput.
 */
public interface IGameInput {

    void buildIEachRound(IPresenter iPresenter, GameRequest gameRequest, ReadFile gateway);

    EachRound getEachRound();

    void runGameforGUI(String text, UserStatistics stats);
}
