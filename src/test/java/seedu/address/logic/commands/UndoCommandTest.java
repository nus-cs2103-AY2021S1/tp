package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTags.CS2101;
import static seedu.address.testutil.TypicalTags.CS2103;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        // set up of models' undo/redo history
        model.deleteTag(CS2101);
        model.commitAddressBook();
        model.deleteTag(CS2103);
        model.commitAddressBook();

        expectedModel.deleteTag(CS2101);
        expectedModel.commitAddressBook();
        expectedModel.deleteTag(CS2103);
        expectedModel.commitAddressBook();
    }

    @Test
    public void execute_multipleUndoableStatesInModel_success() {
        // multiple undoable states in model
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_singleUndoableStatesInModel_success() {
        // single undoable state in model
        model.undoAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_noUndoableStatesInModel_success() {
        // no undoable states in model
        model.undoAddressBook();
        model.undoAddressBook();
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
    }
}
