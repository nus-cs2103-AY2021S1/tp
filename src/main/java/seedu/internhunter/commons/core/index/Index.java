package seedu.internhunter.commons.core.index;

/**
 * Represents a zero-based or one-based index.
 *
 * {@code Index} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code Index} to avoid having to know what
 * base the other component is using for its index. However, after receiving the {@code Index}, that component can
 * convert it back to an int if the index will not be passed to a different component again.
 */
public class Index {

    private final int zeroBasedIndex;

    /**
     * Creates a Index based on {@code zeroBasedIndex}. Index can only be created by calling
     * {@link Index#fromZeroBased(int)} or {@link Index#fromOneBased(int)}.
     *
     * @param zeroBasedIndex Integer based on the zero-based index.
     */
    private Index(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        this.zeroBasedIndex = zeroBasedIndex;
    }

    public int getZeroBased() {
        return zeroBasedIndex;
    }

    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /**
     * Creates a new {@code Index} using a zero-based index.
     *
     * @param zeroBasedIndex Integer based on the zero-based index.
     * @return A new {@code Index} using a zero-based index.
     */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Index} using a one-based index.
     *
     * @param oneBasedIndex Integer based on the one-based index.
     * @return A new {@code Index} using a one-based index.
     */
    public static Index fromOneBased(int oneBasedIndex) {
        return new Index(oneBasedIndex - 1);
    }

    /**
     * Returns an Index that is one lesser than {@code this}.
     *
     * @return An Index that has a its {@code zeroBasedIndex} minus 1.
     */
    public Index minusOne() {
        if (zeroBasedIndex - 1 < 0) {
            return new Index(0);
        }
        return new Index(zeroBasedIndex - 1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Index // instanceof handles nulls
                && zeroBasedIndex == ((Index) other).zeroBasedIndex); // state check
    }

    /**
     * Represents an Index in using the one-based indexing.
     *
     * @return String representation of one-based index.
     */
    @Override
    public String toString() {
        return String.valueOf(getOneBased());
    }

}
