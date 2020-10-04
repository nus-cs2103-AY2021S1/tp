package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;

import seedu.address.model.item.ItemPrecursor;

public class TypicalItemPrecursors {

    public static final ItemPrecursor APPLE_PRECURSOR = new ItemPrecursorBuilder().withName("Apple")
            .withDescription("Recovers 10 hp")
            .withQuantity("9").build();
    public static final ItemPrecursor BANANA_PRECURSOR = new ItemPrecursorBuilder().withName("Banana")
            .withDescription("Used as bait")
            .withQuantity("99").build();
    public static final ItemPrecursor LOCATED_BANANA_PRECURSOR = new ItemPrecursorBuilder().withName("Banana")
            .withDescription("Used as bait")
            .withQuantity("99")
            .withLocations(new HashSet<>(Arrays.asList("Bob's peach orchard"))).build();

    private TypicalItemPrecursors() {} // prevents instantiation
}
