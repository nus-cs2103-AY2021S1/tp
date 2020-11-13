package seedu.address.logic.commands.main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;
import seedu.address.testutil.TypicalBudgets;

public class OpenBudgetCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(TypicalBudgets.getTypicalNusave(), new UserPrefs());
        expectedModel = new ModelManager(model.getNusave(), new UserPrefs());
    }

    @Test
    void equals() {

        BudgetIndex firstBudgetIndex = new BudgetIndexManager(0);
        BudgetIndex secondBudgetIndex = new BudgetIndexManager(1);
        OpenBudgetCommand firstOpenBudgetCommand = new OpenBudgetCommand(firstBudgetIndex);
        OpenBudgetCommand secondOpenBudgetCommand = new OpenBudgetCommand(secondBudgetIndex);

        // same object -> returns true
        assertTrue(firstOpenBudgetCommand.equals(firstOpenBudgetCommand));

        // same values -> returns true
        OpenBudgetCommand openBudgetCommandCopy = new OpenBudgetCommand(firstBudgetIndex);
        assertTrue(firstOpenBudgetCommand.equals(openBudgetCommandCopy));

        // different types -> returns false
        assertFalse(firstOpenBudgetCommand.equals(1));

        // null -> returns false
        assertFalse(firstOpenBudgetCommand.equals(null));

        // different command -> returns false
        assertFalse(firstOpenBudgetCommand.equals(secondOpenBudgetCommand));
    }

    @Test
    void execute_budgetIndexWithinRange_budgetOpened() {
        BudgetIndex toOpen = new BudgetIndexManager(0);
        String expectedMessage = String.format(OpenBudgetCommand.MESSAGE_SUCCESS, toOpen);
        OpenBudgetCommand command = new OpenBudgetCommand(toOpen);
        expectedModel.openBudget(toOpen);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_budgetIndexOutOfRange_exceptionThrown() {
        BudgetIndex toOpen = new BudgetIndexManager(10);
        OpenBudgetCommand command = new OpenBudgetCommand(toOpen);
        assertThrows(CommandException.class, ()-> command.execute(model));
    }
}
