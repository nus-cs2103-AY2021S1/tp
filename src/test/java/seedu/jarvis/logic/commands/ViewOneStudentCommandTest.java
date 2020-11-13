package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.commands.view.ViewOneStudentCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.student.Name;
import seedu.jarvis.testutil.TypicalManagers;
import seedu.jarvis.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewOneStudentCommand.
 */
public class ViewOneStudentCommandTest {

    private Name studentName;
    private Model model;

    @BeforeEach
    public void setUp() {
        studentName = new Name(TypicalStudents.ALICE.getName().fullName);
        model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
    }

    @Test
    public void constructor_nullStudentName_throwsNullPointerException() {
        Name name = null;
        assertThrows(NullPointerException.class, () -> new ViewOneStudentCommand(name));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Name studentName = TypicalStudents.ALICE.getName();
        Model model = null;
        ViewOneStudentCommand viewOneStudentCommand = new ViewOneStudentCommand(studentName);
        assertThrows(NullPointerException.class, () -> viewOneStudentCommand.execute(model));
    }

    @Test
    public void execute_studentNameExists_viewSuccess() {
        Name studentName = TypicalStudents.ALICE.getName();
        ViewOneStudentCommand command = new ViewOneStudentCommand(studentName);
        Model actualModel = new ModelManager(TypicalStudents.getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        String expectedMessage = String.format(ViewOneStudentCommand.MESSAGE_SUCCESS, studentName);

        Model expectedModel = new ModelManager(actualModel.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.updateFilteredPersonList(student -> student.getName().equals(studentName));

        assertCommandSuccess(command, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentNameDoesNotExist_throwsCommandException() {
        Name nonExistentName = TypicalStudents.DIMITRI.getName();
        ViewOneStudentCommand viewOneStudentCommand = new ViewOneStudentCommand(nonExistentName);
        Model actualModel = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        assertCommandFailure(viewOneStudentCommand, actualModel, Messages.MESSAGE_STUDENT_NAME_NOT_FOUND);
    }

    @Test
    public void execute_studentNameExists_commandTargetFeatureAccurate() throws CommandException {
        Name studentName = TypicalStudents.ALICE.getName();
        ViewOneStudentCommand command = new ViewOneStudentCommand(studentName);
        Model actualModel = new ModelManager(TypicalStudents.getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        CommandResult commandResult = command.execute(actualModel);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Students, commandTargetFeature);
    }
}
