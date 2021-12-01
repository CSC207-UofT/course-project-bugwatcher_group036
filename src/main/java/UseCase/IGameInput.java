package UseCase;

public interface IGameInput {

    void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest);

///////////////////////////////////////////////
    //Command Line Methods

    String runGame();

    String runGameForPVE() throws InterruptedException;
    EachRound getEachRound();
    void runGameforGUI(String text);
}
