package UseCase;

public interface IGameInput {

    public void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest);

    public String runGame();

    public String runGameForPVE() throws InterruptedException;


}
