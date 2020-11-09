package seedu.address.logic.command.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.command.CommandTestUtil.showTpoAtIndex;
import static seedu.address.logic.command.CommandTestUtil.showTravelPlanAtIndex;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_TEN;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TravelPlanner;
import seedu.address.model.UserPrefs;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.TravelPlanObject;

/**
 * Contains integration tests and unit tests for
 * {@code DeleteActivityCommand}.
 */
public class DeleteActivityCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        TravelPlanObject activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteActivityCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS,
                activityToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        expectedModel.setDirectory(0);
        expectedModel.deleteTravelPlanObject(activityToDelete);

        assertCommandSuccess(deleteActivityCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(outOfBoundIndex);

        assertCommandFailure(deleteActivityCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTpoAtIndex(model, INDEX_FIRST, "Activity");

        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteActivityCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS,
                activityToDelete);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());

        expectedModel.setDirectory(0);

        expectedModel.deleteTravelPlanObject(activityToDelete);
        showNoActivityList(expectedModel);

        assertCommandSuccess(deleteActivityCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTravelPlanAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_TEN;

        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(outOfBoundIndex);

        assertCommandFailure(deleteActivityCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteActivityCommand deleteFirstCommand = new DeleteActivityCommand(INDEX_FIRST);
        DeleteActivityCommand deleteSecondCommand = new DeleteActivityCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteActivityCommand deleteFirstCommandCopy = new DeleteActivityCommand(INDEX_FIRST);
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
    private void showNoActivityList(Model model) {
        model.updateFilteredActivityList(p -> false);

        assertTrue(model.getFilteredActivityList().isEmpty());
    }
}
