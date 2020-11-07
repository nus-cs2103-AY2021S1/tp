package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.storage.JsonAdaptedFood.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.BAGEL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.Quantity;

public class JsonAdaptedFoodTest {
    private static final String INVALID_DESCRIPTION = "T@na";
    private static final String INVALID_PRIORITY = "MEDIUM-HIGH";
    private static final String INVALID_EXPIRY_DATE = "1-13-2020";
    private static final String INVALID_QUANTITY = "-1 @@";
    private static final String INVALID_QUANTITY_ZEROVALUE = "0";
    private static final String INVALID_QUANTITY_UNIT = "1 @@";
    private static final String INVALID_TAG = "^frozen";
    private static final String VALID_DESCRIPTION = BAGEL.getDescription().toString();
    private static final String VALID_PRIORITY = BAGEL.getPriority().toString();
    private static final String VALID_EXPIRY_DATE = BAGEL.getExpiryDate().toString();
    private static final String VALID_QUANTITY = BAGEL.getQuantity().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BAGEL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validFoodDetails_returnsFood() throws Exception {
        JsonAdaptedFood food = new JsonAdaptedFood(BAGEL);
        assertEquals(BAGEL, food.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(INVALID_DESCRIPTION, VALID_PRIORITY, VALID_EXPIRY_DATE,
                        VALID_QUANTITY, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, VALID_PRIORITY, VALID_EXPIRY_DATE,
                VALID_QUANTITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, INVALID_PRIORITY, VALID_EXPIRY_DATE,
                VALID_QUANTITY, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, null, VALID_EXPIRY_DATE,
                VALID_QUANTITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, INVALID_EXPIRY_DATE,
                VALID_QUANTITY, VALID_TAGS);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, null,
                VALID_QUANTITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, VALID_EXPIRY_DATE,
                null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, VALID_EXPIRY_DATE,
                INVALID_QUANTITY, VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidQuantityZeroValue_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, VALID_EXPIRY_DATE,
                INVALID_QUANTITY_ZEROVALUE, VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidQuantityUnit_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, VALID_EXPIRY_DATE,
                INVALID_QUANTITY_UNIT, VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PRIORITY, VALID_EXPIRY_DATE,
                VALID_QUANTITY, invalidTags);
        assertThrows(IllegalValueException.class, food::toModelType);
    }

}
