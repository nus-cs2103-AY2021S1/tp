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

class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalResiReg(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        // set up undo/redo history
        deleteFirstStudent(model);
        deleteFirstStudent(model);
        model.undoResiReg();
        model.undoResiReg();

        deleteFirstStudent(expectedModel);
        deleteFirstStudent(expectedModel);
        expectedModel.undoResiReg();
        expectedModel.undoResiReg();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }
}
