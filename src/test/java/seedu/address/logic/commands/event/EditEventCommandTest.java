package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.logic.commands.schedulercommands.EditEventCommand.EditEventDescriptor;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.event.EventUtil.DEFAULT_EVENT;
import static seedu.address.testutil.event.EventUtil.VALID_DATE;
import static seedu.address.testutil.event.EventUtil.VALID_DATE_STRING;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;
import static seedu.address.testutil.event.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.event.TypicalIndexes.INDEX_SECOND_EVENT;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.schedulercommands.EditEventCommand;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.testutil.event.EditEventDescriptorBuilder;
import seedu.address.testutil.event.EventBuilder;

public class EditEventCommandTest {

    private Model model = new ModelStubAlwaysAcceptsEditEvent();

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        EditEventDescriptor validDescriptor = new EditEventDescriptor();
        assertThrows(NullPointerException.class, () -> new EditEventCommand(null, validDescriptor));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_EVENT;
        assertThrows(NullPointerException.class, () -> new EditContactCommand(validIndex, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Index index = INDEX_FIRST_EVENT;
        EditEventDescriptor descriptor = new EditEventDescriptor();
        EditEventCommand command = new EditEventCommand(index, descriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_editSuccess() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelStubAlwaysAcceptsEditEvent();
        model.addEvent(VALID_EVENT);
        expectedModel.addEvent(editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_noFieldSpecifiedUnfilteredList_editFailure() {
        EditEventCommand editContactCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptor());
        model.addEvent(VALID_EVENT);
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        String expectedMessage = EditEventCommand.MESSAGE_DUPLICATE_EVENT;
        assertCommandFailure(editContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredEventList_editSuccess() {
        model.addEvent(VALID_EVENT);
        // update Event filtered list to contain only a single Event.
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withName("Homework").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName("Homework")
                .withDate(VALID_DATE_STRING).build();

        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelStubAlwaysAcceptsEditEvent();
        expectedModel.addEvent(VALID_EVENT);

        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_editFailure() {
        Index firstIndex = INDEX_FIRST_EVENT;
        model.addEvent(VALID_EVENT);
        model.addEvent(DEFAULT_EVENT);
        Event firstEvent = model.getFilteredEventList().get(firstIndex.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);
        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_editFailure() {
        model.addEvent(VALID_EVENT);
        model.addEvent(new Event(new EventName("homework"), VALID_DATE));
        // update Contact filtered list to contain only a single Contact
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        int secondEventIndex = INDEX_SECOND_EVENT.getZeroBased();

        // edit contact in filtered list into a duplicate in the contact list
        Event secondEventInList = model.getEventList().getEventList().get(secondEventIndex);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(secondEventInList).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_editFailure() {
        int outOfBoundIndex = model.getFilteredEventList().size() + 1;
        Index invalidIndex = Index.fromOneBased(outOfBoundIndex);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_1).build();
        EditEventCommand editEventCommand = new EditEventCommand(invalidIndex, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of the event list.
     */
    @Test
    public void execute_invalidEventIndexFilteredList_editFailure() {
        model.addEvent(VALID_EVENT);
        model.addEvent(DEFAULT_EVENT);
        // update Contact filtered list to contain only a single Contact
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;

        // ensures that outOfBoundIndex is still in bounds of contact list
        int eventListSize = model.getEventList().getEventList().size();
        assertTrue(outOfBoundIndex.getZeroBased() < eventListSize);

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName("assignment").build();
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName("assignment").build();
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        // same index and descriptor -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(descriptor);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different type -> returns false
        assertFalse(standardCommand.equals(20));

        // different command types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index, same descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, descriptor)));

        // different descriptor, same index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().build())));
    }

    private class ModelStubAlwaysAcceptsEditEvent extends ModelStub {
        private final EventList editedEvents = new EventList();
        private final FilteredList<Event> filteredEvents = new FilteredList<Event>(editedEvents.getEventList());

        @Override
        public EventList getEventList() {
            return editedEvents;
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            return filteredEvents;
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            editedEvents.addEvent(event);
        }

        @Override
        public boolean hasEvent(Event event) {
            return editedEvents.hasEvent(event);
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            editedEvents.setEvent(target, editedEvent);
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            filteredEvents.setPredicate(predicate);
        }

        @Override
        public void commitEventList() {}

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (other instanceof ModelStubAlwaysAcceptsEditEvent) {
                return this.editedEvents.equals(((ModelStubAlwaysAcceptsEditEvent) other).editedEvents);
            } else {
                return false;
            }
        }
    }
}
