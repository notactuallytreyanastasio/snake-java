package snake;
final class SpawnInfo {
    public final Point point;
    public final Direction direction;
    public static final class Builder {
        Point point;
        public Builder point(Point point) {
            this.point = point;
            return this;
        }
        Direction direction;
        public Builder direction(Direction direction) {
            this.direction = direction;
            return this;
        }
        public SpawnInfo build() {
            return new SpawnInfo(point, direction);
        }
    }
    public SpawnInfo(Point point__259, Direction direction__260) {
        this.point = point__259;
        this.direction = direction__260;
    }
    public Point getPoint() {
        return this.point;
    }
    public Direction getDirection() {
        return this.direction;
    }
}
