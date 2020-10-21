package seedu.expense.logic.commands;

import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import org.junit.jupiter.api.Test;

import seedu.expense.model.ExpenseBook;
import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;

public class ClearCommandTest {

    @Test
    public void execute_emptyExpenseBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExpenseBook_success() {
        Model model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());
        Model expectedModel = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());
        expectedModel.setExpenseBook(new ExpenseBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
