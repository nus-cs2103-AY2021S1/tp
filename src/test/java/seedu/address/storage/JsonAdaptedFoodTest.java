package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFood.INVALID_PRICE_FORMAT;
import static seedu.address.storage.JsonAdaptedFood.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;
import seedu.address.model.vendor.Name;
import seedu.address.testutil.FoodBuilder;

public class JsonAdaptedFoodTest {
    public static final Food EGG_PRATA = new FoodBuilder().withName("Egg Prata")
            .withPrice(1.2).withTags("bestseller").build();

    private static final String VALID_NAME = "Egg Prata";
    private static final double VALID_PRICE = 1.20;
    private static final double INVALID_PRICE = -1.20;
    private static final String INVALID_TAG = "#tasty";
    private static final List<JsonAdaptedTag> VALID_TAGS = EGG_PRATA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validFoodDetails_returnsFood() throws Exception {
        JsonAdaptedFood food = new JsonAdaptedFood(EGG_PRATA);
        assertEquals(EGG_PRATA, food.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, INVALID_PRICE, VALID_TAGS);
        assertThrows(IllegalValueException.class, INVALID_PRICE_FORMAT, food::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_PRICE, invalidTags);
        assertThrows(IllegalValueException.class, food::toModelType);
    }

}
