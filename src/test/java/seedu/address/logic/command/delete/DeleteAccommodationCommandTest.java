package seedu.address.logic.command.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.command.CommandTestUtil.showTpoAtIndex;
import static seedu.address.logic.parser.ParserUtil.ACCOMMODATION_INDEX;
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
import seedu.address.model.accommodation.Accommodation;

/**
 * Contains integration tests and unit tests for
 * {@code DeleteAccommodationCommand}.
 */
public class DeleteAccommodationCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Accommodation accommodationToDelete = model.getFilteredAccommodationList()
                .get(INDEX_FIRST.getZeroBased());
        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(INDEX_FIRST);

        String expectedMessage = String.format(deleteAccommodationCommand.MESSAGE_DELETE_ACCOMMODATION_SUCCESS,
                accommodationToDelete);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.deleteTravelPlanObject(accommodationToDelete);

        assertCommandSuccess(deleteAccommodationCommand, model, expectedMessage, expectedModel, ACCOMMODATION_INDEX);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAccommodationList().size() + 1);
        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(outOfBoundIndex);

        assertCommandFailure(deleteAccommodationCommand, model, Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTpoAtIndex(model, INDEX_FIRST, "Accommodation");

        Accommodation accommodationToDelete = model.getFilteredAccommodationList()
                .get(INDEX_FIRST.getZeroBased());
        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteAccommodationCommand.MESSAGE_DELETE_ACCOMMODATION_SUCCESS,
                accommodationToDelete);

        Model expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.deleteTravelPlanObject(accommodationToDelete);
        showNoAccommodationList(expectedModel);

        assertCommandSuccess(deleteAccommodationCommand, model, expectedMessage, expectedModel, ACCOMMODATION_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTpoAtIndex(model, INDEX_FIRST, "Accommodation");

        Index outOfBoundIndex = INDEX_TEN;

        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(outOfBoundIndex);

        assertCommandFailure(deleteAccommodationCommand, model, Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteAccommodationCommand deleteFirstCommand = new DeleteAccommodationCommand(INDEX_FIRST);
        DeleteAccommodationCommand deleteSecondCommand = new DeleteAccommodationCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAccommodationCommand deleteFirstCommandCopy = new DeleteAccommodationCommand(INDEX_FIRST);
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
    private void showNoAccommodationList(Model model) {
        model.updateFilteredAccommodationList(p -> false);

        assertTrue(model.getFilteredAccommodationList().isEmpty());
    }
}
