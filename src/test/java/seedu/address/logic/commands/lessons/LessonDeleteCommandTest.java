package seedu.address.logic.commands.lessons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code LessonDeleteCommand}.
 */
public class LessonDeleteCommandTest {

    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredLessonList_success() {
        Lesson lessonToDelete = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(INDEX_FIRST_LESSON);

        String expectedMessage = String.format(LessonDeleteCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(lessonDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredLessonList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(outOfBoundIndex);

        assertCommandFailure(lessonDeleteCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredLessonList_success() {
        showLessonAtIndex(model, INDEX_FIRST_LESSON);

        Lesson lessonToDelete = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(INDEX_FIRST_LESSON);

        String expectedMessage = String.format(LessonDeleteCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        Model expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);
        showNoLesson(expectedModel);

        assertCommandSuccess(lessonDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredLessonList_throwsCommandException() {
        showLessonAtIndex(model, INDEX_FIRST_LESSON);

        Index outOfBoundIndex = INDEX_SECOND_LESSON;
        // ensures that outOfBoundIndex is still in bounds of fitNUS list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitNus().getLessonList().size());

        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(outOfBoundIndex);

        assertCommandFailure(lessonDeleteCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        LessonDeleteCommand lessonDeleteFirstCommand = new LessonDeleteCommand(INDEX_FIRST_LESSON);
        LessonDeleteCommand lessonDeleteSecondCommand = new LessonDeleteCommand(INDEX_SECOND_LESSON);

        // same object -> returns true
        assertTrue(lessonDeleteFirstCommand.equals(lessonDeleteFirstCommand));

        // same values -> returns true
        LessonDeleteCommand lessonDeleteFirstCommandCopy = new LessonDeleteCommand(INDEX_FIRST_LESSON);
        assertTrue(lessonDeleteFirstCommand.equals(lessonDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(lessonDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(lessonDeleteFirstCommand.equals(null));

        // different lesson -> returns false
        assertFalse(lessonDeleteFirstCommand.equals(lessonDeleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered lesson list to show no one.
     */
    private void showNoLesson(Model model) {
        model.updateFilteredLessonList(p -> false);

        assertTrue(model.getFilteredLessonList().isEmpty());
    }
}
