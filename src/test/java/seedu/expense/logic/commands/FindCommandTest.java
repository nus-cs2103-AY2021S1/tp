package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.testutil.TypicalExpenses.PHONE_BILL;
import static seedu.expense.testutil.TypicalExpenses.SWEE_CHOON;
import static seedu.expense.testutil.TypicalExpenses.ZARA;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.DateMatchesPredicate;
import seedu.expense.model.expense.DescriptionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());
    private Model expectedModel = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));
        DateMatchesPredicate firstDatePredicate =
                new DateMatchesPredicate(Arrays.asList("09-08-2020"));
        DateMatchesPredicate secondDatePredicate =
                new DateMatchesPredicate(Arrays.asList("09-08-2020"));
        FindCommand findFirstCommand = new FindCommand(
                firstPredicate, firstDatePredicate);
        FindCommand findSecondCommand = new FindCommand(
                secondPredicate, secondDatePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(
                firstPredicate, firstDatePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noMatchingKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        DateMatchesPredicate datePredicate =
                new DateMatchesPredicate(Collections.emptyList());
        DescriptionContainsKeywordsPredicate namePredicate = preparePredicate("CannotBeNotFound");
        FindCommand command = new FindCommand(namePredicate, datePredicate);
        expectedModel.updateFilteredExpenseList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 3);
        DateMatchesPredicate datePredicate =
                new DateMatchesPredicate(Collections.emptyList());
        DescriptionContainsKeywordsPredicate namePredicate = preparePredicate("ZARA Phone Swee");
        FindCommand command = new FindCommand(namePredicate, datePredicate);
        expectedModel.updateFilteredExpenseList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ZARA, PHONE_BILL, SWEE_CHOON), model.getFilteredExpenseList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
