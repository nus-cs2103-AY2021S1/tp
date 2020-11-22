package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.DINNER;
import static seedu.address.testutil.TypicalEvents.LUNCH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ModelStub;

class AddEventCommandTest {

    @Test
    void execute_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    void execute_eventAcceptedByModel_addSuccessful() throws CommandException {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validEvent), modelStub.eventsAdded);
        assertTrue(modelStub.hasEvent(validEvent));
    }

    @Test
    void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_EVENT, () ->
                addEventCommand.execute(modelStub));
    }

    @Test
    void testEquals_sameEvent_returnsTrue() {
        Event e = new EventBuilder().build();
        AddEventCommand expected = new AddEventCommand(e);
        assertEquals(expected, new AddEventCommand(e));
    }

    @Test
    void testEquals_differentEvent_returnsFalse() {
        Event e1 = new EventBuilder(LUNCH).build();
        Event e2 = new EventBuilder(DINNER).build();
        assertNotEquals(new AddEventCommand(e1), new AddEventCommand(e2));
    }

    @Test
    void testEquals_null_returnsFalse() {
        Event e = new EventBuilder().build();
        assertNotEquals(null, new AddEventCommand(e));
    }

    /**
     * A Model stub that contains a single event
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accepts the event being added.
     */
    private class ModelStubAcceptingEvent extends ModelStub {
        final List<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }
    }
}
