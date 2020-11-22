package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.CLOTHES;
import static seedu.address.testutil.TypicalExpenses.KEYBOARD;
import static seedu.address.testutil.TypicalExpenses.SNACKS;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.DateContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ListExpenseByDateCommand}.
 */
public class ListExpenseByDateCommandTest {
    private Model model = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());
    private Model expectedModel = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());

    @Test
    public void equals() {
        DateContainsKeywordsPredicate firstPredicate =
                new DateContainsKeywordsPredicate(Collections.singletonList("first"));
        DateContainsKeywordsPredicate secondPredicate =
                new DateContainsKeywordsPredicate(Collections.singletonList("second"));

        ListExpenseByDateCommand listDateFirstCommand = new ListExpenseByDateCommand(firstPredicate);
        ListExpenseByDateCommand listDateSecondCommand = new ListExpenseByDateCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listDateFirstCommand.equals(listDateFirstCommand));

        // same values -> returns true
        ListExpenseByDateCommand listDateFirstCommandCopy = new ListExpenseByDateCommand(firstPredicate);
        assertTrue(listDateFirstCommand.equals(listDateFirstCommandCopy));

        // different types -> returns false
        assertFalse(listDateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listDateFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listDateFirstCommand.equals(listDateSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        DateContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListExpenseByDateCommand command = new ListExpenseByDateCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        Date firstDate = new Date("1");
        Date secondDate = new Date("0");
        String userInput = firstDate.getDate() + " " + secondDate.getDate();
        DateContainsKeywordsPredicate predicate = preparePredicate(userInput);
        ListExpenseByDateCommand command = new ListExpenseByDateCommand(predicate);

        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 3);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CLOTHES, SNACKS, KEYBOARD), model.getFilteredExpenseList());
    }

    /**
     * Parses {@code userInput} into a {@code DateContainsKeywordsPredicate}.
     */
    private DateContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DateContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
