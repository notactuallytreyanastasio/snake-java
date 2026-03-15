package snake_client;
import temper.core.Core;
public final class SnakeClientMain {
    private SnakeClientMain() {
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Core.initSimpleLogging();
        Class.forName("snake_client.SnakeClientGlobal");
        Core.waitUntilTasksComplete();
    }
}
