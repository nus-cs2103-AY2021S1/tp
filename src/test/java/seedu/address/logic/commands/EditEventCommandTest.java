package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;

class EditEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void testEquals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_LUNCH);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_LUNCH);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ListCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditEventCommand(INDEX_SECOND_EVENT, DESC_LUNCH));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditEventCommand(INDEX_FIRST_EVENT, DESC_DINNER));
    }

    @Test
    void execute_allFieldsSpecified_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        // setup the expected edited model
        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_someFieldsSpecified_success() {
        Index eventToEdit = INDEX_SECOND_EVENT;
        Event event = model.getEventList().get(eventToEdit.getZeroBased());

        // setup the expected edited model
        EventBuilder eventInList = new EventBuilder(event);
        Event editedEvent = eventInList.withEventName(VALID_NAME_LUNCH).build();
        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(event, editedEvent);

        // setup the command
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_LUNCH).build();
        EditEventCommand editEventCommand = new EditEventCommand(eventToEdit, descriptor);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_duplicateEvent_failure() {
        Event eventInList = model.getAddressBook().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(eventInList).build();
        EditEventCommand command = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        assertCommandFailure(command, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_LUNCH).build();
        EditEventCommand editCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }
}
