package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.logic.commands.schedulercommands.DeleteEventCommand;
import seedu.address.model.EventList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.event.Event;
import seedu.address.testutil.event.EventBuilder;

public class DeleteEventCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(AssertionError.class, () -> new DeleteEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {

        ModelStubAcceptingEventsDeleted modelStub = new ModelStubAcceptingEventsDeleted();
        modelStub.addEvent(VALID_EVENT);
        Index index = Index.fromOneBased(1);
        CommandResult commandResult = new DeleteEventCommand(index).execute(modelStub);

        assertEquals(String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, VALID_EVENT),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.eventsDeleted);

    }

    @Test
    public void equals() throws CommandException {
        ModelStubAcceptingEventsDeleted modelStub = new ModelStubAcceptingEventsDeleted();
        Event cs2100Quiz = new EventBuilder().withName("CS2100 Quiz").build();
        Event es2660Essay = new EventBuilder().withName("ES2660 Essay").build();
        new AddEventCommand(cs2100Quiz).execute(modelStub);
        new AddEventCommand(es2660Essay).execute(modelStub);
        DeleteEventCommand deleteCommandCs2100 = new DeleteEventCommand(Index.fromOneBased(1));
        DeleteEventCommand deleteCommandEs2660 = new DeleteEventCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteCommandCs2100.equals(deleteCommandCs2100));

        // same values -> returns true
        DeleteEventCommand deleteCommandCs2100Copy = new DeleteEventCommand(Index.fromOneBased(1));
        assertTrue(deleteCommandCs2100.equals(deleteCommandCs2100Copy));

        // different types -> returns false
        assertFalse(deleteCommandCs2100.equals(1));

        // null -> returns false
        assertFalse(deleteCommandCs2100.equals(null));

        // different person -> returns false
        assertFalse(deleteCommandCs2100.equals(deleteCommandEs2660));

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
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsDeleted.add(event);
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

        @Override
        public ObservableList<Event> getFilteredEventList() {
            return FXCollections.observableList(eventsDeleted);
        }

        @Override
        public void commitEventList() {}
    }
}
