package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.ItemList;
import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item APPLE = new ItemBuilder()
            .withId(1)
            .withRecipe(new HashSet<>())
            .withName("Apple")
            .withDescription("Recovers 10 hp")
            .withQuantity("9").build();
    public static final Item BANANA = new ItemBuilder()
            .withId(2)
            .withRecipe(new HashSet<>())
            .withName("Banana")
            .withDescription("Used as bait")
            .withQuantity("99").build();
    public static final Item PEAR = new ItemBuilder()
            .withId(3)
            .withRecipe(new HashSet<>())
            .withDescription("No description given")
            .withQuantity("1").build();

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
        return new ArrayList<>(Arrays.asList(APPLE, BANANA, PEAR));
    }
}
