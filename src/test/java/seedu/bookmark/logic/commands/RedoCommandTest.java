package seedu.bookmark.logic.commands;

import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.ViewType;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;

public class RedoCommandTest {

    @Test
    public void undo_nothingToRedo_throwsCommandException() {
        Model model = new ModelManager();

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_NO_UNDONE_CHANGES);
    }

    @Test
    public void redo_validRedo_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.addBook(HARRY_POTTER);
        model.addBook(HARRY_POTTER);
        model.save();
        model.undo();
        CommandResult expectedResult = new CommandResult(RedoCommand.MESSAGE_SUCCESS, false , false,
                ViewType.DEFAULT);
        assertCommandSuccess(new RedoCommand(), model, expectedResult, expectedModel);
    }
}
