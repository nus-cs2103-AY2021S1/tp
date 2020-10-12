package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAnimals.getTypicalZooKeepBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ZooKeepBook;

public class ClearCommandTest {

    @Test
    public void execute_emptyZooKeepBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyZooKeepBook_success() {
        Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());
        expectedModel.setZooKeepBook(new ZooKeepBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
