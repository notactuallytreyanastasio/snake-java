package snake_server;
import temper.core.Nullable;
import java.util.Optional;
import temper.core.Core;
import temper.core.Generator.DoneResult;
import temper.core.Generator.ValueResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import temper.std.ws.WsConnection;
import java.util.List;
import temper.core.Generator;
import temper.core.Generator.Result;
import snake.SnakeGlobal;
import temper.std.ws.WsGlobal;
import java.util.function.Function;
import snake.MultiSnakeGame;
import snake.PlayerSnake;
import snake.Right;
import temper.std.ws.WsServer;
import temper.std.io.IoGlobal;
import snake.Up;
import snake.Down;
import snake.Left;
import java.util.ArrayList;
import snake.Direction;
import snake.Dead;
import java.util.logging.Logger;
import temper.core.Core.Console;
import java.util.function.Supplier;
public final class SnakeServerGlobal {
    private SnakeServerGlobal() {
    }
    static final Console console_56;
    static int detectedCols__31;
    static int detectedRows__32;
    static final int boardWidth__33;
    static final int boardHeight__34;
    static MultiSnakeGame game__35;
    static List<WsConnection> wsConns__36;
    static int nextId__37;
    static boolean running__38;
    static Generator<Optional<? super Object>> fn__269() {
        class Local_1 {
            int caseIndex_283 = 0;
            int t_250 = 0;
            @Nullable List<PlayerSnake> t_251 = null;
            @Nullable PlayerSnake t_255 = null;
            @Nullable List<Direction> t_259 = null;
            @Nullable MultiSnakeGame t_260 = null;
            int t_263 = 0;
            boolean fail_72 = false;
            boolean fail_90 = false;
            boolean fail_91 = false;
            @Nullable CompletableFuture<Optional<? super Object>> promise_280 = null;
            @Nullable List<Direction> dirs__49 = null;
            int i__50 = 0;
            String frame__52 = "";
            @Nullable List<WsConnection> conns__53 = null;
            int ci__54 = 0;
            @Nullable CompletableFuture<Optional<? super Object>> promise_281 = null;
            @Nullable CompletableFuture<Optional<? super Object>> promise_282 = null;
        }
        final Local_1 local$1 = new Local_1();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_294 = generator_279 -> {
            while (true) {
                int caseIndexLocal_284 = local$1.caseIndex_283;
                local$1.caseIndex_283 = -1;
                switch (caseIndexLocal_284) {
                    case 0:
                        {
                        local$1.caseIndex_283 = 1;
                        break;
                    }
                    case 1:
                        {
                        if (game__35.getSnakes().size() != 0) {
                            local$1.caseIndex_283 = 2;
                        } else {
                            local$1.caseIndex_283 = 3;
                        }
                        break;
                    }
                    case 2:
                        {
                        console_56.log("Game starting!");
                        local$1.caseIndex_283 = 6;
                        break;
                    }
                    case 3:
                        {
                        local$1.promise_280 = Core.stdSleep(500);
                        local$1.caseIndex_283 = 4;
                        local$1.promise_280.handle((ignored$1, ignored$2) -> {
                                generator_279.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 4:
                        {
                        local$1.fail_72 = false;
                        try {
                            local$1.promise_280.get();
                        } catch (InterruptedException ignored$3) {
                            break;
                        } catch (ExecutionException ignored$4) {
                            local$1.fail_72 = true;
                        }
                        if (local$1.fail_72) {
                            local$1.caseIndex_283 = 17;
                        } else {
                            local$1.caseIndex_283 = 5;
                        }
                        break;
                    }
                    case 5:
                        {
                        local$1.caseIndex_283 = 1;
                        break;
                    }
                    case 6:
                        {
                        if (running__38) {
                            local$1.caseIndex_283 = 7;
                        } else {
                            local$1.caseIndex_283 = 17;
                        }
                        break;
                    }
                    case 7:
                        {
                        local$1.dirs__49 = new ArrayList<>();
                        local$1.caseIndex_283 = 8;
                        break;
                    }
                    case 8:
                        {
                        local$1.t_250 = game__35.getSnakes().size();
                        if (local$1.i__50 >= local$1.t_250) {
                            local$1.caseIndex_283 = 9;
                        } else {
                            local$1.caseIndex_283 = 10;
                        }
                        break;
                    }
                    case 9:
                        {
                        local$1.t_259 = List.copyOf(local$1.dirs__49);
                        local$1.t_260 = SnakeGlobal.multiTick(game__35, local$1.t_259);
                        game__35 = local$1.t_260;
                        local$1.frame__52 = SnakeGlobal.multiRender(game__35);
                        local$1.conns__53 = List.copyOf(wsConns__36);
                        local$1.caseIndex_283 = 11;
                        break;
                    }
                    case 10:
                        {
                        local$1.t_251 = game__35.getSnakes();
                        local$1.t_255 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                        PlayerSnake snake__51 = Core.listGetOr(local$1.t_251, local$1.i__50, local$1.t_255);
                        Core.listAdd(local$1.dirs__49, snake__51.getDirection());
                        local$1.i__50 = local$1.i__50 + 1;
                        local$1.caseIndex_283 = 8;
                        break;
                    }
                    case 11:
                        {
                        local$1.t_263 = local$1.conns__53.size();
                        if (local$1.ci__54 >= local$1.t_263) {
                            local$1.caseIndex_283 = 12;
                        } else {
                            local$1.caseIndex_283 = 13;
                        }
                        break;
                    }
                    case 12:
                        {
                        local$1.promise_281 = Core.stdSleep(200);
                        local$1.caseIndex_283 = 16;
                        local$1.promise_281.handle((ignored$5, ignored$6) -> {
                                generator_279.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 13:
                        {
                        WsConnection conn__55 = Core.listGet(local$1.conns__53, local$1.ci__54);
                        local$1.promise_282 = WsGlobal.wsSend(conn__55, local$1.frame__52);
                        local$1.caseIndex_283 = 14;
                        local$1.promise_282.handle((ignored$7, ignored$8) -> {
                                generator_279.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 14:
                        {
                        local$1.fail_90 = false;
                        try {
                            local$1.promise_282.get();
                        } catch (InterruptedException ignored$9) {
                            break;
                        } catch (ExecutionException ignored$10) {
                            local$1.fail_90 = true;
                        }
                        if (local$1.fail_90) {
                            local$1.caseIndex_283 = 15;
                        } else {
                            local$1.caseIndex_283 = 15;
                        }
                        break;
                    }
                    case 15:
                        {
                        local$1.ci__54 = local$1.ci__54 + 1;
                        local$1.caseIndex_283 = 11;
                        break;
                    }
                    case 16:
                        {
                        local$1.fail_91 = false;
                        try {
                            local$1.promise_281.get();
                        } catch (InterruptedException ignored$11) {
                            break;
                        } catch (ExecutionException ignored$12) {
                            local$1.fail_91 = true;
                        }
                        if (local$1.fail_91) {
                            local$1.caseIndex_283 = 17;
                        } else {
                            local$1.caseIndex_283 = 18;
                        }
                        break;
                    }
                    case 17:
                        {
                        return DoneResult.get();
                    }
                    case 18:
                        {
                        local$1.caseIndex_283 = 6;
                        break;
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_294 :: apply);
    }
    static Generator<Optional<? super Object>> fn__268() {
        class Local_2 {
            int caseIndex_300 = 0;
            String t_238 = "";
            @Nullable WsConnection t_132 = null;
            boolean fail_95 = false;
            boolean fail_98 = false;
            @Nullable WsServer server__40 = null;
            @Nullable CompletableFuture<WsServer> promise_298 = null;
            @Nullable WsConnection ws__41 = null;
            @Nullable CompletableFuture<WsConnection> promise_299 = null;
        }
        final Local_2 local$2 = new Local_2();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_309 = generator_297 -> {
            while (true) {
                int caseIndexLocal_301 = local$2.caseIndex_300;
                local$2.caseIndex_300 = -1;
                switch (caseIndexLocal_301) {
                    case 0:
                        {
                        console_56.log("Snake Multiplayer Server");
                        console_56.log("Starting on port 8080...");
                        local$2.promise_298 = WsGlobal.wsListen(8080);
                        local$2.caseIndex_300 = 1;
                        local$2.promise_298.handle((ignored$13, ignored$14) -> {
                                generator_297.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 1:
                        {
                        local$2.fail_95 = false;
                        try {
                            local$2.server__40 = local$2.promise_298.get();
                        } catch (InterruptedException ignored$15) {
                            break;
                        } catch (ExecutionException ignored$16) {
                            local$2.fail_95 = true;
                        }
                        if (local$2.fail_95) {
                            local$2.caseIndex_300 = 6;
                        } else {
                            local$2.caseIndex_300 = 2;
                        }
                        break;
                    }
                    case 2:
                        {
                        console_56.log("Listening on ws://localhost:8080");
                        console_56.log("Waiting for players to connect...");
                        local$2.caseIndex_300 = 3;
                        break;
                    }
                    case 3:
                        {
                        if (running__38) {
                            local$2.caseIndex_300 = 4;
                        } else {
                            local$2.caseIndex_300 = 6;
                        }
                        break;
                    }
                    case 4:
                        {
                        local$2.promise_299 = WsGlobal.wsAccept(local$2.server__40);
                        local$2.caseIndex_300 = 5;
                        local$2.promise_299.handle((ignored$17, ignored$18) -> {
                                generator_297.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 5:
                        {
                        local$2.fail_98 = false;
                        try {
                            local$2.t_132 = local$2.promise_299.get();
                        } catch (InterruptedException ignored$19) {
                            break;
                        } catch (ExecutionException ignored$20) {
                            local$2.fail_98 = true;
                        }
                        if (local$2.fail_98) {
                            local$2.caseIndex_300 = 6;
                        } else {
                            local$2.caseIndex_300 = 7;
                        }
                        break;
                    }
                    case 6:
                        {
                        return DoneResult.get();
                    }
                    case 7:
                        {
                        local$2.ws__41 = local$2.t_132;
                        int playerId__42 = nextId__37;
                        nextId__37 = nextId__37 + 1;
                        game__35 = SnakeGlobal.addPlayer(game__35, playerId__42 * 7 + 13);
                        Core.listAdd(wsConns__36, local$2.ws__41);
                        String symbol__43 = SnakeGlobal.playerHeadChar(playerId__42);
                        local$2.t_238 = Integer.toString(playerId__42);
                        console_56.log("Player " + local$2.t_238 + " (" + symbol__43 + ") connected!");
                        int connId__44 = playerId__42;
                        WsConnection connWs__45 = local$2.ws__41;
                        Supplier<Generator<Optional<? super Object>>> fn__231 = () -> {
                            class Local_3 {
                                int caseIndex_305 = 0;
                                boolean t_217 = false;
                                @Nullable Up t_218 = null;
                                @Nullable MultiSnakeGame t_219 = null;
                                @Nullable Down t_220 = null;
                                @Nullable MultiSnakeGame t_221 = null;
                                @Nullable Left t_222 = null;
                                @Nullable MultiSnakeGame t_223 = null;
                                @Nullable Right t_224 = null;
                                @Nullable MultiSnakeGame t_225 = null;
                                String t_226 = "";
                                @Nullable String t_116 = null;
                                String t_127 = "";
                                boolean fail_104 = false;
                                @Nullable String msg__47 = null;
                                @Nullable CompletableFuture<@Nullable String> promise_304 = null;
                            }
                            final Local_3 local$3 = new Local_3();
                            Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_308 = generator_303 -> {
                                while (true) {
                                    int caseIndexLocal_306 = local$3.caseIndex_305;
                                    local$3.caseIndex_305 = -1;
                                    switch (caseIndexLocal_306) {
                                        case 0:
                                            {
                                            local$3.caseIndex_305 = 1;
                                            break;
                                        }
                                        case 1:
                                            {
                                            if (running__38) {
                                                local$3.caseIndex_305 = 2;
                                            } else {
                                                local$3.caseIndex_305 = 4;
                                            }
                                            break;
                                        }
                                        case 2:
                                            {
                                            local$3.promise_304 = WsGlobal.wsRecv(connWs__45);
                                            local$3.caseIndex_305 = 3;
                                            local$3.promise_304.handle((ignored$21, ignored$22) -> {
                                                    generator_303.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 3:
                                            {
                                            local$3.fail_104 = false;
                                            try {
                                                local$3.t_116 = local$3.promise_304.get();
                                            } catch (InterruptedException ignored$23) {
                                                break;
                                            } catch (ExecutionException ignored$24) {
                                                local$3.fail_104 = true;
                                            }
                                            if (local$3.fail_104) {
                                                local$3.caseIndex_305 = 4;
                                            } else {
                                                local$3.caseIndex_305 = 5;
                                            }
                                            break;
                                        }
                                        case 4:
                                            {
                                            return DoneResult.get();
                                        }
                                        case 5:
                                            {
                                            local$3.msg__47 = local$3.t_116;
                                            if (local$3.msg__47 != null) {
                                                local$3.caseIndex_305 = 6;
                                            } else {
                                                local$3.caseIndex_305 = 7;
                                            }
                                            break;
                                        }
                                        case 6:
                                            {
                                            local$3.t_217 = local$3.msg__47 instanceof String;
                                            local$3.caseIndex_305 = 8;
                                            break;
                                        }
                                        case 7:
                                            {
                                            local$3.t_217 = false;
                                            local$3.caseIndex_305 = 8;
                                            break;
                                        }
                                        case 8:
                                            {
                                            if (local$3.t_217) {
                                                local$3.caseIndex_305 = 9;
                                            } else {
                                                local$3.caseIndex_305 = 10;
                                            }
                                            break;
                                        }
                                        case 9:
                                            {
                                            if (local$3.msg__47 == null) {
                                                local$3.caseIndex_305 = 11;
                                            } else {
                                                local$3.caseIndex_305 = 12;
                                            }
                                            break;
                                        }
                                        case 10:
                                            {
                                            local$3.t_226 = Integer.toString(connId__44);
                                            console_56.log("Player " + local$3.t_226 + " disconnected");
                                            local$3.caseIndex_305 = 4;
                                            break;
                                        }
                                        case 11:
                                            {
                                            Core.throwBubble();
                                            local$3.caseIndex_305 = 13;
                                            break;
                                        }
                                        case 12:
                                            {
                                            local$3.t_127 = local$3.msg__47;
                                            local$3.caseIndex_305 = 13;
                                            break;
                                        }
                                        case 13:
                                            {
                                            if (local$3.t_127.equals("u")) {
                                                local$3.caseIndex_305 = 14;
                                            } else {
                                                local$3.caseIndex_305 = 15;
                                            }
                                            break;
                                        }
                                        case 14:
                                            {
                                            local$3.t_218 = new Up();
                                            local$3.t_219 = SnakeGlobal.changePlayerDirection(game__35, connId__44, local$3.t_218);
                                            game__35 = local$3.t_219;
                                            local$3.caseIndex_305 = 21;
                                            break;
                                        }
                                        case 15:
                                            {
                                            if (local$3.t_127.equals("d")) {
                                                local$3.caseIndex_305 = 16;
                                            } else {
                                                local$3.caseIndex_305 = 17;
                                            }
                                            break;
                                        }
                                        case 16:
                                            {
                                            local$3.t_220 = new Down();
                                            local$3.t_221 = SnakeGlobal.changePlayerDirection(game__35, connId__44, local$3.t_220);
                                            game__35 = local$3.t_221;
                                            local$3.caseIndex_305 = 21;
                                            break;
                                        }
                                        case 17:
                                            {
                                            if (local$3.t_127.equals("l")) {
                                                local$3.caseIndex_305 = 18;
                                            } else {
                                                local$3.caseIndex_305 = 19;
                                            }
                                            break;
                                        }
                                        case 18:
                                            {
                                            local$3.t_222 = new Left();
                                            local$3.t_223 = SnakeGlobal.changePlayerDirection(game__35, connId__44, local$3.t_222);
                                            game__35 = local$3.t_223;
                                            local$3.caseIndex_305 = 21;
                                            break;
                                        }
                                        case 19:
                                            {
                                            if (local$3.t_127.equals("r")) {
                                                local$3.caseIndex_305 = 20;
                                            } else {
                                                local$3.caseIndex_305 = 21;
                                            }
                                            break;
                                        }
                                        case 20:
                                            {
                                            local$3.t_224 = new Right();
                                            local$3.t_225 = SnakeGlobal.changePlayerDirection(game__35, connId__44, local$3.t_224);
                                            game__35 = local$3.t_225;
                                            local$3.caseIndex_305 = 21;
                                            break;
                                        }
                                        case 21:
                                            {
                                            local$3.caseIndex_305 = 1;
                                            break;
                                        }
                                        default:
                                            {
                                            return DoneResult.get();
                                        }
                                    }
                                }
                            };
                            return Core.safeAdaptGeneratorFn(convertedCoroutine_308 :: apply);
                        };
                        Core.runAsync(fn__231);
                        local$2.caseIndex_300 = 3;
                        break;
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_309 :: apply);
    }
    static {
        console_56 = Core.getConsole(Logger.getLogger("snake_server"));
        detectedCols__31 = IoGlobal.terminalColumns();
        detectedRows__32 = IoGlobal.terminalRows();
        if (detectedCols__31 > 100) {
            boardWidth__33 = detectedCols__31 - 4;
        } else {
            boardWidth__33 = 80;
        }
        if (detectedRows__32 > 30) {
            boardHeight__34 = detectedRows__32 - 12;
        } else {
            boardHeight__34 = 30;
        }
        game__35 = SnakeGlobal.newMultiGame(boardWidth__33, boardHeight__34, 0, 42);
        wsConns__36 = new ArrayList<>();
        nextId__37 = 0;
        running__38 = true;
        Core.runAsync(SnakeServerGlobal :: fn__269);
        Core.runAsync(SnakeServerGlobal :: fn__268);
    }
}
