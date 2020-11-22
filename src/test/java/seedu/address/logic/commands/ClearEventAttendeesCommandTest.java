package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.ClearEventAttendeesCommand.MESSAGE_CLEAR_EVENT_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

public class ClearEventAttendeesCommandTest {
    private static final Index INDEX_FIRST_EVENT = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_clearEventAttendees_emptyEventAttendees() throws CommandException {
        Event event = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        String expectedMessage = String.format(MESSAGE_CLEAR_EVENT_SUCCESS, event);

        // execute clear event attendees command
        CommandResult commandResult = new ClearEventAttendeesCommand(INDEX_FIRST_EVENT).execute(model);

        // compare output
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Collections.emptySet(),
                model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased()).getAttendeesList());
    }

    @Test
    public void execute_clearEventAttendees_success() throws CommandException {
        // assign Alice
        assignFirstPersonToFirstEvent(model);
        Event event = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        String expectedMessage = String.format(MESSAGE_CLEAR_EVENT_SUCCESS, event);

        // execute clear event attendees command
        CommandResult commandResult = new ClearEventAttendeesCommand(INDEX_FIRST_EVENT).execute(model);

        // compare output
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Collections.emptySet(),
                model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased()).getAttendeesList());

    }

    @Test
    public void execute_invalidIndex_throwCommandException() throws ParseException {
        ClearEventAttendeesCommand clearEventAttendeesCommand =
                new ClearEventAttendeesCommand(ParserUtil.parseIndex("10"));

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, () -> clearEventAttendeesCommand.execute(model));

    }

    /**
     * Assigns the first resident to the first event.
     * @param model model to be updated
     * @throws CommandException if an error occurs during command execution
     */
    private void assignFirstPersonToFirstEvent(Model model) throws CommandException {
        CommandResult assignAliceModel = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT).execute(model);
    }
}
