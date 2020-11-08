package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOW_QUANTITY_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BANANA;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.testutil.UpdateStockDescriptorBuilder;

public class UpdateStockDescriptorTest {

    @Test
    public void equals() {
        // EP: same values -> returns true
        UpdateStockDescriptor descriptorWithSameValues = new UpdateStockDescriptor(DESC_APPLE);
        assertTrue(DESC_APPLE.equals(descriptorWithSameValues));

        // EP: same object -> returns true
        assertTrue(DESC_APPLE.equals(DESC_APPLE));

        // EP: null -> returns false
        assertFalse(DESC_BANANA.equals(null));

        // EP: different types -> returns false
        assertFalse(DESC_BANANA.equals(5));

        // EP: different values -> returns false
        assertFalse(DESC_APPLE.equals(DESC_BANANA));

        // EP: different name -> returns false
        UpdateStockDescriptor editedApple = new UpdateStockDescriptorBuilder(DESC_APPLE).withName(VALID_NAME_BANANA)
                .build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // EP: different serial number -> returns false
        editedApple = new UpdateStockDescriptorBuilder(DESC_APPLE).withSerialNumber(VALID_SERIAL_NUMBER_BANANA)
                .build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // EP: different quantity  -> returns false
        editedApple = new UpdateStockDescriptorBuilder(DESC_APPLE).withQuantity(VALID_QUANTITY_BANANA)
                .build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // EP: different location -> returns false
        editedApple = new UpdateStockDescriptorBuilder(DESC_APPLE).withLocation(VALID_QUANTITY_BANANA)
                .build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // EP: different quantity adder -> returns false
        editedApple = new UpdateStockDescriptorBuilder(DESC_APPLE).withQuantityAdder(VALID_QUANTITY_BANANA)
                .build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // EP: different low quantity -> returns false
        editedApple = new UpdateStockDescriptorBuilder(DESC_APPLE).withLowQuantity(VALID_LOW_QUANTITY_BANANA)
                .build();
        assertFalse(DESC_APPLE.equals(editedApple));
    }
}
