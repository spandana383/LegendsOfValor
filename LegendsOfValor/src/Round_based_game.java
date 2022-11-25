import java.io.FileNotFoundException;

public interface Round_based_game {
    abstract void startARound();
    abstract void endARound();
    abstract void startGame() throws FileNotFoundException;
    abstract void endGame();
}
