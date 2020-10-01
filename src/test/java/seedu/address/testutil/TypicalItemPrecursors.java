package seedu.address.testutil;

import seedu.address.model.item.ItemPrecursor;

public class TypicalItemPrecursors {

    public static final ItemPrecursor APPLE_PRECURSOR = new ItemPrecursorBuilder().withName("Apple")
            .withDescription("Recovers 10 hp")
            .withQuantity("9").build();
    public static final ItemPrecursor BANANA_PRECURSOR = new ItemPrecursorBuilder().withName("Banana")
            .withDescription("Used as bait")
            .withQuantity("99").build();

    private TypicalItemPrecursors() {} // prevents instantiation
}
