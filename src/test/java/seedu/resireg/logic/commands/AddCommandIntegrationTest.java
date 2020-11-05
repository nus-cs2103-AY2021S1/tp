package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.student.Student;
import seedu.resireg.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private CommandHistory history = new CommandHistory();

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
        expectedModel.addStudent(validStudent);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(new AddCommand(validStudent), model, history,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent.getNameAsString(),
                    validStudent.getStudentId().value), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getResiReg().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, history, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
