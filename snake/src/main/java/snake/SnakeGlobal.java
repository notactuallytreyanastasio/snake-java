package snake;
import java.util.List;
import temper.core.Core;
import java.util.ArrayList;
import java.util.function.Consumer;
import temper.core.Nullable;
public final class SnakeGlobal {
    private SnakeGlobal() {
    }
    public static Direction move(Point head__97, List<Point> body__98, Point food__99, int width__100, int height__101) {
        return new Right();
    }
    public static boolean pointEquals(Point a__114, Point b__115) {
        boolean return__49;
        int t_2788;
        int t_2789;
        if (a__114.getX() == b__115.getX()) {
            t_2788 = a__114.getY();
            t_2789 = b__115.getY();
            return__49 = t_2788 == t_2789;
        } else {
            return__49 = false;
        }
        return return__49;
    }
    public static boolean isOpposite(Direction a__117, Direction b__118) {
        boolean return__50;
        if (a__117 instanceof Up) {
            return__50 = b__118 instanceof Down;
        } else if (a__117 instanceof Down) {
            return__50 = b__118 instanceof Up;
        } else if (a__117 instanceof Left) {
            return__50 = b__118 instanceof Right;
        } else if (a__117 instanceof Right) {
            return__50 = b__118 instanceof Left;
        } else {
            return__50 = false;
        }
        return return__50;
    }
    public static Point directionDelta(Direction dir__120) {
        Point return__51;
        if (dir__120 instanceof Up) {
            return__51 = new Point(0, -1);
        } else if (dir__120 instanceof Down) {
            return__51 = new Point(0, 1);
        } else if (dir__120 instanceof Left) {
            return__51 = new Point(-1, 0);
        } else if (dir__120 instanceof Right) {
            return__51 = new Point(1, 0);
        } else {
            return__51 = new Point(0, 0);
        }
        return return__51;
    }
    public static RandomResult nextRandom(int seed__127, int max__128) {
        int t_1684;
        int t_1686;
        int raw__130 = seed__127 * 1664525 + 1013904223;
        int masked__131 = raw__130 & 2147483647;
        int posVal__132;
        if (masked__131 < 0) {
            posVal__132 = -masked__131;
        } else {
            posVal__132 = masked__131;
        }
        int value__133 = 0;
        if (max__128 > 0) {
            try {
                t_1684 = Core.modIntInt(posVal__132, max__128);
                t_1686 = t_1684;
            } catch (RuntimeException ignored$1) {
                t_1686 = 0;
            }
            value__133 = t_1686;
        }
        return new RandomResult(value__133, masked__131);
    }
    static FoodPlacement placeFood__92(List<Point> snake__156, int width__157, int height__158, int seed__159) {
        FoodPlacement return__60;
        int t_2755;
        Point t_2766;
        int t_1649;
        int t_1651;
        int t_1653;
        int t_1655;
        fn__160: {
            int totalCells__161 = width__157 * height__158;
            int currentSeed__162 = seed__159;
            int attempt__163 = 0;
            while (attempt__163 < totalCells__161) {
                RandomResult result__164 = SnakeGlobal.nextRandom(currentSeed__162, totalCells__161);
                t_2755 = result__164.getNextSeed();
                currentSeed__162 = t_2755;
                int px__165 = 0;
                int py__166 = 0;
                if (width__157 > 0) {
                    try {
                        t_1649 = Core.modIntInt(result__164.getValue(), width__157);
                        t_1651 = t_1649;
                    } catch (RuntimeException ignored$2) {
                        t_1651 = 0;
                    }
                    px__165 = t_1651;
                    try {
                        t_1653 = Core.divIntInt(result__164.getValue(), width__157);
                        t_1655 = t_1653;
                    } catch (RuntimeException ignored$3) {
                        t_1655 = 0;
                    }
                    py__166 = t_1655;
                }
                Point candidate__167 = new Point(px__165, py__166);
                class Local_1 {
                    boolean occupied__168 = false;
                }
                final Local_1 local$1 = new Local_1();
                Consumer<Point> fn__2754 = seg__169 -> {
                    if (SnakeGlobal.pointEquals(seg__169, candidate__167)) {
                        local$1.occupied__168 = true;
                    }
                };
                snake__156.forEach(fn__2754);
                if (!local$1.occupied__168) {
                    return__60 = new FoodPlacement(candidate__167, currentSeed__162);
                    break fn__160;
                }
                attempt__163 = attempt__163 + 1;
            }
            int y__170 = 0;
            while (y__170 < height__158) {
                int x__171 = 0;
                while (x__171 < width__157) {
                    Point candidate__172 = new Point(x__171, y__170);
                    class Local_2 {
                        boolean free__173 = true;
                    }
                    final Local_2 local$2 = new Local_2();
                    Consumer<Point> fn__2753 = seg__174 -> {
                        if (SnakeGlobal.pointEquals(seg__174, candidate__172)) {
                            local$2.free__173 = false;
                        }
                    };
                    snake__156.forEach(fn__2753);
                    if (local$2.free__173) {
                        return__60 = new FoodPlacement(candidate__172, currentSeed__162);
                        break fn__160;
                    }
                    x__171 = x__171 + 1;
                }
                y__170 = y__170 + 1;
            }
            t_2766 = new Point(0, 0);
            return__60 = new FoodPlacement(t_2766, currentSeed__162);
        }
        return return__60;
    }
    public static SnakeGame newGame(int width__175, int height__176, int seed__177) {
        int t_1632;
        int t_1634;
        int t_1635;
        int t_1637;
        int centerX__179 = 0;
        int centerY__180 = 0;
        if (width__175 > 0) {
            t_1632 = width__175 / 2;
            t_1634 = t_1632;
            centerX__179 = t_1634;
        }
        if (height__176 > 0) {
            t_1635 = height__176 / 2;
            t_1637 = t_1635;
            centerY__180 = t_1637;
        }
        List<Point> snake__181 = List.of(new Point(centerX__179, centerY__180), new Point(centerX__179 - 1, centerY__180), new Point(centerX__179 - 2, centerY__180));
        FoodPlacement foodResult__182 = SnakeGlobal.placeFood__92(snake__181, width__175, height__176, seed__177);
        Right t_2748 = new Right();
        Point t_2749 = foodResult__182.getPoint();
        Playing t_2750 = new Playing();
        int t_2751 = foodResult__182.getSeed();
        return new SnakeGame(width__175, height__176, snake__181, t_2748, t_2749, 0, t_2750, t_2751);
    }
    public static SnakeGame changeDirection(SnakeGame game__183, Direction dir__184) {
        SnakeGame return__62;
        int t_2736;
        int t_2737;
        List<Point> t_2738;
        Point t_2739;
        int t_2740;
        GameStatus t_2741;
        int t_2742;
        if (SnakeGlobal.isOpposite(game__183.getDirection(), dir__184)) {
            return__62 = game__183;
        } else {
            t_2736 = game__183.getWidth();
            t_2737 = game__183.getHeight();
            t_2738 = game__183.getSnake();
            t_2739 = game__183.getFood();
            t_2740 = game__183.getScore();
            t_2741 = game__183.getStatus();
            t_2742 = game__183.getRngSeed();
            return__62 = new SnakeGame(t_2736, t_2737, t_2738, dir__184, t_2739, t_2740, t_2741, t_2742);
        }
        return return__62;
    }
    public static SnakeGame tick(SnakeGame game__186) {
        SnakeGame return__63;
        int t_2676;
        int t_2677;
        int t_2678;
        int t_2679;
        List<Point> t_2680;
        Direction t_2681;
        Point t_2682;
        int t_2683;
        GameOver t_2684;
        int t_2685;
        int t_2689;
        int t_2691;
        List<Point> t_2692;
        Point t_2693;
        int t_2695;
        int t_2696;
        List<Point> t_2697;
        Direction t_2698;
        Point t_2699;
        int t_2700;
        GameOver t_2701;
        int t_2702;
        int t_2707;
        int t_2709;
        List<Point> t_2710;
        Point t_2711;
        int t_2716;
        int t_2717;
        int t_2718;
        int t_2720;
        int t_2721;
        Direction t_2722;
        Point t_2723;
        Playing t_2724;
        int t_2725;
        int t_2727;
        int t_2728;
        Direction t_2729;
        Point t_2730;
        int t_2731;
        GameStatus t_2732;
        int t_2733;
        boolean t_1559;
        boolean t_1560;
        boolean t_1561;
        fn__187: {
            if (game__186.getStatus() instanceof GameOver) {
                return__63 = game__186;
                break fn__187;
            }
            Point delta__188 = SnakeGlobal.directionDelta(game__186.getDirection());
            Point head__189 = Core.listGetOr(game__186.getSnake(), 0, new Point(0, 0));
            Point newHead__190 = new Point(head__189.getX() + delta__188.getX(), head__189.getY() + delta__188.getY());
            if (newHead__190.getX() < 0) {
                t_1561 = true;
            } else {
                if (newHead__190.getX() >= game__186.getWidth()) {
                    t_1560 = true;
                } else {
                    if (newHead__190.getY() < 0) {
                        t_1559 = true;
                    } else {
                        t_2676 = newHead__190.getY();
                        t_2677 = game__186.getHeight();
                        t_1559 = t_2676 >= t_2677;
                    }
                    t_1560 = t_1559;
                }
                t_1561 = t_1560;
            }
            if (t_1561) {
                t_2678 = game__186.getWidth();
                t_2679 = game__186.getHeight();
                t_2680 = game__186.getSnake();
                t_2681 = game__186.getDirection();
                t_2682 = game__186.getFood();
                t_2683 = game__186.getScore();
                t_2684 = new GameOver();
                t_2685 = game__186.getRngSeed();
                return__63 = new SnakeGame(t_2678, t_2679, t_2680, t_2681, t_2682, t_2683, t_2684, t_2685);
                break fn__187;
            }
            boolean eating__191 = SnakeGlobal.pointEquals(newHead__190, game__186.getFood());
            int checkLength__192;
            if (eating__191) {
                t_2689 = game__186.getSnake().size();
                checkLength__192 = t_2689;
            } else {
                t_2691 = game__186.getSnake().size();
                checkLength__192 = t_2691 - 1;
            }
            int i__193 = 0;
            while (i__193 < checkLength__192) {
                t_2692 = game__186.getSnake();
                t_2693 = new Point(-1, -1);
                if (SnakeGlobal.pointEquals(newHead__190, Core.listGetOr(t_2692, i__193, t_2693))) {
                    t_2695 = game__186.getWidth();
                    t_2696 = game__186.getHeight();
                    t_2697 = game__186.getSnake();
                    t_2698 = game__186.getDirection();
                    t_2699 = game__186.getFood();
                    t_2700 = game__186.getScore();
                    t_2701 = new GameOver();
                    t_2702 = game__186.getRngSeed();
                    return__63 = new SnakeGame(t_2695, t_2696, t_2697, t_2698, t_2699, t_2700, t_2701, t_2702);
                    break fn__187;
                }
                i__193 = i__193 + 1;
            }
            List<Point> newSnakeBuilder__194 = new ArrayList<>();
            Core.listAdd(newSnakeBuilder__194, newHead__190);
            int keepLength__195;
            if (eating__191) {
                t_2707 = game__186.getSnake().size();
                keepLength__195 = t_2707;
            } else {
                t_2709 = game__186.getSnake().size();
                keepLength__195 = t_2709 - 1;
            }
            int i__196 = 0;
            while (i__196 < keepLength__195) {
                t_2710 = game__186.getSnake();
                t_2711 = new Point(0, 0);
                Core.listAdd(newSnakeBuilder__194, Core.listGetOr(t_2710, i__196, t_2711));
                i__196 = i__196 + 1;
            }
            List<Point> newSnake__197 = List.copyOf(newSnakeBuilder__194);
            if (eating__191) {
                int newScore__198 = game__186.getScore() + 1;
                t_2716 = game__186.getWidth();
                t_2717 = game__186.getHeight();
                t_2718 = game__186.getRngSeed();
                FoodPlacement foodResult__199 = SnakeGlobal.placeFood__92(newSnake__197, t_2716, t_2717, t_2718);
                t_2720 = game__186.getWidth();
                t_2721 = game__186.getHeight();
                t_2722 = game__186.getDirection();
                t_2723 = foodResult__199.getPoint();
                t_2724 = new Playing();
                t_2725 = foodResult__199.getSeed();
                return__63 = new SnakeGame(t_2720, t_2721, newSnake__197, t_2722, t_2723, newScore__198, t_2724, t_2725);
            } else {
                t_2727 = game__186.getWidth();
                t_2728 = game__186.getHeight();
                t_2729 = game__186.getDirection();
                t_2730 = game__186.getFood();
                t_2731 = game__186.getScore();
                t_2732 = game__186.getStatus();
                t_2733 = game__186.getRngSeed();
                return__63 = new SnakeGame(t_2727, t_2728, newSnake__197, t_2729, t_2730, t_2731, t_2732, t_2733);
            }
        }
        return return__63;
    }
    static String cellChar__93(SnakeGame game__209, Point p__210) {
        String return__65;
        int t_2655;
        List<Point> t_2656;
        Point t_2657;
        Point t_2658;
        Point t_2659;
        fn__211: {
            Point head__212 = Core.listGetOr(game__209.getSnake(), 0, new Point(-1, -1));
            if (SnakeGlobal.pointEquals(p__210, head__212)) {
                return__65 = "@";
                break fn__211;
            }
            int i__213 = 1;
            while (true) {
                t_2655 = game__209.getSnake().size();
                if (i__213 >= t_2655) {
                    break;
                }
                t_2656 = game__209.getSnake();
                t_2657 = new Point(-1, -1);
                t_2658 = Core.listGetOr(t_2656, i__213, t_2657);
                if (SnakeGlobal.pointEquals(p__210, t_2658)) {
                    return__65 = "o";
                    break fn__211;
                }
                i__213 = i__213 + 1;
            }
            t_2659 = game__209.getFood();
            if (SnakeGlobal.pointEquals(p__210, t_2659)) {
                return__65 = "*";
                break fn__211;
            }
            return__65 = " ";
        }
        return return__65;
    }
    public static String render(SnakeGame game__200) {
        int t_2630;
        int t_2633;
        int t_2635;
        int t_2641;
        StringBuilder sb__202 = new StringBuilder();
        sb__202.append("\u001b[2J\u001b[H");
        sb__202.append("#");
        int x__203 = 0;
        while (true) {
            t_2630 = game__200.getWidth();
            if (x__203 >= t_2630) {
                break;
            }
            sb__202.append("#");
            x__203 = x__203 + 1;
        }
        sb__202.append("#\r\n");
        int y__204 = 0;
        while (true) {
            t_2633 = game__200.getHeight();
            if (y__204 >= t_2633) {
                break;
            }
            sb__202.append("#");
            int x__205 = 0;
            while (true) {
                t_2635 = game__200.getWidth();
                if (x__205 >= t_2635) {
                    break;
                }
                Point p__206 = new Point(x__205, y__204);
                sb__202.append(SnakeGlobal.cellChar__93(game__200, p__206));
                x__205 = x__205 + 1;
            }
            sb__202.append("#\r\n");
            y__204 = y__204 + 1;
        }
        sb__202.append("#");
        int x__207 = 0;
        while (true) {
            t_2641 = game__200.getWidth();
            if (x__207 >= t_2641) {
                break;
            }
            sb__202.append("#");
            x__207 = x__207 + 1;
        }
        sb__202.append("#\r\n");
        String statusText__208;
        GameStatus t_2644 = game__200.getStatus();
        if (t_2644 instanceof Playing) {
            statusText__208 = "Playing";
        } else if (t_2644 instanceof GameOver) {
            statusText__208 = "GAME OVER";
        } else {
            statusText__208 = "";
        }
        sb__202.append("Score: " + Integer.toString(game__200.getScore()) + "  " + statusText__208 + "\r" + "\n");
        return sb__202.toString();
    }
    static SpawnInfo spawnPosition__94(int width__261, int height__262, int index__263, int total__264) {
        SpawnInfo return__79;
        Point t_2615;
        Right t_2616;
        Point t_2618;
        Left t_2619;
        Point t_2621;
        Down t_2622;
        Point t_2624;
        Up t_2625;
        int t_1458;
        int t_1460;
        int t_1461;
        int t_1463;
        int t_1464;
        int t_1466;
        int t_1467;
        int t_1469;
        int t_1470;
        int t_1472;
        fn__265: {
            int cx__266 = 0;
            int cy__267 = 0;
            if (width__261 > 0) {
                t_1458 = width__261 / 2;
                t_1460 = t_1458;
                cx__266 = t_1460;
            }
            if (height__262 > 0) {
                t_1461 = height__262 / 2;
                t_1463 = t_1461;
                cy__267 = t_1463;
            }
            int qx__268 = 0;
            int qy__269 = 0;
            if (width__261 > 0) {
                t_1464 = width__261 / 4;
                t_1466 = t_1464;
                qx__268 = t_1466;
            }
            if (height__262 > 0) {
                t_1467 = height__262 / 4;
                t_1469 = t_1467;
                qy__269 = t_1469;
            }
            int slot__270 = 0;
            if (total__264 > 0) {
                t_1470 = index__263 % 4;
                t_1472 = t_1470;
                slot__270 = t_1472;
            }
            if (slot__270 == 0) {
                t_2615 = new Point(qx__268, cy__267);
                t_2616 = new Right();
                return__79 = new SpawnInfo(t_2615, t_2616);
                break fn__265;
            }
            if (slot__270 == 1) {
                t_2618 = new Point(width__261 - qx__268 - 1, cy__267);
                t_2619 = new Left();
                return__79 = new SpawnInfo(t_2618, t_2619);
                break fn__265;
            }
            if (slot__270 == 2) {
                t_2621 = new Point(cx__266, qy__269);
                t_2622 = new Down();
                return__79 = new SpawnInfo(t_2621, t_2622);
                break fn__265;
            }
            t_2624 = new Point(cx__266, height__262 - qy__269 - 1);
            t_2625 = new Up();
            return__79 = new SpawnInfo(t_2624, t_2625);
        }
        return return__79;
    }
    static List<Point> collectAllSegments__95(List<PlayerSnake> snakes__271) {
        int t_2602;
        PlayerSnake t_2606;
        int t_2609;
        List<Point> t_2610;
        Point t_2611;
        List<Point> builder__273 = new ArrayList<>();
        int i__274 = 0;
        while (true) {
            t_2602 = snakes__271.size();
            if (i__274 >= t_2602) {
                break;
            }
            t_2606 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__275 = Core.listGetOr(snakes__271, i__274, t_2606);
            int j__276 = 0;
            while (true) {
                t_2609 = snake__275.getSegments().size();
                if (j__276 >= t_2609) {
                    break;
                }
                t_2610 = snake__275.getSegments();
                t_2611 = new Point(0, 0);
                Core.listAdd(builder__273, Core.listGetOr(t_2610, j__276, t_2611));
                j__276 = j__276 + 1;
            }
            i__274 = i__274 + 1;
        }
        return List.copyOf(builder__273);
    }
    public static MultiSnakeGame newMultiGame(int width__240, int height__241, int numPlayers__242, int seed__243) {
        Alive t_2591;
        List<PlayerSnake> snakeBuilder__245 = new ArrayList<>();
        int currentSeed__246 = seed__243;
        int i__247 = 0;
        while (i__247 < numPlayers__242) {
            SpawnInfo spawn__248 = SnakeGlobal.spawnPosition__94(width__240, height__241, i__247, numPlayers__242);
            Direction dir__249 = spawn__248.getDirection();
            int startX__250 = spawn__248.getPoint().getX();
            int startY__251 = spawn__248.getPoint().getY();
            Point delta__252 = SnakeGlobal.directionDelta(dir__249);
            List<Point> segments__253 = List.of(new Point(startX__250, startY__251), new Point(startX__250 - delta__252.getX(), startY__251 - delta__252.getY()), new Point(startX__250 - delta__252.getX() * 2, startY__251 - delta__252.getY() * 2));
            t_2591 = new Alive();
            Core.listAdd(snakeBuilder__245, new PlayerSnake(i__247, segments__253, dir__249, 0, t_2591));
            i__247 = i__247 + 1;
        }
        List<PlayerSnake> t_2594 = List.copyOf(snakeBuilder__245);
        List<Point> allSegments__254 = SnakeGlobal.collectAllSegments__95(t_2594);
        FoodPlacement foodResult__255 = SnakeGlobal.placeFood__92(allSegments__254, width__240, height__241, currentSeed__246);
        List<PlayerSnake> t_2597 = List.copyOf(snakeBuilder__245);
        Point t_2598 = foodResult__255.getPoint();
        int t_2599 = foodResult__255.getSeed();
        return new MultiSnakeGame(width__240, height__241, t_2597, t_2598, t_2599, 0);
    }
    public static MultiSnakeGame multiTick(MultiSnakeGame game__277, List<Direction> directions__278) {
        int t_2420;
        List<PlayerSnake> t_2421;
        PlayerSnake t_2425;
        Direction t_2427;
        int t_2435;
        List<PlayerSnake> t_2436;
        PlayerSnake t_2440;
        List<Direction> t_2444;
        Right t_2445;
        int t_2463;
        List<PlayerSnake> t_2464;
        PlayerSnake t_2468;
        Point t_2473;
        int t_2479;
        int t_2480;
        int t_2482;
        List<Point> t_2483;
        Point t_2484;
        int t_2487;
        List<PlayerSnake> t_2488;
        PlayerSnake t_2492;
        int t_2497;
        List<Point> t_2498;
        Point t_2499;
        int t_2502;
        List<PlayerSnake> t_2503;
        PlayerSnake t_2507;
        Point t_2511;
        int t_2516;
        Point t_2518;
        int t_2523;
        List<PlayerSnake> t_2524;
        PlayerSnake t_2528;
        Point t_2541;
        Direction t_2543;
        int t_2546;
        int t_2548;
        List<Point> t_2551;
        Point t_2552;
        int t_2555;
        int t_2556;
        int t_2566;
        int t_2567;
        int t_2568;
        Point t_2570;
        int t_2571;
        boolean t_1320;
        boolean t_1321;
        boolean t_1322;
        int t_1392;
        int t_1400;
        List<Direction> newDirs__280 = new ArrayList<>();
        int i__281 = 0;
        while (true) {
            t_2420 = game__277.getSnakes().size();
            if (i__281 >= t_2420) {
                break;
            }
            t_2421 = game__277.getSnakes();
            t_2425 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__282 = Core.listGetOr(t_2421, i__281, t_2425);
            t_2427 = snake__282.getDirection();
            Direction inputDir__283 = Core.listGetOr(directions__278, i__281, t_2427);
            if (SnakeGlobal.isOpposite(snake__282.getDirection(), inputDir__283)) {
                Core.listAdd(newDirs__280, snake__282.getDirection());
            } else {
                Core.listAdd(newDirs__280, inputDir__283);
            }
            i__281 = i__281 + 1;
        }
        List<Point> newHeads__284 = new ArrayList<>();
        int i__285 = 0;
        while (true) {
            t_2435 = game__277.getSnakes().size();
            if (i__285 >= t_2435) {
                break;
            }
            t_2436 = game__277.getSnakes();
            t_2440 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__286 = Core.listGetOr(t_2436, i__285, t_2440);
            if (snake__286.getStatus() instanceof Alive) {
                t_2444 = List.copyOf(newDirs__280);
                t_2445 = new Right();
                Direction dir__287 = Core.listGetOr(t_2444, i__285, t_2445);
                Point delta__288 = SnakeGlobal.directionDelta(dir__287);
                Point head__289 = Core.listGetOr(snake__286.getSegments(), 0, new Point(0, 0));
                Core.listAdd(newHeads__284, new Point(head__289.getX() + delta__288.getX(), head__289.getY() + delta__288.getY()));
            } else {
                Core.listAdd(newHeads__284, new Point(-1, -1));
            }
            i__285 = i__285 + 1;
        }
        List<Point> headsList__290 = List.copyOf(newHeads__284);
        List<Direction> dirsList__291 = List.copyOf(newDirs__280);
        List<Boolean> aliveBuilder__292 = new ArrayList<>();
        int i__293 = 0;
        while (true) {
            t_2463 = game__277.getSnakes().size();
            if (i__293 >= t_2463) {
                break;
            }
            t_2464 = game__277.getSnakes();
            t_2468 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__294 = Core.listGetOr(t_2464, i__293, t_2468);
            if (!(snake__294.getStatus() instanceof Alive)) {
                Core.listAdd(aliveBuilder__292, false);
            } else {
                t_2473 = new Point(-1, -1);
                Point nh__295 = Core.listGetOr(headsList__290, i__293, t_2473);
                boolean dead__296 = false;
                if (nh__295.getX() < 0) {
                    t_1322 = true;
                } else {
                    if (nh__295.getX() >= game__277.getWidth()) {
                        t_1321 = true;
                    } else {
                        if (nh__295.getY() < 0) {
                            t_1320 = true;
                        } else {
                            t_2479 = nh__295.getY();
                            t_2480 = game__277.getHeight();
                            t_1320 = t_2479 >= t_2480;
                        }
                        t_1321 = t_1320;
                    }
                    t_1322 = t_1321;
                }
                if (t_1322) {
                    dead__296 = true;
                }
                if (!dead__296) {
                    int s__297 = 0;
                    while (true) {
                        t_2482 = snake__294.getSegments().size();
                        if (s__297 >= t_2482 - 1) {
                            break;
                        }
                        t_2483 = snake__294.getSegments();
                        t_2484 = new Point(-2, -2);
                        if (SnakeGlobal.pointEquals(nh__295, Core.listGetOr(t_2483, s__297, t_2484))) {
                            dead__296 = true;
                        }
                        s__297 = s__297 + 1;
                    }
                }
                if (!dead__296) {
                    int j__298 = 0;
                    while (true) {
                        t_2487 = game__277.getSnakes().size();
                        if (j__298 >= t_2487) {
                            break;
                        }
                        if (j__298 != i__293) {
                            t_2488 = game__277.getSnakes();
                            t_2492 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                            PlayerSnake other__299 = Core.listGetOr(t_2488, j__298, t_2492);
                            if (other__299.getStatus() instanceof Alive) {
                                int s__300 = 0;
                                while (true) {
                                    t_2497 = other__299.getSegments().size();
                                    if (s__300 >= t_2497 - 1) {
                                        break;
                                    }
                                    t_2498 = other__299.getSegments();
                                    t_2499 = new Point(-2, -2);
                                    if (SnakeGlobal.pointEquals(nh__295, Core.listGetOr(t_2498, s__300, t_2499))) {
                                        dead__296 = true;
                                    }
                                    s__300 = s__300 + 1;
                                }
                            }
                        }
                        j__298 = j__298 + 1;
                    }
                }
                if (!dead__296) {
                    int j__301 = 0;
                    while (true) {
                        t_2502 = game__277.getSnakes().size();
                        if (j__301 >= t_2502) {
                            break;
                        }
                        if (j__301 != i__293) {
                            t_2503 = game__277.getSnakes();
                            t_2507 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                            PlayerSnake otherSnake__302 = Core.listGetOr(t_2503, j__301, t_2507);
                            if (otherSnake__302.getStatus() instanceof Alive) {
                                t_2511 = new Point(-3, -3);
                                Point otherHead__303 = Core.listGetOr(headsList__290, j__301, t_2511);
                                if (SnakeGlobal.pointEquals(nh__295, otherHead__303)) {
                                    dead__296 = true;
                                }
                            }
                        }
                        j__301 = j__301 + 1;
                    }
                }
                Core.listAdd(aliveBuilder__292, !dead__296);
            }
            i__293 = i__293 + 1;
        }
        List<Boolean> aliveList__304 = List.copyOf(aliveBuilder__292);
        int eaterIndex__305 = -1;
        int i__306 = 0;
        while (true) {
            t_2516 = game__277.getSnakes().size();
            if (i__306 >= t_2516) {
                break;
            }
            if (Core.listGetOr(aliveList__304, i__306, false)) {
                t_2518 = new Point(-1, -1);
                Point nh__307 = Core.listGetOr(headsList__290, i__306, t_2518);
                if (SnakeGlobal.pointEquals(nh__307, game__277.getFood())) {
                    eaterIndex__305 = i__306;
                }
            }
            i__306 = i__306 + 1;
        }
        List<PlayerSnake> resultSnakes__308 = new ArrayList<>();
        int i__309 = 0;
        while (true) {
            t_2523 = game__277.getSnakes().size();
            if (i__309 >= t_2523) {
                break;
            }
            t_2524 = game__277.getSnakes();
            t_2528 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__310 = Core.listGetOr(t_2524, i__309, t_2528);
            if (!(snake__310.getStatus() instanceof Alive)) {
                Core.listAdd(resultSnakes__308, snake__310);
            } else if (!Core.listGetOr(aliveList__304, i__309, false)) {
                Core.listAdd(resultSnakes__308, new PlayerSnake(snake__310.getId(), snake__310.getSegments(), snake__310.getDirection(), snake__310.getScore(), new Dead()));
            } else {
                t_2541 = new Point(0, 0);
                Point nh__311 = Core.listGetOr(headsList__290, i__309, t_2541);
                t_2543 = snake__310.getDirection();
                Direction dir__312 = Core.listGetOr(dirsList__291, i__309, t_2543);
                boolean isEating__313 = i__309 == eaterIndex__305;
                if (isEating__313) {
                    t_2546 = snake__310.getSegments().size();
                    t_1392 = t_2546;
                } else {
                    t_2548 = snake__310.getSegments().size();
                    t_1392 = t_2548 - 1;
                }
                int keepLen__314 = t_1392;
                List<Point> newSegs__315 = new ArrayList<>();
                Core.listAdd(newSegs__315, nh__311);
                int s__316 = 0;
                while (s__316 < keepLen__314) {
                    t_2551 = snake__310.getSegments();
                    t_2552 = new Point(0, 0);
                    Core.listAdd(newSegs__315, Core.listGetOr(t_2551, s__316, t_2552));
                    s__316 = s__316 + 1;
                }
                if (isEating__313) {
                    t_2555 = snake__310.getScore();
                    t_1400 = t_2555 + 1;
                } else {
                    t_2556 = snake__310.getScore();
                    t_1400 = t_2556;
                }
                int newScore__317 = t_1400;
                Core.listAdd(resultSnakes__308, new PlayerSnake(snake__310.getId(), List.copyOf(newSegs__315), dir__312, newScore__317, new Alive()));
            }
            i__309 = i__309 + 1;
        }
        List<PlayerSnake> resultSnakesList__318 = List.copyOf(resultSnakes__308);
        Point t_2563 = game__277.getFood();
        Point newFood__319 = t_2563;
        int t_2564 = game__277.getRngSeed();
        int newSeed__320 = t_2564;
        if (eaterIndex__305 >= 0) {
            List<Point> allSegs__321 = SnakeGlobal.collectAllSegments__95(resultSnakesList__318);
            t_2566 = game__277.getWidth();
            t_2567 = game__277.getHeight();
            t_2568 = game__277.getRngSeed();
            FoodPlacement foodResult__322 = SnakeGlobal.placeFood__92(allSegs__321, t_2566, t_2567, t_2568);
            t_2570 = foodResult__322.getPoint();
            newFood__319 = t_2570;
            t_2571 = foodResult__322.getSeed();
            newSeed__320 = t_2571;
        }
        int t_2572 = game__277.getWidth();
        int t_2573 = game__277.getHeight();
        int t_2574 = game__277.getTickCount();
        return new MultiSnakeGame(t_2572, t_2573, resultSnakesList__318, newFood__319, newSeed__320, t_2574 + 1);
    }
    public static MultiSnakeGame changePlayerDirection(MultiSnakeGame game__323, int playerId__324, Direction dir__325) {
        int t_2393;
        List<PlayerSnake> t_2394;
        PlayerSnake t_2398;
        Direction t_2403;
        int t_2404;
        List<Point> t_2405;
        int t_2406;
        PlayerStatus t_2407;
        boolean t_1245;
        boolean t_1246;
        List<PlayerSnake> newSnakes__327 = new ArrayList<>();
        int i__328 = 0;
        while (true) {
            t_2393 = game__323.getSnakes().size();
            if (i__328 >= t_2393) {
                break;
            }
            t_2394 = game__323.getSnakes();
            t_2398 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__329 = Core.listGetOr(t_2394, i__328, t_2398);
            if (snake__329.getId() == playerId__324) {
                if (snake__329.getStatus() instanceof Alive) {
                    t_2403 = snake__329.getDirection();
                    t_1245 = !SnakeGlobal.isOpposite(t_2403, dir__325);
                } else {
                    t_1245 = false;
                }
                t_1246 = t_1245;
            } else {
                t_1246 = false;
            }
            if (t_1246) {
                t_2404 = snake__329.getId();
                t_2405 = snake__329.getSegments();
                t_2406 = snake__329.getScore();
                t_2407 = snake__329.getStatus();
                Core.listAdd(newSnakes__327, new PlayerSnake(t_2404, t_2405, dir__325, t_2406, t_2407));
            } else {
                Core.listAdd(newSnakes__327, snake__329);
            }
            i__328 = i__328 + 1;
        }
        return new MultiSnakeGame(game__323.getWidth(), game__323.getHeight(), List.copyOf(newSnakes__327), game__323.getFood(), game__323.getRngSeed(), game__323.getTickCount());
    }
    public static boolean isMultiGameOver(MultiSnakeGame game__330) {
        boolean return__83;
        int t_2378;
        List<PlayerSnake> t_2379;
        PlayerSnake t_2383;
        int aliveCount__332 = 0;
        int i__333 = 0;
        while (true) {
            t_2378 = game__330.getSnakes().size();
            if (i__333 >= t_2378) {
                break;
            }
            t_2379 = game__330.getSnakes();
            t_2383 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__334 = Core.listGetOr(t_2379, i__333, t_2383);
            if (snake__334.getStatus() instanceof Alive) {
                aliveCount__332 = aliveCount__332 + 1;
            }
            i__333 = i__333 + 1;
        }
        if (game__330.getSnakes().size() == 0) {
            return__83 = false;
        } else if (game__330.getSnakes().size() == 1) {
            return__83 = aliveCount__332 == 0;
        } else {
            return__83 = aliveCount__332 <= 1;
        }
        return return__83;
    }
    public static MultiSnakeGame addPlayer(MultiSnakeGame game__335, int seed__336) {
        int t_2356;
        List<PlayerSnake> t_2357;
        PlayerSnake t_2361;
        int newId__338 = game__335.getSnakes().size();
        SpawnInfo spawn__339 = SnakeGlobal.spawnPosition__94(game__335.getWidth(), game__335.getHeight(), newId__338, newId__338 + 1);
        Direction dir__340 = spawn__339.getDirection();
        Point delta__341 = SnakeGlobal.directionDelta(dir__340);
        int startX__342 = spawn__339.getPoint().getX();
        int startY__343 = spawn__339.getPoint().getY();
        List<Point> segments__344 = List.of(new Point(startX__342, startY__343), new Point(startX__342 - delta__341.getX(), startY__343 - delta__341.getY()), new Point(startX__342 - delta__341.getX() * 2, startY__343 - delta__341.getY() * 2));
        PlayerSnake newSnake__345 = new PlayerSnake(newId__338, segments__344, dir__340, 0, new Alive());
        List<PlayerSnake> builder__346 = new ArrayList<>();
        int i__347 = 0;
        while (true) {
            t_2356 = game__335.getSnakes().size();
            if (i__347 >= t_2356) {
                break;
            }
            t_2357 = game__335.getSnakes();
            t_2361 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            Core.listAdd(builder__346, Core.listGetOr(t_2357, i__347, t_2361));
            i__347 = i__347 + 1;
        }
        Core.listAdd(builder__346, newSnake__345);
        List<PlayerSnake> t_2365 = List.copyOf(builder__346);
        List<Point> allSegs__348 = SnakeGlobal.collectAllSegments__95(t_2365);
        int t_2367 = game__335.getWidth();
        int t_2368 = game__335.getHeight();
        FoodPlacement foodResult__349 = SnakeGlobal.placeFood__92(allSegs__348, t_2367, t_2368, seed__336);
        return new MultiSnakeGame(game__335.getWidth(), game__335.getHeight(), List.copyOf(builder__346), foodResult__349.getPoint(), foodResult__349.getSeed(), game__335.getTickCount());
    }
    public static MultiSnakeGame removePlayer(MultiSnakeGame game__350, int playerId__351) {
        int t_2318;
        List<PlayerSnake> t_2319;
        PlayerSnake t_2323;
        List<PlayerSnake> builder__353 = new ArrayList<>();
        int i__354 = 0;
        while (true) {
            t_2318 = game__350.getSnakes().size();
            if (i__354 >= t_2318) {
                break;
            }
            t_2319 = game__350.getSnakes();
            t_2323 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__355 = Core.listGetOr(t_2319, i__354, t_2323);
            if (snake__355.getId() != playerId__351) {
                Core.listAdd(builder__353, snake__355);
            }
            i__354 = i__354 + 1;
        }
        return new MultiSnakeGame(game__350.getWidth(), game__350.getHeight(), List.copyOf(builder__353), game__350.getFood(), game__350.getRngSeed(), game__350.getTickCount());
    }
    public static String playerHeadChar(int id__368) {
        String return__87;
        if (id__368 == 0) {
            return__87 = "@";
        } else if (id__368 == 1) {
            return__87 = "#";
        } else if (id__368 == 2) {
            return__87 = "$";
        } else if (id__368 == 3) {
            return__87 = "%";
        } else {
            return__87 = "&";
        }
        return return__87;
    }
    public static String playerBodyChar(int id__370) {
        String return__88;
        if (id__370 == 0) {
            return__88 = "o";
        } else if (id__370 == 1) {
            return__88 = "+";
        } else if (id__370 == 2) {
            return__88 = "~";
        } else if (id__370 == 3) {
            return__88 = "=";
        } else {
            return__88 = ".";
        }
        return return__88;
    }
    static String multiCellChar__96(MultiSnakeGame game__372, Point p__373) {
        String return__89;
        int t_2288;
        List<PlayerSnake> t_2289;
        PlayerSnake t_2293;
        int t_2300;
        int t_2302;
        List<PlayerSnake> t_2303;
        PlayerSnake t_2307;
        int t_2310;
        List<Point> t_2311;
        Point t_2312;
        Point t_2313;
        int t_2314;
        Point t_2315;
        fn__374: {
            int i__375 = 0;
            while (true) {
                t_2288 = game__372.getSnakes().size();
                if (i__375 >= t_2288) {
                    break;
                }
                t_2289 = game__372.getSnakes();
                t_2293 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                PlayerSnake snake__376 = Core.listGetOr(t_2289, i__375, t_2293);
                if (snake__376.getSegments().size() > 0) {
                    Point head__377 = Core.listGetOr(snake__376.getSegments(), 0, new Point(-1, -1));
                    if (SnakeGlobal.pointEquals(p__373, head__377)) {
                        t_2300 = snake__376.getId();
                        return__89 = SnakeGlobal.playerHeadChar(t_2300);
                        break fn__374;
                    }
                }
                i__375 = i__375 + 1;
            }
            int i__378 = 0;
            while (true) {
                t_2302 = game__372.getSnakes().size();
                if (i__378 >= t_2302) {
                    break;
                }
                t_2303 = game__372.getSnakes();
                t_2307 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
                PlayerSnake snake__379 = Core.listGetOr(t_2303, i__378, t_2307);
                int j__380 = 1;
                while (true) {
                    t_2310 = snake__379.getSegments().size();
                    if (j__380 >= t_2310) {
                        break;
                    }
                    t_2311 = snake__379.getSegments();
                    t_2312 = new Point(-1, -1);
                    t_2313 = Core.listGetOr(t_2311, j__380, t_2312);
                    if (SnakeGlobal.pointEquals(p__373, t_2313)) {
                        t_2314 = snake__379.getId();
                        return__89 = SnakeGlobal.playerBodyChar(t_2314);
                        break fn__374;
                    }
                    j__380 = j__380 + 1;
                }
                i__378 = i__378 + 1;
            }
            t_2315 = game__372.getFood();
            if (SnakeGlobal.pointEquals(p__373, t_2315)) {
                return__89 = "*";
                break fn__374;
            }
            return__89 = " ";
        }
        return return__89;
    }
    public static String multiRender(MultiSnakeGame game__356) {
        int t_2255;
        int t_2258;
        int t_2260;
        int t_2266;
        int t_2270;
        List<PlayerSnake> t_2271;
        PlayerSnake t_2275;
        PlayerStatus t_2277;
        String t_1116;
        StringBuilder sb__358 = new StringBuilder();
        sb__358.append("\u001b[2J\u001b[H");
        sb__358.append("#");
        int x__359 = 0;
        while (true) {
            t_2255 = game__356.getWidth();
            if (x__359 >= t_2255) {
                break;
            }
            sb__358.append("#");
            x__359 = x__359 + 1;
        }
        sb__358.append("#\r\n");
        int y__360 = 0;
        while (true) {
            t_2258 = game__356.getHeight();
            if (y__360 >= t_2258) {
                break;
            }
            sb__358.append("#");
            int x__361 = 0;
            while (true) {
                t_2260 = game__356.getWidth();
                if (x__361 >= t_2260) {
                    break;
                }
                Point p__362 = new Point(x__361, y__360);
                sb__358.append(SnakeGlobal.multiCellChar__96(game__356, p__362));
                x__361 = x__361 + 1;
            }
            sb__358.append("#\r\n");
            y__360 = y__360 + 1;
        }
        sb__358.append("#");
        int x__363 = 0;
        while (true) {
            t_2266 = game__356.getWidth();
            if (x__363 >= t_2266) {
                break;
            }
            sb__358.append("#");
            x__363 = x__363 + 1;
        }
        sb__358.append("#\r\n");
        int i__364 = 0;
        while (true) {
            t_2270 = game__356.getSnakes().size();
            if (i__364 >= t_2270) {
                break;
            }
            t_2271 = game__356.getSnakes();
            t_2275 = new PlayerSnake(0, List.of(), new Right(), 0, new Dead());
            PlayerSnake snake__365 = Core.listGetOr(t_2271, i__364, t_2275);
            t_2277 = snake__365.getStatus();
            if (t_2277 instanceof Alive) {
                t_1116 = "Playing";
            } else if (t_2277 instanceof Dead) {
                t_1116 = "DEAD";
            } else {
                t_1116 = "";
            }
            String statusText__366 = t_1116;
            String symbol__367 = SnakeGlobal.playerHeadChar(snake__365.getId());
            sb__358.append("P" + Integer.toString(snake__365.getId()) + " " + symbol__367 + ": " + Integer.toString(snake__365.getScore()) + "  " + statusText__366 + "\r" + "\n");
            i__364 = i__364 + 1;
        }
        return sb__358.toString();
    }
    public static String directionToString(Direction dir__381) {
        String return__90;
        if (dir__381 instanceof Up) {
            return__90 = "up";
        } else if (dir__381 instanceof Down) {
            return__90 = "down";
        } else if (dir__381 instanceof Left) {
            return__90 = "left";
        } else if (dir__381 instanceof Right) {
            return__90 = "right";
        } else {
            return__90 = "right";
        }
        return return__90;
    }
    public static @Nullable Direction stringToDirection(String s__383) {
        @Nullable Direction return__91;
        fn__384: {
            if (s__383.equals("up")) {
                return__91 = new Up();
                break fn__384;
            }
            if (s__383.equals("down")) {
                return__91 = new Down();
                break fn__384;
            }
            if (s__383.equals("left")) {
                return__91 = new Left();
                break fn__384;
            }
            if (s__383.equals("right")) {
                return__91 = new Right();
                break fn__384;
            }
            return__91 = null;
        }
        return return__91;
    }
}
