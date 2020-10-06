package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class AddProfilePictureCommandTest {

    private Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
    private File profilePicture = new File("data/stock_picture.png");

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProfilePictureCommand(null,
                                                                                    INDEX_FIRST_PATIENT));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProfilePictureCommand(profilePicture, null));
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddProfilePictureCommand addProfilePictureCommand = new AddProfilePictureCommand(profilePicture,
                                                                                         outOfBoundIndex);

        assertCommandFailure(addProfilePictureCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of clinical
     */
    @Test
    public void execute_invalidPatientIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);
        Index outOfBoundIndex = INDEX_SECOND_PATIENT;
        // ensures that outOfBoundIndex is still in bounds of clinical list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCliniCal().getPatientList().size());

        AddProfilePictureCommand addProfilePictureCommand = new AddProfilePictureCommand(profilePicture,
                outOfBoundIndex);

        assertCommandFailure(addProfilePictureCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }


    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);
        Index index = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        Patient editedPatient = new PatientBuilder(patientInFilteredList)
                                    .withProfilePicture("f/data/stock_picture.png").build();
        AddProfilePictureCommand addProfilePictureCommand =
                                 new AddProfilePictureCommand(profilePicture, index);

        String expectedMessage = String.format(AddProfilePictureCommand.MESSAGE_ADD_PROFILE_PICTURE_SUCCESS,
                                               editedPatient);

        Model expectedModel = new ModelManager(new CliniCal(model.getCliniCal()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);
        Model actualModel = expectedModel;

        assertEquals(expectedModel, actualModel);
    }
}
