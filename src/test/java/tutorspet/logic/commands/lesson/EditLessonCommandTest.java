package tutorspet.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutorspet.commons.core.Messages.MESSAGE_DUPLICATE_LESSON;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_OVERLAP_LESSON;
import static tutorspet.logic.commands.CommandTestUtil.DESC_LESSON_FRI_8_TO_10;
import static tutorspet.logic.commands.CommandTestUtil.DESC_LESSON_WED_2_TO_4;
import static tutorspet.logic.commands.CommandTestUtil.VALID_DAY_FRI_LESSON_FRI_8_TO_10;
import static tutorspet.logic.commands.CommandTestUtil.VALID_END_TIME_1000_LESSON_FRI_8_TO_10;
import static tutorspet.logic.commands.CommandTestUtil.VALID_START_TIME_0900;
import static tutorspet.logic.commands.CommandTestUtil.VALID_START_TIME_1400_LESSON_WED_2_TO_4;
import static tutorspet.logic.commands.CommandTestUtil.VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutorspet.logic.commands.CommandTestUtil.showModuleClassAtIndex;
import static tutorspet.logic.commands.lesson.EditLessonCommand.MESSAGE_COMMIT;
import static tutorspet.logic.commands.lesson.EditLessonCommand.MESSAGE_SUCCESS;
import static tutorspet.logic.util.ModuleClassUtil.editLessonInModuleClass;
import static tutorspet.model.lesson.Lesson.MESSAGE_CONSTRAINTS;
import static tutorspet.testutil.Assert.assertThrows;
import static tutorspet.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutorspet.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutorspet.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static tutorspet.testutil.TypicalLesson.LESSON_FRI_8_TO_10;
import static tutorspet.testutil.TypicalTutorsPet.getTypicalTutorsPet;

import org.junit.jupiter.api.Test;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.logic.commands.lesson.EditLessonCommand.EditLessonDescriptor;
import tutorspet.model.Model;
import tutorspet.model.ModelManager;
import tutorspet.model.TutorsPet;
import tutorspet.model.UserPrefs;
import tutorspet.model.attendance.AttendanceRecordList;
import tutorspet.model.lesson.Lesson;
import tutorspet.model.moduleclass.ModuleClass;
import tutorspet.testutil.EditLessonDescriptorBuilder;
import tutorspet.testutil.LessonBuilder;

public class EditLessonCommandTest {

    private static final Lesson EDITED_LESSON = new LessonBuilder().build();
    private static final EditLessonDescriptor EDITED_LESSON_DESCRIPTOR =
            new EditLessonDescriptorBuilder(EDITED_LESSON).build();

    private Model model = new ModelManager(getTypicalTutorsPet(), new UserPrefs());

