package seedu.address.logic.commands.budget;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBudgets;

public class CloseBudgetCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(TypicalBudgets.getTypicalNusave(), new UserPrefs());
        expectedModel = new ModelManager(model.getNusave(), new UserPrefs());
    }

    @Test
    void execute_budgetIndexWithinRange_budgetOpened() {
        String expectedMessage = String.format(CloseBudgetCommand.MESSAGE_SUCCESS);
        CloseBudgetCommand command = new CloseBudgetCommand();
        expectedModel.closeBudget();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
