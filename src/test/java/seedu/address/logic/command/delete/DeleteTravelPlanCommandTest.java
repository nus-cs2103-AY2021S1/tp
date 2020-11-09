package seedu.address.logic.command.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.command.CommandTestUtil.showTravelPlanAtIndex;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST_TRAVELPLAN;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_SECOND_TRAVELPLAN;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_TEN_TRAVELPLAN;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TravelPlanner;
import seedu.address.model.UserPrefs;
import seedu.address.model.travelplan.TravelPlan;

/**
 * Contains integration tests and unit tests for
 * {@code DeleteTravelPlanCommand}.
 */
public class DeleteTravelPlanCommandTest {

    private Model model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        TravelPlan travelPlanToDelete = model.getFilteredTravelPlanList().get(INDEX_FIRST_TRAVELPLAN.getZeroBased());
        DeleteTravelPlanCommand deleteTravelPlanCommand = new DeleteTravelPlanCommand(INDEX_FIRST_TRAVELPLAN);

        String expectedMessage = String.format(DeleteTravelPlanCommand.MESSAGE_DELETE_TRAVELPLAN_SUCCESS,
                travelPlanToDelete);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.deleteTravelPlan(travelPlanToDelete);

        assertCommandSuccess(deleteTravelPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTravelPlanList().size() + 1);
        DeleteTravelPlanCommand deleteTravelPlanCommand = new DeleteTravelPlanCommand(outOfBoundIndex);

        assertCommandFailure(deleteTravelPlanCommand, model, Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTravelPlanAtIndex(model, INDEX_FIRST_TRAVELPLAN);

        TravelPlan travelPlanToDelete = model.getFilteredTravelPlanList().get(INDEX_FIRST_TRAVELPLAN.getZeroBased());
        DeleteTravelPlanCommand deleteTravelPlanCommand = new DeleteTravelPlanCommand(INDEX_FIRST_TRAVELPLAN);

        String expectedMessage = String.format(DeleteTravelPlanCommand.MESSAGE_DELETE_TRAVELPLAN_SUCCESS,
                travelPlanToDelete);

        Model expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.deleteTravelPlan(travelPlanToDelete);
        showNoTravelPlan(expectedModel);

        assertCommandSuccess(deleteTravelPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTravelPlanAtIndex(model, INDEX_FIRST_TRAVELPLAN);

        Index outOfBoundIndex = INDEX_TEN_TRAVELPLAN;

        DeleteTravelPlanCommand deleteTravelPlanCommand = new DeleteTravelPlanCommand(outOfBoundIndex);

        assertCommandFailure(deleteTravelPlanCommand, model, Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTravelPlanCommand deleteFirstCommand = new DeleteTravelPlanCommand(INDEX_FIRST_TRAVELPLAN);
        DeleteTravelPlanCommand deleteSecondCommand = new DeleteTravelPlanCommand(INDEX_SECOND_TRAVELPLAN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTravelPlanCommand deleteFirstCommandCopy = new DeleteTravelPlanCommand(INDEX_FIRST_TRAVELPLAN);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different travelPlan -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTravelPlan(Model model) {
        model.updateFilteredTravelPlanList(p -> false);

        assertTrue(model.getFilteredTravelPlanList().isEmpty());
    }
}
