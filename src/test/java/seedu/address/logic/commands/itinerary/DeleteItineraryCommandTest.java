package seedu.address.logic.commands.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.itinerary.Itinerary;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteItineraryCommand}.
 */
public class DeleteItineraryCommandTest {

    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(INDEX_FIRST.getZeroBased());
        DeleteItineraryCommand deleteItineraryCommand = new DeleteItineraryCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItineraryCommand.MESSAGE_DELETE_ITINERARY_SUCCESS,
                itineraryToDelete);

        ModelManager expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(),
                new UserPrefs());
        expectedModel.deleteItinerary(itineraryToDelete);

        assertCommandSuccess(deleteItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        DeleteItineraryCommand deleteItineraryCommand = new DeleteItineraryCommand(outOfBoundIndex);

        assertCommandFailure(deleteItineraryCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showItineraryAtIndex(model, INDEX_FIRST);

        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(INDEX_FIRST.getZeroBased());
        DeleteItineraryCommand deleteItineraryCommand = new DeleteItineraryCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItineraryCommand.MESSAGE_DELETE_ITINERARY_SUCCESS,
                itineraryToDelete);

        Model expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
        expectedModel.deleteItinerary(itineraryToDelete);
        showNoItinerary(expectedModel);

        assertCommandSuccess(deleteItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showItineraryAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of itinerary list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getItineraryList().getItineraryList().size());

        DeleteItineraryCommand deleteItineraryCommand = new DeleteItineraryCommand(outOfBoundIndex);

        assertCommandFailure(deleteItineraryCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_hasCurrentItineraryBefore_noCurrentItineraryAfter() {
        // Follows execute_validIndexUnfilteredList_success() except with current itinerary
        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(INDEX_FIRST.getZeroBased());
        DeleteItineraryCommand deleteItineraryCommand = new DeleteItineraryCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItineraryCommand.MESSAGE_DELETE_ITINERARY_SUCCESS,
                itineraryToDelete);

        ModelManager expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(),
                new UserPrefs());
        expectedModel.setCurrentItinerary(null);
        expectedModel.deleteItinerary(itineraryToDelete);

        model.setCurrentItinerary(JAPAN_TRIP);

        assertCommandSuccess(deleteItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteItineraryCommand deleteFirstCommand = new DeleteItineraryCommand(INDEX_FIRST);
        DeleteItineraryCommand deleteSecondCommand = new DeleteItineraryCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteItineraryCommand deleteFirstCommandCopy = new DeleteItineraryCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no itinerary.
     */
    private void showNoItinerary(Model model) {
        model.updateFilteredItineraryList(p -> false);

        assertTrue(model.getFilteredItineraryList().isEmpty());
    }
}
