package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMenuItems.CHEESE_PRATA;
import static seedu.address.testutil.TypicalMenuItems.PRATA;
import static seedu.address.testutil.TypicalMenuItems.getTypicalMenuItems;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.MenuItemBuilder;




public class MenuItemTest {

    @Test
    public void equals() {
        getTypicalMenuItems();
        // same values -> returns true
        MenuItem prataCopy = new MenuItemBuilder(PRATA).build();
        assertTrue(PRATA.equals(prataCopy));

        // same object -> returns true
        assertTrue(PRATA.equals(PRATA));

        // null -> returns false
        assertFalse(PRATA.equals(null));

        // different type -> returns false
        assertFalse(PRATA.equals(5));

        // different food -> returns false
        assertFalse(PRATA.equals(CHEESE_PRATA));
        // different name -> returns false
        MenuItem editedPrata = new MenuItemBuilder(PRATA).withName("Cheese Prata").build();
        assertFalse(PRATA.equals(editedPrata));

        // different price -> returns false
        editedPrata = new MenuItemBuilder(PRATA).withPrice(2.3).build();
        assertFalse(PRATA.equals(editedPrata));

        // different tags -> returns false
        editedPrata = new MenuItemBuilder(PRATA).withTags("false").build();
        assertFalse(PRATA.equals(editedPrata));
    }

    @Test
    public void getPriceString() {
        assertEquals(PRATA.getPriceString(), "$1.00");
        assertNotEquals(PRATA.getPriceString(), "$1.20");
    }

    @Test
    public void invalidPriceTest() {
        assertFalse(Food.isValidPrice(-2));
        // one equivalence value is 0
        assertFalse(Food.isValidPrice(0));

        // Test prices less than $1.00
        assertTrue(Food.isValidPrice(.40));

        assertTrue(Food.isValidPrice(2));
        assertTrue(Food.isValidPrice(2.54));
        assertTrue(Food.isValidPrice(1.2));

        // Price should have only 2 decimal points
        assertFalse(Food.isValidPrice(2.555));
    }

    @Test
    public void setTags_validTags_success() {
        MenuItem prataCopy = new MenuItemBuilder().withName("Prata").withPrice(1).build();
        MenuItem expectedPrata = new MenuItemBuilder().withName("Prata").withPrice(1)
                .withTags("small", "medium", "large").build();
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("small"));
        tags.add(new Tag("medium"));
        tags.add(new Tag("large"));
        prataCopy.setTags(tags);
        assertEquals(prataCopy, expectedPrata);
    }
}
