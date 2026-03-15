package snake_game;
import temper.core.Nullable;
import temper.core.Core;
import java.util.Optional;
import temper.core.Generator.DoneResult;
import temper.core.Generator.ValueResult;
import temper.core.Generator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import temper.core.Generator.Result;
import snake.Direction;
import snake.SnakeGlobal;
import java.util.function.Function;
import snake.SnakeGame;
import snake.Right;
import snake.Up;
import snake.Down;
import snake.Left;
import java.util.logging.Logger;
import temper.core.Core.Console;
import snake.Playing;
public final class SnakeGameGlobal {
    private SnakeGameGlobal() {
    }
    static final Console console_28;
    static Direction inputDirection__20;
    static @Nullable Direction parseInput__6(String line__21) {
        @Nullable Direction return__5;
        if (line__21.equals("w")) {
            return__5 = new Up();
        } else if (line__21.equals("s")) {
            return__5 = new Down();
        } else if (line__21.equals("a")) {
            return__5 = new Left();
        } else if (line__21.equals("d")) {
            return__5 = new Right();
        } else {
            return__5 = null;
        }
        return return__5;
    }
    static Generator<Optional<? super Object>> fn__145() {
        class Local_1 {
            int caseIndex_155 = 0;
            boolean t_135 = false;
            boolean t_136 = false;
            @Nullable String t_78 = null;
            String t_82 = "";
            boolean fail_43 = false;
            @Nullable String line__24 = null;
            @Nullable CompletableFuture<@Nullable String> promise_154 = null;
            @Nullable Direction dir__25 = null;
        }
        final Local_1 local$1 = new Local_1();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_162 = generator_153 -> {
            while (true) {
                int caseIndexLocal_156 = local$1.caseIndex_155;
                local$1.caseIndex_155 = -1;
                switch (caseIndexLocal_156) {
                    case 0:
                        {
                        local$1.caseIndex_155 = 1;
                        break;
                    }
                    case 1:
                        {
                        local$1.promise_154 = Core.stdReadLine();
                        local$1.caseIndex_155 = 2;
                        local$1.promise_154.handle((ignored$1, ignored$2) -> {
                                generator_153.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 2:
                        {
                        local$1.fail_43 = false;
                        try {
                            local$1.t_78 = local$1.promise_154.get();
                        } catch (InterruptedException ignored$3) {
                            break;
                        } catch (ExecutionException ignored$4) {
                            local$1.fail_43 = true;
                        }
                        if (local$1.fail_43) {
                            local$1.caseIndex_155 = 8;
                        } else {
                            local$1.caseIndex_155 = 3;
                        }
                        break;
                    }
                    case 3:
                        {
                        local$1.line__24 = local$1.t_78;
                        if (local$1.line__24 != null) {
                            local$1.caseIndex_155 = 4;
                        } else {
                            local$1.caseIndex_155 = 5;
                        }
                        break;
                    }
                    case 4:
                        {
                        local$1.t_135 = local$1.line__24 instanceof String;
                        local$1.caseIndex_155 = 6;
                        break;
                    }
                    case 5:
                        {
                        local$1.t_135 = false;
                        local$1.caseIndex_155 = 6;
                        break;
                    }
                    case 6:
                        {
                        if (local$1.t_135) {
                            local$1.caseIndex_155 = 7;
                        } else {
                            local$1.caseIndex_155 = 8;
                        }
                        break;
                    }
                    case 7:
                        {
                        if (local$1.line__24 == null) {
                            local$1.caseIndex_155 = 9;
                        } else {
                            local$1.caseIndex_155 = 10;
                        }
                        break;
                    }
                    case 8:
                        {
                        return DoneResult.get();
                    }
                    case 9:
                        {
                        Core.throwBubble();
                        local$1.caseIndex_155 = 11;
                        break;
                    }
                    case 10:
                        {
                        local$1.t_82 = local$1.line__24;
                        local$1.caseIndex_155 = 11;
                        break;
                    }
                    case 11:
                        {
                        local$1.dir__25 = SnakeGameGlobal.parseInput__6(local$1.t_82);
                        if (local$1.dir__25 != null) {
                            local$1.caseIndex_155 = 12;
                        } else {
                            local$1.caseIndex_155 = 13;
                        }
                        break;
                    }
                    case 12:
                        {
                        local$1.t_136 = local$1.dir__25 instanceof Direction;
                        local$1.caseIndex_155 = 14;
                        break;
                    }
                    case 13:
                        {
                        local$1.t_136 = false;
                        local$1.caseIndex_155 = 14;
                        break;
                    }
                    case 14:
                        {
                        if (local$1.t_136) {
                            local$1.caseIndex_155 = 15;
                        } else {
                            local$1.caseIndex_155 = 16;
                        }
                        break;
                    }
                    case 15:
                        {
                        if (local$1.dir__25 == null) {
                            local$1.caseIndex_155 = 17;
                        } else {
                            local$1.caseIndex_155 = 18;
                        }
                        break;
                    }
                    case 16:
                        {
                        local$1.caseIndex_155 = 1;
                        break;
                    }
                    case 17:
                        {
                        Core.throwBubble();
                        local$1.caseIndex_155 = 16;
                        break;
                    }
                    case 18:
                        {
                        inputDirection__20 = local$1.dir__25;
                        local$1.caseIndex_155 = 16;
                        break;
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_162 :: apply);
    }
    static Generator<Optional<? super Object>> fn__144() {
        class Local_2 {
            int caseIndex_168 = 0;
            @Nullable SnakeGame t_121 = null;
            @Nullable SnakeGame t_124 = null;
            String t_125 = "";
            String t_127 = "";
            String t_130 = "";
            boolean fail_49 = false;
            boolean fail_57 = false;
            @Nullable CompletableFuture<Optional<? super Object>> promise_166 = null;
            @Nullable SnakeGame game__27 = null;
            @Nullable CompletableFuture<Optional<? super Object>> promise_167 = null;
        }
        final Local_2 local$2 = new Local_2();
        Function<Generator<Optional<? super Object>>, Result<Optional<? super Object>>> convertedCoroutine_171 = generator_165 -> {
            while (true) {
                int caseIndexLocal_169 = local$2.caseIndex_168;
                local$2.caseIndex_168 = -1;
                switch (caseIndexLocal_169) {
                    case 0:
                        {
                        console_28.log("Snake! Use w/a/s/d + Enter to move.");
                        console_28.log("Starting in 1 second...");
                        local$2.promise_166 = Core.stdSleep(1000);
                        local$2.caseIndex_168 = 1;
                        local$2.promise_166.handle((ignored$5, ignored$6) -> {
                                generator_165.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 1:
                        {
                        local$2.fail_49 = false;
                        try {
                            local$2.promise_166.get();
                        } catch (InterruptedException ignored$7) {
                            break;
                        } catch (ExecutionException ignored$8) {
                            local$2.fail_49 = true;
                        }
                        if (local$2.fail_49) {
                            local$2.caseIndex_168 = 7;
                        } else {
                            local$2.caseIndex_168 = 2;
                        }
                        break;
                    }
                    case 2:
                        {
                        local$2.t_121 = SnakeGlobal.newGame(20, 10, 42);
                        local$2.game__27 = local$2.t_121;
                        local$2.caseIndex_168 = 3;
                        break;
                    }
                    case 3:
                        {
                        if (!(local$2.game__27.getStatus() instanceof Playing)) {
                            local$2.caseIndex_168 = 4;
                        } else {
                            local$2.caseIndex_168 = 5;
                        }
                        break;
                    }
                    case 4:
                        {
                        local$2.t_127 = SnakeGlobal.render(local$2.game__27);
                        console_28.log(local$2.t_127);
                        local$2.t_130 = Integer.toString(local$2.game__27.getScore());
                        console_28.log("Final score: " + local$2.t_130);
                        local$2.caseIndex_168 = 7;
                        break;
                    }
                    case 5:
                        {
                        local$2.game__27 = SnakeGlobal.changeDirection(local$2.game__27, inputDirection__20);
                        local$2.t_124 = SnakeGlobal.tick(local$2.game__27);
                        local$2.game__27 = local$2.t_124;
                        local$2.t_125 = SnakeGlobal.render(local$2.game__27);
                        console_28.log(local$2.t_125);
                        local$2.promise_167 = Core.stdSleep(200);
                        local$2.caseIndex_168 = 6;
                        local$2.promise_167.handle((ignored$9, ignored$10) -> {
                                generator_165.get();
                                return null;
                        });
                        return new ValueResult<>(Optional.empty());
                    }
                    case 6:
                        {
                        local$2.fail_57 = false;
                        try {
                            local$2.promise_167.get();
                        } catch (InterruptedException ignored$11) {
                            break;
                        } catch (ExecutionException ignored$12) {
                            local$2.fail_57 = true;
                        }
                        if (local$2.fail_57) {
                            local$2.caseIndex_168 = 7;
                        } else {
                            local$2.caseIndex_168 = 8;
                        }
                        break;
                    }
                    case 7:
                        {
                        return DoneResult.get();
                    }
                    case 8:
                        {
                        local$2.caseIndex_168 = 3;
                        break;
                    }
                    default:
                        {
                        return DoneResult.get();
                    }
                }
            }
        };
        return Core.safeAdaptGeneratorFn(convertedCoroutine_171 :: apply);
    }
    static {
        console_28 = Core.getConsole(Logger.getLogger("snake_game"));
        inputDirection__20 = new Right();
        Core.runAsync(SnakeGameGlobal :: fn__145);
        Core.runAsync(SnakeGameGlobal :: fn__144);
    }
}
