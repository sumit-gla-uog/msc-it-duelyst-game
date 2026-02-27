package structures;
public class GameStateStore {

    private static final GameStateStore INSTANCE = new GameStateStore();
    private final GameState gameState;

    private GameStateStore() {
        this.gameState = new GameState();
    }

    public static GameStateStore getInstance() {
        return INSTANCE;
    }

    public GameState getGameState() {
        return gameState;
    }
}
