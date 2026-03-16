package snake_client;
import java.util.Optional;
import temper.core.Nullable;
import temper.core.Generator.ValueResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import temper.std.ws.WsGlobal;
import temper.core.Core;
import temper.core.Generator.DoneResult;
import temper.core.Generator;
import temper.std.ws.WsConnection;
import temper.core.Generator.Result;
import java.util.function.Function;
import java.util.logging.Logger;
import temper.core.Core.Console;
import java.util.function.Supplier;
public final class SnakeClientGlobal {
    private SnakeClientGlobal() {
    }
    static final Console console_18;
    static boolean connected__11;
    static Generator<Optional<? super Object>> fn__107() {
        class Local_1 {
            int caseIndex_117 = 0;
            boolean t_101 = false;
            @Nullable WsConnection t_63 = null;
            @Nullable String t_69 = null;
            String t_71 = "";
            boolean fail_36 = false;
            boolean fail_37 = false;
            boolean fail_46 = false;
            boolean fail_50 = false;
            @Nullable WsConnection ws__14 = null;
            @Nullable CompletableFuture<WsConnection> promise_113 = null;
            @Nullable CompletableFuture<Optional<? super Object>> promise_114 = null;
            @Nullable String msg__17 = null;
            @Nullable CompletableFuture<@Nullable String> promise_115 = null;
            @Nullable CompletableFuture<Optional<? super Object>> promise_116 = null;
        }
        final Local_1 local$1 = new Local_1();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_135 = generator_112 -> {
            while (true) {
                int caseIndexLocal_118 = local$1.caseIndex_117;
                local$1.caseIndex_117 = -1;
                switch (caseIndexLocal_118) {
                    case 0:
                        {
                        console_18.log("Snake Multiplayer Client");
                        console_18.log("Connecting to ws://localhost:8080...");
                        local$1.promise_113 = WsGlobal.wsConnect("ws://localhost:8080");
                        local$1.caseIndex_117 = 1;
                        local$1.promise_113.handle((ignored$1, ignored$2) -> {
                                generator_112.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 1:
                        {
                        local$1.fail_36 = false;
                        try {
                            local$1.t_63 = local$1.promise_113.get();
                        } catch (InterruptedException ignored$3) {
                            break;
                        } catch (ExecutionException ignored$4) {
                            local$1.fail_36 = true;
                        }
                        if (local$1.fail_36) {
                            local$1.caseIndex_117 = 20;
                        } else {
                            local$1.caseIndex_117 = 2;
                        }
                        break;
                    }
                    case 2:
                        {
                        local$1.ws__14 = local$1.t_63;
                        local$1.promise_114 = WsGlobal.wsSend(local$1.ws__14, "join");
                        local$1.caseIndex_117 = 3;
                        local$1.promise_114.handle((ignored$5, ignored$6) -> {
                                generator_112.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 3:
                        {
                        local$1.fail_37 = false;
                        try {
                            local$1.promise_114.get();
                        } catch (InterruptedException ignored$7) {
                            break;
                        } catch (ExecutionException ignored$8) {
                            local$1.fail_37 = true;
                        }
                        if (local$1.fail_37) {
                            local$1.caseIndex_117 = 4;
                        } else {
                            local$1.caseIndex_117 = 4;
                        }
                        break;
                    }
                    case 4:
                        {
                        console_18.log("Connected! Use w/a/s/d to move.");
                        Supplier<Generator<Optional<? super Object>>> fn__95 = () -> {
                            class Local_2 {
                                int caseIndex_128 = 0;
                                boolean t_91 = false;
                                @Nullable String t_51 = null;
                                String t_61 = "";
                                boolean fail_40 = false;
                                boolean fail_42 = false;
                                boolean fail_43 = false;
                                boolean fail_44 = false;
                                boolean fail_45 = false;
                                @Nullable String line__16 = null;
                                @Nullable CompletableFuture<@Nullable String> promise_123 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_124 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_125 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_126 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_127 = null;
                            }
                            final Local_2 local$2 = new Local_2();
                            Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_132 = generator_122 -> {
                                while (true) {
                                    int caseIndexLocal_129 = local$2.caseIndex_128;
                                    local$2.caseIndex_128 = -1;
                                    switch (caseIndexLocal_129) {
                                        case 0:
                                            {
                                            local$2.caseIndex_128 = 1;
                                            break;
                                        }
                                        case 1:
                                            {
                                            if (connected__11) {
                                                local$2.caseIndex_128 = 2;
                                            } else {
                                                local$2.caseIndex_128 = 9;
                                            }
                                            break;
                                        }
                                        case 2:
                                            {
                                            local$2.promise_123 = Core.stdReadLine();
                                            local$2.caseIndex_128 = 3;
                                            local$2.promise_123.handle((ignored$9, ignored$10) -> {
                                                    generator_122.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 3:
                                            {
                                            local$2.fail_40 = false;
                                            try {
                                                local$2.t_51 = local$2.promise_123.get();
                                            } catch (InterruptedException ignored$11) {
                                                break;
                                            } catch (ExecutionException ignored$12) {
                                                local$2.fail_40 = true;
                                            }
                                            if (local$2.fail_40) {
                                                local$2.caseIndex_128 = 9;
                                            } else {
                                                local$2.caseIndex_128 = 4;
                                            }
                                            break;
                                        }
                                        case 4:
                                            {
                                            local$2.line__16 = local$2.t_51;
                                            if (local$2.line__16 != null) {
                                                local$2.caseIndex_128 = 5;
                                            } else {
                                                local$2.caseIndex_128 = 6;
                                            }
                                            break;
                                        }
                                        case 5:
                                            {
                                            local$2.t_91 = local$2.line__16 instanceof String;
                                            local$2.caseIndex_128 = 7;
                                            break;
                                        }
                                        case 6:
                                            {
                                            local$2.t_91 = false;
                                            local$2.caseIndex_128 = 7;
                                            break;
                                        }
                                        case 7:
                                            {
                                            if (local$2.t_91) {
                                                local$2.caseIndex_128 = 8;
                                            } else {
                                                local$2.caseIndex_128 = 9;
                                            }
                                            break;
                                        }
                                        case 8:
                                            {
                                            if (local$2.line__16 == null) {
                                                local$2.caseIndex_128 = 10;
                                            } else {
                                                local$2.caseIndex_128 = 11;
                                            }
                                            break;
                                        }
                                        case 9:
                                            {
                                            return DoneResult.get();
                                        }
                                        case 10:
                                            {
                                            Core.throwBubble();
                                            local$2.caseIndex_128 = 12;
                                            break;
                                        }
                                        case 11:
                                            {
                                            local$2.t_61 = local$2.line__16;
                                            local$2.caseIndex_128 = 12;
                                            break;
                                        }
                                        case 12:
                                            {
                                            if (local$2.t_61.equals("w")) {
                                                local$2.caseIndex_128 = 13;
                                            } else {
                                                local$2.caseIndex_128 = 14;
                                            }
                                            break;
                                        }
                                        case 13:
                                            {
                                            local$2.promise_124 = WsGlobal.wsSend(local$1.ws__14, "u");
                                            local$2.caseIndex_128 = 15;
                                            local$2.promise_124.handle((ignored$13, ignored$14) -> {
                                                    generator_122.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 14:
                                            {
                                            if (local$2.t_61.equals("s")) {
                                                local$2.caseIndex_128 = 16;
                                            } else {
                                                local$2.caseIndex_128 = 17;
                                            }
                                            break;
                                        }
                                        case 15:
                                            {
                                            local$2.fail_42 = false;
                                            try {
                                                local$2.promise_124.get();
                                            } catch (InterruptedException ignored$15) {
                                                break;
                                            } catch (ExecutionException ignored$16) {
                                                local$2.fail_42 = true;
                                            }
                                            if (local$2.fail_42) {
                                                local$2.caseIndex_128 = 24;
                                            } else {
                                                local$2.caseIndex_128 = 24;
                                            }
                                            break;
                                        }
                                        case 16:
                                            {
                                            local$2.promise_125 = WsGlobal.wsSend(local$1.ws__14, "d");
                                            local$2.caseIndex_128 = 18;
                                            local$2.promise_125.handle((ignored$17, ignored$18) -> {
                                                    generator_122.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 17:
                                            {
                                            if (local$2.t_61.equals("a")) {
                                                local$2.caseIndex_128 = 19;
                                            } else {
                                                local$2.caseIndex_128 = 20;
                                            }
                                            break;
                                        }
                                        case 18:
                                            {
                                            local$2.fail_43 = false;
                                            try {
                                                local$2.promise_125.get();
                                            } catch (InterruptedException ignored$19) {
                                                break;
                                            } catch (ExecutionException ignored$20) {
                                                local$2.fail_43 = true;
                                            }
                                            if (local$2.fail_43) {
                                                local$2.caseIndex_128 = 24;
                                            } else {
                                                local$2.caseIndex_128 = 24;
                                            }
                                            break;
                                        }
                                        case 19:
                                            {
                                            local$2.promise_126 = WsGlobal.wsSend(local$1.ws__14, "l");
                                            local$2.caseIndex_128 = 21;
                                            local$2.promise_126.handle((ignored$21, ignored$22) -> {
                                                    generator_122.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 20:
                                            {
                                            if (local$2.t_61.equals("d")) {
                                                local$2.caseIndex_128 = 22;
                                            } else {
                                                local$2.caseIndex_128 = 24;
                                            }
                                            break;
                                        }
                                        case 21:
                                            {
                                            local$2.fail_44 = false;
                                            try {
                                                local$2.promise_126.get();
                                            } catch (InterruptedException ignored$23) {
                                                break;
                                            } catch (ExecutionException ignored$24) {
                                                local$2.fail_44 = true;
                                            }
                                            if (local$2.fail_44) {
                                                local$2.caseIndex_128 = 24;
                                            } else {
                                                local$2.caseIndex_128 = 24;
                                            }
                                            break;
                                        }
                                        case 22:
                                            {
                                            local$2.promise_127 = WsGlobal.wsSend(local$1.ws__14, "r");
                                            local$2.caseIndex_128 = 23;
                                            local$2.promise_127.handle((ignored$25, ignored$26) -> {
                                                    generator_122.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 23:
                                            {
                                            local$2.fail_45 = false;
                                            try {
                                                local$2.promise_127.get();
                                            } catch (InterruptedException ignored$27) {
                                                break;
                                            } catch (ExecutionException ignored$28) {
                                                local$2.fail_45 = true;
                                            }
                                            if (local$2.fail_45) {
                                                local$2.caseIndex_128 = 24;
                                            } else {
                                                local$2.caseIndex_128 = 24;
                                            }
                                            break;
                                        }
                                        case 24:
                                            {
                                            local$2.caseIndex_128 = 1;
                                            break;
                                        }
                                        default:
                                            {
                                            return DoneResult.get();
                                        }
                                    }
                                }
                            };
                            return Core.safeAdaptGeneratorFn(convertedCoroutine_132 :: apply);
                        };
                        Core.runAsync(fn__95);
                        local$1.caseIndex_117 = 5;
                        break;
                    }
                    case 5:
                        {
                        if (connected__11) {
                            local$1.caseIndex_117 = 6;
                        } else {
                            local$1.caseIndex_117 = 18;
                        }
                        break;
                    }
                    case 6:
                        {
                        local$1.promise_115 = WsGlobal.wsRecv(local$1.ws__14);
                        local$1.caseIndex_117 = 7;
                        local$1.promise_115.handle((ignored$29, ignored$30) -> {
                                generator_112.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 7:
                        {
                        local$1.fail_46 = false;
                        try {
                            local$1.t_69 = local$1.promise_115.get();
                        } catch (InterruptedException ignored$31) {
                            break;
                        } catch (ExecutionException ignored$32) {
                            local$1.fail_46 = true;
                        }
                        if (local$1.fail_46) {
                            local$1.caseIndex_117 = 20;
                        } else {
                            local$1.caseIndex_117 = 8;
                        }
                        break;
                    }
                    case 8:
                        {
                        local$1.msg__17 = local$1.t_69;
                        if (local$1.msg__17 != null) {
                            local$1.caseIndex_117 = 9;
                        } else {
                            local$1.caseIndex_117 = 10;
                        }
                        break;
                    }
                    case 9:
                        {
                        local$1.t_101 = local$1.msg__17 instanceof String;
                        local$1.caseIndex_117 = 11;
                        break;
                    }
                    case 10:
                        {
                        local$1.t_101 = false;
                        local$1.caseIndex_117 = 11;
                        break;
                    }
                    case 11:
                        {
                        if (local$1.t_101) {
                            local$1.caseIndex_117 = 12;
                        } else {
                            local$1.caseIndex_117 = 13;
                        }
                        break;
                    }
                    case 12:
                        {
                        if (local$1.msg__17 == null) {
                            local$1.caseIndex_117 = 14;
                        } else {
                            local$1.caseIndex_117 = 15;
                        }
                        break;
                    }
                    case 13:
                        {
                        console_18.log("Disconnected from server.");
                        connected__11 = false;
                        local$1.caseIndex_117 = 17;
                        break;
                    }
                    case 14:
                        {
                        Core.throwBubble();
                        local$1.caseIndex_117 = 16;
                        break;
                    }
                    case 15:
                        {
                        local$1.t_71 = local$1.msg__17;
                        local$1.caseIndex_117 = 16;
                        break;
                    }
                    case 16:
                        {
                        console_18.log(local$1.t_71);
                        local$1.caseIndex_117 = 17;
                        break;
                    }
                    case 17:
                        {
                        local$1.caseIndex_117 = 5;
                        break;
                    }
                    case 18:
                        {
                        local$1.promise_116 = WsGlobal.wsClose(local$1.ws__14);
                        local$1.caseIndex_117 = 19;
                        local$1.promise_116.handle((ignored$33, ignored$34) -> {
                                generator_112.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 19:
                        {
                        local$1.fail_50 = false;
                        try {
                            local$1.promise_116.get();
                        } catch (InterruptedException ignored$35) {
                            break;
                        } catch (ExecutionException ignored$36) {
                            local$1.fail_50 = true;
                        }
                        if (local$1.fail_50) {
                            local$1.caseIndex_117 = 20;
                        } else {
                            local$1.caseIndex_117 = 20;
                        }
                        break;
                    }
                    case 20:
                        {
                        return DoneResult.get();
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_135 :: apply);
    }
    static {
        console_18 = Core.getConsole(Logger.getLogger("snake_client"));
        connected__11 = true;
        Core.runAsync(SnakeClientGlobal :: fn__107);
    }
}
