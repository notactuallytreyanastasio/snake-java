package snake_test;
import temper.std.testing.Test;
import java.util.function.Supplier;
import snake.SnakeGlobal;
import temper.core.Core;
import snake.Point;
import java.util.List;
import snake.SnakeGame;
import snake.PlayerSnake;
import snake.Right;
import snake.MultiSnakeGame;
import snake.Dead;
import snake.Up;
import snake.Left;
import snake.Direction;
import snake.GameOver;
import snake.Down;
import snake.Alive;
import snake.RandomResult;
import snake.Playing;
import temper.core.Nullable;
public final class SnakeTestTest {
    private SnakeTestTest() {
    }
    @org.junit.jupiter.api.Test public void initialStateHasSnakeNearCenter__168() {
        Test test_0 = new Test();
        try {
            SnakeGame game__65 = SnakeGlobal.newGame(10, 10, 42);
            Point head__66 = Core.listGetOr(game__65.getSnake(), 0, new Point(-1, -1));
            boolean t_1567 = head__66.getX() == 5;
            Supplier<String> fn__1560 = () -> "head x should be 5, got " + Integer.toString(head__66.getX());
            test_0.assert_(t_1567, fn__1560);
            boolean t_1571 = head__66.getY() == 5;
            Supplier<String> fn__1559 = () -> "head y should be 5, got " + Integer.toString(head__66.getY());
            test_0.assert_(t_1571, fn__1559);
            boolean t_1576 = game__65.getSnake().size() == 3;
            Supplier<String> fn__1558 = () -> "snake should start with 3 segments";
            test_0.assert_(t_1576, fn__1558);
        } finally {
            test_0.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialStatusIsPlaying__169() {
        Test test_1 = new Test();
        try {
            SnakeGame game__68 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_1551 = game__68.getStatus() instanceof Playing;
            Supplier<String> fn__1548 = () -> "initial status should be Playing";
            test_1.assert_(t_1551, fn__1548);
        } finally {
            test_1.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialDirectionIsRight__170() {
        Test test_2 = new Test();
        try {
            SnakeGame game__70 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_1545 = game__70.getDirection() instanceof Right;
            Supplier<String> fn__1542 = () -> "initial direction should be Right";
            test_2.assert_(t_1545, fn__1542);
        } finally {
            test_2.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialScoreIs0__171() {
        Test test_3 = new Test();
        try {
            SnakeGame game__72 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_1540 = game__72.getScore() == 0;
            Supplier<String> fn__1536 = () -> "initial score should be 0";
            test_3.assert_(t_1540, fn__1536);
        } finally {
            test_3.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesRight__172() {
        Test test_4 = new Test();
        try {
            SnakeGame game__74 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame moved__75 = SnakeGlobal.tick(game__74);
            Point head__76 = Core.listGetOr(moved__75.getSnake(), 0, new Point(-1, -1));
            boolean t_1530 = head__76.getX() == 6;
            Supplier<String> fn__1522 = () -> "head should move right to x=6, got " + Integer.toString(head__76.getX());
            test_4.assert_(t_1530, fn__1522);
            boolean t_1534 = head__76.getY() == 5;
            Supplier<String> fn__1521 = () -> "head y should stay 5, got " + Integer.toString(head__76.getY());
            test_4.assert_(t_1534, fn__1521);
        } finally {
            test_4.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesDown__173() {
        Test test_5 = new Test();
        try {
            SnakeGame game__78 = SnakeGlobal.changeDirection(SnakeGlobal.newGame(10, 10, 42), new Down());
            SnakeGame moved__79 = SnakeGlobal.tick(game__78);
            Point head__80 = Core.listGetOr(moved__79.getSnake(), 0, new Point(-1, -1));
            boolean t_1511 = head__80.getX() == 5;
            Supplier<String> fn__1502 = () -> "head x should stay 5, got " + Integer.toString(head__80.getX());
            test_5.assert_(t_1511, fn__1502);
            boolean t_1515 = head__80.getY() == 6;
            Supplier<String> fn__1501 = () -> "head should move down to y=6, got " + Integer.toString(head__80.getY());
            test_5.assert_(t_1515, fn__1501);
        } finally {
            test_5.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesUp__174() {
        Test test_6 = new Test();
        try {
            SnakeGame game__82 = SnakeGlobal.changeDirection(SnakeGlobal.newGame(10, 10, 42), new Up());
            SnakeGame moved__83 = SnakeGlobal.tick(game__82);
            Point head__84 = Core.listGetOr(moved__83.getSnake(), 0, new Point(-1, -1));
            boolean t_1495 = head__84.getY() == 4;
            Supplier<String> fn__1486 = () -> "head should move up to y=4, got " + Integer.toString(head__84.getY());
            test_6.assert_(t_1495, fn__1486);
        } finally {
            test_6.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void oppositeDirectionIsRejected__175() {
        Test test_7 = new Test();
        try {
            SnakeGame game__86 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame changed__87 = SnakeGlobal.changeDirection(game__86, new Left());
            boolean t_1481 = changed__87.getDirection() instanceof Right;
            Supplier<String> fn__1477 = () -> "should still be Right after trying Left";
            test_7.assert_(t_1481, fn__1477);
        } finally {
            test_7.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void nonOppositeDirectionIsAccepted__176() {
        Test test_8 = new Test();
        try {
            SnakeGame game__89 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame changed__90 = SnakeGlobal.changeDirection(game__89, new Up());
            boolean t_1474 = changed__90.getDirection() instanceof Up;
            Supplier<String> fn__1470 = () -> "should change to Up";
            test_8.assert_(t_1474, fn__1470);
        } finally {
            test_8.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void wallCollisionCausesGameOver__177() {
        Test test_9 = new Test();
        try {
            SnakeGame t_1465;
            SnakeGame t_1464 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame game__92 = t_1464;
            int i__93 = 0;
            while (i__93 < 10) {
                t_1465 = SnakeGlobal.tick(game__92);
                game__92 = t_1465;
                i__93 = i__93 + 1;
            }
            boolean t_1467 = game__92.getStatus() instanceof GameOver;
            Supplier<String> fn__1463 = () -> "should be GameOver after hitting wall";
            test_9.assert_(t_1467, fn__1463);
        } finally {
            test_9.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void selfCollisionCausesGameOver__178() {
        Test test_10 = new Test();
        try {
            List<Point> snake__95 = List.of(new Point(5, 5), new Point(6, 5), new Point(6, 4), new Point(5, 4), new Point(4, 4), new Point(4, 5), new Point(4, 6));
            SnakeGame t_1457 = new SnakeGame(10, 10, snake__95, new Left(), new Point(0, 0), 0, new Playing(), 42);
            SnakeGame game__96 = t_1457;
            SnakeGame t_1458 = SnakeGlobal.tick(game__96);
            game__96 = t_1458;
            boolean t_1460 = game__96.getStatus() instanceof GameOver;
            Supplier<String> fn__1446 = () -> "should be GameOver after self collision";
            test_10.assert_(t_1460, fn__1446);
        } finally {
            test_10.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void pointEqualsWorksForSamePoints__179() {
        Test test_11 = new Test();
        try {
            boolean t_1444 = SnakeGlobal.pointEquals(new Point(3, 4), new Point(3, 4));
            Supplier<String> fn__1440 = () -> "same points should be equal";
            test_11.assert_(t_1444, fn__1440);
        } finally {
            test_11.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void pointEqualsWorksForDifferentPoints__180() {
        Test test_12 = new Test();
        try {
            boolean t_1438 = !SnakeGlobal.pointEquals(new Point(3, 4), new Point(5, 6));
            Supplier<String> fn__1434 = () -> "different points should not be equal";
            test_12.assert_(t_1438, fn__1434);
        } finally {
            test_12.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void isOppositeDetectsOppositeDirections__181() {
        Test test_13 = new Test();
        try {
            boolean t_1422 = SnakeGlobal.isOpposite(new Up(), new Down());
            Supplier<String> fn__1418 = () -> "Up/Down are opposite";
            test_13.assert_(t_1422, fn__1418);
            boolean t_1427 = SnakeGlobal.isOpposite(new Left(), new Right());
            Supplier<String> fn__1417 = () -> "Left/Right are opposite";
            test_13.assert_(t_1427, fn__1417);
            boolean t_1432 = !SnakeGlobal.isOpposite(new Up(), new Left());
            Supplier<String> fn__1416 = () -> "Up/Left are not opposite";
            test_13.assert_(t_1432, fn__1416);
        } finally {
            test_13.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void directionDeltaReturnsCorrectDeltas__182() {
        Test test_14 = new Test();
        try {
            int t_1408;
            int t_1413;
            boolean t_701;
            boolean t_706;
            Point up__101 = SnakeGlobal.directionDelta(new Up());
            if (up__101.getX() == 0) {
                t_1408 = up__101.getY();
                t_701 = t_1408 == -1;
            } else {
                t_701 = false;
            }
            Supplier<String> fn__1405 = () -> "Up should be (0, -1)";
            test_14.assert_(t_701, fn__1405);
            Point right__102 = SnakeGlobal.directionDelta(new Right());
            if (right__102.getX() == 1) {
                t_1413 = right__102.getY();
                t_706 = t_1413 == 0;
            } else {
                t_706 = false;
            }
            Supplier<String> fn__1404 = () -> "Right should be (1, 0)";
            test_14.assert_(t_706, fn__1404);
        } finally {
            test_14.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void prngIsDeterministic__183() {
        Test test_17 = new Test();
        try {
            RandomResult r1__104 = SnakeGlobal.nextRandom(42, 100);
            RandomResult r2__105 = SnakeGlobal.nextRandom(42, 100);
            boolean t_1397 = r1__104.getValue() == r2__105.getValue();
            Supplier<String> fn__1393 = () -> "same seed should produce same value";
            test_17.assert_(t_1397, fn__1393);
            boolean t_1402 = r1__104.getNextSeed() == r2__105.getNextSeed();
            Supplier<String> fn__1392 = () -> "same seed should produce same next seed";
            test_17.assert_(t_1402, fn__1392);
        } finally {
            test_17.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void prngProducesValuesInRange__184() {
        Test test_18 = new Test();
        try {
            int t_1389;
            boolean t_691;
            RandomResult r__107 = SnakeGlobal.nextRandom(42, 10);
            if (r__107.getValue() >= 0) {
                t_1389 = r__107.getValue();
                t_691 = t_1389 < 10;
            } else {
                t_691 = false;
            }
            Supplier<String> fn__1387 = () -> "value should be in [0, 10), got " + Integer.toString(r__107.getValue());
            test_18.assert_(t_691, fn__1387);
        } finally {
            test_18.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void tickDoesNothingWhenGameIsOver__185() {
        Test test_20 = new Test();
        try {
            SnakeGame t_1370;
            SnakeGame t_1369 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame game__109 = t_1369;
            int i__110 = 0;
            while (i__110 < 10) {
                t_1370 = SnakeGlobal.tick(game__109);
                game__109 = t_1370;
                i__110 = i__110 + 1;
            }
            boolean t_1372 = game__109.getStatus() instanceof GameOver;
            Supplier<String> fn__1368 = () -> "should be GameOver";
            test_20.assert_(t_1372, fn__1368);
            Point head1__111 = Core.listGetOr(game__109.getSnake(), 0, new Point(-1, -1));
            SnakeGame t_1378 = SnakeGlobal.tick(game__109);
            game__109 = t_1378;
            Point head2__112 = Core.listGetOr(game__109.getSnake(), 0, new Point(-1, -1));
            boolean t_1383 = SnakeGlobal.pointEquals(head1__111, head2__112);
            Supplier<String> fn__1367 = () -> "snake should not move after game over";
            test_20.assert_(t_1383, fn__1367);
        } finally {
            test_20.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameCreatesCorrectNumberOfSnakes__186() {
        Test test_21 = new Test();
        try {
            MultiSnakeGame game__114 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            boolean t_1365 = game__114.getSnakes().size() == 2;
            Supplier<String> fn__1360 = () -> "should have 2 snakes";
            test_21.assert_(t_1365, fn__1360);
        } finally {
            test_21.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameSnakesStartAlive__187() {
        Test test_22 = new Test();
        try {
            MultiSnakeGame game__116 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            PlayerSnake s0__117 = Core.listGetOr(game__116.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__118 = Core.listGetOr(game__116.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1353 = s0__117.getStatus() instanceof Alive;
            Supplier<String> fn__1338 = () -> "player 0 should be alive";
            test_22.assert_(t_1353, fn__1338);
            boolean t_1357 = s1__118.getStatus() instanceof Alive;
            Supplier<String> fn__1337 = () -> "player 1 should be alive";
            test_22.assert_(t_1357, fn__1337);
        } finally {
            test_22.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameSnakesStartAtDifferentPositions__188() {
        Test test_23 = new Test();
        try {
            MultiSnakeGame game__120 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            PlayerSnake s0__121 = Core.listGetOr(game__120.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__122 = Core.listGetOr(game__120.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            Point h0__123 = Core.listGetOr(s0__121.getSegments(), 0, new Point(-1, -1));
            Point h1__124 = Core.listGetOr(s1__122.getSegments(), 0, new Point(-1, -1));
            boolean t_1335 = !SnakeGlobal.pointEquals(h0__123, h1__124);
            Supplier<String> fn__1314 = () -> "snakes should start at different positions";
            test_23.assert_(t_1335, fn__1314);
        } finally {
            test_23.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameSnakesHave3_segmentsEach__189() {
        Test test_24 = new Test();
        try {
            MultiSnakeGame game__126 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            PlayerSnake s0__127 = Core.listGetOr(game__126.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__128 = Core.listGetOr(game__126.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1307 = s0__127.getSegments().size() == 3;
            Supplier<String> fn__1290 = () -> "player 0 should have 3 segments";
            test_24.assert_(t_1307, fn__1290);
            boolean t_1312 = s1__128.getSegments().size() == 3;
            Supplier<String> fn__1289 = () -> "player 1 should have 3 segments";
            test_24.assert_(t_1312, fn__1289);
        } finally {
            test_24.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiTickMovesBothSnakes__190() {
        Test test_25 = new Test();
        try {
            MultiSnakeGame game__130 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            Point h0Before__131 = Core.listGetOr(Core.listGetOr(game__130.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead())).getSegments(), 0, new Point(0, 0));
            Point h1Before__132 = Core.listGetOr(Core.listGetOr(game__130.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead())).getSegments(), 0, new Point(0, 0));
            List<Direction> dirs__133 = List.of(new Right(), new Left());
            MultiSnakeGame after__134 = SnakeGlobal.multiTick(game__130, dirs__133);
            Point h0After__135 = Core.listGetOr(Core.listGetOr(after__134.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead())).getSegments(), 0, new Point(0, 0));
            Point h1After__136 = Core.listGetOr(Core.listGetOr(after__134.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead())).getSegments(), 0, new Point(0, 0));
            boolean t_1284 = !SnakeGlobal.pointEquals(h0Before__131, h0After__135);
            Supplier<String> fn__1242 = () -> "snake 0 should have moved";
            test_25.assert_(t_1284, fn__1242);
            boolean t_1287 = !SnakeGlobal.pointEquals(h1Before__132, h1After__136);
            Supplier<String> fn__1241 = () -> "snake 1 should have moved";
            test_25.assert_(t_1287, fn__1241);
        } finally {
            test_25.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiWallCollisionKillsOneSnake__191() {
        Test test_26 = new Test();
        try {
            MultiSnakeGame t_1227;
            int t_1229;
            List<PlayerSnake> t_1230;
            PlayerSnake t_1234;
            MultiSnakeGame t_1224 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            MultiSnakeGame game__138 = t_1224;
            List<Direction> dirs__139 = List.of(new Right(), new Left());
            int i__140 = 0;
            while (i__140 < 20) {
                t_1227 = SnakeGlobal.multiTick(game__138, dirs__139);
                game__138 = t_1227;
                i__140 = i__140 + 1;
            }
            int deadCount__141 = 0;
            int i__142 = 0;
            while (true) {
                t_1229 = game__138.getSnakes().size();
                if (i__142 >= t_1229) {
                    break;
                }
                t_1230 = game__138.getSnakes();
                t_1234 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                PlayerSnake snake__143 = Core.listGetOr(t_1230, i__142, t_1234);
                if (snake__143.getStatus() instanceof Dead) {
                    deadCount__141 = deadCount__141 + 1;
                }
                i__142 = i__142 + 1;
            }
            boolean t_1239 = deadCount__141 > 0;
            Supplier<String> fn__1223 = () -> "at least one snake should be dead after 20 ticks toward walls";
            test_26.assert_(t_1239, fn__1223);
        } finally {
            test_26.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameOverWhenOnePlayerLeft__192() {
        Test test_27 = new Test();
        try {
            MultiSnakeGame t_1219;
            MultiSnakeGame t_1216 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            MultiSnakeGame game__145 = t_1216;
            List<Direction> dirs__146 = List.of(new Right(), new Left());
            int i__147 = 0;
            while (i__147 < 30) {
                t_1219 = SnakeGlobal.multiTick(game__145, dirs__146);
                game__145 = t_1219;
                i__147 = i__147 + 1;
            }
            boolean t_1220 = SnakeGlobal.isMultiGameOver(game__145);
            Supplier<String> fn__1215 = () -> "game should be over after enough ticks";
            test_27.assert_(t_1220, fn__1215);
        } finally {
            test_27.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void changePlayerDirectionWorks__193() {
        Test test_28 = new Test();
        try {
            MultiSnakeGame game__149 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            Up t_1203 = new Up();
            MultiSnakeGame changed__150 = SnakeGlobal.changePlayerDirection(game__149, 0, t_1203);
            PlayerSnake s0__151 = Core.listGetOr(changed__150.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1212 = s0__151.getDirection() instanceof Up;
            Supplier<String> fn__1201 = () -> "player 0 direction should be Up";
            test_28.assert_(t_1212, fn__1201);
        } finally {
            test_28.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void changePlayerDirectionRejectsOpposite__194() {
        Test test_29 = new Test();
        try {
            MultiSnakeGame game__153 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            Left t_1189 = new Left();
            MultiSnakeGame changed__154 = SnakeGlobal.changePlayerDirection(game__153, 0, t_1189);
            PlayerSnake s0__155 = Core.listGetOr(changed__154.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1198 = s0__155.getDirection() instanceof Right;
            Supplier<String> fn__1187 = () -> "should reject opposite direction";
            test_29.assert_(t_1198, fn__1187);
        } finally {
            test_29.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void addPlayerAddsANewSnake__195() {
        Test test_30 = new Test();
        try {
            MultiSnakeGame game__157 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            MultiSnakeGame bigger__158 = SnakeGlobal.addPlayer(game__157, 99);
            boolean t_1185 = bigger__158.getSnakes().size() == 3;
            Supplier<String> fn__1179 = () -> "should have 3 snakes after adding";
            test_30.assert_(t_1185, fn__1179);
        } finally {
            test_30.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void removePlayerRemovesASnake__196() {
        Test test_31 = new Test();
        try {
            MultiSnakeGame game__160 = SnakeGlobal.newMultiGame(20, 10, 3, 42);
            MultiSnakeGame smaller__161 = SnakeGlobal.removePlayer(game__160, 1);
            boolean t_1177 = smaller__161.getSnakes().size() == 2;
            Supplier<String> fn__1171 = () -> "should have 2 snakes after removing";
            test_31.assert_(t_1177, fn__1171);
        } finally {
            test_31.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiRenderProducesOutput__197() {
        Test test_32 = new Test();
        try {
            MultiSnakeGame game__163 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            String rendered__164 = SnakeGlobal.multiRender(game__163);
            boolean t_1169 = !rendered__164.equals("");
            Supplier<String> fn__1165 = () -> "render should produce output";
            test_32.assert_(t_1169, fn__1165);
        } finally {
            test_32.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void directionToStringAndStringToDirectionRoundTrip__198() {
        Test test_33 = new Test();
        try {
            String d__166 = SnakeGlobal.directionToString(new Up());
            boolean t_1161 = d__166.equals("up");
            Supplier<String> fn__1158 = () -> "Up should serialize to 'up'";
            test_33.assert_(t_1161, fn__1158);
            @Nullable Direction parsed__167 = SnakeGlobal.stringToDirection("down");
            Supplier<String> fn__1157 = () -> "'down' should parse to Down";
            test_33.assert_(true, fn__1157);
        } finally {
            test_33.softFailToHard();
        }
    }
}
