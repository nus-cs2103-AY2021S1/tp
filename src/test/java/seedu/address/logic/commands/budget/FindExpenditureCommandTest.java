package seedu.address.logic.commands.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expenditure.Name;
import seedu.address.state.Page;
import seedu.address.state.budgetindex.BudgetIndexManager;
import seedu.address.testutil.TypicalBudgets;
import seedu.address.testutil.TypicalExpenditure;

class FindExpenditureCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(TypicalBudgets.getTypicalNusave(), new UserPrefs());
        expectedModel = new ModelManager(model.getNusave(), new UserPrefs());
    }

    @Test
    void equals() {

        Name firstPredicate = new Name("first");
        Name secondPredicate = new Name("second");
        FindExpenditureCommand findFirstCommand = new FindExpenditureCommand(firstPredicate);
        FindExpenditureCommand findSecondCommand = new FindExpenditureCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindExpenditureCommand findFirstCommandCopy = new FindExpenditureCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    void execute_matchingKeyword_oneBudgetFound() {
        String keyword = TypicalExpenditure.SUBWAY_SOUP_NAME;
        model.openBudget(new BudgetIndexManager(0));
        model.setPage(Page.BUDGET);
        expectedModel.openBudget(new BudgetIndexManager(0));
        expectedModel.setPage(Page.BUDGET);
        String expectedMessage = String.format(FindExpenditureCommand.MESSAGE_SUCCESS, keyword);
        FindExpenditureCommand command = new FindExpenditureCommand(new Name(keyword));
        expectedModel.updateFilteredRenderableList(x -> x.contains(keyword));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_nonMatchingKeyword_noExpenditureFound() {
        String keyword = "No Expenditure will match this search";
        model.openBudget(new BudgetIndexManager(0));
        model.setPage(Page.BUDGET);
        expectedModel.openBudget(new BudgetIndexManager(0));
        expectedModel.setPage(Page.BUDGET);
        String expectedMessage = String.format(FindExpenditureCommand.MESSAGE_NO_EXPENDITURES_FOUND, keyword);
        FindExpenditureCommand command = new FindExpenditureCommand(new Name(keyword));
        expectedModel.updateFilteredRenderableList(x -> x.contains(keyword));
        assertEquals(Collections.emptyList(), expectedModel.getFilteredRenderableList());
    }

}
