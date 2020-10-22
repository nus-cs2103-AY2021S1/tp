package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_LOCATION_PEACH_ORCHARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.Collections;
import java.util.HashSet;

import seedu.address.logic.parser.ItemParserUtil;
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
            .withLocations(new HashSet<>(Collections.singletonList(VALID_ITEM_LOCATION_PEACH_ORCHARD))).build();
    public static final ItemPrecursor DEFAULT_DESCRIPTION_PRECURSOR = new ItemPrecursorBuilder()
            .withName(VALID_ITEM_NAME_BANANA)
            .withDescription(ItemParserUtil.DEFAULT_DESCRIPTION)
            .withQuantity(VALID_ITEM_QUANTITY_BANANA)
            .withLocations(new HashSet<>(Collections.singletonList(VALID_ITEM_LOCATION_PEACH_ORCHARD)))
            .build();
    public static final ItemPrecursor DEFAULT_QUANTITY_PRECURSOR = new ItemPrecursorBuilder()
            .withName(VALID_ITEM_NAME_BANANA)
            .withDescription(VALID_ITEM_DESCRIPTION_BANANA)
            .withQuantity(ItemParserUtil.DEFAULT_QUANTITY)
            .withLocations(new HashSet<>(Collections.singletonList(VALID_ITEM_LOCATION_PEACH_ORCHARD)))
            .build();
    public static final ItemPrecursor DEFAULT_TAGS_PRECURSOR = new ItemPrecursorBuilder()
            .withName(VALID_ITEM_NAME_BANANA)
            .withDescription(VALID_ITEM_DESCRIPTION_BANANA)
            .withQuantity(ItemParserUtil.DEFAULT_QUANTITY)
            .withLocations(new HashSet<>(Collections.singletonList(VALID_ITEM_LOCATION_PEACH_ORCHARD)))
            .withTags(getTypicalTagSet())
            .build();

    private TypicalItemPrecursors() {} // prevents instantiation
}
