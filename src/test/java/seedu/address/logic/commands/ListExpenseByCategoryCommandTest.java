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
import seedu.address.model.expense.CategoryContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ListExpenseByCategoryCommand}.
 */
public class ListExpenseByCategoryCommandTest {
    private Model model = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());
    private Model expectedModel = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());

    @Test
    public void equals() {
        CategoryContainsKeywordsPredicate firstPredicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("first"));
        CategoryContainsKeywordsPredicate secondPredicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("second"));

        ListExpenseByCategoryCommand listCategoryFirstCommand = new ListExpenseByCategoryCommand(firstPredicate);
        ListExpenseByCategoryCommand listCategorySecondCommand = new ListExpenseByCategoryCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listCategoryFirstCommand.equals(listCategoryFirstCommand));

        // same values -> returns true
        ListExpenseByCategoryCommand listCategoryFirstCommandCopy = new ListExpenseByCategoryCommand(firstPredicate);
        assertTrue(listCategoryFirstCommand.equals(listCategoryFirstCommandCopy));

        // different types -> returns false
        assertFalse(listCategoryFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listCategoryFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listCategoryFirstCommand.equals(listCategorySecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        CategoryContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListExpenseByCategoryCommand command = new ListExpenseByCategoryCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 3);
        CategoryContainsKeywordsPredicate predicate = preparePredicate("CLOTHES snacks digital devices");
        ListExpenseByCategoryCommand command = new ListExpenseByCategoryCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CLOTHES, SNACKS, KEYBOARD), model.getFilteredExpenseList());
    }

    /**
     * Parses {@code userInput} into a {@code CategoryContainsKeywordsPredicate}.
     */
    private CategoryContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CategoryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
