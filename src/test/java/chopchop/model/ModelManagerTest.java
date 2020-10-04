package chopchop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static chopchop.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;
import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalIngredients.APRICOT;
import static chopchop.testutil.TypicalIngredients.BANANA;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import chopchop.commons.core.GuiSettings;
import chopchop.model.attributes.NameContainsKeywordsPredicate;
import chopchop.model.ingredient.IngredientBook;
import chopchop.model.recipe.RecipeBook;
import chopchop.testutil.IngredientBookBuilder;

public class ModelManagerTest {

    private chopchop.model.ModelManager modelManager = new chopchop.model.ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new IngredientBook(), new IngredientBook(modelManager.getIngredientBook()));
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasIngredient(null));
    }

    @Test
    public void hasIngredient_indNotInIngredientBook_returnsFalse() {
        assertFalse(modelManager.hasIngredient(APRICOT));
    }

    @Test
    public void hasIngredient_ingredientInIngredientBook_returnsTrue() {
        modelManager.addIngredient(APRICOT);
        assertTrue(modelManager.hasIngredient(APRICOT));
    }

    @Test
    public void getFilteredPIngredientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredIngredientList().remove(0));
    }

    @Test
    public void equals() {
        RecipeBook recipeBook = new RecipeBook();
        IngredientBook ingredientBook = new IngredientBookBuilder()
                                            .withIngredient(APRICOT).withIngredient(BANANA).build();
        IngredientBook differentIngredientBook = new IngredientBook();
        UserPrefs userPrefs = new chopchop.model.UserPrefs();

        // same values -> returns true
        modelManager = new chopchop.model.ModelManager(recipeBook, ingredientBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(recipeBook, ingredientBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different ingredientBook -> returns false
        assertFalse(modelManager.equals(new chopchop.model
            .ModelManager(recipeBook, differentIngredientBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = APRICOT.getName().fullName.split("\\s+");
        modelManager.updateFilteredIngredientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(recipeBook, ingredientBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);

    }
}
