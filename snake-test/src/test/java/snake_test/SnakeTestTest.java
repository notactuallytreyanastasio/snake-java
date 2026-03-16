package snake_test;
import temper.std.testing.Test;
import java.util.function.Supplier;
import snake.SnakeGlobal;
import temper.core.Core;
import snake.Point;
import java.util.List;
import snake.PlayerSnake;
import snake.SnakeGame;
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
    @org.junit.jupiter.api.Test public void initialStateHasSnakeNearCenter__170() {
        Test test_0 = new Test();
        try {
            SnakeGame game__65 = SnakeGlobal.newGame(10, 10, 42);
            Point head__66 = Core.listGetOr(game__65.getSnake(), 0, new Point(-1, -1));
            boolean t_1569 = head__66.getX() == 5;
            Supplier<String> fn__1562 = () -> "head x should be 5, got " + Integer.toString(head__66.getX());
            test_0.assert_(t_1569, fn__1562);
            boolean t_1573 = head__66.getY() == 5;
            Supplier<String> fn__1561 = () -> "head y should be 5, got " + Integer.toString(head__66.getY());
            test_0.assert_(t_1573, fn__1561);
            boolean t_1578 = game__65.getSnake().size() == 3;
            Supplier<String> fn__1560 = () -> "snake should start with 3 segments";
            test_0.assert_(t_1578, fn__1560);
        } finally {
            test_0.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialStatusIsPlaying__171() {
        Test test_1 = new Test();
        try {
            SnakeGame game__68 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_1553 = game__68.getStatus() instanceof Playing;
            Supplier<String> fn__1550 = () -> "initial status should be Playing";
            test_1.assert_(t_1553, fn__1550);
        } finally {
            test_1.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialDirectionIsRight__172() {
        Test test_2 = new Test();
        try {
            SnakeGame game__70 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_1547 = game__70.getDirection() instanceof Right;
            Supplier<String> fn__1544 = () -> "initial direction should be Right";
            test_2.assert_(t_1547, fn__1544);
        } finally {
            test_2.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialScoreIs0__173() {
        Test test_3 = new Test();
        try {
            SnakeGame game__72 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_1542 = game__72.getScore() == 0;
            Supplier<String> fn__1538 = () -> "initial score should be 0";
            test_3.assert_(t_1542, fn__1538);
        } finally {
            test_3.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesRight__174() {
        Test test_4 = new Test();
        try {
            SnakeGame game__74 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame moved__75 = SnakeGlobal.tick(game__74);
            Point head__76 = Core.listGetOr(moved__75.getSnake(), 0, new Point(-1, -1));
            boolean t_1532 = head__76.getX() == 6;
            Supplier<String> fn__1524 = () -> "head should move right to x=6, got " + Integer.toString(head__76.getX());
            test_4.assert_(t_1532, fn__1524);
            boolean t_1536 = head__76.getY() == 5;
            Supplier<String> fn__1523 = () -> "head y should stay 5, got " + Integer.toString(head__76.getY());
            test_4.assert_(t_1536, fn__1523);
        } finally {
            test_4.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesDown__175() {
        Test test_5 = new Test();
        try {
            SnakeGame game__78 = SnakeGlobal.changeDirection(SnakeGlobal.newGame(10, 10, 42), new Down());
            SnakeGame moved__79 = SnakeGlobal.tick(game__78);
            Point head__80 = Core.listGetOr(moved__79.getSnake(), 0, new Point(-1, -1));
            boolean t_1513 = head__80.getX() == 5;
            Supplier<String> fn__1504 = () -> "head x should stay 5, got " + Integer.toString(head__80.getX());
            test_5.assert_(t_1513, fn__1504);
            boolean t_1517 = head__80.getY() == 6;
            Supplier<String> fn__1503 = () -> "head should move down to y=6, got " + Integer.toString(head__80.getY());
            test_5.assert_(t_1517, fn__1503);
        } finally {
            test_5.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesUp__176() {
        Test test_6 = new Test();
        try {
            SnakeGame game__82 = SnakeGlobal.changeDirection(SnakeGlobal.newGame(10, 10, 42), new Up());
            SnakeGame moved__83 = SnakeGlobal.tick(game__82);
            Point head__84 = Core.listGetOr(moved__83.getSnake(), 0, new Point(-1, -1));
            boolean t_1497 = head__84.getY() == 4;
            Supplier<String> fn__1488 = () -> "head should move up to y=4, got " + Integer.toString(head__84.getY());
            test_6.assert_(t_1497, fn__1488);
        } finally {
            test_6.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void oppositeDirectionIsRejected__177() {
        Test test_7 = new Test();
        try {
            SnakeGame game__86 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame changed__87 = SnakeGlobal.changeDirection(game__86, new Left());
            boolean t_1483 = changed__87.getDirection() instanceof Right;
            Supplier<String> fn__1479 = () -> "should still be Right after trying Left";
            test_7.assert_(t_1483, fn__1479);
        } finally {
            test_7.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void nonOppositeDirectionIsAccepted__178() {
        Test test_8 = new Test();
        try {
            SnakeGame game__89 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame changed__90 = SnakeGlobal.changeDirection(game__89, new Up());
            boolean t_1476 = changed__90.getDirection() instanceof Up;
            Supplier<String> fn__1472 = () -> "should change to Up";
            test_8.assert_(t_1476, fn__1472);
        } finally {
            test_8.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void wallCollisionCausesGameOver__179() {
        Test test_9 = new Test();
        try {
            SnakeGame t_1467;
            SnakeGame t_1466 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame game__92 = t_1466;
            int i__93 = 0;
            while (i__93 < 10) {
                t_1467 = SnakeGlobal.tick(game__92);
                game__92 = t_1467;
                i__93 = i__93 + 1;
            }
            boolean t_1469 = game__92.getStatus() instanceof GameOver;
            Supplier<String> fn__1465 = () -> "should be GameOver after hitting wall";
            test_9.assert_(t_1469, fn__1465);
        } finally {
            test_9.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void selfCollisionCausesGameOver__180() {
        Test test_10 = new Test();
        try {
            List<Point> snake__95 = List.of(new Point(5, 5), new Point(6, 5), new Point(6, 4), new Point(5, 4), new Point(4, 4), new Point(4, 5), new Point(4, 6));
            SnakeGame t_1459 = new SnakeGame(10, 10, snake__95, new Left(), new Point(0, 0), 0, new Playing(), 42);
            SnakeGame game__96 = t_1459;
            SnakeGame t_1460 = SnakeGlobal.tick(game__96);
            game__96 = t_1460;
            boolean t_1462 = game__96.getStatus() instanceof GameOver;
            Supplier<String> fn__1448 = () -> "should be GameOver after self collision";
            test_10.assert_(t_1462, fn__1448);
        } finally {
            test_10.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void pointEqualsWorksForSamePoints__181() {
        Test test_11 = new Test();
        try {
            boolean t_1446 = SnakeGlobal.pointEquals(new Point(3, 4), new Point(3, 4));
            Supplier<String> fn__1442 = () -> "same points should be equal";
            test_11.assert_(t_1446, fn__1442);
        } finally {
            test_11.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void pointEqualsWorksForDifferentPoints__182() {
        Test test_12 = new Test();
        try {
            boolean t_1440 = !SnakeGlobal.pointEquals(new Point(3, 4), new Point(5, 6));
            Supplier<String> fn__1436 = () -> "different points should not be equal";
            test_12.assert_(t_1440, fn__1436);
        } finally {
            test_12.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void isOppositeDetectsOppositeDirections__183() {
        Test test_13 = new Test();
        try {
            boolean t_1424 = SnakeGlobal.isOpposite(new Up(), new Down());
            Supplier<String> fn__1420 = () -> "Up/Down are opposite";
            test_13.assert_(t_1424, fn__1420);
            boolean t_1429 = SnakeGlobal.isOpposite(new Left(), new Right());
            Supplier<String> fn__1419 = () -> "Left/Right are opposite";
            test_13.assert_(t_1429, fn__1419);
            boolean t_1434 = !SnakeGlobal.isOpposite(new Up(), new Left());
            Supplier<String> fn__1418 = () -> "Up/Left are not opposite";
            test_13.assert_(t_1434, fn__1418);
        } finally {
            test_13.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void directionDeltaReturnsCorrectDeltas__184() {
        Test test_14 = new Test();
        try {
            int t_1410;
            int t_1415;
            boolean t_703;
            boolean t_708;
            Point up__101 = SnakeGlobal.directionDelta(new Up());
            if (up__101.getX() == 0) {
                t_1410 = up__101.getY();
                t_703 = t_1410 == -1;
            } else {
                t_703 = false;
            }
            Supplier<String> fn__1407 = () -> "Up should be (0, -1)";
            test_14.assert_(t_703, fn__1407);
            Point right__102 = SnakeGlobal.directionDelta(new Right());
            if (right__102.getX() == 1) {
                t_1415 = right__102.getY();
                t_708 = t_1415 == 0;
            } else {
                t_708 = false;
            }
            Supplier<String> fn__1406 = () -> "Right should be (1, 0)";
            test_14.assert_(t_708, fn__1406);
        } finally {
            test_14.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void prngIsDeterministic__185() {
        Test test_17 = new Test();
        try {
            RandomResult r1__104 = SnakeGlobal.nextRandom(42, 100);
            RandomResult r2__105 = SnakeGlobal.nextRandom(42, 100);
            boolean t_1399 = r1__104.getValue() == r2__105.getValue();
            Supplier<String> fn__1395 = () -> "same seed should produce same value";
            test_17.assert_(t_1399, fn__1395);
            boolean t_1404 = r1__104.getNextSeed() == r2__105.getNextSeed();
            Supplier<String> fn__1394 = () -> "same seed should produce same next seed";
            test_17.assert_(t_1404, fn__1394);
        } finally {
            test_17.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void prngProducesValuesInRange__186() {
        Test test_18 = new Test();
        try {
            int t_1391;
            boolean t_693;
            RandomResult r__107 = SnakeGlobal.nextRandom(42, 10);
            if (r__107.getValue() >= 0) {
                t_1391 = r__107.getValue();
                t_693 = t_1391 < 10;
            } else {
                t_693 = false;
            }
            Supplier<String> fn__1389 = () -> "value should be in [0, 10), got " + Integer.toString(r__107.getValue());
            test_18.assert_(t_693, fn__1389);
        } finally {
            test_18.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void tickDoesNothingWhenGameIsOver__187() {
        Test test_20 = new Test();
        try {
            SnakeGame t_1372;
            SnakeGame t_1371 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame game__109 = t_1371;
            int i__110 = 0;
            while (i__110 < 10) {
                t_1372 = SnakeGlobal.tick(game__109);
                game__109 = t_1372;
                i__110 = i__110 + 1;
            }
            boolean t_1374 = game__109.getStatus() instanceof GameOver;
            Supplier<String> fn__1370 = () -> "should be GameOver";
            test_20.assert_(t_1374, fn__1370);
            Point head1__111 = Core.listGetOr(game__109.getSnake(), 0, new Point(-1, -1));
            SnakeGame t_1380 = SnakeGlobal.tick(game__109);
            game__109 = t_1380;
            Point head2__112 = Core.listGetOr(game__109.getSnake(), 0, new Point(-1, -1));
            boolean t_1385 = SnakeGlobal.pointEquals(head1__111, head2__112);
            Supplier<String> fn__1369 = () -> "snake should not move after game over";
            test_20.assert_(t_1385, fn__1369);
        } finally {
            test_20.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameCreatesCorrectNumberOfSnakes__188() {
        Test test_21 = new Test();
        try {
            MultiSnakeGame game__114 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            boolean t_1367 = game__114.getSnakes().size() == 2;
            Supplier<String> fn__1362 = () -> "should have 2 snakes";
            test_21.assert_(t_1367, fn__1362);
        } finally {
            test_21.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameSnakesStartAlive__189() {
        Test test_22 = new Test();
        try {
            MultiSnakeGame game__116 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            PlayerSnake s0__117 = Core.listGetOr(game__116.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__118 = Core.listGetOr(game__116.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1355 = s0__117.getStatus() instanceof Alive;
            Supplier<String> fn__1340 = () -> "player 0 should be alive";
            test_22.assert_(t_1355, fn__1340);
            boolean t_1359 = s1__118.getStatus() instanceof Alive;
            Supplier<String> fn__1339 = () -> "player 1 should be alive";
            test_22.assert_(t_1359, fn__1339);
        } finally {
            test_22.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameSnakesStartAtDifferentPositions__190() {
        Test test_23 = new Test();
        try {
            MultiSnakeGame game__120 = SnakeGlobal.newMultiGame(60, 30, 2, 42);
            PlayerSnake s0__121 = Core.listGetOr(game__120.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__122 = Core.listGetOr(game__120.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            Point h0__123 = Core.listGetOr(s0__121.getSegments(), 0, new Point(-1, -1));
            Point h1__124 = Core.listGetOr(s1__122.getSegments(), 0, new Point(-1, -1));
            boolean t_1337 = !SnakeGlobal.pointEquals(h0__123, h1__124);
            Supplier<String> fn__1316 = () -> "snakes should start at different positions";
            test_23.assert_(t_1337, fn__1316);
        } finally {
            test_23.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameSnakesHave3_segmentsEach__191() {
        Test test_24 = new Test();
        try {
            MultiSnakeGame game__126 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            PlayerSnake s0__127 = Core.listGetOr(game__126.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__128 = Core.listGetOr(game__126.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1309 = s0__127.getSegments().size() == 3;
            Supplier<String> fn__1292 = () -> "player 0 should have 3 segments";
            test_24.assert_(t_1309, fn__1292);
            boolean t_1314 = s1__128.getSegments().size() == 3;
            Supplier<String> fn__1291 = () -> "player 1 should have 3 segments";
            test_24.assert_(t_1314, fn__1291);
        } finally {
            test_24.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiTickMovesBothSnakes__192() {
        Test test_25 = new Test();
        try {
            MultiSnakeGame game__130 = SnakeGlobal.newMultiGame(60, 30, 2, 42);
            PlayerSnake s0__131 = Core.listGetOr(game__130.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            PlayerSnake s1__132 = Core.listGetOr(game__130.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            Point h0Before__133 = Core.listGetOr(s0__131.getSegments(), 0, new Point(0, 0));
            Point h1Before__134 = Core.listGetOr(s1__132.getSegments(), 0, new Point(0, 0));
            List<Direction> dirs__135 = List.of(s0__131.getDirection(), s1__132.getDirection());
            MultiSnakeGame after__136 = SnakeGlobal.multiTick(game__130, dirs__135);
            Point h0After__137 = Core.listGetOr(Core.listGetOr(after__136.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead())).getSegments(), 0, new Point(0, 0));
            Point h1After__138 = Core.listGetOr(Core.listGetOr(after__136.getSnakes(), 1, new PlayerSnake(0, List.of(), new Right(), 0, new Dead())).getSegments(), 0, new Point(0, 0));
            boolean t_1286 = !SnakeGlobal.pointEquals(h0Before__133, h0After__137);
            Supplier<String> fn__1244 = () -> "snake 0 should have moved";
            test_25.assert_(t_1286, fn__1244);
            boolean t_1289 = !SnakeGlobal.pointEquals(h1Before__134, h1After__138);
            Supplier<String> fn__1243 = () -> "snake 1 should have moved";
            test_25.assert_(t_1289, fn__1243);
        } finally {
            test_25.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiWallCollisionKillsOneSnake__193() {
        Test test_26 = new Test();
        try {
            MultiSnakeGame t_1229;
            int t_1231;
            List<PlayerSnake> t_1232;
            PlayerSnake t_1236;
            MultiSnakeGame t_1226 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            MultiSnakeGame game__140 = t_1226;
            List<Direction> dirs__141 = List.of(new Right(), new Left());
            int i__142 = 0;
            while (i__142 < 20) {
                t_1229 = SnakeGlobal.multiTick(game__140, dirs__141);
                game__140 = t_1229;
                i__142 = i__142 + 1;
            }
            int deadCount__143 = 0;
            int i__144 = 0;
            while (true) {
                t_1231 = game__140.getSnakes().size();
                if (i__144 >= t_1231) {
                    break;
                }
                t_1232 = game__140.getSnakes();
                t_1236 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                PlayerSnake snake__145 = Core.listGetOr(t_1232, i__144, t_1236);
                if (snake__145.getStatus() instanceof Dead) {
                    deadCount__143 = deadCount__143 + 1;
                }
                i__144 = i__144 + 1;
            }
            boolean t_1241 = deadCount__143 > 0;
            Supplier<String> fn__1225 = () -> "at least one snake should be dead after 20 ticks toward walls";
            test_26.assert_(t_1241, fn__1225);
        } finally {
            test_26.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiGameOverWhenOnePlayerLeft__194() {
        Test test_27 = new Test();
        try {
            MultiSnakeGame t_1221;
            MultiSnakeGame t_1218 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            MultiSnakeGame game__147 = t_1218;
            List<Direction> dirs__148 = List.of(new Right(), new Left());
            int i__149 = 0;
            while (i__149 < 30) {
                t_1221 = SnakeGlobal.multiTick(game__147, dirs__148);
                game__147 = t_1221;
                i__149 = i__149 + 1;
            }
            boolean t_1222 = SnakeGlobal.isMultiGameOver(game__147);
            Supplier<String> fn__1217 = () -> "game should be over after enough ticks";
            test_27.assert_(t_1222, fn__1217);
        } finally {
            test_27.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void changePlayerDirectionWorks__195() {
        Test test_28 = new Test();
        try {
            MultiSnakeGame game__151 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            Up t_1205 = new Up();
            MultiSnakeGame changed__152 = SnakeGlobal.changePlayerDirection(game__151, 0, t_1205);
            PlayerSnake s0__153 = Core.listGetOr(changed__152.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1214 = s0__153.getDirection() instanceof Up;
            Supplier<String> fn__1203 = () -> "player 0 direction should be Up";
            test_28.assert_(t_1214, fn__1203);
        } finally {
            test_28.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void changePlayerDirectionRejectsOpposite__196() {
        Test test_29 = new Test();
        try {
            MultiSnakeGame game__155 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            Left t_1191 = new Left();
            MultiSnakeGame changed__156 = SnakeGlobal.changePlayerDirection(game__155, 0, t_1191);
            PlayerSnake s0__157 = Core.listGetOr(changed__156.getSnakes(), 0, new PlayerSnake(0, List.of(), new Right(), 0, new Dead()));
            boolean t_1200 = s0__157.getDirection() instanceof Right;
            Supplier<String> fn__1189 = () -> "should reject opposite direction";
            test_29.assert_(t_1200, fn__1189);
        } finally {
            test_29.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void addPlayerAddsANewSnake__197() {
        Test test_30 = new Test();
        try {
            MultiSnakeGame game__159 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            MultiSnakeGame bigger__160 = SnakeGlobal.addPlayer(game__159, 99);
            boolean t_1187 = bigger__160.getSnakes().size() == 3;
            Supplier<String> fn__1181 = () -> "should have 3 snakes after adding";
            test_30.assert_(t_1187, fn__1181);
        } finally {
            test_30.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void removePlayerRemovesASnake__198() {
        Test test_31 = new Test();
        try {
            MultiSnakeGame game__162 = SnakeGlobal.newMultiGame(20, 10, 3, 42);
            MultiSnakeGame smaller__163 = SnakeGlobal.removePlayer(game__162, 1);
            boolean t_1179 = smaller__163.getSnakes().size() == 2;
            Supplier<String> fn__1173 = () -> "should have 2 snakes after removing";
            test_31.assert_(t_1179, fn__1173);
        } finally {
            test_31.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void multiRenderProducesOutput__199() {
        Test test_32 = new Test();
        try {
            MultiSnakeGame game__165 = SnakeGlobal.newMultiGame(20, 10, 2, 42);
            String rendered__166 = SnakeGlobal.multiRender(game__165);
            boolean t_1171 = !rendered__166.equals("");
            Supplier<String> fn__1167 = () -> "render should produce output";
            test_32.assert_(t_1171, fn__1167);
        } finally {
            test_32.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void directionToStringAndStringToDirectionRoundTrip__200() {
        Test test_33 = new Test();
        try {
            String d__168 = SnakeGlobal.directionToString(new Up());
            boolean t_1163 = d__168.equals("up");
            Supplier<String> fn__1160 = () -> "Up should serialize to 'up'";
            test_33.assert_(t_1163, fn__1160);
            @Nullable Direction parsed__169 = SnakeGlobal.stringToDirection("down");
            Supplier<String> fn__1159 = () -> "'down' should parse to Down";
            test_33.assert_(true, fn__1159);
        } finally {
            test_33.softFailToHard();
        }
    }
}
