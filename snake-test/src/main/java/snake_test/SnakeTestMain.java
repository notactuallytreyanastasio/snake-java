package snake_test;
import temper.core.Core;
public final class SnakeTestMain {
    private SnakeTestMain() {
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Core.initSimpleLogging();
        Class.forName("snake_test.SnakeTestGlobal");
        Core.waitUntilTasksComplete();
    }
}
