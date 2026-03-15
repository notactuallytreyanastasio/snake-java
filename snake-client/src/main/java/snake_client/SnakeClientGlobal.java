package snake_client;
import java.util.Optional;
import temper.core.Nullable;
import temper.core.Generator.ValueResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import temper.core.Core;
import temper.std.ws.WsGlobal;
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
    static Generator<Optional<? super Object>> fn__102() {
        class Local_1 {
            int caseIndex_111 = 0;
            boolean t_96 = false;
            @Nullable WsConnection t_61 = null;
            @Nullable String t_64 = null;
            String t_66 = "";
            boolean fail_35 = false;
            boolean fail_44 = false;
            boolean fail_48 = false;
            @Nullable WsConnection ws__14 = null;
            @Nullable CompletableFuture<WsConnection> promise_108 = null;
            @Nullable String msg__17 = null;
            @Nullable CompletableFuture<@Nullable String> promise_109 = null;
            @Nullable CompletableFuture<Optional<? super Object>> promise_110 = null;
        }
        final Local_1 local$1 = new Local_1();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_129 = generator_107 -> {
            while (true) {
                int caseIndexLocal_112 = local$1.caseIndex_111;
                local$1.caseIndex_111 = -1;
                switch (caseIndexLocal_112) {
                    case 0:
                        {
                        console_18.log("Snake Multiplayer Client");
                        console_18.log("Connecting to ws://localhost:8080...");
                        local$1.promise_108 = WsGlobal.wsConnect("ws://localhost:8080");
                        local$1.caseIndex_111 = 1;
                        local$1.promise_108.handle((ignored$1, ignored$2) -> {
                                generator_107.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 1:
                        {
                        local$1.fail_35 = false;
                        try {
                            local$1.t_61 = local$1.promise_108.get();
                        } catch (InterruptedException ignored$3) {
                            break;
                        } catch (ExecutionException ignored$4) {
                            local$1.fail_35 = true;
                        }
                        if (local$1.fail_35) {
                            local$1.caseIndex_111 = 18;
                        } else {
                            local$1.caseIndex_111 = 2;
                        }
                        break;
                    }
                    case 2:
                        {
                        local$1.ws__14 = local$1.t_61;
                        console_18.log("Connected! Use w/a/s/d to move.");
                        Supplier<Generator<Optional<? super Object>>> fn__90 = () -> {
                            class Local_2 {
                                int caseIndex_122 = 0;
                                boolean t_86 = false;
                                @Nullable String t_49 = null;
                                String t_59 = "";
                                boolean fail_38 = false;
                                boolean fail_40 = false;
                                boolean fail_41 = false;
                                boolean fail_42 = false;
                                boolean fail_43 = false;
                                @Nullable String line__16 = null;
                                @Nullable CompletableFuture<@Nullable String> promise_117 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_118 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_119 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_120 = null;
                                @Nullable CompletableFuture<Optional<? super Object>> promise_121 = null;
                            }
                            final Local_2 local$2 = new Local_2();
                            Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_126 = generator_116 -> {
                                while (true) {
                                    int caseIndexLocal_123 = local$2.caseIndex_122;
                                    local$2.caseIndex_122 = -1;
                                    switch (caseIndexLocal_123) {
                                        case 0:
                                            {
                                            local$2.caseIndex_122 = 1;
                                            break;
                                        }
                                        case 1:
                                            {
                                            if (connected__11) {
                                                local$2.caseIndex_122 = 2;
                                            } else {
                                                local$2.caseIndex_122 = 9;
                                            }
                                            break;
                                        }
                                        case 2:
                                            {
                                            local$2.promise_117 = Core.stdReadLine();
                                            local$2.caseIndex_122 = 3;
                                            local$2.promise_117.handle((ignored$5, ignored$6) -> {
                                                    generator_116.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 3:
                                            {
                                            local$2.fail_38 = false;
                                            try {
                                                local$2.t_49 = local$2.promise_117.get();
                                            } catch (InterruptedException ignored$7) {
                                                break;
                                            } catch (ExecutionException ignored$8) {
                                                local$2.fail_38 = true;
                                            }
                                            if (local$2.fail_38) {
                                                local$2.caseIndex_122 = 9;
                                            } else {
                                                local$2.caseIndex_122 = 4;
                                            }
                                            break;
                                        }
                                        case 4:
                                            {
                                            local$2.line__16 = local$2.t_49;
                                            if (local$2.line__16 != null) {
                                                local$2.caseIndex_122 = 5;
                                            } else {
                                                local$2.caseIndex_122 = 6;
                                            }
                                            break;
                                        }
                                        case 5:
                                            {
                                            local$2.t_86 = local$2.line__16 instanceof String;
                                            local$2.caseIndex_122 = 7;
                                            break;
                                        }
                                        case 6:
                                            {
                                            local$2.t_86 = false;
                                            local$2.caseIndex_122 = 7;
                                            break;
                                        }
                                        case 7:
                                            {
                                            if (local$2.t_86) {
                                                local$2.caseIndex_122 = 8;
                                            } else {
                                                local$2.caseIndex_122 = 9;
                                            }
                                            break;
                                        }
                                        case 8:
                                            {
                                            if (local$2.line__16 == null) {
                                                local$2.caseIndex_122 = 10;
                                            } else {
                                                local$2.caseIndex_122 = 11;
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
                                            local$2.caseIndex_122 = 12;
                                            break;
                                        }
                                        case 11:
                                            {
                                            local$2.t_59 = local$2.line__16;
                                            local$2.caseIndex_122 = 12;
                                            break;
                                        }
                                        case 12:
                                            {
                                            if (local$2.t_59.equals("w")) {
                                                local$2.caseIndex_122 = 13;
                                            } else {
                                                local$2.caseIndex_122 = 14;
                                            }
                                            break;
                                        }
                                        case 13:
                                            {
                                            local$2.promise_118 = WsGlobal.wsSend(local$1.ws__14, "u");
                                            local$2.caseIndex_122 = 15;
                                            local$2.promise_118.handle((ignored$9, ignored$10) -> {
                                                    generator_116.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 14:
                                            {
                                            if (local$2.t_59.equals("s")) {
                                                local$2.caseIndex_122 = 16;
                                            } else {
                                                local$2.caseIndex_122 = 17;
                                            }
                                            break;
                                        }
                                        case 15:
                                            {
                                            local$2.fail_40 = false;
                                            try {
                                                local$2.promise_118.get();
                                            } catch (InterruptedException ignored$11) {
                                                break;
                                            } catch (ExecutionException ignored$12) {
                                                local$2.fail_40 = true;
                                            }
                                            if (local$2.fail_40) {
                                                local$2.caseIndex_122 = 24;
                                            } else {
                                                local$2.caseIndex_122 = 24;
                                            }
                                            break;
                                        }
                                        case 16:
                                            {
                                            local$2.promise_119 = WsGlobal.wsSend(local$1.ws__14, "d");
                                            local$2.caseIndex_122 = 18;
                                            local$2.promise_119.handle((ignored$13, ignored$14) -> {
                                                    generator_116.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 17:
                                            {
                                            if (local$2.t_59.equals("a")) {
                                                local$2.caseIndex_122 = 19;
                                            } else {
                                                local$2.caseIndex_122 = 20;
                                            }
                                            break;
                                        }
                                        case 18:
                                            {
                                            local$2.fail_41 = false;
                                            try {
                                                local$2.promise_119.get();
                                            } catch (InterruptedException ignored$15) {
                                                break;
                                            } catch (ExecutionException ignored$16) {
                                                local$2.fail_41 = true;
                                            }
                                            if (local$2.fail_41) {
                                                local$2.caseIndex_122 = 24;
                                            } else {
                                                local$2.caseIndex_122 = 24;
                                            }
                                            break;
                                        }
                                        case 19:
                                            {
                                            local$2.promise_120 = WsGlobal.wsSend(local$1.ws__14, "l");
                                            local$2.caseIndex_122 = 21;
                                            local$2.promise_120.handle((ignored$17, ignored$18) -> {
                                                    generator_116.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 20:
                                            {
                                            if (local$2.t_59.equals("d")) {
                                                local$2.caseIndex_122 = 22;
                                            } else {
                                                local$2.caseIndex_122 = 24;
                                            }
                                            break;
                                        }
                                        case 21:
                                            {
                                            local$2.fail_42 = false;
                                            try {
                                                local$2.promise_120.get();
                                            } catch (InterruptedException ignored$19) {
                                                break;
                                            } catch (ExecutionException ignored$20) {
                                                local$2.fail_42 = true;
                                            }
                                            if (local$2.fail_42) {
                                                local$2.caseIndex_122 = 24;
                                            } else {
                                                local$2.caseIndex_122 = 24;
                                            }
                                            break;
                                        }
                                        case 22:
                                            {
                                            local$2.promise_121 = WsGlobal.wsSend(local$1.ws__14, "r");
                                            local$2.caseIndex_122 = 23;
                                            local$2.promise_121.handle((ignored$21, ignored$22) -> {
                                                    generator_116.get();
                                                    return null;
                                            });
                                            return new ValueResult<>(Optional.empty());
                                        }
                                        case 23:
                                            {
                                            local$2.fail_43 = false;
                                            try {
                                                local$2.promise_121.get();
                                            } catch (InterruptedException ignored$23) {
                                                break;
                                            } catch (ExecutionException ignored$24) {
                                                local$2.fail_43 = true;
                                            }
                                            if (local$2.fail_43) {
                                                local$2.caseIndex_122 = 24;
                                            } else {
                                                local$2.caseIndex_122 = 24;
                                            }
                                            break;
                                        }
                                        case 24:
                                            {
                                            local$2.caseIndex_122 = 1;
                                            break;
                                        }
                                        default:
                                            {
                                            return DoneResult.get();
                                        }
                                    }
                                }
                            };
                            return Core.safeAdaptGeneratorFn(convertedCoroutine_126 :: apply);
                        };
                        Core.runAsync(fn__90);
                        local$1.caseIndex_111 = 3;
                        break;
                    }
                    case 3:
                        {
                        if (connected__11) {
                            local$1.caseIndex_111 = 4;
                        } else {
                            local$1.caseIndex_111 = 16;
                        }
                        break;
                    }
                    case 4:
                        {
                        local$1.promise_109 = WsGlobal.wsRecv(local$1.ws__14);
                        local$1.caseIndex_111 = 5;
                        local$1.promise_109.handle((ignored$25, ignored$26) -> {
                                generator_107.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 5:
                        {
                        local$1.fail_44 = false;
                        try {
                            local$1.t_64 = local$1.promise_109.get();
                        } catch (InterruptedException ignored$27) {
                            break;
                        } catch (ExecutionException ignored$28) {
                            local$1.fail_44 = true;
                        }
                        if (local$1.fail_44) {
                            local$1.caseIndex_111 = 18;
                        } else {
                            local$1.caseIndex_111 = 6;
                        }
                        break;
                    }
                    case 6:
                        {
                        local$1.msg__17 = local$1.t_64;
                        if (local$1.msg__17 != null) {
                            local$1.caseIndex_111 = 7;
                        } else {
                            local$1.caseIndex_111 = 8;
                        }
                        break;
                    }
                    case 7:
                        {
                        local$1.t_96 = local$1.msg__17 instanceof String;
                        local$1.caseIndex_111 = 9;
                        break;
                    }
                    case 8:
                        {
                        local$1.t_96 = false;
                        local$1.caseIndex_111 = 9;
                        break;
                    }
                    case 9:
                        {
                        if (local$1.t_96) {
                            local$1.caseIndex_111 = 10;
                        } else {
                            local$1.caseIndex_111 = 11;
                        }
                        break;
                    }
                    case 10:
                        {
                        if (local$1.msg__17 == null) {
                            local$1.caseIndex_111 = 12;
                        } else {
                            local$1.caseIndex_111 = 13;
                        }
                        break;
                    }
                    case 11:
                        {
                        console_18.log("Disconnected from server.");
                        connected__11 = false;
                        local$1.caseIndex_111 = 15;
                        break;
                    }
                    case 12:
                        {
                        Core.throwBubble();
                        local$1.caseIndex_111 = 14;
                        break;
                    }
                    case 13:
                        {
                        local$1.t_66 = local$1.msg__17;
                        local$1.caseIndex_111 = 14;
                        break;
                    }
                    case 14:
                        {
                        console_18.log(local$1.t_66);
                        local$1.caseIndex_111 = 15;
                        break;
                    }
                    case 15:
                        {
                        local$1.caseIndex_111 = 3;
                        break;
                    }
                    case 16:
                        {
                        local$1.promise_110 = WsGlobal.wsClose(local$1.ws__14);
                        local$1.caseIndex_111 = 17;
                        local$1.promise_110.handle((ignored$29, ignored$30) -> {
                                generator_107.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 17:
                        {
                        local$1.fail_48 = false;
                        try {
                            local$1.promise_110.get();
                        } catch (InterruptedException ignored$31) {
                            break;
                        } catch (ExecutionException ignored$32) {
                            local$1.fail_48 = true;
                        }
                        if (local$1.fail_48) {
                            local$1.caseIndex_111 = 18;
                        } else {
                            local$1.caseIndex_111 = 18;
                        }
                        break;
                    }
                    case 18:
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
        return Core.safeAdaptGeneratorFn(convertedCoroutine_129 :: apply);
    }
    static {
        console_18 = Core.getConsole(Logger.getLogger("snake_client"));
        connected__11 = true;
        Core.runAsync(SnakeClientGlobal :: fn__102);
    }
}
