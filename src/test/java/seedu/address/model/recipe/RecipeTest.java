package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_SANDWICH;
import static seedu.address.testutil.TypicalRecipes.MARGARITAS;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ingredient.Ingredient;
import seedu.address.testutil.RecipeBuilder;

public class RecipeTest {

    @Test
    public void isSameRecipeNameAndIngredient() {
        // same object -> returns true
        assertTrue(SANDWICH.isSameRecipeNameAndIngredientName(SANDWICH));

        // null -> returns false
        assertFalse(SANDWICH.isSameRecipeNameAndIngredientName(null));

        // different ingredients -> returns false
        Recipe editedSandwich =
                new RecipeBuilder(SANDWICH)
                        .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_SANDWICH)
                        .build();
        assertFalse(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        // same ingredients and quantity -> returns true
        editedSandwich =
                new RecipeBuilder(SANDWICH).withIngredient(VALID_INGREDIENT_SANDWICH, VALID_QUANTITY_SANDWICH)
                        .build();
        assertTrue(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        // same ingredients and diff quantity -> returns true
        editedSandwich =
                new RecipeBuilder(SANDWICH).withIngredient(VALID_INGREDIENT_SANDWICH, VALID_QUANTITY_MARGARITAS)
                        .build();
        assertTrue(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        // different ingredients and quantity -> returns false
        editedSandwich = new RecipeBuilder(SANDWICH)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                .build();
        assertFalse(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        // different name -> returns false
        editedSandwich = new RecipeBuilder(SANDWICH).withName(VALID_NAME_MARGARITAS).build();
        assertFalse(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        // same name -> returns true
        editedSandwich = new RecipeBuilder(SANDWICH).withName("Burger").build();
        assertFalse(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        // same name, same ingredients, different attributes -> returns true
        editedSandwich = new RecipeBuilder(SANDWICH)
               .build();
        assertTrue(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));

        editedSandwich.setDefaultImage();
        assertTrue(SANDWICH.isSameRecipeNameAndIngredientName(editedSandwich));
    }


    @Test
    public void isSameRecipe() {
        // same object -> returns true
        assertTrue(SANDWICH.isSameRecipe(SANDWICH));

        // null -> returns false
        assertFalse(SANDWICH.isSameRecipe(null));

        // different ingredients -> returns false
        Recipe editedSandwich =
                new RecipeBuilder(SANDWICH)
                        .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_SANDWICH)
                        .build();
        assertFalse(SANDWICH.isSameRecipe(editedSandwich));

        // same ingredients and quantity -> returns true
        editedSandwich =
                new RecipeBuilder(SANDWICH).withIngredient(VALID_INGREDIENT_SANDWICH,
                        VALID_QUANTITY_SANDWICH)
                        .build();
        assertTrue(SANDWICH.isSameRecipe(editedSandwich));

        // same ingredients and diff quantity -> returns false
        editedSandwich =
                new RecipeBuilder(SANDWICH).withIngredient(VALID_INGREDIENT_SANDWICH,
                        VALID_QUANTITY_MARGARITAS)
                        .build();
        assertFalse(SANDWICH.isSameRecipe(editedSandwich));

        // different ingredients and quantity -> returns false
        editedSandwich = new RecipeBuilder(SANDWICH)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                .build();
        assertFalse(SANDWICH.isSameRecipe(editedSandwich));

        // different name -> returns false
        editedSandwich = new RecipeBuilder(SANDWICH).withName(VALID_NAME_MARGARITAS).build();
        assertFalse(SANDWICH.isSameRecipe(editedSandwich));

        // same name -> returns true
        editedSandwich = new RecipeBuilder(SANDWICH).withName("Burger").build();
        assertFalse(SANDWICH.isSameRecipe(editedSandwich));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Recipe aliceCopy = new RecipeBuilder(SANDWICH).build();
        assertTrue(SANDWICH.equals(aliceCopy));

        // same object -> returns true
        assertTrue(SANDWICH.equals(SANDWICH));

        // null -> returns false
        assertFalse(SANDWICH.equals(null));

        // different type -> returns false
        assertFalse(SANDWICH.equals(5));

        // different recipe -> returns false
        assertFalse(SANDWICH.equals(MARGARITAS));

        // different name -> returns false
        Recipe editedSandwich = new RecipeBuilder(SANDWICH).withName(VALID_NAME_MARGARITAS).build();
        assertFalse(SANDWICH.equals(editedSandwich));

        // different ingredients -> returns false
        editedSandwich = new RecipeBuilder(SANDWICH)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_SANDWICH)
                .build();
        assertFalse(SANDWICH.equals(editedSandwich));

    }

    @Test
    public void isSameIngredient() {
        ArrayList<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_SANDWICH));
        assertFalse(SANDWICH.isSameIngredients(list));
    }

}
