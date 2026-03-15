package snake_server;
import temper.core.Core;
public final class SnakeServerMain {
    private SnakeServerMain() {
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Core.initSimpleLogging();
        Class.forName("snake_server.SnakeServerGlobal");
        Core.waitUntilTasksComplete();
    }
}
