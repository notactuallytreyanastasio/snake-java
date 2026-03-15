package snake;
public final class RandomResult {
    public final int value;
    public final int nextSeed;
    public static final class Builder {
        int value;
        boolean value__set;
        public Builder value(int value) {
            value__set = true;
            this.value = value;
            return this;
        }
        int nextSeed;
        boolean nextSeed__set;
        public Builder nextSeed(int nextSeed) {
            nextSeed__set = true;
            this.nextSeed = nextSeed;
            return this;
        }
        public RandomResult build() {
            if (!value__set || !nextSeed__set) {
                StringBuilder _message = new StringBuilder("Missing required fields:");
                if (!value__set) {
                    _message.append(" value");
                }
                if (!nextSeed__set) {
                    _message.append(" nextSeed");
                }
                throw new IllegalStateException(_message.toString());
            }
            return new RandomResult(value, nextSeed);
        }
    }
    public RandomResult(int value__125, int nextSeed__126) {
        this.value = value__125;
        this.nextSeed = nextSeed__126;
    }
    public int getValue() {
        return this.value;
    }
    public int getNextSeed() {
        return this.nextSeed;
    }
}
