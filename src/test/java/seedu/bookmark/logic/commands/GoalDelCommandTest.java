package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Goal;

public class GoalDelCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Goal validGoal = new Goal("10", "15-10-2024");

    @Test
    public void execute_validIndex_success() {
        GoalDelCommand cmd = new GoalDelCommand(INDEX_FIRST_BOOK);
        // Establish: Expected model, expected CommandResult
        Book bookToChange = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book bookChanged = Book.setGoal(bookToChange, validGoal); // Make sure goal exists first
        model.setBook(bookToChange, bookChanged); // Just to make sure lmao

        // Expected model stays default

        String expectedMsg = String.format(GoalDelCommand.MESSAGE_SUCCESS, bookToChange.getName());
        CommandResult expectedResult = new CommandResult(expectedMsg, false, false,
                ViewType.MOST_RECENTLY_USED);

        assertCommandSuccess(cmd, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_bookNoGoal_exception() {
        GoalDelCommand cmd = new GoalDelCommand(INDEX_FIRST_BOOK);
        Book bookToChange = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());

        assertCommandFailure(cmd, model, String.format(GoalDelCommand.MESSAGE_NO_GOAL, bookToChange.getName()));
    }

    @Test
    public void execute_invalidIndex_exception() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        GoalDelCommand goalDelCommand = new GoalDelCommand(outOfBoundIndex);

        assertCommandFailure(goalDelCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GoalDelCommand cmd1 = new GoalDelCommand(INDEX_FIRST_BOOK);
        GoalDelCommand cmd2 = new GoalDelCommand(INDEX_SECOND_BOOK);

        GoalDelCommand cmd1Clone = new GoalDelCommand(INDEX_FIRST_BOOK);

        // True when same object
        assertTrue(cmd1.equals(cmd1)); // short circuit

        // False with diff index
        assertFalse(cmd2.equals(cmd1));

        // True with same index
        assertTrue(cmd1.equals(cmd1Clone));

        // False if diff type
        assertFalse(cmd1.equals(1));

        // False if null
        assertFalse(cmd1.equals(null));
    }

}
