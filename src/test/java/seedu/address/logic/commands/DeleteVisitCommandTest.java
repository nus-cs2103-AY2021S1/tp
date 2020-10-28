package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPatients;
import seedu.address.testutil.TypicalVisits;

public class DeleteVisitCommandTest {

    private static final int VALID_VISIT_INDEX = 1;
    private static final int INVALID_VISIT_INDEX = 0;
    private ReadOnlyCliniCal sampleCliniCal = TypicalPatients.getTypicalCliniCal();
    private Model model = new ModelManager(sampleCliniCal, new UserPrefs());

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteVisitCommand(1, null));
    }

//    @Test
//    public void execute_deleteVisitCommand_failure() {
//        ObservableList<Patient> filteredPatientList = model.getFilteredPatientList();
//        Index indexOfPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
//        Patient originalPatient = filteredPatientList.get(indexOfPatient.getZeroBased());
//
//        VisitHistory aliceVisitHistory = TypicalVisits.getTypicalVisitHistoryAlice();
//        Patient editedPatient = new PatientBuilder(originalPatient).withVisitHistory(aliceVisitHistory).build();
//
//        DeleteVisitCommand deleteVisitCommand = new DeleteVisitCommand(VALID_VISIT_INDEX, indexOfPatient);
//
//        String expectedMessage = String.format(DeleteVisitCommand.MESSAGE_DELETE_VISIT_SUCCESS, editedPatient);
//
//        ReadOnlyCliniCal modelCliniCal = model.getCliniCal();
//        ReadOnlyCliniCal cliniCal = new CliniCal(modelCliniCal);
//        Model expectedModel = new ModelManager(cliniCal, new UserPrefs());
//
//        expectedModel.setPatient(originalPatient, editedPatient);
//
//        VisitHistory updatedVisitHistory = editedPatient.getVisitHistory();
//        ObservableList<Visit> updatedObservableList = updatedVisitHistory.getObservableVisits();
//        CommandResult expectedCommandResult = new CommandResult(expectedMessage, updatedObservableList);
//
//        try {
//            CommandResult commandResult = deleteVisitCommand.execute(model);
//            // Patient edited has visit history updated, hence it is no longer similar to the original visit history.
//            assertNotEquals(expectedCommandResult, commandResult);
//            assertNotEquals(expectedModel, model);
//        } catch (CommandException exception) {
//            throw new AssertionError("Command has failed", exception);
//        }
//    }

    @Test
    public void execute_invalidVisitIndex_failure() {
        Index indexOfPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        DeleteVisitCommand deleteVisitCommand = new DeleteVisitCommand(INVALID_VISIT_INDEX, indexOfPatient);

        ObservableList<Patient> filteredPatientList = model.getFilteredPatientList();
        int indexOfPatientAsInt = indexOfPatient.getZeroBased();
        VisitHistory visitHistory = filteredPatientList.get(indexOfPatientAsInt).getVisitHistory();
        int sizeOfVisitHistory = visitHistory.getVisits().size();

        DeleteVisitCommand deleteCommand = new DeleteVisitCommand(sizeOfVisitHistory + 1,
                                                                  TypicalIndexes.INDEX_FIRST_PATIENT);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_VISIT_INDEX);
        CommandTestUtil.assertCommandFailure(deleteVisitCommand, model, Messages.MESSAGE_INVALID_VISIT_INDEX);
    }

    @Test
    public void equals() {
        Index indexOfFirstPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;

        DeleteVisitCommand firstCommand = new DeleteVisitCommand(1, indexOfFirstPatient);
        DeleteVisitCommand secondCommand = new DeleteVisitCommand(1, indexOfSecondPatient);

        // Null value. Returns false
        assertFalse(firstCommand.equals(null));

        // Same object. Returns true
        assertTrue(firstCommand.equals(firstCommand));

        // Same object. Returns true
        assertTrue(secondCommand.equals(secondCommand));

        // Same values. Returns true
        DeleteVisitCommand firstCommandCopy = new DeleteVisitCommand(1, indexOfFirstPatient);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // Different command. Returns false
        ListCommand listCommand = new ListCommand();
        assertFalse(firstCommand.equals(listCommand));

        // Different type of input. Returns false
        assertFalse(firstCommand.equals("test"));

        // Different type of input. Returns false
        assertFalse(firstCommand.equals(10));

        // Different patient index. Returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
