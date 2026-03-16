package snake_server;
import temper.core.Nullable;
import temper.core.Core;
import java.util.Optional;
import temper.core.Generator.DoneResult;
import temper.core.Generator.ValueResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import temper.std.ws.WsConnection;
import java.util.List;
import snake.SnakeGlobal;
import temper.core.Generator;
import temper.std.ws.WsGlobal;
import snake.MultiSnakeGame;
import temper.core.Generator.Result;
import java.util.function.Function;
import snake.Right;
import snake.Up;
import snake.Down;
import snake.Left;
import snake.PlayerSnake;
import temper.std.ws.WsServer;
import temper.std.io.IoGlobal;
import java.util.ArrayList;
import snake.Direction;
import snake.Dead;
import java.util.logging.Logger;
import temper.core.Core.Console;
import java.util.function.Consumer;
import java.util.function.Supplier;
public final class SnakeServerGlobal {
    private SnakeServerGlobal() {
    }
    static final Console console_61;
    static int detectedCols__35;
    static int detectedRows__36;
    static final int boardWidth__37;
    static final int boardHeight__38;
    static MultiSnakeGame game__39;
    static List<WsConnection> wsConns__40;
    static int nextId__41;
    static boolean running__42;
    static Generator<Optional<? super Object>> fn__330() {
        class Local_1 {
            int caseIndex_343 = 0;
            int t_311 = 0;
            @Nullable List<PlayerSnake> t_312 = null;
            @Nullable PlayerSnake t_316 = null;
            @Nullable List<Direction> t_320 = null;
            @Nullable MultiSnakeGame t_321 = null;
            boolean fail_80 = false;
            boolean fail_97 = false;
            @Nullable CompletableFuture<Optional<? super Object>> promise_341 = null;
            @Nullable List<Direction> dirs__55 = null;
            int i__56 = 0;
            @Nullable CompletableFuture<Optional<? super Object>> promise_342 = null;
        }
        final Local_1 local$1 = new Local_1();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_353 = generator_340 -> {
            while (true) {
                int caseIndexLocal_344 = local$1.caseIndex_343;
                local$1.caseIndex_343 = -1;
                switch (caseIndexLocal_344) {
                    case 0:
                        {
                        local$1.caseIndex_343 = 1;
                        break;
                    }
                    case 1:
                        {
                        if (game__39.getSnakes().size() != 0) {
                            local$1.caseIndex_343 = 2;
                        } else {
                            local$1.caseIndex_343 = 3;
                        }
                        break;
                    }
                    case 2:
                        {
                        console_61.log("Game starting!");
                        local$1.caseIndex_343 = 6;
                        break;
                    }
                    case 3:
                        {
                        local$1.promise_341 = Core.stdSleep(500);
                        local$1.caseIndex_343 = 4;
                        local$1.promise_341.handle((ignored$1, ignored$2) -> {
                                generator_340.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 4:
                        {
                        local$1.fail_80 = false;
                        try {
                            local$1.promise_341.get();
                        } catch (InterruptedException ignored$3) {
                            break;
                        } catch (ExecutionException ignored$4) {
                            local$1.fail_80 = true;
                        }
                        if (local$1.fail_80) {
                            local$1.caseIndex_343 = 12;
                        } else {
                            local$1.caseIndex_343 = 5;
                        }
                        break;
                    }
                    case 5:
                        {
                        local$1.caseIndex_343 = 1;
                        break;
                    }
                    case 6:
                        {
                        if (running__42) {
                            local$1.caseIndex_343 = 7;
                        } else {
                            local$1.caseIndex_343 = 12;
                        }
                        break;
                    }
                    case 7:
                        {
                        local$1.dirs__55 = new ArrayList<>();
                        local$1.caseIndex_343 = 8;
                        break;
                    }
                    case 8:
                        {
                        local$1.t_311 = game__39.getSnakes().size();
                        if (local$1.i__56 >= local$1.t_311) {
                            local$1.caseIndex_343 = 9;
                        } else {
                            local$1.caseIndex_343 = 10;
                        }
                        break;
                    }
                    case 9:
                        {
                        local$1.t_320 = List.copyOf(local$1.dirs__55);
                        local$1.t_321 = SnakeGlobal.multiTick(game__39, local$1.t_320);
                        game__39 = local$1.t_321;
                        String frame__58 = SnakeGlobal.multiRender(game__39);
                        List<WsConnection> conns__59 = List.copyOf(wsConns__40);
                        Consumer<WsConnection> fn__305 = conn__60 -> {
                            WsGlobal.wsSend(conn__60, frame__58);
                        };
                        conns__59.forEach(fn__305);
                        local$1.promise_342 = Core.stdSleep(200);
                        local$1.caseIndex_343 = 11;
                        local$1.promise_342.handle((ignored$5, ignored$6) -> {
                                generator_340.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 10:
                        {
                        local$1.t_312 = game__39.getSnakes();
                        local$1.t_316 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                        PlayerSnake snake__57 = Core.listGetOr(local$1.t_312, local$1.i__56, local$1.t_316);
                        Core.listAdd(local$1.dirs__55, snake__57.getDirection());
                        local$1.i__56 = local$1.i__56 + 1;
                        local$1.caseIndex_343 = 8;
                        break;
                    }
                    case 11:
                        {
                        local$1.fail_97 = false;
                        try {
                            local$1.promise_342.get();
                        } catch (InterruptedException ignored$7) {
                            break;
                        } catch (ExecutionException ignored$8) {
                            local$1.fail_97 = true;
                        }
                        if (local$1.fail_97) {
                            local$1.caseIndex_343 = 12;
                        } else {
                            local$1.caseIndex_343 = 13;
                        }
                        break;
                    }
                    case 12:
                        {
                        return DoneResult.get();
                    }
                    case 13:
                        {
                        local$1.caseIndex_343 = 6;
                        break;
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_353 :: apply);
    }
    static Generator<Optional<? super Object>> fn__329() {
        class Local_2 {
            int caseIndex_360 = 0;
            boolean t_284 = false;
            @Nullable MultiSnakeGame t_287 = null;
            String t_289 = "";
            boolean t_291 = false;
            @Nullable Up t_292 = null;
            @Nullable MultiSnakeGame t_293 = null;
            @Nullable Down t_294 = null;
            @Nullable MultiSnakeGame t_295 = null;
            @Nullable Left t_296 = null;
            @Nullable MultiSnakeGame t_297 = null;
            @Nullable Right t_298 = null;
            @Nullable MultiSnakeGame t_299 = null;
            @Nullable WsConnection t_151 = null;
            @Nullable String t_152 = null;
            @Nullable String t_154 = null;
            String t_156 = "";
            String t_171 = "";
            boolean fail_101 = false;
            boolean fail_104 = false;
            boolean fail_105 = false;
            @Nullable WsServer server__44 = null;
            @Nullable CompletableFuture<WsServer> promise_357 = null;
            @Nullable WsConnection ws__45 = null;
            @Nullable CompletableFuture<WsConnection> promise_358 = null;
            @Nullable String firstMsgRaw__46 = null;
            @Nullable CompletableFuture<@Nullable String> promise_359 = null;
            boolean isSpectator__47 = false;
            int playerId__48 = 0;
        }
        final Local_2 local$2 = new Local_2();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_369 = generator_356 -> {
            while (true) {
                int caseIndexLocal_361 = local$2.caseIndex_360;
                local$2.caseIndex_360 = -1;
                switch (caseIndexLocal_361) {
                    case 0:
                        {
                        console_61.log("Snake Multiplayer Server");
                        console_61.log("Starting on port 8080...");
                        local$2.promise_357 = WsGlobal.wsListen(8080);
                        local$2.caseIndex_360 = 1;
                        local$2.promise_357.handle((ignored$9, ignored$10) -> {
                                generator_356.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 1:
                        {
                        local$2.fail_101 = false;
                        try {
                            local$2.server__44 = local$2.promise_357.get();
                        } catch (InterruptedException ignored$11) {
                            break;
                        } catch (ExecutionException ignored$12) {
                            local$2.fail_101 = true;
                        }
                        if (local$2.fail_101) {
                            local$2.caseIndex_360 = 6;
                        } else {
                            local$2.caseIndex_360 = 2;
                        }
                        break;
                    }
                    case 2:
                        {
                        console_61.log("Listening on ws://localhost:8080");
                        console_61.log("Waiting for players to connect...");
                        local$2.caseIndex_360 = 3;
                        break;
                    }
                    case 3:
                        {
                        if (running__42) {
                            local$2.caseIndex_360 = 4;
                        } else {
                            local$2.caseIndex_360 = 6;
                        }
                        break;
                    }
                    case 4:
                        {
                        local$2.promise_358 = WsGlobal.wsAccept(local$2.server__44);
                        local$2.caseIndex_360 = 5;
                        local$2.promise_358.handle((ignored$13, ignored$14) -> {
                                generator_356.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 5:
                        {
                        local$2.fail_104 = false;
                        try {
                            local$2.t_151 = local$2.promise_358.get();
                        } catch (InterruptedException ignored$15) {
                            break;
                        } catch (ExecutionException ignored$16) {
                            local$2.fail_104 = true;
                        }
                        if (local$2.fail_104) {
                            local$2.caseIndex_360 = 6;
                        } else {
                            local$2.caseIndex_360 = 7;
                        }
                        break;
                    }
                    case 6:
                        {
                        return DoneResult.get();
                    }
                    case 7:
                        {
                        local$2.ws__45 = local$2.t_151;
                        local$2.promise_359 = WsGlobal.wsRecv(local$2.ws__45);
                        local$2.caseIndex_360 = 8;
                        local$2.promise_359.handle((ignored$17, ignored$18) -> {
                                generator_356.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 8:
                        {
                        local$2.fail_105 = false;
                        try {
                            local$2.t_152 = local$2.promise_359.get();
                        } catch (InterruptedException ignored$19) {
                            break;
                        } catch (ExecutionException ignored$20) {
                            local$2.fail_105 = true;
                        }
                        if (local$2.fail_105) {
                            local$2.caseIndex_360 = 9;
                        } else {
                            local$2.caseIndex_360 = 10;
                        }
                        break;
                    }
                    case 9:
                        {
                        local$2.t_154 = null;
                        local$2.caseIndex_360 = 11;
                        break;
                    }
                    case 10:
                        {
                        local$2.t_154 = local$2.t_152;
                        local$2.caseIndex_360 = 11;
                        break;
                    }
                    case 11:
                        {
                        local$2.firstMsgRaw__46 = local$2.t_154;
                        if (local$2.firstMsgRaw__46 != null) {
                            local$2.caseIndex_360 = 12;
                        } else {
                            local$2.caseIndex_360 = 13;
                        }
                        break;
                    }
                    case 12:
                        {
                        local$2.t_284 = local$2.firstMsgRaw__46 instanceof String;
                        local$2.caseIndex_360 = 14;
                        break;
                    }
                    case 13:
                        {
                        local$2.t_284 = false;
                        local$2.caseIndex_360 = 14;
                        break;
                    }
                    case 14:
                        {
                        if (local$2.t_284) {
                            local$2.caseIndex_360 = 15;
                        } else {
                            local$2.caseIndex_360 = 20;
                        }
                        break;
                    }
                    case 15:
                        {
                        if (local$2.firstMsgRaw__46 == null) {
                            local$2.caseIndex_360 = 16;
                        } else {
                            local$2.caseIndex_360 = 17;
                        }
                        break;
                    }
                    case 16:
                        {
                        Core.throwBubble();
                        local$2.caseIndex_360 = 18;
                        break;
                    }
                    case 17:
                        {
                        local$2.t_156 = local$2.firstMsgRaw__46;
                        local$2.caseIndex_360 = 18;
                        break;
                    }
                    case 18:
                        {
                        if (local$2.t_156.equals("spectate")) {
                            local$2.caseIndex_360 = 19;
                        } else {
                            local$2.caseIndex_360 = 20;
                        }
                        break;
                    }
                    case 19:
                        {
                        local$2.isSpectator__47 = true;
                        local$2.caseIndex_360 = 20;
                        break;
                    }
                    case 20:
                        {
                        if (local$2.isSpectator__47) {
                            local$2.caseIndex_360 = 21;
                        } else {
                            local$2.caseIndex_360 = 22;
                        }
                        break;
                    }
                    case 21:
                        {
                        Core.listAdd(wsConns__40, local$2.ws__45);
                        console_61.log("Spectator connected!");
                        local$2.caseIndex_360 = 38;
                        break;
                    }
                    case 22:
                        {
                        local$2.playerId__48 = nextId__41;
                        nextId__41 = nextId__41 + 1;
                        local$2.t_287 = SnakeGlobal.addPlayer(game__39, local$2.playerId__48 * 7 + 13);
                        game__39 = local$2.t_287;
                        Core.listAdd(wsConns__40, local$2.ws__45);
                        String symbol__49 = SnakeGlobal.playerHeadChar(local$2.playerId__48);
                        local$2.t_289 = Integer.toString(local$2.playerId__48);
                        console_61.log("Player " + local$2.t_289 + " (" + symbol__49 + ") connected!");
                        if (local$2.firstMsgRaw__46 != null) {
                            local$2.caseIndex_360 = 23;
                        } else {
                            local$2.caseIndex_360 = 24;
                        }
                        break;
                    }
                    case 23:
                        {
                        local$2.t_291 = local$2.firstMsgRaw__46 instanceof String;
                        local$2.caseIndex_360 = 25;
                        break;
                    }
                    case 24:
                        {
                        local$2.t_291 = false;
                        local$2.caseIndex_360 = 25;
                        break;
                    }
                    case 25:
                        {
                        if (local$2.t_291) {
                            local$2.caseIndex_360 = 26;
                        } else {
                            local$2.caseIndex_360 = 37;
                        }
                        break;
                    }
                    case 26:
                        {
                        if (local$2.firstMsgRaw__46 == null) {
                            local$2.caseIndex_360 = 27;
                        } else {
                            local$2.caseIndex_360 = 28;
                        }
                        break;
                    }
                    case 27:
                        {
                        Core.throwBubble();
                        local$2.caseIndex_360 = 29;
                        break;
                    }
                    case 28:
                        {
                        local$2.t_171 = local$2.firstMsgRaw__46;
                        local$2.caseIndex_360 = 29;
                        break;
                    }
                    case 29:
                        {
                        if (local$2.t_171.equals("u")) {
                            local$2.caseIndex_360 = 30;
                        } else {
                            local$2.caseIndex_360 = 31;
                        }
                        break;
                    }
                    case 30:
                        {
                        local$2.t_292 = new Up();
                        local$2.t_293 = SnakeGlobal.changePlayerDirection(game__39, local$2.playerId__48, local$2.t_292);
                        game__39 = local$2.t_293;
                        local$2.caseIndex_360 = 37;
                        break;
                    }
                    case 31:
                        {
                        if (local$2.t_171.equals("d")) {
                            local$2.caseIndex_360 = 32;
                        } else {
                            local$2.caseIndex_360 = 33;
                        }
                        break;
                    }
                    case 32:
                        {
                        local$2.t_294 = new Down();
                        local$2.t_295 = SnakeGlobal.changePlayerDirection(game__39, local$2.playerId__48, local$2.t_294);
                        game__39 = local$2.t_295;
                        local$2.caseIndex_360 = 37;
                        break;
                    }
                    case 33:
                        {
                        if (local$2.t_171.equals("l")) {
                            local$2.caseIndex_360 = 34;
                        } else {
                            local$2.caseIndex_360 = 35;
                        }
                        break;
                    }
                    case 34:
                        {
                        local$2.t_296 = new Left();
                        local$2.t_297 = SnakeGlobal.changePlayerDirection(game__39, local$2.playerId__48, local$2.t_296);
                        game__39 = local$2.t_297;
                        local$2.caseIndex_360 = 37;
                        break;
                    }
                    case 35:
                        {
                        if (local$2.t_171.equals("r")) {
                            local$2.caseIndex_360 = 36;
                        } else {
                            local$2.caseIndex_360 = 37;
                        }
                        break;
                    }
                    case 36:
                        {
                        local$2.t_298 = new Right();
                        local$2.t_299 = SnakeGlobal.changePlayerDirection(game__39, local$2.playerId__48, local$2.t_298);
                        game__39 = local$2.t_299;
                        local$2.caseIndex_360 = 37;
                        break;
                    }
                    case 37:
                        {
                        int connId__50 = local$2.playerId__48;
                        WsConnection connWs__51 = local$2.ws__45;
                        Supplier<Generator<Optional<? super Object>>> fn__279 = () -> {
                            class Local_3 {
                                int caseIndex_366 = 0;
                                boolean t_265 = false;
                                @Nullable Up t_266 = null;
                                @Nullable MultiSnakeGame t_267 = null;
                                @Nullable Down t_268 = null;
                                @Nullable MultiSnakeGame t_269 = null;
                                @Nullable Left t_270 = null;
                                @Nullable MultiSnakeGame t_271 = null;
                                @Nullable Right t_272 = null;
                                @Nullable MultiSnakeGame t_273 = null;
                                String t_274 = "";
                                @Nullable String t_135 = null;
                                String t_146 = "";
                                boolean fail_123 = false;
                                @Nullable String msg__53 = null;
                                @Nullable CompletableFuture<@Nullable String> promise_365 = null;
                            }
                            final Local_3 local$3 = new Local_3();
                            Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_368 = generator_364 -> {
                                while (true) {
                                    int caseIndexLocal_367 = local$3.caseIndex_366;
                                    local$3.caseIndex_366 = -1;
                                    switch (caseIndexLocal_367) {
                                        case 0:
                                            {
                                            local$3.caseIndex_366 = 1;
                                            break;
                                        }
                                        case 1:
                                            {
                                            if (running__42) {
                                                local$3.caseIndex_366 = 2;
                                            } else {
                                                local$3.caseIndex_366 = 4;
                                            }
                                            break;
                                        }
                                        case 2:
                                            {
                                            local$3.promise_365 = WsGlobal.wsRecv(connWs__51);
                                            local$3.caseIndex_366 = 3;
                                            local$3.promise_365.handle((ignored$21, ignored$22) -> {
                                                    generator_364.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 3:
                                            {
                                            local$3.fail_123 = false;
                                            try {
                                                local$3.t_135 = local$3.promise_365.get();
                                            } catch (InterruptedException ignored$23) {
                                                break;
                                            } catch (ExecutionException ignored$24) {
                                                local$3.fail_123 = true;
                                            }
                                            if (local$3.fail_123) {
                                                local$3.caseIndex_366 = 4;
                                            } else {
                                                local$3.caseIndex_366 = 5;
                                            }
                                            break;
                                        }
                                        case 4:
                                            {
                                            return DoneResult.get();
                                        }
                                        case 5:
                                            {
                                            local$3.msg__53 = local$3.t_135;
                                            if (local$3.msg__53 != null) {
                                                local$3.caseIndex_366 = 6;
                                            } else {
                                                local$3.caseIndex_366 = 7;
                                            }
                                            break;
                                        }
                                        case 6:
                                            {
                                            local$3.t_265 = local$3.msg__53 instanceof String;
                                            local$3.caseIndex_366 = 8;
                                            break;
                                        }
                                        case 7:
                                            {
                                            local$3.t_265 = false;
                                            local$3.caseIndex_366 = 8;
                                            break;
                                        }
                                        case 8:
                                            {
                                            if (local$3.t_265) {
                                                local$3.caseIndex_366 = 9;
                                            } else {
                                                local$3.caseIndex_366 = 10;
                                            }
                                            break;
                                        }
                                        case 9:
                                            {
                                            if (local$3.msg__53 == null) {
                                                local$3.caseIndex_366 = 11;
                                            } else {
                                                local$3.caseIndex_366 = 12;
                                            }
                                            break;
                                        }
                                        case 10:
                                            {
                                            local$3.t_274 = Integer.toString(connId__50);
                                            console_61.log("Player " + local$3.t_274 + " disconnected");
                                            local$3.caseIndex_366 = 4;
                                            break;
                                        }
                                        case 11:
                                            {
                                            Core.throwBubble();
                                            local$3.caseIndex_366 = 13;
                                            break;
                                        }
                                        case 12:
                                            {
                                            local$3.t_146 = local$3.msg__53;
                                            local$3.caseIndex_366 = 13;
                                            break;
                                        }
                                        case 13:
                                            {
                                            if (local$3.t_146.equals("u")) {
                                                local$3.caseIndex_366 = 14;
                                            } else {
                                                local$3.caseIndex_366 = 15;
                                            }
                                            break;
                                        }
                                        case 14:
                                            {
                                            local$3.t_266 = new Up();
                                            local$3.t_267 = SnakeGlobal.changePlayerDirection(game__39, connId__50, local$3.t_266);
                                            game__39 = local$3.t_267;
                                            local$3.caseIndex_366 = 21;
                                            break;
                                        }
                                        case 15:
                                            {
                                            if (local$3.t_146.equals("d")) {
                                                local$3.caseIndex_366 = 16;
                                            } else {
                                                local$3.caseIndex_366 = 17;
                                            }
                                            break;
                                        }
                                        case 16:
                                            {
                                            local$3.t_268 = new Down();
                                            local$3.t_269 = SnakeGlobal.changePlayerDirection(game__39, connId__50, local$3.t_268);
                                            game__39 = local$3.t_269;
                                            local$3.caseIndex_366 = 21;
                                            break;
                                        }
                                        case 17:
                                            {
                                            if (local$3.t_146.equals("l")) {
                                                local$3.caseIndex_366 = 18;
                                            } else {
                                                local$3.caseIndex_366 = 19;
                                            }
                                            break;
                                        }
                                        case 18:
                                            {
                                            local$3.t_270 = new Left();
                                            local$3.t_271 = SnakeGlobal.changePlayerDirection(game__39, connId__50, local$3.t_270);
                                            game__39 = local$3.t_271;
                                            local$3.caseIndex_366 = 21;
                                            break;
                                        }
                                        case 19:
                                            {
                                            if (local$3.t_146.equals("r")) {
                                                local$3.caseIndex_366 = 20;
                                            } else {
                                                local$3.caseIndex_366 = 21;
                                            }
                                            break;
                                        }
                                        case 20:
                                            {
                                            local$3.t_272 = new Right();
                                            local$3.t_273 = SnakeGlobal.changePlayerDirection(game__39, connId__50, local$3.t_272);
                                            game__39 = local$3.t_273;
                                            local$3.caseIndex_366 = 21;
                                            break;
                                        }
                                        case 21:
                                            {
                                            local$3.caseIndex_366 = 1;
                                            break;
                                        }
                                        default:
                                            {
                                            return DoneResult.get();
                                        }
                                    }
                                }
                            };
                            return Core.safeAdaptGeneratorFn(convertedCoroutine_368 :: apply);
                        };
                        Core.runAsync(fn__279);
                        local$2.caseIndex_360 = 38;
                        break;
                    }
                    case 38:
                        {
                        local$2.caseIndex_360 = 3;
                        break;
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_369 :: apply);
    }
    static {
        console_61 = Core.getConsole(Logger.getLogger("snake_server"));
        detectedCols__35 = IoGlobal.terminalColumns();
        detectedRows__36 = IoGlobal.terminalRows();
        if (detectedCols__35 > 100) {
            boardWidth__37 = detectedCols__35 - 4;
        } else {
            boardWidth__37 = 80;
        }
        if (detectedRows__36 > 30) {
            boardHeight__38 = detectedRows__36 - 12;
        } else {
            boardHeight__38 = 30;
        }
        game__39 = SnakeGlobal.newMultiGame(boardWidth__37, boardHeight__38, 0, 42);
        wsConns__40 = new ArrayList<>();
        nextId__41 = 0;
        running__42 = true;
        Core.runAsync(SnakeServerGlobal :: fn__330);
        Core.runAsync(SnakeServerGlobal :: fn__329);
    }
}
