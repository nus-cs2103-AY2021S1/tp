package seedu.address.testutil;

import static seedu.address.logic.parser.ItemParserUtil.DEFAULT_DESCRIPTION;
import static seedu.address.logic.parser.ItemParserUtil.DEFAULT_QUANTITY;
import static seedu.address.testutil.TypicalTags.getSingleTagSet;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.ItemList;
import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item APPLE = new ItemBuilder()
            .withId(1)
            .withRecipe(Set.of(3))
            .withName("Apple")
            .withDescription("Recovers 10 hp")
            .withQuantity("9")
            .withTags(getTypicalTagSet())
            .build();
    public static final Item BANANA = new ItemBuilder()
            .withId(2)
            .withRecipe(new HashSet<>())
            .withName("Banana")
            .withDescription("Used as bait")
            .withQuantity("99").build();
    public static final Item PEAR = new ItemBuilder()
            .withName("Pear")
            .withId(3)
            .withRecipe(new HashSet<>())
            .withDescription(DEFAULT_DESCRIPTION)
            .withQuantity(DEFAULT_QUANTITY)
            .withTags(getSingleTagSet())
            .build();
    public static final Item APPLE_PIE_ITEM = new ItemBuilder()
            .withName("Apple pie")
            .withId(4)
            .withRecipe(Set.of(1))
            .build();

    private TypicalItems() {} // prevents instantiation

    /**
     * Returns an {@code ItemList} with all the typical items.
     */
    public static ItemList getTypicalItemList() {
        ItemList ab = new ItemList();
        for (Item item : getTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(APPLE, BANANA, PEAR, APPLE_PIE_ITEM));
    }
}
