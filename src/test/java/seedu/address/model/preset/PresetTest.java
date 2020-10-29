package seedu.address.model.preset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrderItems.CHEESE_PRATA;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.testutil.OrderItemBuilder;
import seedu.address.testutil.PresetBuilder;

public class PresetTest {

    private final Preset preset = new PresetBuilder().build();
    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> preset.contains(null));

    }

    @Test
    public void contains_orderItemNotInList_returnsFalse() {
        assertFalse(preset.contains(CHEESE_PRATA));
    }

    @Test
    public void contains_orderItemInList_returnsTrue() throws IllegalValueException {
        preset.addOrderItem(CHEESE_PRATA);
        assertTrue(preset.contains(CHEESE_PRATA));
    }

    @Test
    public void contains_orderItemWithSameIdentityFieldsInList_returnsTrue() throws IllegalValueException {
        preset.addOrderItem(CHEESE_PRATA);
        OrderItem editedCheesePrata = new OrderItemBuilder(CHEESE_PRATA).build();
        assertTrue(preset.contains(editedCheesePrata));
    }

    @Test
    public void equals() {
        preset.addAllOrderItems(PresetBuilder.TYPICAL_ORDER_ITEMS);
        Preset presetCopy = new PresetBuilder().withOrderItems(PresetBuilder.TYPICAL_ORDER_ITEMS).build();

        // same values -> returns true
        assertTrue(preset.equals(presetCopy));

        // same object -> returns true
        assertTrue(preset.equals(preset));

        // null -> returns false
        assertFalse(preset.equals(null));

        // different type -> returns false
        assertFalse(preset.equals(2));

        // different name, same orderItems -> returns true
        Preset editedPreset = new PresetBuilder().withName("Other name")
                .withOrderItems(PresetBuilder.TYPICAL_ORDER_ITEMS).build();
        assertFalse(preset.equals(editedPreset));

        // same name, different orderItems -> returns false
        editedPreset = new PresetBuilder().build();
        assertFalse(preset.equals(editedPreset));

    }
}
