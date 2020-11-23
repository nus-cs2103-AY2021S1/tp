package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;

/**
 * Contains integration tests (interaction with the Model) for {@code CountdownCommand}.
 */
public class CountdownCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
    private EventDate eventDate = new EventDate("31-10-2021", false);
    private EventName eventName = new EventName("Halloween Party");
    private Index index = Index.fromOneBased(1);

    @Test
    public void execute_switchCountdownTab_success() {
        CountdownCommand countdownCommand = new CountdownCommand();
        String expectedMessage = CountdownCommand.MESSAGE_SWITCH_TAB_SUCCESS;
        assertCommandSuccess(countdownCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_addCountdown_success() {
        CountdownCommand countdownCommand = new CountdownCommand(eventName, eventDate);
        Event eventToAdd = new Event(eventName, eventDate);
        String expectedMessage = String.format(CountdownCommand.MESSAGE_ADD_EVENT_SUCCESS, eventToAdd);
        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.addEvent(eventToAdd);
        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteCountdown_success() {
        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_TASK.getZeroBased());
        CountdownCommand countdownCommand = new CountdownCommand(index, true);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_countEventsInDays_success() {
        int queryDays = 365;
        CountdownCommand countdownCommand = new CountdownCommand(queryDays);

        int numEventsInDays = 0;
        StringBuilder listEvents = new StringBuilder();
        for (Event event : model.getFilteredEventList()) {
            if (event.getDaysRemaining() <= queryDays && event.getDaysRemaining() >= 0) {
                numEventsInDays++;
                listEvents.append(String.format("%d. %s\n", numEventsInDays, event));
            }
        }
        String expectedMessage = String.format(CountdownCommand.MESSAGE_COUNT_EVENTS_IN_DAYS_SUCCESS,
                numEventsInDays, queryDays, listEvents.toString());

        assertCommandSuccess(countdownCommand, model, expectedMessage, model);
    }

    @Test
    public void testEquals() {
        CountdownCommand countdownCommand1 = new CountdownCommand();
        CountdownCommand countdownCommand2 = new CountdownCommand();

        CountdownCommand countdownCommand3 = new CountdownCommand(eventName, eventDate);
        CountdownCommand countdownCommand4 = new CountdownCommand(eventName, eventDate);
        CountdownCommand countdownCommand5 = new CountdownCommand(index, true);
        CountdownCommand countdownCommand6 = new CountdownCommand(index, true);

        EventName eventName1 = new EventName("Christmas Party");
        EventDate eventDate1 = new EventDate("25-Dec-2021", false);
        Index index1 = Index.fromOneBased(2);
        CountdownCommand countdownCommand7 = new CountdownCommand(eventName, eventDate1);
        CountdownCommand countdownCommand8 = new CountdownCommand(eventName1, eventDate);
        CountdownCommand countdownCommand9 = new CountdownCommand(eventName1, eventDate1);
        CountdownCommand countdownCommand10 = new CountdownCommand(index1, true);

        int queryDays1 = 7;
        int queryDays2 = 8;
        CountdownCommand countdownCommand11 = new CountdownCommand(queryDays1);
        CountdownCommand countdownCommand12 = new CountdownCommand(queryDays1);
        CountdownCommand countdownCommand13 = new CountdownCommand(queryDays2);

        // same object -> return true
        assertEquals(countdownCommand1, countdownCommand1);
        assertEquals(countdownCommand3, countdownCommand3);
        assertEquals(countdownCommand5, countdownCommand5);
        assertEquals(countdownCommand11, countdownCommand11);

        // same values -> return true
        assertEquals(countdownCommand1, countdownCommand2);
        assertEquals(countdownCommand3, countdownCommand4);
        assertEquals(countdownCommand5, countdownCommand6);
        assertEquals(countdownCommand11, countdownCommand12);

        // different values -> return false
        assertNotEquals(countdownCommand3, countdownCommand7);
        assertNotEquals(countdownCommand3, countdownCommand8);
        assertNotEquals(countdownCommand3, countdownCommand9);
        assertNotEquals(countdownCommand5, countdownCommand10);
        assertNotEquals(countdownCommand11, countdownCommand13);
        assertNotEquals(countdownCommand13, countdownCommand1);

        // different types -> return false
        assertNotEquals(countdownCommand1, "countdown");
        assertNotEquals(countdownCommand5, 5);

        // null -> return false
        assertNotEquals(countdownCommand1, null);
        assertNotEquals(countdownCommand3, null);
        assertNotEquals(countdownCommand5, null);
    }

}
