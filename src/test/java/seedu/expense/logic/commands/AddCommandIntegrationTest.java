package seedu.expense.logic.commands;

import static seedu.expense.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.Expense;
import seedu.expense.testutil.ExpenseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());
    }

    @Test
    public void execute_newExpense_success() {
        Expense validExpense = new ExpenseBuilder().build();

        Model expectedModel = new ModelManager(model.getExpenseBook(), new UserPrefs(), new AliasMap());
        expectedModel.addExpense(validExpense);

        assertCommandSuccess(new AddCommand(validExpense), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validExpense), expectedModel);
    }

    @Test
    public void execute_duplicateExpense_throwsCommandException() {
        Expense expenseInList = model.getExpenseBook().getExpenseList().get(0);
        assertCommandFailure(new AddCommand(expenseInList), model, AddCommand.MESSAGE_DUPLICATE_EXPENSE);
    }

}
