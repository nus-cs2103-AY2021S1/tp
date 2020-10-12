package tp.cap5buddy.logic.parser;

/**
 * Represents the object to holds the information of the prefix and its index position.
 */
public class PrefixPosition {
    private int prefixIndex;
    private final Prefix prefix;

    /**
     * Creates a new PrefixPosition container.
     * @param startPosition index of the starting character of the prefix.
     * @param prefix the prefix represented.
     */
    public PrefixPosition(int startPosition, Prefix prefix) {
        this.prefixIndex = startPosition;
        this.prefix = prefix;
    }

    /**
     * Returns the index for this Prefix.
     * @return int index.
     */
    public int getPrefixIndex() {
        return this.prefixIndex;
    }

    /**
     * Returns the Prefix tagged to this container.
     * @return Prefix prefix stored.
     */
    public Prefix getPrefix() {
        return this.prefix;
    }
}
