package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.EGGS;
import static seedu.address.testutil.TypicalRecipes.ENCHILADAS;
import static seedu.address.testutil.TypicalRecipes.PATTY;
import static seedu.address.testutil.TypicalRecipes.PORK;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.model.recipe.RecipeContainsIngredientsPredicate;
import seedu.address.model.recipe.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SearchRecipeCommandTest {
    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        SearchRecipeCommand findFirstCommand = new SearchRecipeCommand(firstPredicate);
        SearchRecipeCommand findSecondCommand = new SearchRecipeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        SearchRecipeCommand findFirstCommandCopy = new SearchRecipeCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different recipe -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroNameKeywords_noRecipeFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        SearchRecipeCommand command = new SearchRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecipeList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Pork Egg Enchiladas");
        SearchRecipeCommand command = new SearchRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PORK, ENCHILADAS, EGGS), model.getFilteredRecipeList());
    }

    @Test
    public void execute_zeroTagKeywords_noRecipeFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate(" ");
        SearchRecipeCommand command = new SearchRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecipeList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("low calories");
        SearchRecipeCommand command = new SearchRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ENCHILADAS, PATTY), model.getFilteredRecipeList());
    }

    @Test
    public void execute_zeroIngredientKeywords_noRecipeFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 0);
        RecipeContainsIngredientsPredicate predicate = prepareIngredientsPredicate(" ");
        SearchRecipeCommand command = new SearchRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecipeList());
    }

    @Test
    public void execute_multipleIngredientKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 1);
        RecipeContainsIngredientsPredicate predicate = prepareIngredientsPredicate("Egg");
        SearchRecipeCommand command = new SearchRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EGGS), model.getFilteredRecipeList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RecipeContainsKeywordsPredicate}.
     */
    private RecipeContainsIngredientsPredicate prepareIngredientsPredicate(String userInput) {
        return new RecipeContainsIngredientsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
