package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRecipe.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.PASTA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.RecipeImage;

public class JsonAdaptedRecipeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final ArrayList<Ingredient> INVALID_INGREDIENT =
            new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("@olive oil")}));
    private static final int INVALID_CALORIES = -1;
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = PASTA.getName().toString();
    private static final ArrayList<Ingredient> VALID_INGREDIENT = PASTA.getIngredient();
    private static final int VALID_CALORIES = PASTA.getCalories().value;
    private static final ArrayList<Instruction> VALID_INSTRUCTION = PASTA.getInstruction();
    private static final RecipeImage VALID_RECIPE_IMAGE = PASTA.getRecipeImage();
    private static final List<JsonAdaptedTag> VALID_TAGS = PASTA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRecipeDetails_returnsRecipe() throws Exception {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(PASTA);
        assertEquals(PASTA, recipe.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(INVALID_NAME, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                        VALID_INGREDIENT, VALID_CALORIES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(null, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                VALID_INGREDIENT, VALID_CALORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredient_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                        INVALID_INGREDIENT, VALID_CALORIES, VALID_TAGS);
        String expectedMessage = Ingredient.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullIngredient_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION, VALID_RECIPE_IMAGE,
                null, VALID_CALORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ingredient.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidCalories_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION,
                        VALID_RECIPE_IMAGE, VALID_INGREDIENT, INVALID_CALORIES, VALID_TAGS);
        String expectedMessage = Calories.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullCalories_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_NAME, VALID_INSTRUCTION,
                VALID_RECIPE_IMAGE, VALID_INGREDIENT, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Calories.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedConsumption consump = new JsonAdaptedConsumption(VALID_NAME, VALID_INSTRUCTION,
                VALID_RECIPE_IMAGE, VALID_INGREDIENT, null, invalidTags);
        assertThrows(IllegalValueException.class, consump::toModelType);
    }
}
