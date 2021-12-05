package UseCase;

import Entity.ReadFile;
import LogIn.LogInEntity.UserStatistics;

public interface IGameInput {

    void buildIEachRound(IPresenter iPresenter, GameRequest gameRequest, ReadFile gateway);

    EachRound getEachRound();

    void runGameforGUI(String text, UserStatistics stats);
}
