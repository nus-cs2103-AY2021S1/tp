package seedu.address.logic.commands.timetable;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1800;
import static seedu.address.model.timetable.DurationTest.DURATION_1630_1730;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.CS2030;
import static seedu.address.testutil.TypicalLessons.MA1521;
import static seedu.address.testutil.TypicalSlots.getTypicalFitNus;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Slot;

public class TimetableAddLessonCommandTest {

    public static final String MESSAGE_SUCCESS = "Slot added to Timetable: %1$s";
    public static final String MESSAGE_MISSING_LESSON = "This lesson does not exist in fitNUS";
    public static final String MESSAGE_DUPLICATE_SLOT = "This slot already exists in your timetable";
    public static final String MESSAGE_OVERLAP_SLOT = "This slot overlaps with another slot in your timetable";

    private static final Model typicalModel = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TimetableAddLessonCommand(null, Day.MONDAY, DURATION_1600_1800));
    }

    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TimetableAddLessonCommand(MA1521, null, DURATION_1600_1800));
    }

    @Test
    public void constructor_nullDuration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TimetableAddLessonCommand(MA1521, Day.MONDAY, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        TimetableAddLessonCommand command = new TimetableAddLessonCommand(MA1521, Day.MONDAY, DURATION_1600_1800);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_lessonDoesNotExist_throwsCommandException() {
        TimetableAddLessonCommand command = new TimetableAddLessonCommand(CS2030, Day.MONDAY, DURATION_1600_1800);
        assertCommandFailure(command, typicalModel, MESSAGE_MISSING_LESSON);
    }

    @Test
    public void execute_duplicateSlot_throwsCommandException() {
        TimetableAddLessonCommand command = new TimetableAddLessonCommand(MA1521, Day.FRIDAY, DURATION_1600_1800);
        assertCommandFailure(command, typicalModel, MESSAGE_DUPLICATE_SLOT);
    }

    @Test
    public void execute_overlapSlot_throwsCommandException() {
        TimetableAddLessonCommand command = new TimetableAddLessonCommand(MA1521, Day.FRIDAY, DURATION_1630_1730);
        assertCommandFailure(command, typicalModel, MESSAGE_OVERLAP_SLOT);
    }

    @Test
    public void execute_validSlot_success() {
        TimetableAddLessonCommand command = new TimetableAddLessonCommand(MA1521, Day.MONDAY, DURATION_1600_1800);

        Model expectedModel = new ModelManager(typicalModel.getFitNus(), new UserPrefs());
        Slot slot = new Slot(MA1521, Day.MONDAY, DURATION_1600_1800);
        expectedModel.addSlotToTimetable(slot);
        String expectedMessage = String.format(MESSAGE_SUCCESS, slot);

        assertCommandSuccess(command, typicalModel, expectedMessage, expectedModel);
    }
}
