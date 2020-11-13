package seedu.address.logic.commands.budget;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBudgets;

class ListExpenditureCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(TypicalBudgets.getTypicalNusave(), new UserPrefs());
        expectedModel = new ModelManager(model.getNusave(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListExpenditureCommand(), model,
                ListExpenditureCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
