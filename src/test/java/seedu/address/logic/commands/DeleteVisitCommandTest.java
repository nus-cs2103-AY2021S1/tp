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
import seedu.address.model.visit.VisitHistory;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPatients;

public class DeleteVisitCommandTest {

    private static final int INVALID_VISIT_INDEX = 0;
    private ReadOnlyCliniCal sampleCliniCal = TypicalPatients.getTypicalCliniCal();
    private Model model = new ModelManager(sampleCliniCal, new UserPrefs());

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteVisitCommand(null, 1));
    }

    @Test
    public void execute_invalidVisitIndex_failure() {
        Index indexOfPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        DeleteVisitCommand deleteVisitCommand = new DeleteVisitCommand(indexOfPatient, INVALID_VISIT_INDEX);

        ObservableList<Patient> filteredPatientList = model.getFilteredPatientList();
        int indexOfPatientAsInt = indexOfPatient.getZeroBased();
        VisitHistory visitHistory = filteredPatientList.get(indexOfPatientAsInt).getVisitHistory();
        int sizeOfVisitHistory = visitHistory.getVisits().size();

        DeleteVisitCommand deleteCommand = new DeleteVisitCommand(TypicalIndexes.INDEX_FIRST_PATIENT,
            sizeOfVisitHistory + 1);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_VISIT_INDEX);
        CommandTestUtil.assertCommandFailure(deleteVisitCommand, model, Messages.MESSAGE_INVALID_VISIT_INDEX);
    }

    @Test
    public void equals() {
        Index indexOfFirstPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;

        DeleteVisitCommand firstCommand = new DeleteVisitCommand(indexOfFirstPatient, 1);
        DeleteVisitCommand secondCommand = new DeleteVisitCommand(indexOfSecondPatient, 1);

        // Null value. Returns false
        assertFalse(firstCommand.equals(null));

        // Same object. Returns true
        assertTrue(firstCommand.equals(firstCommand));

        // Same object. Returns true
        assertTrue(secondCommand.equals(secondCommand));

        // Same values. Returns true
        DeleteVisitCommand firstCommandCopy = new DeleteVisitCommand(indexOfFirstPatient, 1);
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
