package seedu.taskmaster.logic.commands;

import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalStudents;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;

public class RandomStudentCommandTest {

    private final Session existingSession = new Session(
            new SessionName("Existing Session"),
            new SessionDateTime(LocalDateTime.of(2020, 11, 1, 12, 0)),
            getTypicalStudents());
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
    }

    @Test
    public void execute_nullCurrentSessions_exceptionThrown() {

        RandomStudentCommand randomStudentCommand = new RandomStudentCommand();

        String expectedMessage = "Please select a session first!";
        assertCommandFailure(randomStudentCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptySessionList_exceptionThrown() {
        model.setSessions(new ArrayList<>());

        RandomStudentCommand randomStudentCommand = new RandomStudentCommand();

        String expectedMessage = "There are no sessions yet!";
        assertCommandFailure(randomStudentCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptySession_exceptionThrown() {
        Session emptySession = new Session(
                new SessionName("Empty Session"),
                new SessionDateTime(LocalDateTime.of(2020, 11, 1, 12, 0)),
                new ArrayList<>());
        model.addSession(emptySession);

        RandomStudentCommand randomStudentCommand = new RandomStudentCommand();

        String expectedMessage = "The student record list has no students who are present!";

        assertCommandFailure(randomStudentCommand, model, expectedMessage);
    }

    @Test
    public void execute_success() {
        long seed = System.currentTimeMillis();

        model.addSession(existingSession);
        model.markAllStudents(AttendanceType.PRESENT);

        RandomStudentCommand randomStudentCommand = new RandomStudentCommand(new Random(seed));

        String expectedMessage = RandomStudentCommand.MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel.addSession(existingSession);
        expectedModel.markAllStudents(AttendanceType.PRESENT);
        expectedModel.showRandomStudent(new Random(seed));

        assertCommandSuccess(randomStudentCommand, model, expectedMessage, expectedModel);
    }


}
