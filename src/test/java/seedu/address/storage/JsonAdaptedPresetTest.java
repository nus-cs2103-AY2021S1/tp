package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPreset.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPresets.PRESET;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.vendor.Name;
import seedu.address.testutil.PresetBuilder;
import seedu.address.testutil.TypicalOrderItems;


public class JsonAdaptedPresetTest {

    public static final List<JsonAdaptedOrderItem> VALID_ORDER_ITEMS = PresetBuilder
            .TYPICAL_ORDER_ITEMS.stream().map(JsonAdaptedOrderItem::new).collect(Collectors.toList());
    public static final String VALID_NAME = TypicalOrderItems.VALID_NAME_PRATA;
    @Test
    public void toModelType_validPresetDetails_returnsPreset() throws Exception {
        JsonAdaptedPreset preset = new JsonAdaptedPreset(PRESET);
        assertEquals(PRESET, preset.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPreset preset = new JsonAdaptedPreset(null, VALID_ORDER_ITEMS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, preset::toModelType);
    }

    @Test
    public void toModelType_nullOrder_throwsIllegalValueException() {
        JsonAdaptedPreset preset = new JsonAdaptedPreset(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Order Items");
        assertThrows(IllegalValueException.class, expectedMessage, preset::toModelType);
    }

}
