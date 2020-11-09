package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.THIS_WEEK_SATURDAY_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.TODAY_APPOINTMENT;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.filters.appointmentfilters.SearchAppointmentTagsFilter;
import seedu.address.testutil.TypicalAppointments;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for AppointmentTagCommand.
 */
public class AppointmentTagCommandTest {

    private Model model = new ModelManager(TypicalAppointments.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalAppointments.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SearchAppointmentTagsFilter firstPredicate =
                new SearchAppointmentTagsFilter(Collections.singletonList("first"));
        SearchAppointmentTagsFilter secondPredicate =
                new SearchAppointmentTagsFilter(Collections.singletonList("second"));

        AppointmentTagCommand findFirstCommand = new AppointmentTagCommand(firstPredicate);
        AppointmentTagCommand findSecondCommand = new AppointmentTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        AppointmentTagCommand findFirstCommandCopy = new AppointmentTagCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        SearchAppointmentTagsFilter predicate = preparePredicate(" ");
        AppointmentTagCommand command = new AppointmentTagCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_invalidKeywords_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        SearchAppointmentTagsFilter predicate = preparePredicate("wazzupdizawrongkeyword");
        AppointmentTagCommand command = new AppointmentTagCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_multipleKeywords_multipleAppointmendFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 2);
        SearchAppointmentTagsFilter predicate = preparePredicate("today saturday");
        AppointmentTagCommand command = new AppointmentTagCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TODAY_APPOINTMENT,
                THIS_WEEK_SATURDAY_APPOINTMENT), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code SearchAppointmentTagsFilter}.
     */
    private SearchAppointmentTagsFilter preparePredicate(String userInput) {
        return new SearchAppointmentTagsFilter(Arrays.asList(userInput.split("\\s+")));
    }

}
