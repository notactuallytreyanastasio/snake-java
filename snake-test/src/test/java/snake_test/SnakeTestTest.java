package snake_test;
import temper.std.testing.Test;
import java.util.function.Supplier;
import snake.SnakeGlobal;
import snake.Point;
import snake.SnakeGame;
import temper.core.Core;
import snake.Up;
import snake.Left;
import snake.Right;
import java.util.List;
import snake.GameOver;
import snake.Down;
import snake.RandomResult;
import snake.Playing;
public final class SnakeTestTest {
    private SnakeTestTest() {
    }
    @org.junit.jupiter.api.Test public void initialStateHasSnakeNearCenter__86() {
        Test test_0 = new Test();
        try {
            SnakeGame game__38 = SnakeGlobal.newGame(10, 10, 42);
            Point head__39 = Core.listGetOr(game__38.getSnake(), 0, new Point(-1, -1));
            boolean t_766 = head__39.getX() == 5;
            Supplier<String> fn__759 = () -> "head x should be 5, got " + Integer.toString(head__39.getX());
            test_0.assert_(t_766, fn__759);
            boolean t_770 = head__39.getY() == 5;
            Supplier<String> fn__758 = () -> "head y should be 5, got " + Integer.toString(head__39.getY());
            test_0.assert_(t_770, fn__758);
            boolean t_775 = game__38.getSnake().size() == 3;
            Supplier<String> fn__757 = () -> "snake should start with 3 segments";
            test_0.assert_(t_775, fn__757);
        } finally {
            test_0.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialStatusIsPlaying__87() {
        Test test_1 = new Test();
        try {
            SnakeGame game__41 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_750 = game__41.getStatus() instanceof Playing;
            Supplier<String> fn__747 = () -> "initial status should be Playing";
            test_1.assert_(t_750, fn__747);
        } finally {
            test_1.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialDirectionIsRight__88() {
        Test test_2 = new Test();
        try {
            SnakeGame game__43 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_744 = game__43.getDirection() instanceof Right;
            Supplier<String> fn__741 = () -> "initial direction should be Right";
            test_2.assert_(t_744, fn__741);
        } finally {
            test_2.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void initialScoreIs0__89() {
        Test test_3 = new Test();
        try {
            SnakeGame game__45 = SnakeGlobal.newGame(10, 10, 42);
            boolean t_739 = game__45.getScore() == 0;
            Supplier<String> fn__735 = () -> "initial score should be 0";
            test_3.assert_(t_739, fn__735);
        } finally {
            test_3.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesRight__90() {
        Test test_4 = new Test();
        try {
            SnakeGame game__47 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame moved__48 = SnakeGlobal.tick(game__47);
            Point head__49 = Core.listGetOr(moved__48.getSnake(), 0, new Point(-1, -1));
            boolean t_729 = head__49.getX() == 6;
            Supplier<String> fn__721 = () -> "head should move right to x=6, got " + Integer.toString(head__49.getX());
            test_4.assert_(t_729, fn__721);
            boolean t_733 = head__49.getY() == 5;
            Supplier<String> fn__720 = () -> "head y should stay 5, got " + Integer.toString(head__49.getY());
            test_4.assert_(t_733, fn__720);
        } finally {
            test_4.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesDown__91() {
        Test test_5 = new Test();
        try {
            SnakeGame game__51 = SnakeGlobal.changeDirection(SnakeGlobal.newGame(10, 10, 42), new Down());
            SnakeGame moved__52 = SnakeGlobal.tick(game__51);
            Point head__53 = Core.listGetOr(moved__52.getSnake(), 0, new Point(-1, -1));
            boolean t_710 = head__53.getX() == 5;
            Supplier<String> fn__701 = () -> "head x should stay 5, got " + Integer.toString(head__53.getX());
            test_5.assert_(t_710, fn__701);
            boolean t_714 = head__53.getY() == 6;
            Supplier<String> fn__700 = () -> "head should move down to y=6, got " + Integer.toString(head__53.getY());
            test_5.assert_(t_714, fn__700);
        } finally {
            test_5.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void snakeMovesUp__92() {
        Test test_6 = new Test();
        try {
            SnakeGame game__55 = SnakeGlobal.changeDirection(SnakeGlobal.newGame(10, 10, 42), new Up());
            SnakeGame moved__56 = SnakeGlobal.tick(game__55);
            Point head__57 = Core.listGetOr(moved__56.getSnake(), 0, new Point(-1, -1));
            boolean t_694 = head__57.getY() == 4;
            Supplier<String> fn__685 = () -> "head should move up to y=4, got " + Integer.toString(head__57.getY());
            test_6.assert_(t_694, fn__685);
        } finally {
            test_6.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void oppositeDirectionIsRejected__93() {
        Test test_7 = new Test();
        try {
            SnakeGame game__59 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame changed__60 = SnakeGlobal.changeDirection(game__59, new Left());
            boolean t_680 = changed__60.getDirection() instanceof Right;
            Supplier<String> fn__676 = () -> "should still be Right after trying Left";
            test_7.assert_(t_680, fn__676);
        } finally {
            test_7.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void nonOppositeDirectionIsAccepted__94() {
        Test test_8 = new Test();
        try {
            SnakeGame game__62 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame changed__63 = SnakeGlobal.changeDirection(game__62, new Up());
            boolean t_673 = changed__63.getDirection() instanceof Up;
            Supplier<String> fn__669 = () -> "should change to Up";
            test_8.assert_(t_673, fn__669);
        } finally {
            test_8.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void wallCollisionCausesGameOver__95() {
        Test test_9 = new Test();
        try {
            SnakeGame t_664;
            SnakeGame t_663 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame game__65 = t_663;
            int i__66 = 0;
            while (i__66 < 10) {
                t_664 = SnakeGlobal.tick(game__65);
                game__65 = t_664;
                i__66 = i__66 + 1;
            }
            boolean t_666 = game__65.getStatus() instanceof GameOver;
            Supplier<String> fn__662 = () -> "should be GameOver after hitting wall";
            test_9.assert_(t_666, fn__662);
        } finally {
            test_9.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void selfCollisionCausesGameOver__96() {
        Test test_10 = new Test();
        try {
            List<Point> snake__68 = List.of(new Point(5, 5), new Point(6, 5), new Point(6, 4), new Point(5, 4), new Point(4, 4), new Point(4, 5), new Point(4, 6));
            SnakeGame t_656 = new SnakeGame(10, 10, snake__68, new Left(), new Point(0, 0), 0, new Playing(), 42);
            SnakeGame game__69 = t_656;
            SnakeGame t_657 = SnakeGlobal.tick(game__69);
            game__69 = t_657;
            boolean t_659 = game__69.getStatus() instanceof GameOver;
            Supplier<String> fn__645 = () -> "should be GameOver after self collision";
            test_10.assert_(t_659, fn__645);
        } finally {
            test_10.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void pointEqualsWorksForSamePoints__97() {
        Test test_11 = new Test();
        try {
            boolean t_643 = SnakeGlobal.pointEquals(new Point(3, 4), new Point(3, 4));
            Supplier<String> fn__639 = () -> "same points should be equal";
            test_11.assert_(t_643, fn__639);
        } finally {
            test_11.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void pointEqualsWorksForDifferentPoints__98() {
        Test test_12 = new Test();
        try {
            boolean t_637 = !SnakeGlobal.pointEquals(new Point(3, 4), new Point(5, 6));
            Supplier<String> fn__633 = () -> "different points should not be equal";
            test_12.assert_(t_637, fn__633);
        } finally {
            test_12.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void isOppositeDetectsOppositeDirections__99() {
        Test test_13 = new Test();
        try {
            boolean t_621 = SnakeGlobal.isOpposite(new Up(), new Down());
            Supplier<String> fn__617 = () -> "Up/Down are opposite";
            test_13.assert_(t_621, fn__617);
            boolean t_626 = SnakeGlobal.isOpposite(new Left(), new Right());
            Supplier<String> fn__616 = () -> "Left/Right are opposite";
            test_13.assert_(t_626, fn__616);
            boolean t_631 = !SnakeGlobal.isOpposite(new Up(), new Left());
            Supplier<String> fn__615 = () -> "Up/Left are not opposite";
            test_13.assert_(t_631, fn__615);
        } finally {
            test_13.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void directionDeltaReturnsCorrectDeltas__100() {
        Test test_14 = new Test();
        try {
            int t_607;
            int t_612;
            boolean t_279;
            boolean t_284;
            Point up__74 = SnakeGlobal.directionDelta(new Up());
            if (up__74.getX() == 0) {
                t_607 = up__74.getY();
                t_279 = t_607 == -1;
            } else {
                t_279 = false;
            }
            Supplier<String> fn__604 = () -> "Up should be (0, -1)";
            test_14.assert_(t_279, fn__604);
            Point right__75 = SnakeGlobal.directionDelta(new Right());
            if (right__75.getX() == 1) {
                t_612 = right__75.getY();
                t_284 = t_612 == 0;
            } else {
                t_284 = false;
            }
            Supplier<String> fn__603 = () -> "Right should be (1, 0)";
            test_14.assert_(t_284, fn__603);
        } finally {
            test_14.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void prngIsDeterministic__101() {
        Test test_17 = new Test();
        try {
            RandomResult r1__77 = SnakeGlobal.nextRandom(42, 100);
            RandomResult r2__78 = SnakeGlobal.nextRandom(42, 100);
            boolean t_596 = r1__77.getValue() == r2__78.getValue();
            Supplier<String> fn__592 = () -> "same seed should produce same value";
            test_17.assert_(t_596, fn__592);
            boolean t_601 = r1__77.getNextSeed() == r2__78.getNextSeed();
            Supplier<String> fn__591 = () -> "same seed should produce same next seed";
            test_17.assert_(t_601, fn__591);
        } finally {
            test_17.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void prngProducesValuesInRange__102() {
        Test test_18 = new Test();
        try {
            int t_588;
            boolean t_269;
            RandomResult r__80 = SnakeGlobal.nextRandom(42, 10);
            if (r__80.getValue() >= 0) {
                t_588 = r__80.getValue();
                t_269 = t_588 < 10;
            } else {
                t_269 = false;
            }
            Supplier<String> fn__586 = () -> "value should be in [0, 10), got " + Integer.toString(r__80.getValue());
            test_18.assert_(t_269, fn__586);
        } finally {
            test_18.softFailToHard();
        }
    }
    @org.junit.jupiter.api.Test public void tickDoesNothingWhenGameIsOver__103() {
        Test test_20 = new Test();
        try {
            SnakeGame t_569;
            SnakeGame t_568 = SnakeGlobal.newGame(10, 10, 42);
            SnakeGame game__82 = t_568;
            int i__83 = 0;
            while (i__83 < 10) {
                t_569 = SnakeGlobal.tick(game__82);
                game__82 = t_569;
                i__83 = i__83 + 1;
            }
            boolean t_571 = game__82.getStatus() instanceof GameOver;
            Supplier<String> fn__567 = () -> "should be GameOver";
            test_20.assert_(t_571, fn__567);
            Point head1__84 = Core.listGetOr(game__82.getSnake(), 0, new Point(-1, -1));
            SnakeGame t_577 = SnakeGlobal.tick(game__82);
            game__82 = t_577;
            Point head2__85 = Core.listGetOr(game__82.getSnake(), 0, new Point(-1, -1));
            boolean t_582 = SnakeGlobal.pointEquals(head1__84, head2__85);
            Supplier<String> fn__566 = () -> "snake should not move after game over";
            test_20.assert_(t_582, fn__566);
        } finally {
            test_20.softFailToHard();
        }
    }
}
