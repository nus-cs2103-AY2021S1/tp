package seedu.address.logic.command.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.command.CommandTestUtil.showTpoAtIndex;
import static seedu.address.logic.parser.ParserUtil.FRIEND_INDEX;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_TEN;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TravelPlanner;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;

/**
 * Contains integration tests and unit tests for
 * {@code DeleteFriendCommand}.
 */
public class DeleteFriendCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Friend friendToDelete = model.getFilteredFriendList().get(INDEX_FIRST.getZeroBased());
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFriendCommand.MESSAGE_DELETE_FRIEND_SUCCESS,
                friendToDelete);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.deleteTravelPlanObject(friendToDelete);

        assertCommandSuccess(deleteFriendCommand, model, expectedMessage, expectedModel, FRIEND_INDEX);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFriendList().size() + 1);
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(outOfBoundIndex);

        assertCommandFailure(deleteFriendCommand, model, Messages.MESSAGE_INVALID_FRIEND_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTpoAtIndex(model, INDEX_FIRST, "Friend");

        Friend friendToDelete = model.getFilteredFriendList().get(INDEX_FIRST.getZeroBased());
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFriendCommand.MESSAGE_DELETE_FRIEND_SUCCESS,
                friendToDelete);

        Model expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.deleteTravelPlanObject(friendToDelete);
        showNoFriendList(expectedModel);

        assertCommandSuccess(deleteFriendCommand, model, expectedMessage, expectedModel, FRIEND_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTpoAtIndex(model, INDEX_FIRST, "Friend");

        Index outOfBoundIndex = INDEX_TEN;

        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(outOfBoundIndex);

        assertCommandFailure(deleteFriendCommand, model, Messages.MESSAGE_INVALID_FRIEND_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteFriendCommand deleteFirstCommand = new DeleteFriendCommand(INDEX_FIRST);
        DeleteFriendCommand deleteSecondCommand = new DeleteFriendCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFriendCommand deleteFirstCommandCopy = new DeleteFriendCommand(INDEX_FIRST);
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
    private void showNoFriendList(Model model) {
        model.updateFilteredFriendList(p -> false);

        assertTrue(model.getFilteredFriendList().isEmpty());
    }
}
