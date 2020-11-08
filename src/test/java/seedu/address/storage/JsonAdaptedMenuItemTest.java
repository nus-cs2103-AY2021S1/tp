package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMenuItem.INVALID_PRICE_FORMAT;
import static seedu.address.storage.JsonAdaptedMenuItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.MenuItem;
import seedu.address.model.vendor.Name;
import seedu.address.testutil.MenuItemBuilder;

public class JsonAdaptedMenuItemTest {
    public static final MenuItem EGG_PRATA = new MenuItemBuilder().withName("Egg Prata")
            .withPrice(1.2).withTags("bestseller").build();

    private static final String VALID_NAME = "Egg Prata";
    private static final double VALID_PRICE = 1.20;
    private static final double INVALID_PRICE = -1.20;
    private static final String INVALID_TAG = "#tasty";
    private static final List<JsonAdaptedTag> VALID_TAGS = EGG_PRATA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validMenuItemDetails_returnsMenuItem() throws Exception {
        JsonAdaptedMenuItem food = new JsonAdaptedMenuItem(EGG_PRATA);
        assertEquals(EGG_PRATA, food.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMenuItem menuItem = new JsonAdaptedMenuItem(null, VALID_PRICE, VALID_TAGS, "");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, menuItem::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedMenuItem item = new JsonAdaptedMenuItem(VALID_NAME, INVALID_PRICE, VALID_TAGS, "");
        assertThrows(IllegalValueException.class, INVALID_PRICE_FORMAT, item::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMenuItem item =
                new JsonAdaptedMenuItem(VALID_NAME, VALID_PRICE, invalidTags, "");
        assertThrows(IllegalValueException.class, item::toModelType);
    }

}
