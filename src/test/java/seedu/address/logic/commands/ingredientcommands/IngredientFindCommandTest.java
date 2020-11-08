package seedu.address.logic.commands.ingredientcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SalesBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.IngredientNameContainsKeywordsPredicate;

public class IngredientFindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_noIngredientFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENTS_LISTED_OVERVIEW, 0);
        IngredientNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        IngredientFindCommand command = new IngredientFindCommand(predicate);
        expectedModel.updateFilteredIngredientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredIngredientList());
    }

    @Test
    public void equals() {
        IngredientNameContainsKeywordsPredicate firstPredicate =
                new IngredientNameContainsKeywordsPredicate(Collections.singletonList("milk"));
        IngredientNameContainsKeywordsPredicate secondPredicate =
                new IngredientNameContainsKeywordsPredicate(Collections.singletonList("boba"));

        IngredientFindCommand findMilkCommand = new IngredientFindCommand(firstPredicate);
        IngredientFindCommand findBobaCommand = new IngredientFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findMilkCommand.equals(findMilkCommand));

        // same values -> returns true
        IngredientFindCommand findMilkCommandCopy = new IngredientFindCommand(firstPredicate);
        assertTrue(findMilkCommand.equals(findMilkCommandCopy));

        // different types -> returns false
        assertFalse(findMilkCommand.equals(1));

        // null -> returns false
        assertFalse(findMilkCommand.equals(null));

        // different ingredients -> returns false
        assertFalse(findMilkCommand.equals(findBobaCommand));

    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private IngredientNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IngredientNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
