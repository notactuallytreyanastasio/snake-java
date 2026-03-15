package snake;
import temper.core.Core;
public final class SnakeMain {
    private SnakeMain() {
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Core.initSimpleLogging();
        Class.forName("snake.SnakeGlobal");
        Core.waitUntilTasksComplete();
    }
}
