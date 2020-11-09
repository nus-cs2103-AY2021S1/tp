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
import seedu.address.model.AttractionList;
import seedu.address.model.ItineraryList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Visited;


/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MarkVisitedAttractionCommand}.
 */
public class MarkVisitedAttractionCommandTest {

    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Attraction attractionToMarkVisited = model.getFilteredAttractionList().get(INDEX_FIRST.getZeroBased());
        MarkVisitedAttractionCommand markVisitedAttractionCommand = new MarkVisitedAttractionCommand(INDEX_FIRST);

        Attraction markedVisitedAttraction = new Attraction(attractionToMarkVisited.getName(),
                attractionToMarkVisited.getPhone(), attractionToMarkVisited.getEmail(),
                attractionToMarkVisited.getAddress(), attractionToMarkVisited.getDescription(),
                attractionToMarkVisited.getLocation(), attractionToMarkVisited.getOpeningHours(),
                attractionToMarkVisited.getPriceRange(), attractionToMarkVisited.getRating(),
                new Visited("TRUE"), attractionToMarkVisited.getTags());

        String expectedMessage = String.format(
                MarkVisitedAttractionCommand.MESSAGE_MARKVISITED_ATTRACTION_SUCCESS, markedVisitedAttraction);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setAttraction(attractionToMarkVisited, markedVisitedAttraction);

        assertCommandSuccess(markVisitedAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAttractionList().size() + 1);
        MarkVisitedAttractionCommand markVisitedAttractionCommand = new MarkVisitedAttractionCommand(outOfBoundIndex);

        assertCommandFailure(markVisitedAttractionCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAttractionAtIndex(model, INDEX_FIRST);

        Attraction attractionToMarkVisited = model.getFilteredAttractionList().get(INDEX_FIRST.getZeroBased());
        MarkVisitedAttractionCommand markVisitedAttractionCommand = new MarkVisitedAttractionCommand(INDEX_FIRST);

        Attraction markedVisitedAttraction = new Attraction(attractionToMarkVisited.getName(),
                attractionToMarkVisited.getPhone(), attractionToMarkVisited.getEmail(),
                attractionToMarkVisited.getAddress(), attractionToMarkVisited.getDescription(),
                attractionToMarkVisited.getLocation(), attractionToMarkVisited.getOpeningHours(),
                attractionToMarkVisited.getPriceRange(), attractionToMarkVisited.getRating(),
                new Visited("TRUE"), attractionToMarkVisited.getTags());

        String expectedMessage = String.format(
                MarkVisitedAttractionCommand.MESSAGE_MARKVISITED_ATTRACTION_SUCCESS, markedVisitedAttraction);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setAttraction(attractionToMarkVisited, markedVisitedAttraction);

        assertCommandSuccess(markVisitedAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAttractionAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of trackPad list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAttractionList().getAttractionList().size());

        MarkVisitedAttractionCommand markVisitedAttractionCommand = new MarkVisitedAttractionCommand(outOfBoundIndex);

        assertCommandFailure(markVisitedAttractionCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkVisitedAttractionCommand markVisitedFirstCommand = new MarkVisitedAttractionCommand(INDEX_FIRST);
        MarkVisitedAttractionCommand markVisitedSecondCommand = new MarkVisitedAttractionCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markVisitedFirstCommand.equals(markVisitedFirstCommand));

        // same values -> returns true
        MarkVisitedAttractionCommand markVisitedFirstCommandCopy = new MarkVisitedAttractionCommand(INDEX_FIRST);
        assertTrue(markVisitedFirstCommand.equals(markVisitedFirstCommandCopy));

        // different types -> returns false
        assertFalse(markVisitedFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markVisitedFirstCommand.equals(null));

        // different attraction -> returns false
        assertFalse(markVisitedFirstCommand.equals(markVisitedSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAttraction(Model model) {
        model.updateFilteredAttractionList(p -> false);

        assertTrue(model.getFilteredAttractionList().isEmpty());
    }
}
