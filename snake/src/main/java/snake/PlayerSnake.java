package snake;
import java.util.List;
public final class PlayerSnake {
    public final int id;
    public final List<Point> segments;
    public final Direction direction;
    public final int score;
    public final PlayerStatus status;
    public static final class Builder {
        int id;
        boolean id__set;
        public Builder id(int id) {
            id__set = true;
            this.id = id;
            return this;
        }
        List<Point> segments;
        public Builder segments(List<Point> segments) {
            this.segments = segments;
            return this;
        }
        Direction direction;
        public Builder direction(Direction direction) {
            this.direction = direction;
            return this;
        }
        int score;
        boolean score__set;
        public Builder score(int score) {
            score__set = true;
            this.score = score;
            return this;
        }
        PlayerStatus status;
        public Builder status(PlayerStatus status) {
            this.status = status;
            return this;
        }
        public PlayerSnake build() {
            if (!id__set || !score__set || segments == null || direction == null || status == null) {
                StringBuilder _message = new StringBuilder("Missing required fields:");
                if (!id__set) {
                    _message.append(" id");
                }
                if (!score__set) {
                    _message.append(" score");
                }
                if (segments == null) {
                    _message.append(" segments");
                }
                if (direction == null) {
                    _message.append(" direction");
                }
                if (status == null) {
                    _message.append(" status");
                }
                throw new IllegalStateException(_message.toString());
            }
            return new PlayerSnake(id, segments, direction, score, status);
        }
    }
    public PlayerSnake(int id__223, List<Point> segments__224, Direction direction__225, int score__226, PlayerStatus status__227) {
        this.id = id__223;
        this.segments = segments__224;
        this.direction = direction__225;
        this.score = score__226;
        this.status = status__227;
    }
    public int getId() {
        return this.id;
    }
    public List<Point> getSegments() {
        return this.segments;
    }
    public Direction getDirection() {
        return this.direction;
    }
    public int getScore() {
        return this.score;
    }
    public PlayerStatus getStatus() {
        return this.status;
    }
}
