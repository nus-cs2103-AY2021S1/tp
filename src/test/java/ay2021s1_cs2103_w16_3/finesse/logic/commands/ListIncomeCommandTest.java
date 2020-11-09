package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2021s1_cs2103_w16_3.finesse.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalFinanceTracker;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.getTypicalIncomes;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ModelManager;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListIncomeCommand.
 */
public class ListIncomeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinanceTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getFinanceTracker(), new UserPrefs());
    }

    @Test
    public void execute_showAllIncomes() {
        expectedModel.updateFilteredIncomeList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        assertCommandSuccess(new ListIncomeCommand(), model,
                new CommandResult(ListIncomeCommand.MESSAGE_SUCCESS, Tab.INCOME), expectedModel);
        assertEquals(getTypicalIncomes().size(), model.getFilteredIncomeList().size());
    }

}
