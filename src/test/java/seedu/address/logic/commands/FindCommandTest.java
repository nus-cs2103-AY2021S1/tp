package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.CARL;
import static seedu.address.testutil.TypicalExpenses.ELLE;
import static seedu.address.testutil.TypicalExpenses.FIONA;
import static seedu.address.testutil.TypicalExpenses.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.DateMatchesPredicate;
import seedu.address.model.expense.NameContainsKeywordsPredicate;
import seedu.address.model.expense.TagsMatchesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        DateMatchesPredicate firstDatePredicate =
                new DateMatchesPredicate(Arrays.asList("09-08-2020"));
        DateMatchesPredicate secondDatePredicate =
                new DateMatchesPredicate(Arrays.asList("09-08-2020"));
        TagsMatchesPredicate firstTagsPredicate =
                new TagsMatchesPredicate(Arrays.asList("tagOne", "tagThree", "tagFour"));
        TagsMatchesPredicate secondTagsPredicate =
                new TagsMatchesPredicate(Arrays.asList("tagTwo", "bye"));
        FindCommand findFirstCommand = new FindCommand(
                firstPredicate, firstDatePredicate, firstTagsPredicate);
        FindCommand findSecondCommand = new FindCommand(
                secondPredicate, secondDatePredicate, secondTagsPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(
                firstPredicate, firstDatePredicate, firstTagsPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        DateMatchesPredicate datePredicate = new DateMatchesPredicate(Arrays.asList(""));
        TagsMatchesPredicate tagsPredicate = new TagsMatchesPredicate(null);
        FindCommand command = new FindCommand(predicate, datePredicate, tagsPredicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 3);
        DateMatchesPredicate datePredicate =
                new DateMatchesPredicate(Arrays.asList("09-08-2020"));
        TagsMatchesPredicate tagsPredicate =
                new TagsMatchesPredicate(Arrays.asList("tagTwo", "bye"));
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(namePredicate, datePredicate, tagsPredicate);
        expectedModel.updateFilteredExpenseList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredExpenseList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
