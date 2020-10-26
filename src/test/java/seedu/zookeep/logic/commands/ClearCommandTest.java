package seedu.zookeep.logic.commands;

import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;

import org.junit.jupiter.api.Test;

import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.ZooKeepBook;

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
