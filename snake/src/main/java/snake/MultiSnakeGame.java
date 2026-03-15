package snake;
import java.util.List;
public final class MultiSnakeGame {
    public final int width;
    public final int height;
    public final List<PlayerSnake> snakes;
    public final Point food;
    public final int rngSeed;
    public final int tickCount;
    public static final class Builder {
        int width;
        boolean width__set;
        public Builder width(int width) {
            width__set = true;
            this.width = width;
            return this;
        }
        int height;
        boolean height__set;
        public Builder height(int height) {
            height__set = true;
            this.height = height;
            return this;
        }
        List<PlayerSnake> snakes;
        public Builder snakes(List<PlayerSnake> snakes) {
            this.snakes = snakes;
            return this;
        }
        Point food;
        public Builder food(Point food) {
            this.food = food;
            return this;
        }
        int rngSeed;
        boolean rngSeed__set;
        public Builder rngSeed(int rngSeed) {
            rngSeed__set = true;
            this.rngSeed = rngSeed;
            return this;
        }
        int tickCount;
        boolean tickCount__set;
        public Builder tickCount(int tickCount) {
            tickCount__set = true;
            this.tickCount = tickCount;
            return this;
        }
        public MultiSnakeGame build() {
            if (!width__set || !height__set || !rngSeed__set || !tickCount__set || snakes == null || food == null) {
                StringBuilder _message = new StringBuilder("Missing required fields:");
                if (!width__set) {
                    _message.append(" width");
                }
                if (!height__set) {
                    _message.append(" height");
                }
                if (!rngSeed__set) {
                    _message.append(" rngSeed");
                }
                if (!tickCount__set) {
                    _message.append(" tickCount");
                }
                if (snakes == null) {
                    _message.append(" snakes");
                }
                if (food == null) {
                    _message.append(" food");
                }
                throw new IllegalStateException(_message.toString());
            }
            return new MultiSnakeGame(width, height, snakes, food, rngSeed, tickCount);
        }
    }
    public MultiSnakeGame(int width__234, int height__235, List<PlayerSnake> snakes__236, Point food__237, int rngSeed__238, int tickCount__239) {
        this.width = width__234;
        this.height = height__235;
        this.snakes = snakes__236;
        this.food = food__237;
        this.rngSeed = rngSeed__238;
        this.tickCount = tickCount__239;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public List<PlayerSnake> getSnakes() {
        return this.snakes;
    }
    public Point getFood() {
        return this.food;
    }
    public int getRngSeed() {
        return this.rngSeed;
    }
    public int getTickCount() {
        return this.tickCount;
    }
}
