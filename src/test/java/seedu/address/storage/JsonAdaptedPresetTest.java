package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPreset.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPresets.PRESET;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.vendor.Name;
import seedu.address.testutil.PresetBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedPresetTest {

    public static final List<JsonAdaptedOrderItem> VALID_ORDERITEMS = PresetBuilder
            .TYPICAL_ORDER_ITEMS.stream().map(JsonAdaptedOrderItem::new).collect(Collectors.toList());
    
    @Test
    public void toModelType_validPresetDetails_returnsPreset() throws Exception {
        JsonAdaptedPreset preset = new JsonAdaptedPreset(PRESET);
        assertEquals(PRESET, preset.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPreset Preset = new JsonAdaptedPreset(null, VALID_ORDERITEMS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, Preset::toModelType);
    }

}
