package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_ASSIGN_PERSON_SUCCESS;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_DUPLICATE_PERSON_ADDED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public class AssignCommandTest {
    private static final Index INDEX_FIRST_EVENT = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_assignResident_success() throws CommandException {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event event = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        CommandResult commandResult = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT).execute(model);

        // compare output
        assertEquals(String.format(MESSAGE_ASSIGN_PERSON_SUCCESS, person.getName(), event.getName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_dupeResidentInList_throwsCommandException() throws CommandException {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event event = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        CommandResult commandResult = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT).execute(model);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT);

        assertThrows(CommandException.class,
                String.format(MESSAGE_DUPLICATE_PERSON_ADDED,
                        person.getName(),
                        event.getName()), () -> assignCommand.execute(model));
    }
}
