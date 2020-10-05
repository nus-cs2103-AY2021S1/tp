package tp.cap5buddy.logic.parser;

public class PrefixPosition {
    private int prefixIndex;
    private final Prefix prefix;

    public PrefixPosition(int startPosition, Prefix prefix) {
        this.prefixIndex = startPosition;
        this.prefix = prefix;
    }

    public int getPrefixIndex() {
        return this.prefixIndex;
    }

    public Prefix getPrefix() {
        return this.prefix;
    }
}
