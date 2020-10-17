package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.PASTA;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.testutil.WishfulShrinkingBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new WishfulShrinking(), new WishfulShrinking(modelManager.getWishfulShrinking()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setWishfulShrinkingFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setWishfulShrinkingFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setWishfulShrinkingFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setWishfulShrinkingFilePath(null));
    }

    @Test
    public void setWishfulShrinkingFilePath_validPath_setsWishfulShrinkingFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setWishfulShrinkingFilePath(path);
        assertEquals(path, modelManager.getWishfulShrinkingFilePath());
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInWishfulShrinking_returnsFalse() {
        assertFalse(modelManager.hasRecipe(SANDWICH));
    }

    @Test
    public void hasRecipe_recipeInWishfulShrinking_returnsTrue() {
        modelManager.addRecipe(SANDWICH);
        assertTrue(modelManager.hasRecipe(SANDWICH));
    }

    @Test
    public void getFilteredRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRecipeList().remove(0));
    }

    @Test
    public void equals() {
        WishfulShrinking wishfulShrinking = new WishfulShrinkingBuilder()
                .withRecipe(SANDWICH)
                .withRecipe(PASTA)
                .build();
        WishfulShrinking differentWishfulShrinking = new WishfulShrinking();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(wishfulShrinking, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(wishfulShrinking, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different wishfulShrinking -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentWishfulShrinking, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = SANDWICH.getName().fullName.split("\\s+");
        modelManager.updateFilteredRecipeList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(wishfulShrinking, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setWishfulShrinkingFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(wishfulShrinking, differentUserPrefs)));
    }
}
