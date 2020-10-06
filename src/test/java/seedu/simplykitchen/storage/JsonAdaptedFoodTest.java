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
import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Phone;

public class JsonAdaptedFoodTest {
    private static final String INVALID_DESCRIPTION = "T@na";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EXPIRYDATE = "1-13-2020";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DESCRIPTION = BAGEL.getDescription().toString();
    private static final String VALID_PHONE = BAGEL.getPhone().toString();
    private static final String VALID_EXPIRYDATE = BAGEL.getExpiryDate().toString();
    private static final String VALID_ADDRESS = BAGEL.getAddress().toString();
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
                new JsonAdaptedFood(INVALID_DESCRIPTION, VALID_PHONE, VALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, VALID_PHONE, VALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_DESCRIPTION, INVALID_PHONE, VALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, null,
                VALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PHONE, INVALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PHONE, VALID_EXPIRYDATE, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PHONE, VALID_EXPIRYDATE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_DESCRIPTION, VALID_PHONE, VALID_EXPIRYDATE, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, food::toModelType);
    }

}
