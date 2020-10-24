package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.deleteFirstStudent;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;

class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalResiReg(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        // set up undo/redo history
        deleteFirstStudent(model);
        deleteFirstStudent(model);

        deleteFirstStudent(expectedModel);
        deleteFirstStudent(expectedModel);
    }

    @Test
    void execute() {
        // multiple undoable states in model
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states remaining
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
    }
}
