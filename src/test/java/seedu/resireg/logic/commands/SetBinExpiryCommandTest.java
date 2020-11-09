package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalCommandWordAliases.getTypicalUserPrefs;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SetBinExpiryCommand}. Note that a negative/invalid case (e.g. days less than or equal to 0) is not tested
 * because it is already covered in the parser. This improves efficiency of the testing.
 */
public class SetBinExpiryCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), getTypicalUserPrefs());

    @Test
    public void execute_validDays_success() {
        int setNewDaysExpired = 19;
        SetBinExpiryCommand setCommand = new SetBinExpiryCommand(setNewDaysExpired);

        String expectedMessage = String.format(SetBinExpiryCommand.MESSAGE_SUCCESS, setNewDaysExpired);

        ModelManager expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.setDaysStoredInBin(setNewDaysExpired);


        assertCommandSuccess(setCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SetBinExpiryCommand set19DaysCommand = new SetBinExpiryCommand(19);
        SetBinExpiryCommand set20DaysCommand = new SetBinExpiryCommand(20);

        // same object -> returns true
        assertTrue(set19DaysCommand.equals(set19DaysCommand));

        // same values -> returns true
        SetBinExpiryCommand deleteFirstCommandCopy = new SetBinExpiryCommand(19);
        assertTrue(set19DaysCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(set19DaysCommand.equals(new StudentBuilder().build()));

        // null -> returns false
        assertFalse(set19DaysCommand.equals(null));

        // different command word alias -> returns false
        assertFalse(set19DaysCommand.equals(set20DaysCommand));
    }
}
