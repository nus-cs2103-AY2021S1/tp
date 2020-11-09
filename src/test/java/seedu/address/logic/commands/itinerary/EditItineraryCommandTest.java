package seedu.address.logic.commands.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.model.AttractionList;
import seedu.address.model.ItineraryList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.testutil.EditItineraryDescriptorBuilder;
import seedu.address.testutil.ItineraryBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditItineraryCommand}.
 */
class EditItineraryCommandTest {
    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Itinerary itineraryToEdit = model.getFilteredItineraryList().get(0);
        Itinerary editedItinerary = new ItineraryBuilder().withDays(itineraryToEdit.getDays()).build();
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(editedItinerary).build();
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditItineraryCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setItinerary(itineraryToEdit, editedItinerary);
        assertCommandSuccess(editItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItinerary = Index.fromOneBased(model.getFilteredItineraryList().size());
        Itinerary lastItinerary = model.getFilteredItineraryList().get(indexLastItinerary.getZeroBased());

        ItineraryBuilder itineraryInList = new ItineraryBuilder(lastItinerary);
        Itinerary editedItinerary = itineraryInList.withName(VALID_NAME_PARIS_TRIP)
                .withStartDate(VALID_START_DATE_PARIS_TRIP)
                .build();

        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder()
                .withName(VALID_NAME_PARIS_TRIP)
                .withStartDate(VALID_START_DATE_PARIS_TRIP)
                .build();
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(indexLastItinerary, descriptor);

        String expectedMessage = String.format(EditItineraryCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setItinerary(lastItinerary, editedItinerary);

        assertCommandSuccess(editItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(INDEX_FIRST,
                new EditItineraryDescriptor());

        String expectedMessage = EditItineraryCommand.MESSAGE_FIELD_NOT_CHANGED;

        assertCommandFailure(editItineraryCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showItineraryAtIndex(model, INDEX_FIRST);

        Itinerary itineraryInFilteredList = model.getFilteredItineraryList()
                .get(INDEX_FIRST.getZeroBased());
        Itinerary editedItinerary =
                new ItineraryBuilder(itineraryInFilteredList).withName(VALID_NAME_PARIS_TRIP).build();
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(INDEX_FIRST,
                new EditItineraryDescriptorBuilder().withName(VALID_NAME_PARIS_TRIP).build());

        String expectedMessage = String.format(EditItineraryCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(model.getAttractionList(), new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setItinerary(model.getFilteredItineraryList().get(0), editedItinerary);

        assertCommandSuccess(editItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_hasCurrentItineraryBefore_noCurrentItineraryAfter() {
        // Follows execute_allFieldsSpecifiedUnfilteredList_success() except with current itinerary
        Itinerary itineraryToEdit = model.getFilteredItineraryList().get(0);
        Itinerary editedItinerary = new ItineraryBuilder().withDays(itineraryToEdit.getDays()).build();
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(editedItinerary).build();
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditItineraryCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setItinerary(itineraryToEdit, editedItinerary);
        expectedModel.setCurrentItinerary(null);
        model.setCurrentItinerary(PARIS_TRIP);
        assertCommandSuccess(editItineraryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItineraryUnfilteredList_failure() {
        Itinerary firstItinerary = model.getFilteredItineraryList().get(INDEX_FIRST.getZeroBased());
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(firstItinerary).build();
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editItineraryCommand, model, EditItineraryCommand.MESSAGE_DUPLICATE_ITINERARY);
    }

    @Test
    public void execute_duplicateItineraryFilteredList_failure() {
        showItineraryAtIndex(model, INDEX_FIRST);

        // edit itinerary in filtered list into a duplicate in ItineraryList
        Itinerary itineraryInList = model.getItineraryList()
                .getItineraryList().get(INDEX_SECOND.getZeroBased());
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(INDEX_FIRST,
                new EditItineraryDescriptorBuilder(itineraryInList).build());

        assertCommandFailure(editItineraryCommand, model, EditItineraryCommand.MESSAGE_DUPLICATE_ITINERARY);
    }

    @Test
    public void execute_invalidItineraryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder()
                .withName(VALID_NAME_PARIS_TRIP).build();
        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editItineraryCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of ItineraryList list
     */
    @Test
    public void execute_invalidItineraryIndexFilteredList_failure() {
        showItineraryAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of the ItineraryList list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getItineraryList().getItineraryList().size());

        EditItineraryCommand editItineraryCommand = new EditItineraryCommand(outOfBoundIndex,
                new EditItineraryDescriptorBuilder().withName(VALID_NAME_PARIS_TRIP).build());

        assertCommandFailure(editItineraryCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditItineraryCommand standardCommand = new EditItineraryCommand(INDEX_FIRST, DESC_PARIS_TRIP);

        // same values -> returns true
        EditItineraryDescriptor copyDescriptor = new EditItineraryDescriptor(DESC_PARIS_TRIP);
        EditItineraryCommand commandWithSameValues = new EditItineraryCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearItineraryCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditItineraryCommand(INDEX_SECOND, DESC_PARIS_TRIP)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditItineraryCommand(INDEX_FIRST, DESC_JAPAN_TRIP)));
    }
}
