package snake;
final class FoodPlacement {
    public final Point point;
    public final int seed;
    public static final class Builder {
        Point point;
        public Builder point(Point point) {
            this.point = point;
            return this;
        }
        int seed;
        boolean seed__set;
        public Builder seed(int seed) {
            seed__set = true;
            this.seed = seed;
            return this;
        }
        public FoodPlacement build() {
            if (!seed__set || point == null) {
                StringBuilder _message = new StringBuilder("Missing required fields:");
                if (!seed__set) {
                    _message.append(" seed");
                }
                if (point == null) {
                    _message.append(" point");
                }
                throw new IllegalStateException(_message.toString());
            }
            return new FoodPlacement(point, seed);
        }
    }
    public FoodPlacement(Point point__155, int seed__156) {
        this.point = point__155;
        this.seed = seed__156;
    }
    public Point getPoint() {
        return this.point;
    }
    public int getSeed() {
        return this.seed;
    }
}
