package seedu.taskmaster.logic.commands;

import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskmaster.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskmaster(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
