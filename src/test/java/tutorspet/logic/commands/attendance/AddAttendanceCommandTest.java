package tutorspet.logic.commands.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutorspet.commons.core.Messages.MESSAGE_DUPLICATE_ATTENDANCE;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_WEEK;
import static tutorspet.commons.core.Messages.MESSAGE_MISSING_LINK;
import static tutorspet.logic.commands.CommandTestUtil.VALID_ATTENDANCE;
import static tutorspet.logic.commands.CommandTestUtil.VALID_WEEK_1;
import static tutorspet.logic.commands.CommandTestUtil.VALID_WEEK_5;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutorspet.logic.commands.attendance.AddAttendanceCommand.MESSAGE_COMMIT;
import static tutorspet.logic.commands.attendance.AddAttendanceCommand.MESSAGE_SUCCESS;
import static tutorspet.logic.util.ModuleClassUtil.addAttendanceToModuleClass;
import static tutorspet.logic.util.ModuleClassUtil.getLessonFromModuleClass;
import static tutorspet.testutil.Assert.assertThrows;
import static tutorspet.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutorspet.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static tutorspet.testutil.TypicalTutorsPet.getTypicalTutorsPet;

import org.junit.jupiter.api.Test;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.model.Model;
import tutorspet.model.ModelManager;
import tutorspet.model.UserPrefs;
import tutorspet.model.attendance.Attendance;
import tutorspet.model.attendance.Week;
import tutorspet.model.lesson.Lesson;
import tutorspet.model.moduleclass.ModuleClass;
import tutorspet.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) for {@code AddAttendanceCommand}.
 */
public class AddAttendanceCommandTest {

    private final Model model = new ModelManager(getTypicalTutorsPet(), new UserPrefs());

