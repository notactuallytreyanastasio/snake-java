package snake;
import java.util.List;
public final class SnakeGame {
    public final int width;
    public final int height;
    public final List<Point> snake;
    public final Direction direction;
    public final Point food;
    public final int score;
    public final GameStatus status;
    public final int rngSeed;
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
        List<Point> snake;
        public Builder snake(List<Point> snake) {
            this.snake = snake;
            return this;
        }
        Direction direction;
        public Builder direction(Direction direction) {
            this.direction = direction;
            return this;
        }
        Point food;
        public Builder food(Point food) {
            this.food = food;
            return this;
        }
        int score;
        boolean score__set;
        public Builder score(int score) {
            score__set = true;
            this.score = score;
            return this;
        }
        GameStatus status;
        public Builder status(GameStatus status) {
            this.status = status;
            return this;
        }
        int rngSeed;
        boolean rngSeed__set;
        public Builder rngSeed(int rngSeed) {
            rngSeed__set = true;
            this.rngSeed = rngSeed;
            return this;
        }
        public SnakeGame build() {
            if (!width__set || !height__set || !score__set || !rngSeed__set || snake == null || direction == null || food == null || status == null) {
                StringBuilder _message = new StringBuilder("Missing required fields:");
                if (!width__set) {
                    _message.append(" width");
                }
                if (!height__set) {
                    _message.append(" height");
                }
                if (!score__set) {
                    _message.append(" score");
                }
                if (!rngSeed__set) {
                    _message.append(" rngSeed");
                }
                if (snake == null) {
                    _message.append(" snake");
                }
                if (direction == null) {
                    _message.append(" direction");
                }
                if (food == null) {
                    _message.append(" food");
                }
                if (status == null) {
                    _message.append(" status");
                }
                throw new IllegalStateException(_message.toString());
            }
            return new SnakeGame(width, height, snake, direction, food, score, status, rngSeed);
        }
    }
    public SnakeGame(int width__90, int height__91, List<Point> snake__92, Direction direction__93, Point food__94, int score__95, GameStatus status__96, int rngSeed__97) {
        this.width = width__90;
        this.height = height__91;
        this.snake = snake__92;
        this.direction = direction__93;
        this.food = food__94;
        this.score = score__95;
        this.status = status__96;
        this.rngSeed = rngSeed__97;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public List<Point> getSnake() {
        return this.snake;
    }
    public Direction getDirection() {
        return this.direction;
    }
    public Point getFood() {
        return this.food;
    }
    public int getScore() {
        return this.score;
    }
    public GameStatus getStatus() {
        return this.status;
    }
    public int getRngSeed() {
        return this.rngSeed;
    }
}
