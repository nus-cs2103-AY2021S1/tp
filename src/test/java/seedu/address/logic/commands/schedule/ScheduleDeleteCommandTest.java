package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.NOT_TYPICAL_EVENT;
import static seedu.address.testutil.TypicalEvents.RELAX_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalScheduler;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Reeve;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.DeleteEvent;
import seedu.address.model.event.Scheduler;

public class ScheduleDeleteCommandTest {

    private Model model = new ModelManager(new Reeve(), new UserPrefs(), getTypicalScheduler());

    @Test
    public void execute_validEvent_success() {

        ScheduleDeleteCommand scheduleDeleteCommand =
                new ScheduleDeleteCommand(new DeleteEvent(ALICE_CLASS_EVENT.getEventName(),
                        ALICE_CLASS_EVENT.getEventStartDateTime(), ALICE_CLASS_EVENT.getEventEndDateTime()));

        String expectedMessage = String.format(scheduleDeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, ALICE_CLASS_EVENT);

        ModelManager expectedModel = new ModelManager(new Reeve(), new UserPrefs(), model.getSchedule());
        expectedModel.removeEvent(ALICE_CLASS_EVENT);

        assertCommandSuccess(scheduleDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_eventNotPresent_throwsCommandException() {
        DeleteEvent eventToDelete = new DeleteEvent(NOT_TYPICAL_EVENT.getEventName(),
                NOT_TYPICAL_EVENT.getEventStartDateTime(), NOT_TYPICAL_EVENT.getEventEndDateTime());
        ScheduleDeleteCommand scheduleDeleteCommand = new ScheduleDeleteCommand(eventToDelete);

        assertCommandFailure(scheduleDeleteCommand, model, ScheduleDeleteCommand.MESSAGE_NO_MATCHING_EVENT);
    }

    @Test
    public void execute_eventListEmpty_throwsCommandException() {
        DeleteEvent eventToDelete = new DeleteEvent(ALICE_CLASS_EVENT.getEventName(),
                ALICE_CLASS_EVENT.getEventStartDateTime(), ALICE_CLASS_EVENT.getEventEndDateTime());
        ScheduleDeleteCommand scheduleDeleteCommand = new ScheduleDeleteCommand(eventToDelete);

        Model model = new ModelManager(new Reeve(), new UserPrefs(), new Scheduler());


        assertThrows(CommandException.class, ScheduleDeleteCommand.MESSAGE_EMPTY_EVENT_LIST, ()->
                scheduleDeleteCommand.execute(model));
    }


    @Test
    public void equals() {
        DeleteEvent firstEventToDelete = new DeleteEvent(ALICE_CLASS_EVENT.getEventName(),
                ALICE_CLASS_EVENT.getEventStartDateTime(), ALICE_CLASS_EVENT.getEventEndDateTime());
        DeleteEvent secondEventToDelete = new DeleteEvent(RELAX_EVENT.getEventName(),
                RELAX_EVENT.getEventStartDateTime(), RELAX_EVENT.getEventEndDateTime());
        ScheduleDeleteCommand deleteFirstCommand = new ScheduleDeleteCommand(firstEventToDelete);
        ScheduleDeleteCommand deleteSecondCommand = new ScheduleDeleteCommand(secondEventToDelete);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ScheduleDeleteCommand deleteFirstCommandCopy = new ScheduleDeleteCommand(firstEventToDelete);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
