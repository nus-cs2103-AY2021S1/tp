package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.ALICE;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

//import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
//import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;
//import seedu.address.model.recipe.exceptions.DuplicateRecipeException;
//import seedu.address.testutil.RecipeBuilder;

public class WishfulShrinkingTest {

    private final WishfulShrinking addressBook = new WishfulShrinking();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getRecipeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWishfulShrinking_replacesData() {
        WishfulShrinking newData = getTypicalWishfulShrinking();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    /*@Test
    public void resetData_withDuplicateRecipes_throwsDuplicateRecipeException() {
        // Two recipes with the same identity fields
        Recipe editedAlice = new RecipeBuilder(ALICE)
                .build();
        List<Recipe> newRecipes = Arrays.asList(ALICE, editedAlice);
        WishfulShrinkingStub newData = new WishfulShrinkingStub(newRecipes);

        assertThrows(DuplicateRecipeException.class, () -> addressBook.resetData(newData));
    }*/

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInWishfulShrinking_returnsFalse() {
        assertFalse(addressBook.hasRecipe(ALICE));
    }

    @Test
    public void hasRecipe_recipeInWishfulShrinking_returnsTrue() {
        addressBook.addRecipe(ALICE);
        assertTrue(addressBook.hasRecipe(ALICE));
    }

    /*@Test
    public void hasRecipe_recipeWithSameIdentityFieldsInWishfulShrinking_returnsTrue() {
        addressBook.addRecipe(ALICE);
        Recipe editedAlice = new RecipeBuilder(ALICE)
                .build();
        assertTrue(addressBook.hasRecipe(editedAlice));
    }*/

    @Test
    public void getRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRecipeList().remove(0));
    }

    /**
     * A stub ReadOnlyWishfulShrinking whose recipes list can violate interface constraints.
     */
    private static class WishfulShrinkingStub implements ReadOnlyWishfulShrinking {
        private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

        WishfulShrinkingStub(Collection<Recipe> recipes) {
            this.recipes.setAll(recipes);
        }

        @Override
        public ObservableList<Recipe> getRecipeList() {
            return recipes;
        }

        @Override
        public ObservableList<Ingredient> getIngredientList() {
            return ingredients;
        }
    }

}
