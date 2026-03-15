package snake_game;
import temper.core.Core;
public final class SnakeGameMain {
    private SnakeGameMain() {
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Core.initSimpleLogging();
        Class.forName("snake_game.SnakeGameGlobal");
        Core.waitUntilTasksComplete();
    }
}
