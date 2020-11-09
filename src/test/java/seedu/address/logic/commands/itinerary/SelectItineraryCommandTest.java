package seedu.address.logic.commands.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.itinerary.Itinerary;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code SelectItineraryCommand}.
 */
public class SelectItineraryCommandTest {

    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Itinerary itineraryToSelect = model.getFilteredItineraryList().get(INDEX_FIRST.getZeroBased());
        SelectItineraryCommand selectItineraryCommand = new SelectItineraryCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectItineraryCommand.MESSAGE_SELECT_ITINERARY_SUCCESS,
            itineraryToSelect);

        ModelManager expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(),
            new UserPrefs());
        expectedModel.setCurrentItinerary(itineraryToSelect);

        assertCommandSuccess(selectItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        SelectItineraryCommand selectItineraryCommand = new SelectItineraryCommand(outOfBoundIndex);

        assertCommandFailure(selectItineraryCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showItineraryAtIndex(model, INDEX_FIRST);

        Itinerary itineraryToSelect = model.getFilteredItineraryList().get(INDEX_FIRST.getZeroBased());
        SelectItineraryCommand selectItineraryCommand = new SelectItineraryCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectItineraryCommand.MESSAGE_SELECT_ITINERARY_SUCCESS,
            itineraryToSelect);

        Model expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
        showItineraryAtIndex(expectedModel, INDEX_FIRST);
        expectedModel.setCurrentItinerary(itineraryToSelect);

        assertCommandSuccess(selectItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showItineraryAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of trackPad list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getItineraryList().getItineraryList().size());

        SelectItineraryCommand selectItineraryCommand = new SelectItineraryCommand(outOfBoundIndex);

        assertCommandFailure(selectItineraryCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectItineraryCommand selectFirstCommand = new SelectItineraryCommand(INDEX_FIRST);
        SelectItineraryCommand selectSecondCommand = new SelectItineraryCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectItineraryCommand selectFirstCommandCopy = new SelectItineraryCommand(INDEX_FIRST);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no itinerary.
     */
    private void showNoItinerary(Model model) {
        model.updateFilteredItineraryList(p -> false);

        assertTrue(model.getFilteredItineraryList().isEmpty());
    }
}
