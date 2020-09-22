package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ItemList;
import seedu.address.model.item.Item;



/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item APPLE = new ItemBuilder().withName("Apple")
            .withDescription("Recovers 10 hp")
            .withQuantity("9").build();
    public static final Item BANANA = new ItemBuilder().withName("Banana")
            .withDescription("Used as bait")
            .withQuantity("99").build();

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
        return new ArrayList<>(Arrays.asList(APPLE, BANANA));
    }
}
