package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.model.EventList;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.event.Event;
import seedu.address.testutil.event.EventBuilder;



public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventsAdded modelStub = new ModelStubAcceptingEventsAdded();
        Event event = new EventBuilder().build();
        CommandResult commandResult = new AddEventCommand(event).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, event), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(event), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Event event = new EventBuilder().build();
        AddEventCommand addCommand = new AddEventCommand(event);
        ModelStub modelStub = new ModelStubWithEvent(event);

        assertThrows(CommandException.class,
                AddEventCommand.MESSAGE_DUPLICATE_EVENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
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
    private class ModelStubAcceptingEventsAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::equals);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyEventList getEventList() {
            return new EventList();
        }
    }
}
