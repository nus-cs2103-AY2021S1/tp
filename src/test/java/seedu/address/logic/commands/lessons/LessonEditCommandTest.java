package seedu.address.logic.commands.lessons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand.EditLessonDescriptor;
import seedu.address.model.FitNus;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.LessonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for LessonEditCommand.
 */
public class LessonEditCommandTest {

    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredLessonList_success() {
        Lesson editedLesson = new LessonBuilder().build();
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(editedLesson).build();
        LessonEditCommand lessonEditCommand = new LessonEditCommand(INDEX_FIRST_LESSON, descriptor);

        String expectedMessage = String.format(LessonEditCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());
        expectedModel.setLesson(model.getFilteredLessonList().get(0), editedLesson);

        assertCommandSuccess(lessonEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredLessonList_success() {
        Index indexLastLesson = Index.fromOneBased(model.getFilteredLessonList().size());
        Lesson lastLesson = model.getFilteredLessonList().get(indexLastLesson.getZeroBased());

        LessonBuilder lessonInList = new LessonBuilder(lastLesson);
        Lesson editedLesson = lessonInList.withName(VALID_LESSON_NAME_CS2106).withTags(VALID_LESSON_TAG_EASY).build();

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106)
                .withTags(VALID_LESSON_TAG_EASY).build();
        LessonEditCommand lessonEditCommand = new LessonEditCommand(indexLastLesson, descriptor);

        String expectedMessage = String.format(LessonEditCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());
        expectedModel.setLesson(lastLesson, editedLesson);

        assertCommandSuccess(lessonEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredLessonList_success() {
        LessonEditCommand lessonEditCommand = new LessonEditCommand(INDEX_FIRST_LESSON, new EditLessonDescriptor());
        Lesson editedLesson = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());

        String expectedMessage = String.format(LessonEditCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());

        assertCommandSuccess(lessonEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredLessonList_success() {
        showLessonAtIndex(model, INDEX_FIRST_LESSON);

        Lesson lessonInFilteredList = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        Lesson editedLesson = new LessonBuilder(lessonInFilteredList).withName(VALID_LESSON_NAME_CS2106).build();
        LessonEditCommand lessonEditCommand = new LessonEditCommand(INDEX_FIRST_LESSON,
                new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106).build());

        String expectedMessage = String.format(LessonEditCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());
        expectedModel.setLesson(model.getFilteredLessonList().get(0), editedLesson);

        assertCommandSuccess(lessonEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateLessonUnfilteredList_failure() {
        Lesson firstLesson = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(firstLesson).build();
        LessonEditCommand lessonEditCommand = new LessonEditCommand(INDEX_SECOND_LESSON, descriptor);

        assertCommandFailure(lessonEditCommand, model, LessonEditCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_duplicateLessonFilteredList_failure() {
        showLessonAtIndex(model, INDEX_FIRST_LESSON);

        // edit lesson in filtered list into a duplicate in fitNUS
        Lesson lessonInList = model.getFitNus().getLessonList().get(INDEX_SECOND_LESSON.getZeroBased());
        LessonEditCommand lessonEditCommand = new LessonEditCommand(INDEX_FIRST_LESSON,
                new EditLessonDescriptorBuilder(lessonInList).build());

        assertCommandFailure(lessonEditCommand, model, LessonEditCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_invalidLessonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106).build();
        LessonEditCommand lessonEditCommand = new LessonEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(lessonEditCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered lesson list where index is larger than size of filtered list,
     * but smaller than size of fitNUS
     */
    @Test
    public void execute_invalidLessonIndexFilteredList_failure() {
        showLessonAtIndex(model, INDEX_FIRST_LESSON);
        Index outOfBoundIndex = INDEX_SECOND_LESSON;
        // ensures that outOfBoundIndex is still in bounds of fitNUS list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitNus().getLessonList().size());

        LessonEditCommand lessonEditCommand = new LessonEditCommand(outOfBoundIndex,
                new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106).build());

        assertCommandFailure(lessonEditCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final LessonEditCommand standardCommand = new LessonEditCommand(INDEX_FIRST_LESSON, DESC_CS2030);

        // same values -> returns true
        EditLessonDescriptor copyDescriptor = new EditLessonDescriptor(DESC_CS2030);
        LessonEditCommand commandWithSameValues = new LessonEditCommand(INDEX_FIRST_LESSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new LessonEditCommand(INDEX_SECOND_LESSON, DESC_CS2030)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new LessonEditCommand(INDEX_FIRST_LESSON, DESC_CS2106)));
    }

}
