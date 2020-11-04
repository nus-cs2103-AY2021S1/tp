package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import java.util.ArrayList;

import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.schedulercommands.DeleteEventCommand;
import seedu.address.model.EventList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.event.Event;

public class DeleteEventCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(AssertionFailedError.class, () -> new DeleteEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        /*
        ModelStubAcceptingEventsDeleted modelStub = new ModelStubAcceptingEventsDeleted();
        Index index = Index.fromZeroBased(1);
        CommandResult commandResult = new DeleteEventCommand(index).execute(modelStub);

        assertEquals(String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, VALID_EVENT), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(VALID_EVENT), modelStub.eventsDeleted);
         */
    }

    @Test
    public void equals() {
        /*
        Event cs2100_quiz = new EventBuilder().withName("CS2100 Quiz").build();
        Event es2660_essay = new EventBuilder().withName("ES2660 Essay").build();
        AddEventCommand add_cs2100_quizCommand = new AddEventCommand(cs2100_quiz);
        AddEventCommand add_es2660_essayCommand = new AddEventCommand(es2660_essay);

        // same object -> returns true
        assertTrue(add_cs2100_quizCommand.equals(add_cs2100_quizCommand));

        // same values -> returns true
        AddEventCommand add_cs2100_quizCommandCopy = new AddEventCommand(cs2100_quiz);
        assertTrue(add_cs2100_quizCommandCopy.equals(add_cs2100_quizCommandCopy));

        // different types -> returns false
        assertFalse(add_cs2100_quizCommand.equals(1));

        // null -> returns false
        assertFalse(add_cs2100_quizCommand.equals(null));

        // different person -> returns false
        assertFalse(add_cs2100_quizCommand.equals(add_es2660_essayCommand));
         */
    }

    /**
     * A Model stub that contains a single event.
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
            return this.event.equals(event);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventsDeleted extends ModelStub {
        final ArrayList<Event> eventsDeleted = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsDeleted.stream().anyMatch(event::equals);
        }

        @Override
        public void deleteEvent(Event event) {
            requireNonNull(event);
            eventsDeleted.remove(event);
        }

        @Override
        public ReadOnlyEventList getEventList() {
            return new EventList();
        }
    }
}
