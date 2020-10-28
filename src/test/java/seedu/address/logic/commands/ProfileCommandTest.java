package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPatients;

public class ProfileCommandTest {

    private ReadOnlyCliniCal sampleClinical = TypicalPatients.getTypicalCliniCal();
    private Model model = new ModelManager(sampleClinical, new UserPrefs());

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProfileCommand(null));
    }

    @Test
    public void execute_unfilteredListInvalidPatientIndex_failure() {
        ObservableList<Patient> observableList = model.getFilteredPatientList();
        int sizeOfObservableList = observableList.size();
        Index outOfBoundIndex = Index.fromOneBased(sizeOfObservableList + 1);
        ProfileCommand command = new ProfileCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edits filtered patient list in the scenario where index is greater than size of filtered list, but
     * lesser than size of patient list in CliniCal
     */
    @Test
    public void execute_filteredListInvalidPatientIndex_failure() {
        Index indexOfFirstPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        CommandTestUtil.showPatientAtIndex(model, indexOfFirstPatient);
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;
        Index outOfBoundIndex = indexOfSecondPatient;

        ReadOnlyCliniCal modelClinical = model.getCliniCal();
        ObservableList<Patient> sampleClinical = modelClinical.getPatientList();
        int sizeOfPatientList = sampleClinical.size();

        // Assert that outOfBoundIndex is still in bounds of size of patient list in CliniCal
        assertTrue(outOfBoundIndex.getZeroBased() < sizeOfPatientList);

        ProfileCommand profileCommand = new ProfileCommand(outOfBoundIndex);
        CommandTestUtil.assertCommandFailure(profileCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index indexOfFirstPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;
        final ProfileCommand command = new ProfileCommand(indexOfFirstPatient);

        // Null value. Returns false
        assertFalse(command.equals(null));

        // Same object. Returns true
        assertTrue(command.equals(command));

        // Different types of command. Returns false
        assertFalse(command.equals(new ListCommand()));

        // Different patient index. Returns false
        assertFalse(command.equals(new ProfileCommand(indexOfSecondPatient)));
    }
}
