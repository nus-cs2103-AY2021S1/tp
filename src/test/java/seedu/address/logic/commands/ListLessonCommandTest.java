package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODEL;
import static seedu.address.testutil.TypicalPlanus.getTypicalPlanus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListLessonCommand.
 */
public class ListLessonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPlanus(), new UserPrefs());
        expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListLessonCommand.MESSAGE_SUCCESS, false,
                false, true, false);
        assertCommandSuccess(new ListLessonCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showLessonAtIndex(model, INDEX_FIRST_MODEL);
        CommandResult expectedCommandResult = new CommandResult(ListLessonCommand.MESSAGE_SUCCESS, false,
                false, true, false);
        assertCommandSuccess(new ListLessonCommand(), model, expectedCommandResult, expectedModel);
    }
}
