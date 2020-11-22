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
import seedu.address.model.expense.DescContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ListExpenseByDescCommand}.
 */
public class ListExpenseByDescCommandTest {
    private Model model = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());
    private Model expectedModel = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());

    @Test
    public void equals() {
        DescContainsKeywordsPredicate firstPredicate =
                new DescContainsKeywordsPredicate(Collections.singletonList("first"));
        DescContainsKeywordsPredicate secondPredicate =
                new DescContainsKeywordsPredicate(Collections.singletonList("second"));

        ListExpenseByDescCommand listDescFirstCommand = new ListExpenseByDescCommand(firstPredicate);
        ListExpenseByDescCommand listDescSecondCommand = new ListExpenseByDescCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listDescFirstCommand.equals(listDescFirstCommand));

        // same values -> returns true
        ListExpenseByDescCommand listDescFirstCommandCopy = new ListExpenseByDescCommand(firstPredicate);
        assertTrue(listDescFirstCommand.equals(listDescFirstCommandCopy));

        // different types -> returns false
        assertFalse(listDescFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listDescFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listDescFirstCommand.equals(listDescSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        DescContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListExpenseByDescCommand command = new ListExpenseByDescCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 3);
        DescContainsKeywordsPredicate predicate = preparePredicate("jacket chocolate Keyboard");
        ListExpenseByDescCommand command = new ListExpenseByDescCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CLOTHES, SNACKS, KEYBOARD), model.getFilteredExpenseList());
    }

    /**
     * Parses {@code userInput} into a {@code DescContainsKeywordsPredicate}.
     */
    private DescContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
