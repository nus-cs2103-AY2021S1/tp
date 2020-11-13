package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewAllStudentsCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewAllStudentsCommand.
 */
public class ViewAllStudentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
    }

    @Test
    public void execute_studentListNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewAllStudentsCommand(), model,
                ViewAllStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_studentListIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ViewAllStudentsCommand(), model,
                ViewAllStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewAllStudentsCommand viewAllStudentsCommand = new ViewAllStudentsCommand();
        assertThrows(NullPointerException.class, () -> viewAllStudentsCommand.execute(emptyModel));
    }

    @Test
    public void execute_studentListNotFiltered_commandTargetFeatureAccurate() {
        ViewAllStudentsCommand viewAllStudentsCommand = new ViewAllStudentsCommand();
        CommandResult commandResult = viewAllStudentsCommand.execute(model);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Students, commandTargetFeature);
    }
}
