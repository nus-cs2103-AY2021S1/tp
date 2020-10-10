package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRecipe.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.BENSON;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;

public class JsonAdaptedRecipeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final ArrayList<Ingredient> INVALID_INGREDIENT =
            new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("@olive oil")}));
    private static final int INVALID_CALORIES = -1;
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final ArrayList<Ingredient> VALID_INGREDIENT = BENSON.getIngredient();
    private static final int VALID_CALORIES = BENSON.getCalories().value;
    private static final String VALID_INSTRUCTION = BENSON.getInstruction();
    private static final String VALID_RECIPE_IMAGE = BENSON.getRecipeImage();

    @Test
    public void toModelType_validRecipeDetails_returnsRecipe() throws Exception {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(BENSON);
        assertEquals(BENSON, recipe.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(INVALID_NAME, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                        VALID_INGREDIENT, VALID_CALORIES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(null, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                VALID_INGREDIENT, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredient_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                        INVALID_INGREDIENT, VALID_CALORIES);
        String expectedMessage = Ingredient.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullIngredient_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                null, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ingredient.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidCalories_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION,
                        VALID_RECIPE_IMAGE, VALID_INGREDIENT, INVALID_CALORIES);
        String expectedMessage = Calories.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullCalories_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION,
                VALID_RECIPE_IMAGE, VALID_INGREDIENT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Calories.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    /*@Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }*/

    /*@Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }
    */

}
