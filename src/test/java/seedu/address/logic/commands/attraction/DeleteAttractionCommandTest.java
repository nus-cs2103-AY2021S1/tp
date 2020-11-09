package seedu.address.logic.commands.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAttractionAtIndex;
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
import seedu.address.model.attraction.Attraction;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteAttractionCommand}.
 */
public class DeleteAttractionCommandTest {

    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Attraction attractionToDelete = model.getFilteredAttractionList().get(INDEX_FIRST.getZeroBased());
        DeleteAttractionCommand deleteAttractionCommand = new DeleteAttractionCommand(INDEX_FIRST);

        String expectedMessage = String.format(
                DeleteAttractionCommand.MESSAGE_DELETE_ATTRACTION_SUCCESS, attractionToDelete);

        ModelManager expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(),
                new UserPrefs());
        expectedModel.deleteAttraction(attractionToDelete);

        assertCommandSuccess(deleteAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAttractionList().size() + 1);
        DeleteAttractionCommand deleteAttractionCommand = new DeleteAttractionCommand(outOfBoundIndex);

        assertCommandFailure(deleteAttractionCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAttractionAtIndex(model, INDEX_FIRST);

        Attraction attractionToDelete = model.getFilteredAttractionList().get(INDEX_FIRST.getZeroBased());
        DeleteAttractionCommand deleteAttractionCommand = new DeleteAttractionCommand(INDEX_FIRST);

        String expectedMessage = String.format(
                DeleteAttractionCommand.MESSAGE_DELETE_ATTRACTION_SUCCESS, attractionToDelete);

        Model expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
        expectedModel.deleteAttraction(attractionToDelete);
        showNoAttraction(expectedModel);

        assertCommandSuccess(deleteAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAttractionAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of attraction list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAttractionList().getAttractionList().size());

        DeleteAttractionCommand deleteAttractionCommand = new DeleteAttractionCommand(outOfBoundIndex);

        assertCommandFailure(deleteAttractionCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteAttractionCommand deleteFirstCommand = new DeleteAttractionCommand(INDEX_FIRST);
        DeleteAttractionCommand deleteSecondCommand = new DeleteAttractionCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAttractionCommand deleteFirstCommandCopy = new DeleteAttractionCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different attraction -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAttraction(Model model) {
        model.updateFilteredAttractionList(p -> false);

        assertTrue(model.getFilteredAttractionList().isEmpty());
    }
}
