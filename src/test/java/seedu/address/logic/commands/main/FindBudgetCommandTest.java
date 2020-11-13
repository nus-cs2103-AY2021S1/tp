package seedu.address.logic.commands.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.budget.Name;
import seedu.address.testutil.TypicalBudget;
import seedu.address.testutil.TypicalBudgets;

class FindBudgetCommandTest {
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
        FindBudgetCommand findFirstCommand = new FindBudgetCommand(firstPredicate);
        FindBudgetCommand findSecondCommand = new FindBudgetCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBudgetCommand findFirstCommandCopy = new FindBudgetCommand(firstPredicate);
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
        String keyword = TypicalBudget.SUBWAY_NAME;
        String expectedMessage = String.format(FindBudgetCommand.MESSAGE_SUCCESS, keyword);
        FindBudgetCommand command = new FindBudgetCommand(new Name(keyword));
        expectedModel.updateFilteredRenderableList(x -> x.contains(keyword));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_nonMatchingKeyword_noBudgetFound() {
        String keyword = "No Budgets will match this search";
        String expectedMessage = String.format(FindBudgetCommand.MESSAGE_NO_BUDGETS_FOUND, keyword);
        FindBudgetCommand command = new FindBudgetCommand(new Name(keyword));
        expectedModel.updateFilteredRenderableList(x -> x.contains(keyword));
        assertCommandFailure(command, expectedModel, expectedMessage);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredRenderableList());
    }

}
