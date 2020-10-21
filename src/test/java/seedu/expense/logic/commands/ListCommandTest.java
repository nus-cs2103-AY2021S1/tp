package seedu.expense.logic.commands;

import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;
import static seedu.expense.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());
        expectedModel = new ModelManager(model.getExpenseBook(), new UserPrefs(), new AliasMap());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
