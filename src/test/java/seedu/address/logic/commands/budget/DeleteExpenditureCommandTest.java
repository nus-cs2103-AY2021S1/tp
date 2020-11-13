package seedu.address.logic.commands.budget;

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
import seedu.address.state.expenditureindex.ExpenditureIndex;
import seedu.address.state.expenditureindex.ExpenditureIndexManager;

class DeleteExpenditureCommandTest {

    private Model model = new ModelManager(getTypicalNusave(), new UserPrefs());

    @Test
    void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->new DeleteExpenditureCommand(null));
    }

    @Test
    void execute_validIndex_deleteSuccessful() throws CommandException {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        model.openBudget(budgetIndex);
        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(0);
        DeleteExpenditureCommand deleteExpenditureCommand = new DeleteExpenditureCommand(expenditureIndex);

        String expectedMessage = DeleteExpenditureCommand.MESSAGE_DELETE_EXPENDITURE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        CommandResult commandResult = deleteExpenditureCommand.execute(model);

        assertEquals(expectedResult, commandResult);
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        model.openBudget(budgetIndex);
        ExpenditureIndex outOfBounds = new ExpenditureIndexManager(model.getFilteredRenderableList().size() + 1);
        DeleteExpenditureCommand deleteExpenditureCommand = new DeleteExpenditureCommand(outOfBounds);

        assertThrows(CommandException.class, () -> deleteExpenditureCommand.execute(model));
    }

    @Test
    void execute_nullModel_throwsNullPointerException() {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        model.openBudget(budgetIndex);
        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(0);
        DeleteExpenditureCommand deleteExpenditureCommand = new DeleteExpenditureCommand(expenditureIndex);

        assertThrows(NullPointerException.class, () -> deleteExpenditureCommand.execute(null));
    }
}
