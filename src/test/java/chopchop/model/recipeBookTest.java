package chopchop.model;

import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.RecipeBook;
import chopchop.model.recipe.ReadOnlyRecipeBook;
import chopchop.model.recipe.exceptions.DuplicateRecipeException;
import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.RecipeBook;
import chopchop.testutil.RecipeBuilder;
import chopchop.testutil.RecipeBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.*;

import static chopchop.logic.commands.CommandTestUtil.VALID_INGREDIENT_EXPIRY_BANANA;
import static chopchop.logic.commands.CommandTestUtil.VALID_INGREDIENT_QTY_BANANA;
import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalIngredients.BANANA;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredients;
import static chopchop.testutil.TypicalRecipes.*;
import static org.junit.jupiter.api.Assertions.*;

public class recipeBookTest {

    private final RecipeBook recipeBook = new RecipeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), recipeBook.getFoodEntryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRecipeBook_replacesData() {
        RecipeBook newData = getTypicalRecipeBook();
        recipeBook.resetData(newData);
        assertEquals(newData, recipeBook);
    }

    @Test
    public void resetData_withDuplicateRecipes_throwsDuplicateRecipeException() {
        // Two recipes with the same identity fields
        Recipe editedRecipe = new RecipeBuilder(APRICOT_SALAD).build();
        List<Recipe> newRecipes = Arrays.asList(APRICOT_SALAD, editedRecipe);
        recipeBookTest.RecipeBookStub newData = new recipeBookTest.RecipeBookStub(newRecipes);

        assertThrows(DuplicateRecipeException.class, () -> recipeBook.resetData(newData));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInRecipeBook_returnsFalse() {
        assertFalse(recipeBook.hasRecipe(APRICOT_SALAD));
    }

    @Test
    public void hasRecipe_recipeInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(APRICOT_SALAD);
        assertTrue(recipeBook.hasRecipe(APRICOT_SALAD));
    }

    @Test
    public void hasRecipe_recipeWithSameIdentityFieldsInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(APRICOT_SALAD);
        Recipe editedRecipe = new RecipeBuilder(APRICOT_SALAD)
            .withIngredients(new ArrayList<>(Arrays.asList(BANANA)))
            .withSteps(new ArrayList<>(Arrays.asList(STEP_BANANA_SALAD)))
            .build();
        assertFalse(recipeBook.hasRecipe(editedRecipe)); //Both identity fields must be equal
    }

    @Test
    public void getRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> recipeBook.getFoodEntryList().remove(0));
    }

    /**
     * A stub ReadOnlyRecipeBook whose recipes list can violate interface constraints.
     */
    private static class RecipeBookStub implements ReadOnlyRecipeBook {
        private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();

        RecipeBookStub(Collection<Recipe> recipes) {
            this.recipes.setAll(recipes);
        }

        @Override
        public ObservableList<Recipe> getFoodEntryList() {
            return recipes;
        }
    }

}
