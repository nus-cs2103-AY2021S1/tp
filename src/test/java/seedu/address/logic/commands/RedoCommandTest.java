package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deleteLastPatient;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class RedoCommandTest {

    private Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCliniCal(), new UserPrefs());

    @BeforeEach
    void setUp() {
        deleteLastPatient(model);
        deleteLastPatient(model);
        deleteLastPatient(model);
        model.undoCliniCal();
        model.undoCliniCal();
        model.undoCliniCal();

        deleteLastPatient(expectedModel);
        deleteLastPatient(expectedModel);
        deleteLastPatient(expectedModel);
        expectedModel.undoCliniCal();
        expectedModel.undoCliniCal();
        expectedModel.undoCliniCal();
    }

    @Test
    void execute() {
        expectedModel.redoCliniCal();
        assertCommandSuccess(new RedoCommand(), model, String.format(RedoCommand.MESSAGE_SUCCESS,
            expectedModel.getRedoCommand()), expectedModel);

        expectedModel.redoCliniCal();
        assertCommandSuccess(new RedoCommand(), model, String.format(RedoCommand.MESSAGE_SUCCESS,
            expectedModel.getRedoCommand()), expectedModel);

        expectedModel.redoCliniCal();
        assertCommandSuccess(new RedoCommand(), model, String.format(RedoCommand.MESSAGE_SUCCESS,
            expectedModel.getRedoCommand()), expectedModel);

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }
}
