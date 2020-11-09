package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.semester.Semester;

public class StartCommandTest {

    private Model model = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartCommand(null));
    }

    @Test
    public void equals() throws ParseException {
        Semester y1s1 = Semester.Y1S1;
        Semester y1s2 = Semester.Y1S2;
        StartCommand startY1s1 = new StartCommand(y1s1);
        StartCommand startY1s2 = new StartCommand(y1s2);

        // same object -> returns true
        assertTrue(startY1s1.equals(startY1s1));

        // same values -> returns true
        StartCommand startY1s1Copy = new StartCommand(y1s1);
        assertTrue(startY1s1.equals(startY1s1Copy));

        // different types -> returns false
        assertFalse(startY1s1.equals(1));

        // null -> returns false
        assertFalse(startY1s1.equals(null));

        // different sem -> returns false
        assertFalse(startY1s1.equals(startY1s2));
    }

    @Test
    public void execute_validSemester_success() throws ParseException {
        Semester semesterToStart = Semester.Y1S1;
        StartCommand startCommand = new StartCommand(semesterToStart);

        String expectedMessage = String.format(StartCommand.MESSAGE_START_SEMESTER_SUCCESS, semesterToStart);

        assertCommandSuccess(startCommand, model, expectedMessage, model);
    }
}
