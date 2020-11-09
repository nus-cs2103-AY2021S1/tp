package tutorspet.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutorspet.logic.commands.lesson.DeleteLessonCommand.MESSAGE_COMMIT;
import static tutorspet.logic.commands.lesson.DeleteLessonCommand.MESSAGE_SUCCESS;
import static tutorspet.logic.util.ModuleClassUtil.deleteLessonFromModuleClass;
import static tutorspet.testutil.Assert.assertThrows;
import static tutorspet.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutorspet.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutorspet.testutil.TypicalTutorsPet.getTypicalTutorsPet;

import org.junit.jupiter.api.Test;

import tutorspet.commons.core.index.Index;
import tutorspet.model.Model;
import tutorspet.model.ModelManager;
import tutorspet.model.UserPrefs;
import tutorspet.model.lesson.Lesson;
import tutorspet.model.moduleclass.ModuleClass;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLessonCommand}
 */
public class DeleteLessonCommandTest {

    private Model model = new ModelManager(getTypicalTutorsPet(), new UserPrefs());

    @Test
    public void constructor_nullModuleClassIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteLessonCommand(null, INDEX_FIRST_ITEM));
    }

    @Test
    public void constructor_nullLessonIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteLessonCommand(INDEX_FIRST_ITEM, null));
    }

    @Test
    public void execute_validIndexes_success() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;

        ModuleClass moduleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Lesson lessonToDelete = moduleClass.getLessons().get(lessonIndex.getZeroBased());
        ModuleClass editedModuleClass = deleteLessonFromModuleClass(moduleClass, lessonToDelete);

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(moduleClassIndex, lessonIndex);
        String commitMessage = String.format(MESSAGE_COMMIT, moduleClass.getName(), lessonToDelete.printLesson());
        String expectedMessage = String.format(MESSAGE_SUCCESS, moduleClass.getName(), lessonToDelete.printLesson());

        ModelManager expectedModel = new ModelManager(model.getTutorsPet(), new UserPrefs());
        expectedModel.setModuleClass(moduleClass, editedModuleClass);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleClassIndex_failure() {
        Index moduleClassIndex = Index.fromOneBased(model.getFilteredModuleClassList().size() + 1);
        Index lessonIndex = INDEX_FIRST_ITEM;

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(moduleClassIndex, lessonIndex);

        assertCommandFailure(deleteLessonCommand, model, MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLessonIndex_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        ModuleClass moduleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Index lessonIndex = Index.fromOneBased(moduleClass.getLessons().size() + 1);

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(moduleClassIndex, lessonIndex);

        assertCommandFailure(deleteLessonCommand, model, MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteLessonCommand deleteFirstLessonCommand =
                new DeleteLessonCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);
        DeleteLessonCommand deleteSecondLessonCommand =
                new DeleteLessonCommand(INDEX_FIRST_ITEM, INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(deleteFirstLessonCommand.equals(deleteFirstLessonCommand));

        // same values -> returns true
        DeleteLessonCommand deleteFirstLessonCommandCopy = new DeleteLessonCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);
        assertTrue(deleteFirstLessonCommand.equals(deleteFirstLessonCommandCopy));

        // null -> returns false
        assertFalse(deleteFirstLessonCommand.equals(null));

        // different lesson -> returns false
        assertFalse(deleteFirstLessonCommand.equals(deleteSecondLessonCommand));
    }
}
