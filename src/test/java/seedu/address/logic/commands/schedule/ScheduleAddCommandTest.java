package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class ScheduleAddCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleAddCommand(null));
    }

    @Test
    public void execute_validEventToAdd_success() throws Exception {

        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new ScheduleAddCommand(validEvent).execute(modelStub);

        assertEquals(String.format(ScheduleAddCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.events);

    }

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

    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> events = new ArrayList<>();

        @Override
        public void addEvent(Event eventToAdd) {
            events.add(eventToAdd);
        }

        @Override
        public boolean hasEvent(Event eventToCheck) {
            return false;
        }

        @Override
        public boolean isClashingEvent(Event event) {
            return false;
        }
    }

}
