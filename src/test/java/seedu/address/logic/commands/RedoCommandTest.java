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

public class RedoCommandTest {

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

        model.undoAddressBook();
        model.undoAddressBook();

        expectedModel.undoAddressBook();
        expectedModel.undoAddressBook();
    }

    @Test
    public void execute_multipleRedoableStatesInModel_success() {
        // multiple undoable states in model
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_singleRedoableStatesInModel_success() {
        // single undoable state in model
        model.redoAddressBook();
        expectedModel.redoAddressBook();
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_noRedoableStatesInModel_success() {
        // no undoable states in model
        model.redoAddressBook();
        model.redoAddressBook();
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }
}
