package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.FilterEventPredicate;
import seedu.address.model.person.Person;

public class ListEventAttendeesCommandTest {
    private static final Index INDEX_FIRST_EVENT = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_listEventAttendees_emptyEventAttendees() throws CommandException {
        Event firstEvent = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        String expectedMessage = String.format(ListEventAttendeesCommand.MESSAGE_FILTER_SUCCESS,
                firstEvent.getName());
        FilterEventPredicate predicate = prepareFilterEventPredicate(
                firstEvent.getAttendeesList());
        ListEventAttendeesCommand command = new ListEventAttendeesCommand(INDEX_FIRST_EVENT);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_listEventAttendees_firstEventAttendees() throws CommandException {
        // assign Alice to both models
        assignFirstPersonToFirstEvent(model, expectedModel);

        // get first event
        Event firstEvent = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String.format(ListEventAttendeesCommand.MESSAGE_FILTER_SUCCESS,
                firstEvent.getName());

        // set up predicate
        FilterEventPredicate predicate = prepareFilterEventPredicate(
                firstEvent.getAttendeesList());

        ListEventAttendeesCommand command = new ListEventAttendeesCommand(INDEX_FIRST_EVENT);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    /**
     * Assigns the first resident to the first event for both models.
     * @param model model to be updated
     * @param expectedModel expected model to be updated
     * @throws CommandException if an error occurs during command execution
     */
    private void assignFirstPersonToFirstEvent(Model model, Model expectedModel) throws CommandException {
        CommandResult assignAliceModel = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT).execute(model);
        CommandResult assignAliceExpected = new AssignCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_EVENT).execute(expectedModel);
    }

    /**
     * Parses {@code Set<Person>} into a {@code FilterEventPredicate}.
     */
    private FilterEventPredicate prepareFilterEventPredicate(Set<Person> attendeeList) {
        return new FilterEventPredicate(attendeeList);
    }

    // debugging
    /*
    [Alice Pauline
    Benson Meier
    Carl Kurz
    Daniel Meier
    Elle Meyer
    Fiona Kunz
    George Best
     */
}
