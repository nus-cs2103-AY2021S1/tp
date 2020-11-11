package seedu.bookmark.logic.commands;

import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.ViewType;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;

public class UndoCommandTest {

    @Test
    public void undo_nothingToUndo_throwsCommandException() {
        Model model = new ModelManager();

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_MOST_RECENT_CHANGE);
    }

    @Test
    public void undo_validUndo_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        model.addBook(HARRY_POTTER);
        model.save();
        CommandResult expectedResult = new CommandResult(UndoCommand.MESSAGE_SUCCESS, false , false,
                ViewType.DEFAULT);
        assertCommandSuccess(new UndoCommand(), model, expectedResult, expectedModel);
    }
}
