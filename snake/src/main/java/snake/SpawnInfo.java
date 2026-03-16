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
    public SpawnInfo(Point point__260, Direction direction__261) {
        this.point = point__260;
        this.direction = direction__261;
    }
    public Point getPoint() {
        return this.point;
    }
    public Direction getDirection() {
        return this.direction;
    }
}
