package seedu.address.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BREAKFAST;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAKFAST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BREAKFAST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureEvent;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Calendar;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.association.FauxPerson;
import seedu.address.testutil.AttendeesBuilder;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditEventCommand.
 */
public class EditEventCommandTest {

    private Model model = new ModelManagerBuilder().withAddressBook(getTypicalAddressBook())
            .withCalendar(getTypicalCalendar()).build();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Set<FauxPerson> attendees = new AttendeesBuilder()
                .withPerson(model.getSortedFilteredPersonList().get(0))
                .withPerson(model.getSortedFilteredPersonList().get(1)).build();
        Event editedEvent = new EventBuilder().withAttendees(attendees).build();
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent)
                .setPersonsToAdd(0, 1).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManagerBuilder()
                .withAddressBook(model.getAddressBook())
                .withCalendar(new Calendar(model.getCalendar())).build();
        expectedModel.setEvent(model.getSortedFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getSortedFilteredEventList().size());
        Event lastEvent = model.getSortedFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withDescription(
                VALID_DESCRIPTION_BREAKFAST).withTime(VALID_TIME_BREAKFAST).build();

        EditEventCommand.EditEventDescriptor descriptor =
                new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAKFAST)
                        .withTime(VALID_TIME_BREAKFAST).build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManagerBuilder().withAddressBook(model.getAddressBook())
                .withCalendar(new Calendar(model.getCalendar())).build();
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditEventCommand editEventCommand =
                new EditEventCommand(INDEX_FIRST_EVENT, new EditEventCommand.EditEventDescriptor());
        Event editedEvent = model.getSortedFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManagerBuilder().withAddressBook(model.getAddressBook())
                .withCalendar(new Calendar(model.getCalendar())).build();

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getSortedFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withDescription(VALID_DESCRIPTION_BREAKFAST)
                .withAttendees(new AttendeesBuilder().withPerson(model.getSortedFilteredPersonList().get(0)).build())
                .build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                        new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAKFAST)
                                .setPersonsToAdd(0).build());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManagerBuilder()
                .withAddressBook(model.getAddressBook()).withCalendar(new Calendar(model.getCalendar())).build();
        expectedModel.setEvent(model.getSortedFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getSortedFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailureEvent(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        // edit event in filtered list into a duplicate in address book
        Event eventInList = model.getCalendar().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder(eventInList).build());

        assertCommandFailureEvent(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredEventList().size() + 1);
        EditEventCommand.EditEventDescriptor descriptor =
                new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAKFAST).build();
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailureEvent(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCalendar().getEventList().size());

        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAKFAST).build());

        assertCommandFailureEvent(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_LUNCH);

        // same values -> returns true
        EditEventCommand.EditEventDescriptor copyDescriptor = new EditEventCommand.EditEventDescriptor(DESC_LUNCH);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearEventCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, DESC_LUNCH)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT, DESC_BREAKFAST)));
    }

}
