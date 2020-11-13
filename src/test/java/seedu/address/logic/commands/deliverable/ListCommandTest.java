package seedu.address.logic.commands.deliverable;

import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.showDeliverableAtIndex;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverableBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ModelDeliverableManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private ModelDeliverable modelDeliverable;
    private ModelDeliverable expectedModelDeliverable;

    @BeforeEach
    public void setUp() {
        modelDeliverable = new ModelDeliverableManager(getTypicalDeliverableBook(), new UserPrefs());
        expectedModelDeliverable = new ModelDeliverableManager(modelDeliverable.getDeliverableBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(
                new ListCommand(), modelDeliverable, ListCommand.MESSAGE_SUCCESS, expectedModelDeliverable);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showDeliverableAtIndex(modelDeliverable, INDEX_FIRST);
        assertCommandSuccess(
                new ListCommand(), modelDeliverable, ListCommand.MESSAGE_SUCCESS, expectedModelDeliverable);
    }
}
