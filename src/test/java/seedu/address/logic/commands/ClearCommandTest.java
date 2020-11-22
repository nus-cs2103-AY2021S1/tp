package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseBook;
import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ClearCommand}.
 */
public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ExpenseModelManager();
        Model expectedModel = new ExpenseModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());
        Model expectedModel = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());
        expectedModel.setExpenseBook(new ExpenseBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
