package temper.std.ws;
import java.util.concurrent.CompletableFuture;
import temper.core.Core;
import java.util.Optional;
import temper.core.Nullable;
public final class WsGlobal {
    private WsGlobal() {
    }
    /**
     * &#42;wsListen&#42; starts a WebSocket server on the given port and resolves when
     * it is ready to accept connections.
     */
    public static CompletableFuture<WsServer> wsListen(int port__6) {
        throw Core.bubble();
    }
    /**
     * &#42;wsAccept&#42; waits for and accepts the next incoming connection on a server.
     */
    public static CompletableFuture<WsConnection> wsAccept(WsServer server__8) {
        throw Core.bubble();
    }
    /**
     * &#42;wsConnect&#42; opens a WebSocket connection to the given URL
     * (e.g. `"ws://localhost:8080"`).
     */
    public static CompletableFuture<WsConnection> wsConnect(String url__10) {
        throw Core.bubble();
    }
    /**
     * &#42;wsSend&#42; sends a text message over a connection.
     */
    public static CompletableFuture<Optional<? super Object>> wsSend(WsConnection conn__12, String msg__13) {
        throw Core.bubble();
    }
    /**
     * &#42;wsRecv&#42; waits for the next message from a connection.
     * Returns `null` if the connection is closed.
     */
    public static CompletableFuture<@Nullable String> wsRecv(WsConnection conn__15) {
        throw Core.bubble();
    }
    /**
     * &#42;wsClose&#42; closes a connection.
     */
    public static CompletableFuture<Optional<? super Object>> wsClose(WsConnection conn__17) {
        throw Core.bubble();
    }
}
