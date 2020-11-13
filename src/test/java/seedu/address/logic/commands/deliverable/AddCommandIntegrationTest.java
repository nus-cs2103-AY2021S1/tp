package seedu.address.logic.commands.deliverable;

import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverableBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ModelDeliverableManager;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.testutil.DeliverableBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private ModelDeliverable modelDeliverable;

    @BeforeEach
    public void setUp() {
        modelDeliverable = new ModelDeliverableManager(getTypicalDeliverableBook(), new UserPrefs());
    }

    @Test
    public void execute_newDeliverable_success() {
        Deliverable validDeliverable = new DeliverableBuilder().build();

        ModelDeliverable expectedModelDeliverable = new ModelDeliverableManager(
                modelDeliverable.getDeliverableBook(), new UserPrefs());
        expectedModelDeliverable.addDeliverable(validDeliverable);

        assertCommandSuccess(new AddCommand(validDeliverable), modelDeliverable,
                String.format(AddCommand.MESSAGE_SUCCESS, validDeliverable), expectedModelDeliverable);
    }

    @Test
    public void execute_duplicateDeliverable_throwsCommandException() {
        Deliverable deliverableInList = modelDeliverable.getDeliverableBook().getDeliverableList().get(0);
        assertCommandFailure(
                new AddCommand(deliverableInList), modelDeliverable, AddCommand.MESSAGE_DUPLICATE_DELIVERABLE);
    }

}
