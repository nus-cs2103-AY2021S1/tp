package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.AmountComparator;
import seedu.expense.model.expense.DateComparator;
import seedu.expense.model.expense.DateMatchesPredicate;
import seedu.expense.model.expense.DescriptionComparator;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());
    private Model expectedModel = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SwitchCommand(null));
    }

    @Test
    public void execute_singleKeyword_sortedExpenses() {
        DateComparator dateComparator =
                new DateComparator(false, false, -1);
        AmountComparator amountComparator =
                new AmountComparator(false, false, -1);
        DescriptionComparator descriptionComparator = new DescriptionComparator(true, true, 0);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "["
                + descriptionComparator.toString() + "]");
        SortCommand command = new SortCommand(dateComparator, amountComparator, descriptionComparator);
        expectedModel.sortExpenseList(descriptionComparator.reversed());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        for (int i = 0; i < expectedModel.getFilteredExpenseList().size(); i++) {
            assertEquals(expectedModel.getFilteredExpenseList().get(i), model.getFilteredExpenseList().get(i));
        }
    }

    @Test
    public void execute_multipleKeywords_expensesSortedByPriority() {
        DateComparator dateComparator =
                new DateComparator(true, false, 0);
        AmountComparator amountComparator =
                new AmountComparator(false, false, -1);
        DescriptionComparator descriptionComparator = new DescriptionComparator(true, true, 1);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                "[" + dateComparator.toString() + ", " + descriptionComparator.toString() + "]");
        SortCommand command = new SortCommand(dateComparator, amountComparator, descriptionComparator);
        expectedModel.sortExpenseList(dateComparator.thenComparing(descriptionComparator.reversed()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        for (int i = 0; i < expectedModel.getFilteredExpenseList().size(); i++) {
            assertEquals(expectedModel.getFilteredExpenseList().get(i), model.getFilteredExpenseList().get(i));
        }
    }

    @Test
    public void execute_singleKeywordAfterFilter_sortedFilteredExpenses() {
        model.updateFilteredExpenseList(new DateMatchesPredicate(Collections.singletonList("29-06-2020")));
        expectedModel.updateFilteredExpenseList(new DateMatchesPredicate(Collections.singletonList("29-06-2020")));
        DateComparator dateComparator =
                new DateComparator(false, false, -1);
        AmountComparator amountComparator =
                new AmountComparator(false, false, -1);
        DescriptionComparator descriptionComparator = new DescriptionComparator(true, true, 0);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "["
                + descriptionComparator.toString() + "]");
        SortCommand command = new SortCommand(dateComparator, amountComparator, descriptionComparator);
        expectedModel.sortExpenseList(descriptionComparator.reversed());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        for (int i = 0; i < expectedModel.getFilteredExpenseList().size(); i++) {
            assertEquals(expectedModel.getFilteredExpenseList().get(i), model.getFilteredExpenseList().get(i));
        }
    }

}
