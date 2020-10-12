// ItemReference.java

package chopchop.parser;

import java.util.Optional;

import chopchop.util.Either;

public class ItemReference {

    private final Either<Integer, String> reference;

    private ItemReference(Either<Integer, String> ref) {
        this.reference = ref;
    }

    /**
     * Returns the zero-based index of the itemreference, if it was an indexed reference.
     */
    public Optional<Integer> getZeroIndex() {
        return this.reference.fromLeftOpt();
    }

    /**
     * Returns the lowercased name of the itemreference, if it was a named reference.
     */
    public Optional<String> getName() {
        return this.reference.fromRightOpt();
    }

    /**
     * Creates an {@code ItemReference} using the given zero-based index.
     *
     * @param idx the zero-based index
     * @return    an ItemReference
     */
    public static ItemReference ofZeroIndex(int idx) {
        return new ItemReference(Either.left(idx));
    }

    /**
     * Creates an {@code ItemReference} using the given one-based index.
     *
     * @param idx the one-based index
     * @return    an ItemReference
     */
    public static ItemReference ofOneIndex(int idx) {
        assert idx > 0;
        return new ItemReference(Either.left(idx - 1));
    }

    /**
     * Creates an {@code ItemReference} using the given name. Note that the name
     * is case-insensitive.
     *
     * @param name the name
     * @return     an ItemReference
     */
    public static ItemReference ofName(String name) {
        return new ItemReference(Either.right(name.toLowerCase()));
    }
}
