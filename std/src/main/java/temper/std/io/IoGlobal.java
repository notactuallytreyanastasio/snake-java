package temper.std.io;
import temper.core.Core;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;
import temper.core.Nullable;
public final class IoGlobal {
    private IoGlobal() {
    }
    public static CompletableFuture<Optional<? super Object>> sleep(int ms__4) {
        throw Core.bubble();
    }
    public static CompletableFuture<@Nullable String> readLine() {
        throw Core.bubble();
    }
    public static int terminalColumns() {
        throw Core.bubble();
    }
    public static int terminalRows() {
        throw Core.bubble();
    }
}
