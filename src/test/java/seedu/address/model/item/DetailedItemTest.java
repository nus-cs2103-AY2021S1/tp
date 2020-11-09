package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;
import seedu.address.ui.DisplayedInventoryType;

public class DetailedItemTest {

    /** Tests successful conversion of Item to Detailed Item. */
    @Test
    public void convertItemToDetailed() {
        DetailedItem detailedApple = APPLE.detailedItem();

        assertNotNull(detailedApple);
        assertEquals(detailedApple.getType(), DisplayedInventoryType.DETAILED_ITEM);
    }

    /** Tests strict equality. */
    @Test
    public void equalToItem() {
        Item apple = new ItemBuilder(APPLE).build();
        DetailedItem detailedApple = apple.detailedItem();

        // same object -> true
        assertEquals(detailedApple, detailedApple);

        // same values -> true
        assertEquals(detailedApple, apple.detailedItem());

        // base item -> true
        assertEquals(detailedApple, apple);

        // null -> false
        assertNotEquals(detailedApple, null);

        // different name -> false
        DetailedItem detailedAppleDiffName = new ItemBuilder(APPLE)
                .withName(VALID_ITEM_NAME_BANANA).build().detailedItem();
        assertNotEquals(detailedApple, detailedAppleDiffName);
    }
}
