package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODEL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODEL;
import static seedu.address.testutil.TypicalPlanus.getTypicalPlanus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLessonCommand}.
 */
public class DeleteLessonCommandTest {
    private Model model = new ModelManager(getTypicalPlanus(), new UserPrefs());

    @Test
    public void execute_oneValidIndexUnfilteredList_success() {
        Index[] indexes = {INDEX_FIRST_MODEL};
        Lesson[] lessonToDelete = new Lesson[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            lessonToDelete[i] = model.getFilteredLessonList().get(indexes[i].getZeroBased());
        }
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        String expectedMessage = DeleteLessonCommand.buildMessage(lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_manyValidIndexUnfilteredList_success() {
        Index[] indexes = {INDEX_FIRST_MODEL, INDEX_SECOND_MODEL};
        Lesson[] lessonToDelete = new Lesson[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            lessonToDelete[i] = model.getFilteredLessonList().get(indexes[i].getZeroBased());
        }
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        String expectedMessage = DeleteLessonCommand.buildMessage(lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        Index[] indexes = {outOfBoundIndex};
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedIndex_throwsCommandException() {
        Index[] indexes = {INDEX_FIRST_MODEL, INDEX_FIRST_MODEL};

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_DUPLICATE_INDEX);
    }

    @Test
    public void execute_manyInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        Index[] indexes = {outOfBoundIndex1, outOfBoundIndex2};

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_oneValidIndexFilteredList_success() {
        showLessonAtIndex(model, INDEX_FIRST_MODEL);
        Index[] indexes = {INDEX_FIRST_MODEL};
        Lesson[] lessonToDelete = new Lesson[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            lessonToDelete[i] = model.getFilteredLessonList().get(indexes[i].getZeroBased());
        }
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        String expectedMessage = DeleteLessonCommand.buildMessage(lessonToDelete);

        Model expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);
        showNoLesson(expectedModel);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneInvalidIndexFilteredList_throwsCommandException() {
        showLessonAtIndex(model, INDEX_FIRST_MODEL);

        Index outOfBoundIndex = INDEX_SECOND_MODEL;
        Index[] indexes = {outOfBoundIndex};
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlanus().getLessonList().size());

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixValidInvalidIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index[] indexes = {INDEX_FIRST_MODEL, outOfBoundIndex};

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixValidInvalidIndexFilteredList_throwsCommandException() {
        showLessonAtIndex(model, INDEX_FIRST_MODEL);
        Index[] indexes = {INDEX_FIRST_MODEL, INDEX_SECOND_MODEL};

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(indexes);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoLesson(Model model) {
        model.updateFilteredLessonList(p -> false);

        assertTrue(model.getFilteredLessonList().isEmpty());
    }
}
