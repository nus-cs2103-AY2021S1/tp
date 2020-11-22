package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ListExpenseCommand}.
 */

//@author BILLXYR
public class ListExpenseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());
        expectedModel = new ExpenseModelManager(model.getExpenseBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListExpenseCommand(), model, ListExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
//@author
