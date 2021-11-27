package UseCase;

public interface IGameInput {

    void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest);

    String runGame();

    String runGameForPVE() throws InterruptedException;
    EachRound getEachRound();
    void runGameforGUI(String text);
}
