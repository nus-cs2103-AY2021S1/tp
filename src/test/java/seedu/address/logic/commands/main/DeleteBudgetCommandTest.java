package seedu.address.logic.commands.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;

class DeleteBudgetCommandTest {

    private Model model = new ModelManager(getTypicalNusave(), new UserPrefs());

    @Test
    void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteBudgetCommand(null));
    }

    @Test
    void execute_validIndex_deleteSuccessful() throws CommandException {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        DeleteBudgetCommand deleteBudgetCommand = new DeleteBudgetCommand(budgetIndex);

        String expectedMessage = DeleteBudgetCommand.MESSAGE_DELETE_BUDGET_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);
        CommandResult commandResult = deleteBudgetCommand.execute(model);

        assertEquals(expectedResult, commandResult);
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        BudgetIndex outOfBounds = new BudgetIndexManager(model.getNusave().getBudgetListAsObservableList().size() + 1);
        DeleteBudgetCommand deleteBudgetCommand = new DeleteBudgetCommand(outOfBounds);

        assertThrows(CommandException.class, () -> deleteBudgetCommand.execute(model));

    }

    @Test
    void execute_nullModel_throwsNullPointerException() {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        DeleteBudgetCommand deleteBudgetCommand = new DeleteBudgetCommand(budgetIndex);

        assertThrows(NullPointerException.class, () -> deleteBudgetCommand.execute(null));
    }
}