    @Test
    public void constructor_nullAttendance_throwsNullPointerException() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Week targetWeek = VALID_WEEK_5;

        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, targetWeek, null));
    }

    @Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Week targetWeek = VALID_WEEK_1;
        Attendance targetAttendance = VALID_ATTENDANCE;

        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(null, lessonIndex, studentIndex,
                        targetWeek, targetAttendance));
        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(moduleClassIndex, null, studentIndex,
                        targetWeek, targetAttendance));
        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, null,
                        targetWeek, targetAttendance));
    }

    @Test
    public void constructor_nullWeek_throwsNullPointerException() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Attendance targetAttendance = VALID_ATTENDANCE;

        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, null, targetAttendance));
    }

    @Test
    public void execute_success() throws CommandException {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Week targetWeek = VALID_WEEK_5;
        Attendance targetAttendance = VALID_ATTENDANCE;

        ModuleClass moduleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Lesson lesson = getLessonFromModuleClass(moduleClass, lessonIndex);
        Student student = model.getFilteredStudentList().get(studentIndex.getZeroBased());
        ModuleClass modifiedModuleClass = addAttendanceToModuleClass(
                moduleClass, lessonIndex, targetWeek, student, targetAttendance);

        String commitMessage = String.format(MESSAGE_COMMIT, student.getName(), moduleClass.getName(),
                lesson.printLesson(), targetWeek);
        String expectedMessage = String.format(MESSAGE_SUCCESS,
                student.getName(), moduleClass.getName(), lesson.printLesson(), targetWeek, targetAttendance);
        Model expectedModel = new ModelManager(model.getTutorsPet(), new UserPrefs());
        expectedModel.setModuleClass(moduleClass, modifiedModuleClass);
        expectedModel.commit(commitMessage);

        AddAttendanceCommand addAttendanceCommand =
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, targetWeek, targetAttendance);

        assertCommandSuccess(addAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existingAttendance_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Week targetWeek = VALID_WEEK_1;
        Attendance targetAttendance = VALID_ATTENDANCE;

        AddAttendanceCommand addAttendanceCommand =
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, targetWeek, targetAttendance);

        assertCommandFailure(addAttendanceCommand, model, MESSAGE_DUPLICATE_ATTENDANCE);
    }

    @Test
    public void execute_studentNotInClass_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_THIRD_ITEM;
        Week targetWeek = VALID_WEEK_1;
        Attendance targetAttendance = VALID_ATTENDANCE;

        AddAttendanceCommand addAttendanceCommand = new AddAttendanceCommand(moduleClassIndex, lessonIndex,
                studentIndex, targetWeek, targetAttendance);

        assertCommandFailure(addAttendanceCommand, model, MESSAGE_MISSING_LINK);
    }

    @Test
    public void execute_invalidClassIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleClassList().size() + 1);
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Week targetWeek = VALID_WEEK_1;
        Attendance targetAttendance = VALID_ATTENDANCE;

        AddAttendanceCommand addAttendanceCommand =
                new AddAttendanceCommand(outOfBoundIndex, lessonIndex, studentIndex, targetWeek, targetAttendance);

        assertCommandFailure(addAttendanceCommand, model, MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLessonIndex_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        ModuleClass moduleClass = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(moduleClass.getLessons().size() + 1);
        Index studentIndex = INDEX_FIRST_ITEM;
        Week targetWeek = VALID_WEEK_1;
        Attendance targetAttendance = VALID_ATTENDANCE;

        AddAttendanceCommand addAttendanceCommand = new AddAttendanceCommand(moduleClassIndex,
                outOfBoundIndex, studentIndex, targetWeek, targetAttendance);

        assertCommandFailure(addAttendanceCommand, model, MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStudentIndex_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Week targetWeek = VALID_WEEK_1;
        Attendance targetAttendance = VALID_ATTENDANCE;

        AddAttendanceCommand addAttendanceCommand = new AddAttendanceCommand(moduleClassIndex,
                lessonIndex, outOfBoundIndex, targetWeek, targetAttendance);

        assertCommandFailure(addAttendanceCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidWeek_failure() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Lesson lesson = model.getFilteredModuleClassList().get(moduleClassIndex.getZeroBased())
            .getLessons().get(lessonIndex.getZeroBased());
        Week invalidWeek =
                new Week(Index.fromOneBased(lesson.getAttendanceRecordList().getAttendanceRecordList().size() + 1));
        Attendance targetAttendance = VALID_ATTENDANCE;

        AddAttendanceCommand addAttendanceCommand =
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, invalidWeek, targetAttendance);

        assertCommandFailure(addAttendanceCommand, model, MESSAGE_INVALID_WEEK);
    }

    @Test
    public void equals() {
        Index moduleClassIndex = INDEX_FIRST_ITEM;
        Index lessonIndex = INDEX_FIRST_ITEM;
        Index studentIndex = INDEX_FIRST_ITEM;
        Week week1 = new Week(Index.fromOneBased(1));
        Week week2 = new Week(Index.fromOneBased(2));
        Attendance attendanceScore90 = new Attendance(90);
        Attendance attendanceScore80 = new Attendance(80);
        AddAttendanceCommand addAttendanceCommand = new AddAttendanceCommand(moduleClassIndex, lessonIndex,
                studentIndex, week1, attendanceScore90);

        // same object -> returns true
        assertTrue(addAttendanceCommand.equals(addAttendanceCommand));

        // same value -> returns true
        AddAttendanceCommand duplicateAddAttendanceCommand = new AddAttendanceCommand(moduleClassIndex,
                lessonIndex, studentIndex, week1, attendanceScore90);
        assertTrue(addAttendanceCommand.equals(duplicateAddAttendanceCommand));

        // different type -> returns false
        assertFalse(addAttendanceCommand.equals(5));

        // null -> returns false
        assertFalse(addAttendanceCommand.equals(null));

        // different attendances -> returns false
        AddAttendanceCommand addAttendanceCommandDifferentAttendance =
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, week1, attendanceScore80);
        assertFalse(addAttendanceCommand.equals(addAttendanceCommandDifferentAttendance));

        // different weeks -> return false
        AddAttendanceCommand addAttendanceCommandDifferentWeek =
                new AddAttendanceCommand(moduleClassIndex, lessonIndex, studentIndex, week2, attendanceScore90);
        assertFalse(addAttendanceCommand.equals(addAttendanceCommandDifferentWeek));
    }
}
