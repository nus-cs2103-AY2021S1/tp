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

class UndoCommandTest {

    private Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCliniCal(), new UserPrefs());

    @BeforeEach
    void setUp() {
        deleteLastPatient(model);
        deleteLastPatient(model);
        deleteLastPatient(model);

        deleteLastPatient(expectedModel);
        deleteLastPatient(expectedModel);
        deleteLastPatient(expectedModel);
    }

    @Test
    void execute() {
        expectedModel.undoCliniCal();
        expectedModel.getUndoCommand();
        assertCommandSuccess(new UndoCommand(), model, String.format(UndoCommand.MESSAGE_SUCCESS,
            expectedModel.getUndoCommand()), expectedModel);

        expectedModel.undoCliniCal();
        assertCommandSuccess(new UndoCommand(), model, String.format(UndoCommand.MESSAGE_SUCCESS,
            expectedModel.getUndoCommand()), expectedModel);

        expectedModel.undoCliniCal();
        assertCommandSuccess(new UndoCommand(), model, String.format(UndoCommand.MESSAGE_SUCCESS,
            expectedModel.getUndoCommand()), expectedModel);

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
    }
}
