package seedu.address.logic.commands.patientcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.ELLE;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.filters.patientfilters.SearchNameFilter;

/**
 * Contains integration tests (interaction with the Model) for {@code PatientFindCommand}.
 */
public class PatientFindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SearchNameFilter firstPredicate =
                new SearchNameFilter(Collections.singletonList("first"));
        SearchNameFilter secondPredicate =
                new SearchNameFilter(Collections.singletonList("second"));

        PatientFindCommand findFirstCommand = new PatientFindCommand(firstPredicate);
        PatientFindCommand findSecondCommand = new PatientFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        PatientFindCommand findFirstCommandCopy = new PatientFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        SearchNameFilter predicate = preparePredicate(" ");
        PatientFindCommand command = new PatientFindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        SearchNameFilter predicate = preparePredicate("Kurz Elle Kunz");
        PatientFindCommand command = new PatientFindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code SearchNameFilter}.
     */
    private SearchNameFilter preparePredicate(String userInput) {
        return new SearchNameFilter(Arrays.asList(userInput.split("\\s+")));
    }
}
