package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedConsumption.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientString;
import seedu.address.model.recipe.Name;

public class JsonAdaptedConsumptionTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_INGREDIENT = "+651234";
    private static final Integer INVALID_CALORIES = -1;

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_INGREDIENT = BENSON.getIngredient().toString();
    private static final int VALID_CALORIES = BENSON.getCalories().value;


    /*@Test
    public void toModelType_validConsumptionDetails_returnsRecipe() throws Exception {
        JsonAdaptedConsumption consumpReturn = new JsonAdaptedConsumption(BENSON);
        Consumption consump = new Consumption(BENSON);
        assertEquals(consump, consumpReturn.toModelType());
    }*/

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedConsumption consump =
                new JsonAdaptedConsumption(INVALID_NAME, VALID_INGREDIENT, VALID_CALORIES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, consump::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedConsumption consump = new JsonAdaptedConsumption(null, VALID_INGREDIENT, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consump::toModelType);
    }

    @Test
    public void toModelType_invalidIngredient_throwsIllegalValueException() {
        JsonAdaptedConsumption consump =
                new JsonAdaptedConsumption(VALID_NAME, INVALID_INGREDIENT, VALID_CALORIES);
        String expectedMessage = IngredientString.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, consump::toModelType);
    }

    @Test
    public void toModelType_nullIngredient_throwsIllegalValueException() {
        JsonAdaptedConsumption consump = new JsonAdaptedConsumption(VALID_NAME, null, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ingredient.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consump::toModelType);
    }

    /*@Test
    public void toModelType_invalidCalories_throwsIllegalValueException() {
        JsonAdaptedConsumption consump =
                new JsonAdaptedConsumption(VALID_NAME, VALID_INGREDIENT, INVALID_CALORIES);
        String expectedMessage = Calories.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, consump::toModelType);
    }

    @Test
    public void toModelType_nullCalories_throwsIllegalValueException() {
        JsonAdaptedConsumption consump = new JsonAdaptedConsumption(VALID_NAME, VALID_INGREDIENT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Calories.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consump::toModelType);
    }*/
}
