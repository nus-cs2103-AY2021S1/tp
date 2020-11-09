package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalExpenses;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListExpenseCommand.
 */
public class ListExpenseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
    }

    @Test
    public void execute_showsAllExpenses() {
        expectedModel.updateFilteredExpenseList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        assertCommandSuccess(new ListExpenseCommand(), model,
                new CommandResult(ListExpenseCommand.MESSAGE_SUCCESS, Tab.EXPENSES), expectedModel);
        assertEquals(getTypicalExpenses().size(), model.getFilteredExpenseList().size());
    }

}
