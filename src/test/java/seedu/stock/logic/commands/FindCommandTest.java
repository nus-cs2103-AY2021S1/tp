package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.commons.core.Messages.MESSAGE_STOCKS_LISTED_OVERVIEW;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.BANANA;
import static seedu.stock.testutil.TypicalStocks.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), getTypicalSerialNumberSetsBook());
    private Model expectedModel = new ModelManager(getTypicalStockBook(),
            new UserPrefs(), getTypicalSerialNumberSetsBook());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        NameContainsKeywordsPredicate thirdPredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("second", "second"));

        FindCommand firstFindCommand = new FindCommand(Collections.singletonList(firstPredicate));
        FindCommand secondFindCommand = new FindCommand(Collections.singletonList(secondPredicate));
        FindCommand thirdFindCommand = new FindCommand(Collections.singletonList(thirdPredicate));

        // same object -> returns true
        assertTrue(firstFindCommand.equals(firstFindCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(Collections.singletonList(firstPredicate));
        assertTrue(firstFindCommand.equals(findFirstCommandCopy));

        FindCommand secondFindCommandCopy = new FindCommand(Collections
                .singletonList(new NameContainsKeywordsPredicate(Collections.singletonList("second"))));
        assertTrue(secondFindCommand.equals(secondFindCommandCopy));

        // different types -> returns false
        assertFalse(firstFindCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindCommand.equals(null));

        // different stock -> returns false
        assertFalse(firstFindCommand.equals(secondFindCommand));

        // one same value, other copy of value -> returns false
        assertFalse(secondFindCommand.equals(thirdFindCommand));
    }

    @Test
    public void execute_zeroKeywords_noStockFound() {
        String expectedMessage = String.format(MESSAGE_STOCKS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("");
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        // status message to show what user has searched for
        String statusMessage = "Searching for:\n" + predicate.toString();
        expectedModel.updateFilteredStockList(predicate);
        assertCommandSuccess(command, model, statusMessage + "\n" + expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStockList());
    }

    @Test
    public void execute_multipleKeywords_multipleStocksFound() {
        String expectedMessage = String.format(MESSAGE_STOCKS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("A");
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        // status message to show what user has searched for
        String statusMessage = "Searching for:\n" + predicate.toString();
        expectedModel.updateFilteredStockList(predicate);
        assertCommandSuccess(command, model, statusMessage + "\n" + expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPLE, BANANA), model.getFilteredStockList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String trimmedUserInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(trimmedUserInput.split("\\s+")));
    }
}
