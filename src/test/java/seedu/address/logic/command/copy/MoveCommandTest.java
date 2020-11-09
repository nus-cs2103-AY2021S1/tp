package seedu.address.logic.command.copy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_ACTIVITY;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getInvalidDateTestTravelPlanner;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.activity.Activity;

public class MoveCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(-1);
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void execute_validIndex_success() {
        MoveCommand moveCommand = new MoveCommand(INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = String.format(MoveCommand.MESSAGE_MOVE_ACTIVITY_SUCCESS,
                INDEX_FIRST.getOneBased(), INDEX_SECOND.getOneBased());

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        Activity activityToMove = expectedModel.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());

        expectedModel.setDirectory(-1);
        expectedModel.copyActivity(activityToMove, INDEX_SECOND);
        expectedModel.deleteActivity(activityToMove);

        assertCommandSuccess(moveCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void execute_duplicateActivity_success() {
        MoveCommand moveCommand = new MoveCommand(INDEX_FIRST, INDEX_FIRST);
        assertCommandFailure(moveCommand, model, MESSAGE_DUPLICATE_ACTIVITY);
    }

    @Test
    public void execute_invalidActivityIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        MoveCommand moveCommand = new MoveCommand(outOfBoundIndex, INDEX_FIRST);

        assertCommandFailure(moveCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTravelPlanIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTravelPlanList().size() + 1);
        MoveCommand moveCommand = new MoveCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(moveCommand, model, Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDate_throwsCommandException() {
        model = new ModelManager(getInvalidDateTestTravelPlanner(), new UserPrefs());
        MoveCommand moveCommand = new MoveCommand(INDEX_FIRST, INDEX_FIRST);
        assertCommandFailure(moveCommand, model, MoveCommand.MESSAGE_DATE_NOT_IN_RANGE_ACTIVITY);
    }

    @Test
    public void execute_notWishList_throwsCommandException() {
        MoveCommand moveCommand = new MoveCommand(INDEX_FIRST, INDEX_SECOND);
        model.setDirectory(0);

        assertCommandFailure(moveCommand, model, MoveCommand.MESSAGE_NOT_WISHLIST);
    }

    @Test
    public void equals() {
        MoveCommand moveCommand = new MoveCommand(INDEX_FIRST, INDEX_FIRST);
        MoveCommand diffActivityMoveCommand = new MoveCommand(INDEX_SECOND, INDEX_FIRST);
        MoveCommand diffTravelPlanMoveCommand = new MoveCommand(INDEX_FIRST, INDEX_SECOND);
        MoveCommand bothDiffMoveCommand = new MoveCommand(INDEX_SECOND, INDEX_SECOND);

        // same object -> returns true
        assertTrue(moveCommand.equals(moveCommand));

        // same values -> returns true
        MoveCommand moveCommandCopy = new MoveCommand(INDEX_FIRST, INDEX_FIRST);
        assertTrue(moveCommand.equals(moveCommandCopy));

        // different types -> returns false
        assertFalse(moveCommand.equals(1));

        // null -> returns false
        assertFalse(moveCommand.equals(null));

        // different activity -> returns false
        assertFalse(moveCommand.equals(diffActivityMoveCommand));

        // different travel plan -> returns false
        assertFalse(moveCommand.equals(diffTravelPlanMoveCommand));

        // different activity and travel plan -> returns false
        assertFalse(moveCommand.equals(bothDiffMoveCommand));
    }
}
