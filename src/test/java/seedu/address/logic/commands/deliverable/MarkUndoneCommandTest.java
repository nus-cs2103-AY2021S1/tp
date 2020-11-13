package seedu.address.logic.commands.deliverable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.deliverable.MarkUndoneCommand.MESSAGE_UNDONE_DELIVERABLE_SUCCESS;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverableBook;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverables;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ModelDeliverableManager;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.testutil.DeliverableBuilder;

public class MarkUndoneCommandTest {
    private ModelDeliverable modelDeliverable = new ModelDeliverableManager(
            getTypicalDeliverableBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {
        Deliverable deliverableToOpen = getTypicalDeliverables().get(INDEX_FIRST.getZeroBased());
        Deliverable openedDeliverable = new DeliverableBuilder(deliverableToOpen).withIsComplete(false).build();
        MarkUndoneCommand command = new MarkUndoneCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_UNDONE_DELIVERABLE_SUCCESS, deliverableToOpen);

        ModelDeliverableManager expectedModel = new ModelDeliverableManager(
                modelDeliverable.getDeliverableBook(), new UserPrefs());
        expectedModel.updateDeliverableStatus(deliverableToOpen, openedDeliverable);

        assertCommandSuccess(command, modelDeliverable, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelDeliverable.getFilteredDeliverableList().size() + 1);
        MarkUndoneCommand command = new MarkUndoneCommand(outOfBoundIndex);

        assertCommandFailure(command, modelDeliverable, MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
    }
}
