package seedu.address.logic.commands.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
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
import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.model.AttractionList;
import seedu.address.model.ItineraryList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditAttractionCommand}.
 */
public class EditAttractionCommandTest {

    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Attraction editedAttraction = new AttractionBuilder().build();
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder(editedAttraction).build();
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditAttractionCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, editedAttraction);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setAttraction(model.getFilteredAttractionList().get(0), editedAttraction);
        assertCommandSuccess(editAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAttraction = Index.fromOneBased(model.getFilteredAttractionList().size());
        Attraction lastAttraction = model.getFilteredAttractionList().get(indexLastAttraction.getZeroBased());

        AttractionBuilder attractionInList = new AttractionBuilder(lastAttraction);
        Attraction editedAttraction = attractionInList.withName(VALID_NAME_MBS).withPhone(VALID_PHONE_MBS)
                .withTags(VALID_TAG_SIGHTSEEING).build();

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_MBS)
                .withPhone(VALID_PHONE_MBS).withTags(VALID_TAG_SIGHTSEEING).build();
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(indexLastAttraction, descriptor);

        String expectedMessage = String.format(EditAttractionCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, editedAttraction);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
                new ItineraryList(model.getItineraryList()),
                new UserPrefs());
        expectedModel.setAttraction(lastAttraction, editedAttraction);

        assertCommandSuccess(editAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(INDEX_FIRST,
                new EditAttractionDescriptor());

        String expectedMessage = EditAttractionCommand.MESSAGE_FIELD_NOT_CHANGED;

        assertCommandFailure(editAttractionCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showAttractionAtIndex(model, INDEX_FIRST);

        Attraction attractionInFilteredList = model.getFilteredAttractionList()
                .get(INDEX_FIRST.getZeroBased());
        Attraction editedAttraction = new AttractionBuilder(attractionInFilteredList).withName(VALID_NAME_MBS).build();
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(INDEX_FIRST,
                new EditAttractionDescriptorBuilder().withName(VALID_NAME_MBS).build());

        String expectedMessage = String.format(EditAttractionCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, editedAttraction);

        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()), model.getItineraryList(),
                new UserPrefs());
        expectedModel.setAttraction(model.getFilteredAttractionList().get(0), editedAttraction);

        assertCommandSuccess(editAttractionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAttractionUnfilteredList_failure() {
        Attraction firstAttraction = model.getFilteredAttractionList().get(INDEX_FIRST.getZeroBased());
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder(firstAttraction).build();
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editAttractionCommand, model, EditAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION);
    }

    @Test
    public void execute_duplicateAttractionFilteredList_failure() {
        showAttractionAtIndex(model, INDEX_FIRST);

        // edit attraction in filtered list into a duplicate in AttractionList
        Attraction attractionInList = model.getAttractionList()
                .getAttractionList().get(INDEX_SECOND.getZeroBased());
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(INDEX_FIRST,
                new EditAttractionDescriptorBuilder(attractionInList).build());

        assertCommandFailure(editAttractionCommand, model, EditAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION);
    }

    @Test
    public void execute_invalidAttractionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAttractionList().size() + 1);
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_MBS).build();
        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAttractionCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of AttractionList list
     */
    @Test
    public void execute_invalidAttractionIndexFilteredList_failure() {
        showAttractionAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of the AttractionList list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAttractionList().getAttractionList().size());

        EditAttractionCommand editAttractionCommand = new EditAttractionCommand(outOfBoundIndex,
                new EditAttractionDescriptorBuilder().withName(VALID_NAME_MBS).build());

        assertCommandFailure(editAttractionCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditAttractionCommand standardCommand = new EditAttractionCommand(INDEX_FIRST, DESC_EIFFEL);

        // same values -> returns true
        EditAttractionDescriptor copyDescriptor = new EditAttractionDescriptor(DESC_EIFFEL);
        EditAttractionCommand commandWithSameValues = new EditAttractionCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAttractionCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAttractionCommand(INDEX_SECOND, DESC_EIFFEL)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAttractionCommand(INDEX_FIRST, DESC_MBS)));
    }

}
