package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedIngredient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.VALID_INGREDIENT2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Name;


public class JsonAdaptedIngredientTest {

    private static final String VALID_VALUE = "Potato";
    private static final String VALID_QUANTITY = " -100g";

    private static final String INVALID_VALUE = "P@T@T@";
    private static final String INVALID_QUANTITY = "-...///";


    @Test
    public void toModelType_validIngredientDetails_returnsIngredient() throws Exception {
        JsonAdaptedIngredient ingredientReturn = new JsonAdaptedIngredient(VALID_INGREDIENT2);
        Ingredient ingredient = VALID_INGREDIENT2;
        assertEquals(ingredient, ingredientReturn.toModelType());
    }

    @Test
    public void toModelType_invalidValue_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(INVALID_VALUE, VALID_QUANTITY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(VALID_VALUE, INVALID_QUANTITY);
        String expectedMessage = Ingredient.HYPHEN_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_nullValue_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(null, VALID_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(VALID_VALUE, null);
        String expectedMessage = Ingredient.HYPHEN_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

}
