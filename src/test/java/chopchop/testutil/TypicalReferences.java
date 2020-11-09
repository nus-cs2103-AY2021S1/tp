// TypicalReferences.java

package chopchop.testutil;

import chopchop.logic.parser.ItemReference;

/**
 * A utility class containing a list of {@code ItemReference} objects to be used in tests.
 */
public class TypicalReferences {
    public static final ItemReference INDEXED_FIRST         = ItemReference.ofOneIndex(1);
    public static final ItemReference INDEXED_SECOND        = ItemReference.ofOneIndex(2);
    public static final ItemReference INDEXED_THIRD         = ItemReference.ofOneIndex(3);

    public static final ItemReference NAMED_BANANA          = ItemReference.ofName("banana");
    public static final ItemReference NAMED_APRICOT         = ItemReference.ofName("apricot");

    public static final ItemReference NAMED_BANANA_SALAD    = ItemReference.ofName("Banana Salad");
    public static final ItemReference NAMED_APRICOT_SALAD   = ItemReference.ofName("Apricot Salad");
}
