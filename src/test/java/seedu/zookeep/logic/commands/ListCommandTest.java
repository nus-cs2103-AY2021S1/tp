package seedu.zookeep.logic.commands;

import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.zookeep.logic.commands.CommandTestUtil.showAnimalAtIndex;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;
import static seedu.zookeep.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
