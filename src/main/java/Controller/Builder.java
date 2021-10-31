package Controller;

public interface Builder {

    void buildPlayerManager();

    void buildDeckManager();

    Controller buildController();

    Controller buildUnoController();
}
