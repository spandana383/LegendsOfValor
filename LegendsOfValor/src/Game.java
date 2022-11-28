import java.io.FileNotFoundException;

public interface Game {
    abstract void startARound();
    abstract void endARound();
    abstract void startGame() throws FileNotFoundException;
    abstract void endGame();
}
