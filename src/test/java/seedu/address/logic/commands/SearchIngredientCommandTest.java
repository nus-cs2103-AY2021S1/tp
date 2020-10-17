package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INGREDIENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIngredients.BANANA;
import static seedu.address.testutil.TypicalIngredients.BREAD;
import static seedu.address.testutil.TypicalIngredients.OAT;
import static seedu.address.testutil.TypicalIngredients.getTypicalWishfulShrinking;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.IngredientContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SearchIngredientCommandTest {
    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void equals() {
        IngredientContainsKeywordsPredicate firstPredicate =
                new IngredientContainsKeywordsPredicate(Collections.singletonList("first"));
        IngredientContainsKeywordsPredicate secondPredicate =
                new IngredientContainsKeywordsPredicate(Collections.singletonList("second"));

        SearchIngredientCommand findFirstCommand = new SearchIngredientCommand(firstPredicate);
        SearchIngredientCommand findSecondCommand = new SearchIngredientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        SearchIngredientCommand findFirstCommandCopy = new SearchIngredientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noIngredientFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENT_LISTED_OVERVIEW, 0) + "\n";
        IngredientContainsKeywordsPredicate predicate = preparePredicate(" ");
        SearchIngredientCommand command = new SearchIngredientCommand(predicate);
        expectedModel.updateFilteredIngredientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredIngredientList());
    }

    @Test
    public void execute_multipleKeywords_multipleIngredientsFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENT_LISTED_OVERVIEW, 3);
        expectedMessage += "\n" + "1. White Bread\n" + "2. Banana\n" + "3. Oat" + "\n";
        IngredientContainsKeywordsPredicate predicate = preparePredicate("Bread Oat Banana");
        SearchIngredientCommand command = new SearchIngredientCommand(predicate);
        expectedModel.updateFilteredIngredientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BREAD, BANANA, OAT), model.getFilteredIngredientList());
    }

    /**
     * Parses {@code userInput} into a {@code IngredientContainsKeywordsPredicate}.
     */
    private IngredientContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IngredientContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
