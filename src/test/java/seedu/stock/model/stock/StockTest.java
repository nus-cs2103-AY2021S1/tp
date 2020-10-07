package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIALNUMBER_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SOURCE_BANANA;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.BANANA;

import org.junit.jupiter.api.Test;

import seedu.stock.testutil.StockBuilder;

public class StockTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(APPLE.isSameStock(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameStock(null));

        // different serialnumber and source -> returns false
        Stock editedApple = new StockBuilder(APPLE).withSerialNumber(VALID_SERIALNUMBER_BANANA)
                .withSource(VALID_SOURCE_BANANA).build();
        assertFalse(BANANA.isSameStock(editedApple));

        // different name -> returns false
        editedApple = new StockBuilder(APPLE).withName(VALID_NAME_BANANA).build();
        assertFalse(BANANA.isSameStock(editedApple));

        // same name, same serial number, same source, different attributes -> returns true
        editedApple = new StockBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA)
                .withLocation(VALID_LOCATION_BANANA).build();
        assertTrue(APPLE.isSameStock(editedApple));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Stock appleCopy = new StockBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different person -> returns false
        assertFalse(APPLE.equals(BANANA));

        // different name -> returns false
        Stock editedApple = new StockBuilder(APPLE).withName(VALID_NAME_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different serial number -> returns false
        editedApple = new StockBuilder(APPLE).withSerialNumber(VALID_SERIALNUMBER_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different email -> returns false
        editedApple = new StockBuilder(APPLE).withSource(VALID_SOURCE_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different address -> returns false
        editedApple = new StockBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new StockBuilder(APPLE).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
