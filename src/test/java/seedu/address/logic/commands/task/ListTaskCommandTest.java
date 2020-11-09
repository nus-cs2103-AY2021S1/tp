package seedu.address.logic.commands.task;

import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;

/**
 * Contains unit tests for ListTaskCommand.
 */
public class ListTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        expectedModel =
                new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