    @Test
    public void constructor_nullModuleClassIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditLessonCommand(
                null, INDEX_FIRST_ITEM, EDITED_LESSON_DESCRIPTOR));
    }

    @Test
    public void constructor_nullLessonIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditLessonCommand(
                INDEX_FIRST_ITEM, null, EDITED_LESSON_DESCRIPTOR));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;

        EditLessonDescriptor editLessonDescriptor =
                new EditLessonDescriptorBuilder(DESC_LESSON_FRI_8_TO_10).build();
        EditLessonCommand editLessonCommand = new EditLessonCommand(
                moduleClassIndex, lessonIndex, editLessonDescriptor);

        ModuleClass moduleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Lesson lessonToEdit = moduleClass.getLessons().get(lessonIndex.getZeroBased());
        AttendanceRecordList attendanceRecordList =
                moduleClass.getLessons().get(lessonIndex.getZeroBased()).getAttendanceRecordList();
        Lesson editedLesson = new LessonBuilder(LESSON_FRI_8_TO_10)
                .withAttendanceRecordList(attendanceRecordList).build();

        ModuleClass updatedModuleClass = editLessonInModuleClass(moduleClass, lessonToEdit, editedLesson);

        String commitMessage = String.format(MESSAGE_COMMIT, moduleClass.getName(), editedLesson.printLesson());
        String expectedMessage = String.format(MESSAGE_SUCCESS, moduleClass.getName(), editedLesson);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.setModuleClass(moduleClass, updatedModuleClass);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withDay(VALID_DAY_FRI_LESSON_FRI_8_TO_10.toString())
                .withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build();
        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, lessonIndex, descriptor);

        ModuleClass firstModuleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Lesson firstLessonInList = firstModuleClass.getLessons().get(lessonIndex.getZeroBased());

        Lesson editedLesson = new LessonBuilder(firstLessonInList)
                .withDay(VALID_DAY_FRI_LESSON_FRI_8_TO_10)
                .withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build();
        ModuleClass updatedModuleClass = editLessonInModuleClass(firstModuleClass, firstLessonInList, editedLesson);

        String commitMessage = String.format(MESSAGE_COMMIT, updatedModuleClass.getName(), editedLesson.printLesson());
        String expectedMessage = String.format(MESSAGE_SUCCESS, updatedModuleClass.getName(), editedLesson);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.setModuleClass(firstModuleClass, updatedModuleClass);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;

        EditLessonCommand editLessonCommand = new EditLessonCommand(
                moduleClassIndex, lessonIndex, new EditLessonCommand.EditLessonDescriptor());

        ModuleClass targetModuleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());

        Lesson editedLesson = targetModuleClass.getLessons().get(lessonIndex.getZeroBased());

        String commitMessage = String.format(MESSAGE_COMMIT, targetModuleClass.getName(), editedLesson.printLesson());
        String expectedMessage = String.format(MESSAGE_SUCCESS, targetModuleClass.getName(), editedLesson);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        showModuleClassAtIndex(model, INDEX_SECOND_ITEM);

        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_SECOND_ITEM;

        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, lessonIndex,
                new EditLessonDescriptorBuilder().withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build());

        ModuleClass moduleClassInFilteredList = model.getFilteredModuleClassList()
                .get(moduleClassIndex.getZeroBased());
        Lesson lessonToEdit = moduleClassInFilteredList.getLessons().get(lessonIndex.getZeroBased());

        Lesson editedLesson = new LessonBuilder(lessonToEdit)
                .withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build();

        ModuleClass updatedModuleClass = editLessonInModuleClass(
                moduleClassInFilteredList, lessonToEdit, editedLesson);

        String commitMessage = String.format(MESSAGE_COMMIT, updatedModuleClass.getName(), editedLesson.printLesson());
        String expectedMessage = String.format(MESSAGE_SUCCESS,
                updatedModuleClass.getName(), editedLesson);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.setModuleClass(moduleClassInFilteredList, updatedModuleClass);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateLessonUnfilteredList_failure() {
        Index moduleClassIndex = INDEX_SECOND_ITEM;
        Index lessonIndex = INDEX_SECOND_ITEM;

        ModuleClass secondModuleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Lesson secondClassFirstLesson = secondModuleClass.getLessons().get(INDEX_FIRST_ITEM.getZeroBased());

        EditLessonDescriptor descriptor =
                new EditLessonDescriptorBuilder(secondClassFirstLesson).build();
        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, lessonIndex, descriptor);

        assertCommandFailure(editLessonCommand, model, MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_overlapLesson_failure() {
        Index moduleClassIndex = INDEX_SECOND_ITEM;
        Index lessonIndex = INDEX_SECOND_ITEM;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withDay(VALID_DAY_FRI_LESSON_FRI_8_TO_10.toString())
                .withStartTime(VALID_START_TIME_0900).build();
        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, lessonIndex, descriptor);

        assertCommandFailure(editLessonCommand, model, MESSAGE_OVERLAP_LESSON);
    }

    @Test
    public void execute_invalidModuleClassIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleClassList().size() + 1);
        EditLessonCommand editLessonCommand = new EditLessonCommand(outOfBoundIndex, INDEX_FIRST_ITEM,
                new EditLessonDescriptorBuilder().withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build());

        assertCommandFailure(editLessonCommand, model, MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of class list.
     */
    @Test
    public void execute_invalidModuleClassIndexFilteredList_failure() {
        showModuleClassAtIndex(model, INDEX_SECOND_ITEM);
        Index outOfBoundIndex = INDEX_THIRD_ITEM;
        // ensures that outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTutorsPet().getModuleClassList().size());

        EditLessonCommand editLessonCommand = new EditLessonCommand(outOfBoundIndex, INDEX_FIRST_ITEM,
                new EditLessonDescriptorBuilder().withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build());

        assertCommandFailure(editLessonCommand, model, MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLessonIndex_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index outOfBoundIndex = INDEX_THIRD_ITEM;
        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, outOfBoundIndex,
                new EditLessonDescriptorBuilder().withVenue(VALID_VENUE_S17_0302_LESSON_FRI_8_TO_10).build());

        assertCommandFailure(editLessonCommand, model, MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStartTime_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, lessonIndex,
                new EditLessonDescriptorBuilder().withStartTime(VALID_START_TIME_1400_LESSON_WED_2_TO_4).build());

        assertCommandFailure(editLessonCommand, model, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_invalidEndTime_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        EditLessonCommand editLessonCommand = new EditLessonCommand(moduleClassIndex, lessonIndex,
                new EditLessonDescriptorBuilder().withEndTime(VALID_END_TIME_1000_LESSON_FRI_8_TO_10).build());

        assertCommandFailure(editLessonCommand, model, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void equals() {
        final EditLessonCommand standardCommand = new EditLessonCommand(
                INDEX_FIRST_ITEM, INDEX_FIRST_ITEM, DESC_LESSON_FRI_8_TO_10);

        // same values -> returns true
        EditLessonDescriptor copyDescriptor =
                new EditLessonDescriptor(DESC_LESSON_FRI_8_TO_10);
        EditLessonCommand commandWithSameValues = new EditLessonCommand(
                INDEX_FIRST_ITEM, INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(1));

        // different module class index -> returns false
        assertFalse(standardCommand.equals(new EditLessonCommand(
                INDEX_SECOND_ITEM, INDEX_FIRST_ITEM, DESC_LESSON_FRI_8_TO_10)));

        // different lesson index -> returns false
        assertFalse(standardCommand.equals(new EditLessonCommand(
                INDEX_FIRST_ITEM, INDEX_SECOND_ITEM, DESC_LESSON_FRI_8_TO_10)));

        // different class and lesson index -> returns false
        assertFalse(standardCommand.equals(new EditLessonCommand(
                INDEX_SECOND_ITEM, INDEX_SECOND_ITEM, DESC_LESSON_FRI_8_TO_10)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditLessonCommand(
                INDEX_FIRST_ITEM, INDEX_FIRST_ITEM, DESC_LESSON_WED_2_TO_4)));
    }
}
