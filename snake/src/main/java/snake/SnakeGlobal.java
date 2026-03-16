package snake;
import java.util.List;
import temper.core.Core;
import java.util.ArrayList;
import java.util.function.Consumer;
import temper.core.Nullable;
public final class SnakeGlobal {
    private SnakeGlobal() {
    }
    public static Direction move(Point head__98, List<Point> body__99, Point food__100, int width__101, int height__102) {
        return new Right();
    }
    public static boolean pointEquals(Point a__115, Point b__116) {
        boolean return__50;
        int t_2803;
        int t_2804;
        if (a__115.getX() == b__116.getX()) {
            t_2803 = a__115.getY();
            t_2804 = b__116.getY();
            return__50 = t_2803 == t_2804;
        } else {
            return__50 = false;
        }
        return return__50;
    }
    public static boolean isOpposite(Direction a__118, Direction b__119) {
        boolean return__51;
        if (a__118 instanceof Up) {
            return__51 = b__119 instanceof Down;
        } else if (a__118 instanceof Down) {
            return__51 = b__119 instanceof Up;
        } else if (a__118 instanceof Left) {
            return__51 = b__119 instanceof Right;
        } else if (a__118 instanceof Right) {
            return__51 = b__119 instanceof Left;
        } else {
            return__51 = false;
        }
        return return__51;
    }
    public static Point directionDelta(Direction dir__121) {
        Point return__52;
        if (dir__121 instanceof Up) {
            return__52 = new Point(0, -1);
        } else if (dir__121 instanceof Down) {
            return__52 = new Point(0, 1);
        } else if (dir__121 instanceof Left) {
            return__52 = new Point(-1, 0);
        } else if (dir__121 instanceof Right) {
            return__52 = new Point(1, 0);
        } else {
            return__52 = new Point(0, 0);
        }
        return return__52;
    }
    public static RandomResult nextRandom(int seed__128, int max__129) {
        int t_1687;
        int t_1689;
        int raw__131 = seed__128 * 1664525 + 1013904223;
        int masked__132 = raw__131 & 2147483647;
        int posVal__133;
        if (masked__132 < 0) {
            posVal__133 = -masked__132;
        } else {
            posVal__133 = masked__132;
        }
        int value__134 = 0;
        if (max__129 > 0) {
            try {
                t_1687 = Core.modIntInt(posVal__133, max__129);
                t_1689 = t_1687;
            } catch (RuntimeException ignored$1) {
                t_1689 = 0;
            }
            value__134 = t_1689;
        }
        return new RandomResult(value__134, masked__132);
    }
    static FoodPlacement placeFood__93(List<Point> snake__157, int width__158, int height__159, int seed__160) {
        FoodPlacement return__61;
        int t_2770;
        Point t_2781;
        int t_1652;
        int t_1654;
        int t_1656;
        int t_1658;
        fn__161: {
            int totalCells__162 = width__158 * height__159;
            int currentSeed__163 = seed__160;
            int attempt__164 = 0;
            while (attempt__164 < totalCells__162) {
                RandomResult result__165 = SnakeGlobal.nextRandom(currentSeed__163, totalCells__162);
                t_2770 = result__165.getNextSeed();
                currentSeed__163 = t_2770;
                int px__166 = 0;
                int py__167 = 0;
                if (width__158 > 0) {
                    try {
                        t_1652 = Core.modIntInt(result__165.getValue(), width__158);
                        t_1654 = t_1652;
                    } catch (RuntimeException ignored$2) {
                        t_1654 = 0;
                    }
                    px__166 = t_1654;
                    try {
                        t_1656 = Core.divIntInt(result__165.getValue(), width__158);
                        t_1658 = t_1656;
                    } catch (RuntimeException ignored$3) {
                        t_1658 = 0;
                    }
                    py__167 = t_1658;
                }
                Point candidate__168 = new Point(px__166, py__167);
                class Local_1 {
                    boolean occupied__169 = false;
                }
                final Local_1 local$1 = new Local_1();
                Consumer<Point> fn__2769 = seg__170 -> {
                    if (SnakeGlobal.pointEquals(seg__170, candidate__168)) {
                        local$1.occupied__169 = true;
                    }
                };
                snake__157.forEach(fn__2769);
                if (!local$1.occupied__169) {
                    return__61 = new FoodPlacement(candidate__168, currentSeed__163);
                    break fn__161;
                }
                attempt__164 = attempt__164 + 1;
            }
            int y__171 = 0;
            while (y__171 < height__159) {
                int x__172 = 0;
                while (x__172 < width__158) {
                    Point candidate__173 = new Point(x__172, y__171);
                    class Local_2 {
                        boolean free__174 = true;
                    }
                    final Local_2 local$2 = new Local_2();
                    Consumer<Point> fn__2768 = seg__175 -> {
                        if (SnakeGlobal.pointEquals(seg__175, candidate__173)) {
                            local$2.free__174 = false;
                        }
                    };
                    snake__157.forEach(fn__2768);
                    if (local$2.free__174) {
                        return__61 = new FoodPlacement(candidate__173, currentSeed__163);
                        break fn__161;
                    }
                    x__172 = x__172 + 1;
                }
                y__171 = y__171 + 1;
            }
            t_2781 = new Point(0, 0);
            return__61 = new FoodPlacement(t_2781, currentSeed__163);
        }
        return return__61;
    }
    public static SnakeGame newGame(int width__176, int height__177, int seed__178) {
        int t_1635;
        int t_1637;
        int t_1638;
        int t_1640;
        int centerX__180 = 0;
        int centerY__181 = 0;
        if (width__176 > 0) {
            t_1635 = width__176 / 2;
            t_1637 = t_1635;
            centerX__180 = t_1637;
        }
        if (height__177 > 0) {
            t_1638 = height__177 / 2;
            t_1640 = t_1638;
            centerY__181 = t_1640;
        }
        List<Point> snake__182 = List.of(new Point(centerX__180, centerY__181), new Point(centerX__180 - 1, centerY__181), new Point(centerX__180 - 2, centerY__181));
        FoodPlacement foodResult__183 = SnakeGlobal.placeFood__93(snake__182, width__176, height__177, seed__178);
        Right t_2763 = new Right();
        Point t_2764 = foodResult__183.getPoint();
        Playing t_2765 = new Playing();
        int t_2766 = foodResult__183.getSeed();
        return new SnakeGame(width__176, height__177, snake__182, t_2763, t_2764, 0, t_2765, t_2766);
    }
    public static SnakeGame changeDirection(SnakeGame game__184, Direction dir__185) {
        SnakeGame return__63;
        int t_2751;
        int t_2752;
        List<Point> t_2753;
        Point t_2754;
        int t_2755;
        GameStatus t_2756;
        int t_2757;
        if (SnakeGlobal.isOpposite(game__184.getDirection(), dir__185)) {
            return__63 = game__184;
        } else {
            t_2751 = game__184.getWidth();
            t_2752 = game__184.getHeight();
            t_2753 = game__184.getSnake();
            t_2754 = game__184.getFood();
            t_2755 = game__184.getScore();
            t_2756 = game__184.getStatus();
            t_2757 = game__184.getRngSeed();
            return__63 = new SnakeGame(t_2751, t_2752, t_2753, dir__185, t_2754, t_2755, t_2756, t_2757);
        }
        return return__63;
    }
    public static SnakeGame tick(SnakeGame game__187) {
        SnakeGame return__64;
        int t_2691;
        int t_2692;
        int t_2693;
        int t_2694;
        List<Point> t_2695;
        Direction t_2696;
        Point t_2697;
        int t_2698;
        GameOver t_2699;
        int t_2700;
        int t_2704;
        int t_2706;
        List<Point> t_2707;
        Point t_2708;
        int t_2710;
        int t_2711;
        List<Point> t_2712;
        Direction t_2713;
        Point t_2714;
        int t_2715;
        GameOver t_2716;
        int t_2717;
        int t_2722;
        int t_2724;
        List<Point> t_2725;
        Point t_2726;
        int t_2731;
        int t_2732;
        int t_2733;
        int t_2735;
        int t_2736;
        Direction t_2737;
        Point t_2738;
        Playing t_2739;
        int t_2740;
        int t_2742;
        int t_2743;
        Direction t_2744;
        Point t_2745;
        int t_2746;
        GameStatus t_2747;
        int t_2748;
        boolean t_1562;
        boolean t_1563;
        boolean t_1564;
        fn__188: {
            if (game__187.getStatus() instanceof GameOver) {
                return__64 = game__187;
                break fn__188;
            }
            Point delta__189 = SnakeGlobal.directionDelta(game__187.getDirection());
            Point head__190 = Core.listGetOr(game__187.getSnake(), 0, new Point(0, 0));
            Point newHead__191 = new Point(head__190.getX() + delta__189.getX(), head__190.getY() + delta__189.getY());
            if (newHead__191.getX() < 0) {
                t_1564 = true;
            } else {
                if (newHead__191.getX() >= game__187.getWidth()) {
                    t_1563 = true;
                } else {
                    if (newHead__191.getY() < 0) {
                        t_1562 = true;
                    } else {
                        t_2691 = newHead__191.getY();
                        t_2692 = game__187.getHeight();
                        t_1562 = t_2691 >= t_2692;
                    }
                    t_1563 = t_1562;
                }
                t_1564 = t_1563;
            }
            if (t_1564) {
                t_2693 = game__187.getWidth();
                t_2694 = game__187.getHeight();
                t_2695 = game__187.getSnake();
                t_2696 = game__187.getDirection();
                t_2697 = game__187.getFood();
                t_2698 = game__187.getScore();
                t_2699 = new GameOver();
                t_2700 = game__187.getRngSeed();
                return__64 = new SnakeGame(t_2693, t_2694, t_2695, t_2696, t_2697, t_2698, t_2699, t_2700);
                break fn__188;
            }
            boolean eating__192 = SnakeGlobal.pointEquals(newHead__191, game__187.getFood());
            int checkLength__193;
            if (eating__192) {
                t_2704 = game__187.getSnake().size();
                checkLength__193 = t_2704;
            } else {
                t_2706 = game__187.getSnake().size();
                checkLength__193 = t_2706 - 1;
            }
            int i__194 = 0;
            while (i__194 < checkLength__193) {
                t_2707 = game__187.getSnake();
                t_2708 = new Point(-1, -1);
                if (SnakeGlobal.pointEquals(newHead__191, Core.listGetOr(t_2707, i__194, t_2708))) {
                    t_2710 = game__187.getWidth();
                    t_2711 = game__187.getHeight();
                    t_2712 = game__187.getSnake();
                    t_2713 = game__187.getDirection();
                    t_2714 = game__187.getFood();
                    t_2715 = game__187.getScore();
                    t_2716 = new GameOver();
                    t_2717 = game__187.getRngSeed();
                    return__64 = new SnakeGame(t_2710, t_2711, t_2712, t_2713, t_2714, t_2715, t_2716, t_2717);
                    break fn__188;
                }
                i__194 = i__194 + 1;
            }
            List<Point> newSnakeBuilder__195 = new ArrayList<>();
            Core.listAdd(newSnakeBuilder__195, newHead__191);
            int keepLength__196;
            if (eating__192) {
                t_2722 = game__187.getSnake().size();
                keepLength__196 = t_2722;
            } else {
                t_2724 = game__187.getSnake().size();
                keepLength__196 = t_2724 - 1;
            }
            int i__197 = 0;
            while (i__197 < keepLength__196) {
                t_2725 = game__187.getSnake();
                t_2726 = new Point(0, 0);
                Core.listAdd(newSnakeBuilder__195, Core.listGetOr(t_2725, i__197, t_2726));
                i__197 = i__197 + 1;
            }
            List<Point> newSnake__198 = List.copyOf(newSnakeBuilder__195);
            if (eating__192) {
                int newScore__199 = game__187.getScore() + 1;
                t_2731 = game__187.getWidth();
                t_2732 = game__187.getHeight();
                t_2733 = game__187.getRngSeed();
                FoodPlacement foodResult__200 = SnakeGlobal.placeFood__93(newSnake__198, t_2731, t_2732, t_2733);
                t_2735 = game__187.getWidth();
                t_2736 = game__187.getHeight();
                t_2737 = game__187.getDirection();
                t_2738 = foodResult__200.getPoint();
                t_2739 = new Playing();
                t_2740 = foodResult__200.getSeed();
                return__64 = new SnakeGame(t_2735, t_2736, newSnake__198, t_2737, t_2738, newScore__199, t_2739, t_2740);
            } else {
                t_2742 = game__187.getWidth();
                t_2743 = game__187.getHeight();
                t_2744 = game__187.getDirection();
                t_2745 = game__187.getFood();
                t_2746 = game__187.getScore();
                t_2747 = game__187.getStatus();
                t_2748 = game__187.getRngSeed();
                return__64 = new SnakeGame(t_2742, t_2743, newSnake__198, t_2744, t_2745, t_2746, t_2747, t_2748);
            }
        }
        return return__64;
    }
    static String cellChar__94(SnakeGame game__210, Point p__211) {
        String return__66;
        int t_2670;
        List<Point> t_2671;
        Point t_2672;
        Point t_2673;
        Point t_2674;
        fn__212: {
            Point head__213 = Core.listGetOr(game__210.getSnake(), 0, new Point(-1, -1));
            if (SnakeGlobal.pointEquals(p__211, head__213)) {
                return__66 = "@";
                break fn__212;
            }
            int i__214 = 1;
            while (true) {
                t_2670 = game__210.getSnake().size();
                if (i__214 >= t_2670) {
                    break;
                }
                t_2671 = game__210.getSnake();
                t_2672 = new Point(-1, -1);
                t_2673 = Core.listGetOr(t_2671, i__214, t_2672);
                if (SnakeGlobal.pointEquals(p__211, t_2673)) {
                    return__66 = "o";
                    break fn__212;
                }
                i__214 = i__214 + 1;
            }
            t_2674 = game__210.getFood();
            if (SnakeGlobal.pointEquals(p__211, t_2674)) {
                return__66 = "*";
                break fn__212;
            }
            return__66 = " ";
        }
        return return__66;
    }
    public static String render(SnakeGame game__201) {
        int t_2645;
        int t_2648;
        int t_2650;
        int t_2656;
        StringBuilder sb__203 = new StringBuilder();
        sb__203.append("\u001b[2J\u001b[H");
        sb__203.append("#");
        int x__204 = 0;
        while (true) {
            t_2645 = game__201.getWidth();
            if (x__204 >= t_2645) {
                break;
            }
            sb__203.append("#");
            x__204 = x__204 + 1;
        }
        sb__203.append("#\r\n");
        int y__205 = 0;
        while (true) {
            t_2648 = game__201.getHeight();
            if (y__205 >= t_2648) {
                break;
            }
            sb__203.append("#");
            int x__206 = 0;
            while (true) {
                t_2650 = game__201.getWidth();
                if (x__206 >= t_2650) {
                    break;
                }
                Point p__207 = new Point(x__206, y__205);
                sb__203.append(SnakeGlobal.cellChar__94(game__201, p__207));
                x__206 = x__206 + 1;
            }
            sb__203.append("#\r\n");
            y__205 = y__205 + 1;
        }
        sb__203.append("#");
        int x__208 = 0;
        while (true) {
            t_2656 = game__201.getWidth();
            if (x__208 >= t_2656) {
                break;
            }
            sb__203.append("#");
            x__208 = x__208 + 1;
        }
        sb__203.append("#\r\n");
        String statusText__209;
        GameStatus t_2659 = game__201.getStatus();
        if (t_2659 instanceof Playing) {
            statusText__209 = "Playing";
        } else if (t_2659 instanceof GameOver) {
            statusText__209 = "GAME OVER";
        } else {
            statusText__209 = "";
        }
        sb__203.append("Score: " + Integer.toString(game__201.getScore()) + "  " + statusText__209 + "\r" + "\n");
        return sb__203.toString();
    }
    static SpawnInfo spawnPosition__95(int width__262, int height__263, int index__264, int seed__265) {
        SpawnInfo return__80;
        Point t_2624;
        Right t_2625;
        Direction t_2631;
        Direction t_2633;
        Direction t_2635;
        Direction t_2637;
        Direction t_2639;
        Point t_2640;
        boolean t_1463;
        int t_1464;
        int t_1466;
        int t_1467;
        int t_1469;
        fn__266: {
            int buf__267 = 5;
            int safeW__268 = width__262 - 10;
            int safeH__269 = height__263 - 10;
            if (safeW__268 < 1) {
                t_1463 = true;
            } else {
                t_1463 = safeH__269 < 1;
            }
            if (t_1463) {
                t_1464 = width__262 / 2;
                t_1466 = t_1464;
                t_1467 = height__263 / 2;
                t_1469 = t_1467;
                t_2624 = new Point(t_1466, t_1469);
                t_2625 = new Right();
                return__80 = new SpawnInfo(t_2624, t_2625);
                break fn__266;
            }
            RandomResult r1__270 = SnakeGlobal.nextRandom(seed__265 * 7 + index__264 * 131 + 37, safeW__268);
            RandomResult r2__271 = SnakeGlobal.nextRandom(r1__270.getNextSeed(), safeH__269);
            int x__272 = 5 + r1__270.getValue();
            int y__273 = 5 + r2__271.getValue();
            RandomResult r3__274 = SnakeGlobal.nextRandom(r2__271.getNextSeed(), 4);
            t_2631 = new Right();
            Direction dir__275 = t_2631;
            if (r3__274.getValue() == 0) {
                t_2633 = new Right();
                dir__275 = t_2633;
            }
            if (r3__274.getValue() == 1) {
                t_2635 = new Left();
                dir__275 = t_2635;
            }
            if (r3__274.getValue() == 2) {
                t_2637 = new Down();
                dir__275 = t_2637;
            }
            if (r3__274.getValue() == 3) {
                t_2639 = new Up();
                dir__275 = t_2639;
            }
            t_2640 = new Point(x__272, y__273);
            return__80 = new SpawnInfo(t_2640, dir__275);
        }
        return return__80;
    }
    static List<Point> collectAllSegments__96(List<PlayerSnake> snakes__276) {
        int t_2611;
        PlayerSnake t_2615;
        int t_2618;
        List<Point> t_2619;
        Point t_2620;
        List<Point> builder__278 = new ArrayList<>();
        int i__279 = 0;
        while (true) {
            t_2611 = snakes__276.size();
            if (i__279 >= t_2611) {
                break;
            }
            t_2615 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__280 = Core.listGetOr(snakes__276, i__279, t_2615);
            int j__281 = 0;
            while (true) {
                t_2618 = snake__280.getSegments().size();
                if (j__281 >= t_2618) {
                    break;
                }
                t_2619 = snake__280.getSegments();
                t_2620 = new Point(0, 0);
                Core.listAdd(builder__278, Core.listGetOr(t_2619, j__281, t_2620));
                j__281 = j__281 + 1;
            }
            i__279 = i__279 + 1;
        }
        return List.copyOf(builder__278);
    }
    public static MultiSnakeGame newMultiGame(int width__241, int height__242, int numPlayers__243, int seed__244) {
        Alive t_2600;
        List<PlayerSnake> snakeBuilder__246 = new ArrayList<>();
        int currentSeed__247 = seed__244;
        int i__248 = 0;
        while (i__248 < numPlayers__243) {
            SpawnInfo spawn__249 = SnakeGlobal.spawnPosition__95(width__241, height__242, i__248, currentSeed__247);
            Direction dir__250 = spawn__249.getDirection();
            int startX__251 = spawn__249.getPoint().getX();
            int startY__252 = spawn__249.getPoint().getY();
            Point delta__253 = SnakeGlobal.directionDelta(dir__250);
            List<Point> segments__254 = List.of(new Point(startX__251, startY__252), new Point(startX__251 - delta__253.getX(), startY__252 - delta__253.getY()), new Point(startX__251 - delta__253.getX() * 2, startY__252 - delta__253.getY() * 2));
            t_2600 = new Alive();
            Core.listAdd(snakeBuilder__246, new PlayerSnake(i__248, segments__254, dir__250, 0, t_2600));
            i__248 = i__248 + 1;
        }
        List<PlayerSnake> t_2603 = List.copyOf(snakeBuilder__246);
        List<Point> allSegments__255 = SnakeGlobal.collectAllSegments__96(t_2603);
        FoodPlacement foodResult__256 = SnakeGlobal.placeFood__93(allSegments__255, width__241, height__242, currentSeed__247);
        List<PlayerSnake> t_2606 = List.copyOf(snakeBuilder__246);
        Point t_2607 = foodResult__256.getPoint();
        int t_2608 = foodResult__256.getSeed();
        return new MultiSnakeGame(width__241, height__242, t_2606, t_2607, t_2608, 0);
    }
    public static MultiSnakeGame multiTick(MultiSnakeGame game__282, List<Direction> directions__283) {
        int t_2429;
        List<PlayerSnake> t_2430;
        PlayerSnake t_2434;
        Direction t_2436;
        int t_2444;
        List<PlayerSnake> t_2445;
        PlayerSnake t_2449;
        List<Direction> t_2453;
        Right t_2454;
        int t_2472;
        List<PlayerSnake> t_2473;
        PlayerSnake t_2477;
        Point t_2482;
        int t_2488;
        int t_2489;
        int t_2491;
        List<Point> t_2492;
        Point t_2493;
        int t_2496;
        List<PlayerSnake> t_2497;
        PlayerSnake t_2501;
        int t_2506;
        List<Point> t_2507;
        Point t_2508;
        int t_2511;
        List<PlayerSnake> t_2512;
        PlayerSnake t_2516;
        Point t_2520;
        int t_2525;
        Point t_2527;
        int t_2532;
        List<PlayerSnake> t_2533;
        PlayerSnake t_2537;
        Point t_2550;
        Direction t_2552;
        int t_2555;
        int t_2557;
        List<Point> t_2560;
        Point t_2561;
        int t_2564;
        int t_2565;
        int t_2575;
        int t_2576;
        int t_2577;
        Point t_2579;
        int t_2580;
        boolean t_1325;
        boolean t_1326;
        boolean t_1327;
        int t_1397;
        int t_1405;
        List<Direction> newDirs__285 = new ArrayList<>();
        int i__286 = 0;
        while (true) {
            t_2429 = game__282.getSnakes().size();
            if (i__286 >= t_2429) {
                break;
            }
            t_2430 = game__282.getSnakes();
            t_2434 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__287 = Core.listGetOr(t_2430, i__286, t_2434);
            t_2436 = snake__287.getDirection();
            Direction inputDir__288 = Core.listGetOr(directions__283, i__286, t_2436);
            if (SnakeGlobal.isOpposite(snake__287.getDirection(), inputDir__288)) {
                Core.listAdd(newDirs__285, snake__287.getDirection());
            } else {
                Core.listAdd(newDirs__285, inputDir__288);
            }
            i__286 = i__286 + 1;
        }
        List<Point> newHeads__289 = new ArrayList<>();
        int i__290 = 0;
        while (true) {
            t_2444 = game__282.getSnakes().size();
            if (i__290 >= t_2444) {
                break;
            }
            t_2445 = game__282.getSnakes();
            t_2449 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__291 = Core.listGetOr(t_2445, i__290, t_2449);
            if (snake__291.getStatus() instanceof Alive) {
                t_2453 = List.copyOf(newDirs__285);
                t_2454 = new Right();
                Direction dir__292 = Core.listGetOr(t_2453, i__290, t_2454);
                Point delta__293 = SnakeGlobal.directionDelta(dir__292);
                Point head__294 = Core.listGetOr(snake__291.getSegments(), 0, new Point(0, 0));
                Core.listAdd(newHeads__289, new Point(head__294.getX() + delta__293.getX(), head__294.getY() + delta__293.getY()));
            } else {
                Core.listAdd(newHeads__289, new Point(-1, -1));
            }
            i__290 = i__290 + 1;
        }
        List<Point> headsList__295 = List.copyOf(newHeads__289);
        List<Direction> dirsList__296 = List.copyOf(newDirs__285);
        List<Boolean> aliveBuilder__297 = new ArrayList<>();
        int i__298 = 0;
        while (true) {
            t_2472 = game__282.getSnakes().size();
            if (i__298 >= t_2472) {
                break;
            }
            t_2473 = game__282.getSnakes();
            t_2477 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__299 = Core.listGetOr(t_2473, i__298, t_2477);
            if (!(snake__299.getStatus() instanceof Alive)) {
                Core.listAdd(aliveBuilder__297, false);
            } else {
                t_2482 = new Point(-1, -1);
                Point nh__300 = Core.listGetOr(headsList__295, i__298, t_2482);
                boolean dead__301 = false;
                if (nh__300.getX() < 0) {
                    t_1327 = true;
                } else {
                    if (nh__300.getX() >= game__282.getWidth()) {
                        t_1326 = true;
                    } else {
                        if (nh__300.getY() < 0) {
                            t_1325 = true;
                        } else {
                            t_2488 = nh__300.getY();
                            t_2489 = game__282.getHeight();
                            t_1325 = t_2488 >= t_2489;
                        }
                        t_1326 = t_1325;
                    }
                    t_1327 = t_1326;
                }
                if (t_1327) {
                    dead__301 = true;
                }
                if (!dead__301) {
                    int s__302 = 0;
                    while (true) {
                        t_2491 = snake__299.getSegments().size();
                        if (s__302 >= t_2491 - 1) {
                            break;
                        }
                        t_2492 = snake__299.getSegments();
                        t_2493 = new Point(-2, -2);
                        if (SnakeGlobal.pointEquals(nh__300, Core.listGetOr(t_2492, s__302, t_2493))) {
                            dead__301 = true;
                        }
                        s__302 = s__302 + 1;
                    }
                }
                if (!dead__301) {
                    int j__303 = 0;
                    while (true) {
                        t_2496 = game__282.getSnakes().size();
                        if (j__303 >= t_2496) {
                            break;
                        }
                        if (j__303 != i__298) {
                            t_2497 = game__282.getSnakes();
                            t_2501 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                            PlayerSnake other__304 = Core.listGetOr(t_2497, j__303, t_2501);
                            if (other__304.getStatus() instanceof Alive) {
                                int s__305 = 0;
                                while (true) {
                                    t_2506 = other__304.getSegments().size();
                                    if (s__305 >= t_2506 - 1) {
                                        break;
                                    }
                                    t_2507 = other__304.getSegments();
                                    t_2508 = new Point(-2, -2);
                                    if (SnakeGlobal.pointEquals(nh__300, Core.listGetOr(t_2507, s__305, t_2508))) {
                                        dead__301 = true;
                                    }
                                    s__305 = s__305 + 1;
                                }
                            }
                        }
                        j__303 = j__303 + 1;
                    }
                }
                if (!dead__301) {
                    int j__306 = 0;
                    while (true) {
                        t_2511 = game__282.getSnakes().size();
                        if (j__306 >= t_2511) {
                            break;
                        }
                        if (j__306 != i__298) {
                            t_2512 = game__282.getSnakes();
                            t_2516 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                            PlayerSnake otherSnake__307 = Core.listGetOr(t_2512, j__306, t_2516);
                            if (otherSnake__307.getStatus() instanceof Alive) {
                                t_2520 = new Point(-3, -3);
                                Point otherHead__308 = Core.listGetOr(headsList__295, j__306, t_2520);
                                if (SnakeGlobal.pointEquals(nh__300, otherHead__308)) {
                                    dead__301 = true;
                                }
                            }
                        }
                        j__306 = j__306 + 1;
                    }
                }
                Core.listAdd(aliveBuilder__297, !dead__301);
            }
            i__298 = i__298 + 1;
        }
        List<Boolean> aliveList__309 = List.copyOf(aliveBuilder__297);
        int eaterIndex__310 = -1;
        int i__311 = 0;
        while (true) {
            t_2525 = game__282.getSnakes().size();
            if (i__311 >= t_2525) {
                break;
            }
            if (Core.listGetOr(aliveList__309, i__311, false)) {
                t_2527 = new Point(-1, -1);
                Point nh__312 = Core.listGetOr(headsList__295, i__311, t_2527);
                if (SnakeGlobal.pointEquals(nh__312, game__282.getFood())) {
                    eaterIndex__310 = i__311;
                }
            }
            i__311 = i__311 + 1;
        }
        List<PlayerSnake> resultSnakes__313 = new ArrayList<>();
        int i__314 = 0;
        while (true) {
            t_2532 = game__282.getSnakes().size();
            if (i__314 >= t_2532) {
                break;
            }
            t_2533 = game__282.getSnakes();
            t_2537 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__315 = Core.listGetOr(t_2533, i__314, t_2537);
            if (!(snake__315.getStatus() instanceof Alive)) {
                Core.listAdd(resultSnakes__313, snake__315);
            } else if (!Core.listGetOr(aliveList__309, i__314, false)) {
                Core.listAdd(resultSnakes__313, new PlayerSnake(snake__315.getId(), snake__315.getSegments(), snake__315.getDirection(), snake__315.getScore(), new Dead()));
            } else {
                t_2550 = new Point(0, 0);
                Point nh__316 = Core.listGetOr(headsList__295, i__314, t_2550);
                t_2552 = snake__315.getDirection();
                Direction dir__317 = Core.listGetOr(dirsList__296, i__314, t_2552);
                boolean isEating__318 = i__314 == eaterIndex__310;
                if (isEating__318) {
                    t_2555 = snake__315.getSegments().size();
                    t_1397 = t_2555;
                } else {
                    t_2557 = snake__315.getSegments().size();
                    t_1397 = t_2557 - 1;
                }
                int keepLen__319 = t_1397;
                List<Point> newSegs__320 = new ArrayList<>();
                Core.listAdd(newSegs__320, nh__316);
                int s__321 = 0;
                while (s__321 < keepLen__319) {
                    t_2560 = snake__315.getSegments();
                    t_2561 = new Point(0, 0);
                    Core.listAdd(newSegs__320, Core.listGetOr(t_2560, s__321, t_2561));
                    s__321 = s__321 + 1;
                }
                if (isEating__318) {
                    t_2564 = snake__315.getScore();
                    t_1405 = t_2564 + 1;
                } else {
                    t_2565 = snake__315.getScore();
                    t_1405 = t_2565;
                }
                int newScore__322 = t_1405;
                Core.listAdd(resultSnakes__313, new PlayerSnake(snake__315.getId(), List.copyOf(newSegs__320), dir__317, newScore__322, new Alive()));
            }
            i__314 = i__314 + 1;
        }
        List<PlayerSnake> resultSnakesList__323 = List.copyOf(resultSnakes__313);
        Point t_2572 = game__282.getFood();
        Point newFood__324 = t_2572;
        int t_2573 = game__282.getRngSeed();
        int newSeed__325 = t_2573;
        if (eaterIndex__310 >= 0) {
            List<Point> allSegs__326 = SnakeGlobal.collectAllSegments__96(resultSnakesList__323);
            t_2575 = game__282.getWidth();
            t_2576 = game__282.getHeight();
            t_2577 = game__282.getRngSeed();
            FoodPlacement foodResult__327 = SnakeGlobal.placeFood__93(allSegs__326, t_2575, t_2576, t_2577);
            t_2579 = foodResult__327.getPoint();
            newFood__324 = t_2579;
            t_2580 = foodResult__327.getSeed();
            newSeed__325 = t_2580;
        }
        int t_2581 = game__282.getWidth();
        int t_2582 = game__282.getHeight();
        int t_2583 = game__282.getTickCount();
        return new MultiSnakeGame(t_2581, t_2582, resultSnakesList__323, newFood__324, newSeed__325, t_2583 + 1);
    }
    public static MultiSnakeGame changePlayerDirection(MultiSnakeGame game__328, int playerId__329, Direction dir__330) {
        int t_2402;
        List<PlayerSnake> t_2403;
        PlayerSnake t_2407;
        Direction t_2412;
        int t_2413;
        List<Point> t_2414;
        int t_2415;
        PlayerStatus t_2416;
        boolean t_1250;
        boolean t_1251;
        List<PlayerSnake> newSnakes__332 = new ArrayList<>();
        int i__333 = 0;
        while (true) {
            t_2402 = game__328.getSnakes().size();
            if (i__333 >= t_2402) {
                break;
            }
            t_2403 = game__328.getSnakes();
            t_2407 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__334 = Core.listGetOr(t_2403, i__333, t_2407);
            if (snake__334.getId() == playerId__329) {
                if (snake__334.getStatus() instanceof Alive) {
                    t_2412 = snake__334.getDirection();
                    t_1250 = !SnakeGlobal.isOpposite(t_2412, dir__330);
                } else {
                    t_1250 = false;
                }
                t_1251 = t_1250;
            } else {
                t_1251 = false;
            }
            if (t_1251) {
                t_2413 = snake__334.getId();
                t_2414 = snake__334.getSegments();
                t_2415 = snake__334.getScore();
                t_2416 = snake__334.getStatus();
                Core.listAdd(newSnakes__332, new PlayerSnake(t_2413, t_2414, dir__330, t_2415, t_2416));
            } else {
                Core.listAdd(newSnakes__332, snake__334);
            }
            i__333 = i__333 + 1;
        }
        return new MultiSnakeGame(game__328.getWidth(), game__328.getHeight(), List.copyOf(newSnakes__332), game__328.getFood(), game__328.getRngSeed(), game__328.getTickCount());
    }
    public static boolean isMultiGameOver(MultiSnakeGame game__335) {
        boolean return__84;
        int t_2387;
        List<PlayerSnake> t_2388;
        PlayerSnake t_2392;
        int aliveCount__337 = 0;
        int i__338 = 0;
        while (true) {
            t_2387 = game__335.getSnakes().size();
            if (i__338 >= t_2387) {
                break;
            }
            t_2388 = game__335.getSnakes();
            t_2392 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__339 = Core.listGetOr(t_2388, i__338, t_2392);
            if (snake__339.getStatus() instanceof Alive) {
                aliveCount__337 = aliveCount__337 + 1;
            }
            i__338 = i__338 + 1;
        }
        if (game__335.getSnakes().size() == 0) {
            return__84 = false;
        } else if (game__335.getSnakes().size() == 1) {
            return__84 = aliveCount__337 == 0;
        } else {
            return__84 = aliveCount__337 <= 1;
        }
        return return__84;
    }
    public static MultiSnakeGame addPlayer(MultiSnakeGame game__340, int seed__341) {
        int t_2365;
        List<PlayerSnake> t_2366;
        PlayerSnake t_2370;
        int newId__343 = game__340.getSnakes().size();
        SpawnInfo spawn__344 = SnakeGlobal.spawnPosition__95(game__340.getWidth(), game__340.getHeight(), newId__343, seed__341);
        Direction dir__345 = spawn__344.getDirection();
        Point delta__346 = SnakeGlobal.directionDelta(dir__345);
        int startX__347 = spawn__344.getPoint().getX();
        int startY__348 = spawn__344.getPoint().getY();
        List<Point> segments__349 = List.of(new Point(startX__347, startY__348), new Point(startX__347 - delta__346.getX(), startY__348 - delta__346.getY()), new Point(startX__347 - delta__346.getX() * 2, startY__348 - delta__346.getY() * 2));
        PlayerSnake newSnake__350 = new PlayerSnake(newId__343, segments__349, dir__345, 0, new Alive());
        List<PlayerSnake> builder__351 = new ArrayList<>();
        int i__352 = 0;
        while (true) {
            t_2365 = game__340.getSnakes().size();
            if (i__352 >= t_2365) {
                break;
            }
            t_2366 = game__340.getSnakes();
            t_2370 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            Core.listAdd(builder__351, Core.listGetOr(t_2366, i__352, t_2370));
            i__352 = i__352 + 1;
        }
        Core.listAdd(builder__351, newSnake__350);
        List<PlayerSnake> t_2374 = List.copyOf(builder__351);
        List<Point> allSegs__353 = SnakeGlobal.collectAllSegments__96(t_2374);
        int t_2376 = game__340.getWidth();
        int t_2377 = game__340.getHeight();
        FoodPlacement foodResult__354 = SnakeGlobal.placeFood__93(allSegs__353, t_2376, t_2377, seed__341);
        return new MultiSnakeGame(game__340.getWidth(), game__340.getHeight(), List.copyOf(builder__351), foodResult__354.getPoint(), foodResult__354.getSeed(), game__340.getTickCount());
    }
    public static MultiSnakeGame removePlayer(MultiSnakeGame game__355, int playerId__356) {
        int t_2327;
        List<PlayerSnake> t_2328;
        PlayerSnake t_2332;
        List<PlayerSnake> builder__358 = new ArrayList<>();
        int i__359 = 0;
        while (true) {
            t_2327 = game__355.getSnakes().size();
            if (i__359 >= t_2327) {
                break;
            }
            t_2328 = game__355.getSnakes();
            t_2332 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__360 = Core.listGetOr(t_2328, i__359, t_2332);
            if (snake__360.getId() != playerId__356) {
                Core.listAdd(builder__358, snake__360);
            }
            i__359 = i__359 + 1;
        }
        return new MultiSnakeGame(game__355.getWidth(), game__355.getHeight(), List.copyOf(builder__358), game__355.getFood(), game__355.getRngSeed(), game__355.getTickCount());
    }
    public static String playerHeadChar(int id__373) {
        String return__88;
        if (id__373 == 0) {
            return__88 = "@";
        } else if (id__373 == 1) {
            return__88 = "#";
        } else if (id__373 == 2) {
            return__88 = "$";
        } else if (id__373 == 3) {
            return__88 = "%";
        } else {
            return__88 = "&";
        }
        return return__88;
    }
    public static String playerBodyChar(int id__375) {
        String return__89;
        if (id__375 == 0) {
            return__89 = "o";
        } else if (id__375 == 1) {
            return__89 = "+";
        } else if (id__375 == 2) {
            return__89 = "~";
        } else if (id__375 == 3) {
            return__89 = "=";
        } else {
            return__89 = ".";
        }
        return return__89;
    }
    static String multiCellChar__97(MultiSnakeGame game__377, Point p__378) {
        String return__90;
        int t_2297;
        List<PlayerSnake> t_2298;
        PlayerSnake t_2302;
        int t_2309;
        int t_2311;
        List<PlayerSnake> t_2312;
        PlayerSnake t_2316;
        int t_2319;
        List<Point> t_2320;
        Point t_2321;
        Point t_2322;
        int t_2323;
        Point t_2324;
        fn__379: {
            int i__380 = 0;
            while (true) {
                t_2297 = game__377.getSnakes().size();
                if (i__380 >= t_2297) {
                    break;
                }
                t_2298 = game__377.getSnakes();
                t_2302 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                PlayerSnake snake__381 = Core.listGetOr(t_2298, i__380, t_2302);
                if (snake__381.getSegments().size() > 0) {
                    Point head__382 = Core.listGetOr(snake__381.getSegments(), 0, new Point(-1, -1));
                    if (SnakeGlobal.pointEquals(p__378, head__382)) {
                        t_2309 = snake__381.getId();
                        return__90 = SnakeGlobal.playerHeadChar(t_2309);
                        break fn__379;
                    }
                }
                i__380 = i__380 + 1;
            }
            int i__383 = 0;
            while (true) {
                t_2311 = game__377.getSnakes().size();
                if (i__383 >= t_2311) {
                    break;
                }
                t_2312 = game__377.getSnakes();
                t_2316 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                PlayerSnake snake__384 = Core.listGetOr(t_2312, i__383, t_2316);
                int j__385 = 1;
                while (true) {
                    t_2319 = snake__384.getSegments().size();
                    if (j__385 >= t_2319) {
                        break;
                    }
                    t_2320 = snake__384.getSegments();
                    t_2321 = new Point(-1, -1);
                    t_2322 = Core.listGetOr(t_2320, j__385, t_2321);
                    if (SnakeGlobal.pointEquals(p__378, t_2322)) {
                        t_2323 = snake__384.getId();
                        return__90 = SnakeGlobal.playerBodyChar(t_2323);
                        break fn__379;
                    }
                    j__385 = j__385 + 1;
                }
                i__383 = i__383 + 1;
            }
            t_2324 = game__377.getFood();
            if (SnakeGlobal.pointEquals(p__378, t_2324)) {
                return__90 = "*";
                break fn__379;
            }
            return__90 = " ";
        }
        return return__90;
    }
    public static String multiRender(MultiSnakeGame game__361) {
        int t_2264;
        int t_2267;
        int t_2269;
        int t_2275;
        int t_2279;
        List<PlayerSnake> t_2280;
        PlayerSnake t_2284;
        PlayerStatus t_2286;
        String t_1121;
        StringBuilder sb__363 = new StringBuilder();
        sb__363.append("\u001b[2J\u001b[H");
        sb__363.append("#");
        int x__364 = 0;
        while (true) {
            t_2264 = game__361.getWidth();
            if (x__364 >= t_2264) {
                break;
            }
            sb__363.append("#");
            x__364 = x__364 + 1;
        }
        sb__363.append("#\r\n");
        int y__365 = 0;
        while (true) {
            t_2267 = game__361.getHeight();
            if (y__365 >= t_2267) {
                break;
            }
            sb__363.append("#");
            int x__366 = 0;
            while (true) {
                t_2269 = game__361.getWidth();
                if (x__366 >= t_2269) {
                    break;
                }
                Point p__367 = new Point(x__366, y__365);
                sb__363.append(SnakeGlobal.multiCellChar__97(game__361, p__367));
                x__366 = x__366 + 1;
            }
            sb__363.append("#\r\n");
            y__365 = y__365 + 1;
        }
        sb__363.append("#");
        int x__368 = 0;
        while (true) {
            t_2275 = game__361.getWidth();
            if (x__368 >= t_2275) {
                break;
            }
            sb__363.append("#");
            x__368 = x__368 + 1;
        }
        sb__363.append("#\r\n");
        int i__369 = 0;
        while (true) {
            t_2279 = game__361.getSnakes().size();
            if (i__369 >= t_2279) {
                break;
            }
            t_2280 = game__361.getSnakes();
            t_2284 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__370 = Core.listGetOr(t_2280, i__369, t_2284);
            t_2286 = snake__370.getStatus();
            if (t_2286 instanceof Alive) {
                t_1121 = "Playing";
            } else if (t_2286 instanceof Dead) {
                t_1121 = "DEAD";
            } else {
                t_1121 = "";
            }
            String statusText__371 = t_1121;
            String symbol__372 = SnakeGlobal.playerHeadChar(snake__370.getId());
            sb__363.append("P" + Integer.toString(snake__370.getId()) + " " + symbol__372 + ": " + Integer.toString(snake__370.getScore()) + "  " + statusText__371 + "\r" + "\n");
            i__369 = i__369 + 1;
        }
        return sb__363.toString();
    }
    public static String directionToString(Direction dir__386) {
        String return__91;
        if (dir__386 instanceof Up) {
            return__91 = "up";
        } else if (dir__386 instanceof Down) {
            return__91 = "down";
        } else if (dir__386 instanceof Left) {
            return__91 = "left";
        } else if (dir__386 instanceof Right) {
            return__91 = "right";
        } else {
            return__91 = "right";
        }
        return return__91;
    }
    public static @Nullable Direction stringToDirection(String s__388) {
        @Nullable Direction return__92;
        fn__389: {
            if (s__388.equals("up")) {
                return__92 = new Up();
                break fn__389;
            }
            if (s__388.equals("down")) {
                return__92 = new Down();
                break fn__389;
            }
            if (s__388.equals("left")) {
                return__92 = new Left();
                break fn__389;
            }
            if (s__388.equals("right")) {
                return__92 = new Right();
                break fn__389;
            }
            return__92 = null;
        }
        return return__92;
    }
}
