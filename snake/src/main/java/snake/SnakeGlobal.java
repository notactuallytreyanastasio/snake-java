package snake;
import java.util.List;
import temper.core.Core;
import java.util.function.Consumer;
import java.util.ArrayList;
public final class SnakeGlobal {
    private SnakeGlobal() {
    }
    public static Direction move(Point head__44, List<Point> body__45, Point food__46, int width__47, int height__48) {
        return new Right();
    }
    public static boolean pointEquals(Point a__61, Point b__62) {
        boolean return__25;
        int t_944;
        int t_945;
        if (a__61.getX() == b__62.getX()) {
            t_944 = a__61.getY();
            t_945 = b__62.getY();
            return__25 = t_944 == t_945;
        } else {
            return__25 = false;
        }
        return return__25;
    }
    public static boolean isOpposite(Direction a__64, Direction b__65) {
        boolean return__26;
        if (a__64 instanceof Up) {
            return__26 = b__65 instanceof Down;
        } else if (a__64 instanceof Down) {
            return__26 = b__65 instanceof Up;
        } else if (a__64 instanceof Left) {
            return__26 = b__65 instanceof Right;
        } else if (a__64 instanceof Right) {
            return__26 = b__65 instanceof Left;
        } else {
            return__26 = false;
        }
        return return__26;
    }
    public static Point directionDelta(Direction dir__67) {
        Point return__27;
        if (dir__67 instanceof Up) {
            return__27 = new Point(0, -1);
        } else if (dir__67 instanceof Down) {
            return__27 = new Point(0, 1);
        } else if (dir__67 instanceof Left) {
            return__27 = new Point(-1, 0);
        } else if (dir__67 instanceof Right) {
            return__27 = new Point(1, 0);
        } else {
            return__27 = new Point(0, 0);
        }
        return return__27;
    }
    public static RandomResult nextRandom(int seed__74, int max__75) {
        int t_587;
        int t_589;
        int raw__77 = seed__74 * 1664525 + 1013904223;
        int masked__78 = raw__77 & 2147483647;
        int posVal__79;
        if (masked__78 < 0) {
            posVal__79 = -masked__78;
        } else {
            posVal__79 = masked__78;
        }
        int value__80 = 0;
        if (max__75 > 0) {
            try {
                t_587 = Core.modIntInt(posVal__79, max__75);
                t_589 = t_587;
            } catch (RuntimeException ignored$1) {
                t_589 = 0;
            }
            value__80 = t_589;
        }
        return new RandomResult(value__80, masked__78);
    }
    static FoodPlacement placeFood__42(List<Point> snake__103, int width__104, int height__105, int seed__106) {
        FoodPlacement return__36;
        int t_911;
        Point t_922;
        int t_552;
        int t_554;
        int t_556;
        int t_558;
        fn__107: {
            int totalCells__108 = width__104 * height__105;
            int currentSeed__109 = seed__106;
            int attempt__110 = 0;
            while (attempt__110 < totalCells__108) {
                RandomResult result__111 = SnakeGlobal.nextRandom(currentSeed__109, totalCells__108);
                t_911 = result__111.getNextSeed();
                currentSeed__109 = t_911;
                int px__112 = 0;
                int py__113 = 0;
                if (width__104 > 0) {
                    try {
                        t_552 = Core.modIntInt(result__111.getValue(), width__104);
                        t_554 = t_552;
                    } catch (RuntimeException ignored$2) {
                        t_554 = 0;
                    }
                    px__112 = t_554;
                    try {
                        t_556 = Core.divIntInt(result__111.getValue(), width__104);
                        t_558 = t_556;
                    } catch (RuntimeException ignored$3) {
                        t_558 = 0;
                    }
                    py__113 = t_558;
                }
                Point candidate__114 = new Point(px__112, py__113);
                class Local_1 {
                    boolean occupied__115 = false;
                }
                final Local_1 local$1 = new Local_1();
                Consumer<Point> fn__910 = seg__116 -> {
                    if (SnakeGlobal.pointEquals(seg__116, candidate__114)) {
                        local$1.occupied__115 = true;
                    }
                };
                snake__103.forEach(fn__910);
                if (!local$1.occupied__115) {
                    return__36 = new FoodPlacement(candidate__114, currentSeed__109);
                    break fn__107;
                }
                attempt__110 = attempt__110 + 1;
            }
            int y__117 = 0;
            while (y__117 < height__105) {
                int x__118 = 0;
                while (x__118 < width__104) {
                    Point candidate__119 = new Point(x__118, y__117);
                    class Local_2 {
                        boolean free__120 = true;
                    }
                    final Local_2 local$2 = new Local_2();
                    Consumer<Point> fn__909 = seg__121 -> {
                        if (SnakeGlobal.pointEquals(seg__121, candidate__119)) {
                            local$2.free__120 = false;
                        }
                    };
                    snake__103.forEach(fn__909);
                    if (local$2.free__120) {
                        return__36 = new FoodPlacement(candidate__119, currentSeed__109);
                        break fn__107;
                    }
                    x__118 = x__118 + 1;
                }
                y__117 = y__117 + 1;
            }
            t_922 = new Point(0, 0);
            return__36 = new FoodPlacement(t_922, currentSeed__109);
        }
        return return__36;
    }
    public static SnakeGame newGame(int width__122, int height__123, int seed__124) {
        int t_535;
        int t_537;
        int t_538;
        int t_540;
        int centerX__126 = 0;
        int centerY__127 = 0;
        if (width__122 > 0) {
            t_535 = width__122 / 2;
            t_537 = t_535;
            centerX__126 = t_537;
        }
        if (height__123 > 0) {
            t_538 = height__123 / 2;
            t_540 = t_538;
            centerY__127 = t_540;
        }
        List<Point> snake__128 = List.of(new Point(centerX__126, centerY__127), new Point(centerX__126 - 1, centerY__127), new Point(centerX__126 - 2, centerY__127));
        FoodPlacement foodResult__129 = SnakeGlobal.placeFood__42(snake__128, width__122, height__123, seed__124);
        Right t_904 = new Right();
        Point t_905 = foodResult__129.getPoint();
        Playing t_906 = new Playing();
        int t_907 = foodResult__129.getSeed();
        return new SnakeGame(width__122, height__123, snake__128, t_904, t_905, 0, t_906, t_907);
    }
    public static SnakeGame changeDirection(SnakeGame game__130, Direction dir__131) {
        SnakeGame return__38;
        int t_892;
        int t_893;
        List<Point> t_894;
        Point t_895;
        int t_896;
        GameStatus t_897;
        int t_898;
        if (SnakeGlobal.isOpposite(game__130.getDirection(), dir__131)) {
            return__38 = game__130;
        } else {
            t_892 = game__130.getWidth();
            t_893 = game__130.getHeight();
            t_894 = game__130.getSnake();
            t_895 = game__130.getFood();
            t_896 = game__130.getScore();
            t_897 = game__130.getStatus();
            t_898 = game__130.getRngSeed();
            return__38 = new SnakeGame(t_892, t_893, t_894, dir__131, t_895, t_896, t_897, t_898);
        }
        return return__38;
    }
    public static SnakeGame tick(SnakeGame game__133) {
        SnakeGame return__39;
        int t_832;
        int t_833;
        int t_834;
        int t_835;
        List<Point> t_836;
        Direction t_837;
        Point t_838;
        int t_839;
        GameOver t_840;
        int t_841;
        int t_845;
        int t_847;
        List<Point> t_848;
        Point t_849;
        int t_851;
        int t_852;
        List<Point> t_853;
        Direction t_854;
        Point t_855;
        int t_856;
        GameOver t_857;
        int t_858;
        int t_863;
        int t_865;
        List<Point> t_866;
        Point t_867;
        int t_872;
        int t_873;
        int t_874;
        int t_876;
        int t_877;
        Direction t_878;
        Point t_879;
        Playing t_880;
        int t_881;
        int t_883;
        int t_884;
        Direction t_885;
        Point t_886;
        int t_887;
        GameStatus t_888;
        int t_889;
        boolean t_462;
        boolean t_463;
        boolean t_464;
        fn__134: {
            if (game__133.getStatus() instanceof GameOver) {
                return__39 = game__133;
                break fn__134;
            }
            Point delta__135 = SnakeGlobal.directionDelta(game__133.getDirection());
            Point head__136 = Core.listGetOr(game__133.getSnake(), 0, new Point(0, 0));
            Point newHead__137 = new Point(head__136.getX() + delta__135.getX(), head__136.getY() + delta__135.getY());
            if (newHead__137.getX() < 0) {
                t_464 = true;
            } else {
                if (newHead__137.getX() >= game__133.getWidth()) {
                    t_463 = true;
                } else {
                    if (newHead__137.getY() < 0) {
                        t_462 = true;
                    } else {
                        t_832 = newHead__137.getY();
                        t_833 = game__133.getHeight();
                        t_462 = t_832 >= t_833;
                    }
                    t_463 = t_462;
                }
                t_464 = t_463;
            }
            if (t_464) {
                t_834 = game__133.getWidth();
                t_835 = game__133.getHeight();
                t_836 = game__133.getSnake();
                t_837 = game__133.getDirection();
                t_838 = game__133.getFood();
                t_839 = game__133.getScore();
                t_840 = new GameOver();
                t_841 = game__133.getRngSeed();
                return__39 = new SnakeGame(t_834, t_835, t_836, t_837, t_838, t_839, t_840, t_841);
                break fn__134;
            }
            boolean eating__138 = SnakeGlobal.pointEquals(newHead__137, game__133.getFood());
            int checkLength__139;
            if (eating__138) {
                t_845 = game__133.getSnake().size();
                checkLength__139 = t_845;
            } else {
                t_847 = game__133.getSnake().size();
                checkLength__139 = t_847 - 1;
            }
            int i__140 = 0;
            while (i__140 < checkLength__139) {
                t_848 = game__133.getSnake();
                t_849 = new Point(-1, -1);
                if (SnakeGlobal.pointEquals(newHead__137, Core.listGetOr(t_848, i__140, t_849))) {
                    t_851 = game__133.getWidth();
                    t_852 = game__133.getHeight();
                    t_853 = game__133.getSnake();
                    t_854 = game__133.getDirection();
                    t_855 = game__133.getFood();
                    t_856 = game__133.getScore();
                    t_857 = new GameOver();
                    t_858 = game__133.getRngSeed();
                    return__39 = new SnakeGame(t_851, t_852, t_853, t_854, t_855, t_856, t_857, t_858);
                    break fn__134;
                }
                i__140 = i__140 + 1;
            }
            List<Point> newSnakeBuilder__141 = new ArrayList<>();
            Core.listAdd(newSnakeBuilder__141, newHead__137);
            int keepLength__142;
            if (eating__138) {
                t_863 = game__133.getSnake().size();
                keepLength__142 = t_863;
            } else {
                t_865 = game__133.getSnake().size();
                keepLength__142 = t_865 - 1;
            }
            int i__143 = 0;
            while (i__143 < keepLength__142) {
                t_866 = game__133.getSnake();
                t_867 = new Point(0, 0);
                Core.listAdd(newSnakeBuilder__141, Core.listGetOr(t_866, i__143, t_867));
                i__143 = i__143 + 1;
            }
            List<Point> newSnake__144 = List.copyOf(newSnakeBuilder__141);
            if (eating__138) {
                int newScore__145 = game__133.getScore() + 1;
                t_872 = game__133.getWidth();
                t_873 = game__133.getHeight();
                t_874 = game__133.getRngSeed();
                FoodPlacement foodResult__146 = SnakeGlobal.placeFood__42(newSnake__144, t_872, t_873, t_874);
                t_876 = game__133.getWidth();
                t_877 = game__133.getHeight();
                t_878 = game__133.getDirection();
                t_879 = foodResult__146.getPoint();
                t_880 = new Playing();
                t_881 = foodResult__146.getSeed();
                return__39 = new SnakeGame(t_876, t_877, newSnake__144, t_878, t_879, newScore__145, t_880, t_881);
            } else {
                t_883 = game__133.getWidth();
                t_884 = game__133.getHeight();
                t_885 = game__133.getDirection();
                t_886 = game__133.getFood();
                t_887 = game__133.getScore();
                t_888 = game__133.getStatus();
                t_889 = game__133.getRngSeed();
                return__39 = new SnakeGame(t_883, t_884, newSnake__144, t_885, t_886, t_887, t_888, t_889);
            }
        }
        return return__39;
    }
    static String cellChar__43(SnakeGame game__156, Point p__157) {
        String return__41;
        int t_811;
        List<Point> t_812;
        Point t_813;
        Point t_814;
        Point t_815;
        fn__158: {
            Point head__159 = Core.listGetOr(game__156.getSnake(), 0, new Point(-1, -1));
            if (SnakeGlobal.pointEquals(p__157, head__159)) {
                return__41 = "@";
                break fn__158;
            }
            int i__160 = 1;
            while (true) {
                t_811 = game__156.getSnake().size();
                if (i__160 >= t_811) {
                    break;
                }
                t_812 = game__156.getSnake();
                t_813 = new Point(-1, -1);
                t_814 = Core.listGetOr(t_812, i__160, t_813);
                if (SnakeGlobal.pointEquals(p__157, t_814)) {
                    return__41 = "o";
                    break fn__158;
                }
                i__160 = i__160 + 1;
            }
            t_815 = game__156.getFood();
            if (SnakeGlobal.pointEquals(p__157, t_815)) {
                return__41 = "*";
                break fn__158;
            }
            return__41 = " ";
        }
        return return__41;
    }
    public static String render(SnakeGame game__147) {
        int t_786;
        int t_789;
        int t_791;
        int t_797;
        StringBuilder sb__149 = new StringBuilder();
        sb__149.append("\u001b[2J\u001b[H");
        sb__149.append("#");
        int x__150 = 0;
        while (true) {
            t_786 = game__147.getWidth();
            if (x__150 >= t_786) {
                break;
            }
            sb__149.append("#");
            x__150 = x__150 + 1;
        }
        sb__149.append("#\r\n");
        int y__151 = 0;
        while (true) {
            t_789 = game__147.getHeight();
            if (y__151 >= t_789) {
                break;
            }
            sb__149.append("#");
            int x__152 = 0;
            while (true) {
                t_791 = game__147.getWidth();
                if (x__152 >= t_791) {
                    break;
                }
                Point p__153 = new Point(x__152, y__151);
                sb__149.append(SnakeGlobal.cellChar__43(game__147, p__153));
                x__152 = x__152 + 1;
            }
            sb__149.append("#\r\n");
            y__151 = y__151 + 1;
        }
        sb__149.append("#");
        int x__154 = 0;
        while (true) {
            t_797 = game__147.getWidth();
            if (x__154 >= t_797) {
                break;
            }
            sb__149.append("#");
            x__154 = x__154 + 1;
        }
        sb__149.append("#\r\n");
        String statusText__155;
        GameStatus t_800 = game__147.getStatus();
        if (t_800 instanceof Playing) {
            statusText__155 = "Playing";
        } else if (t_800 instanceof GameOver) {
            statusText__155 = "GAME OVER";
        } else {
            statusText__155 = "";
        }
        sb__149.append("Score: " + Integer.toString(game__147.getScore()) + "  " + statusText__155 + "\r" + "\n");
        return sb__149.toString();
    }
}
