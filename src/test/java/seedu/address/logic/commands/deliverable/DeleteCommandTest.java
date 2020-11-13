package seedu.address.logic.commands.deliverable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.showDeliverableAtIndex;
import static seedu.address.logic.commands.deliverable.DeleteCommand.MESSAGE_DELETE_DELIVERABLE_SUCCESS;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverableBook;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverables;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ModelDeliverableManager;
import seedu.address.model.deliverable.deliverable.Deliverable;


public class DeleteCommandTest {

    private ModelDeliverable modelDeliverable = new ModelDeliverableManager(
            getTypicalDeliverableBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {
        Deliverable deliverableToDelete = getTypicalDeliverables().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_DELIVERABLE_SUCCESS, deliverableToDelete);

        ModelDeliverableManager expectedModel = new ModelDeliverableManager(
                modelDeliverable.getDeliverableBook(), new UserPrefs());
        expectedModel.deleteDeliverable(deliverableToDelete);

        assertCommandSuccess(deleteCommand, modelDeliverable, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelDeliverable.getFilteredDeliverableList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelDeliverable, MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDeliverableAtIndex(modelDeliverable, INDEX_FIRST);

        Deliverable deliverableToDelete = modelDeliverable.getFilteredDeliverableList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_DELIVERABLE_SUCCESS, deliverableToDelete);

        ModelDeliverable expectedModelDeliverable = new ModelDeliverableManager(
                modelDeliverable.getDeliverableBook(), new UserPrefs());
        expectedModelDeliverable.deleteDeliverable(deliverableToDelete);
        showNoDeliverable(expectedModelDeliverable);

        assertCommandSuccess(deleteCommand, modelDeliverable, expectedMessage, expectedModelDeliverable);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDeliverableAtIndex(modelDeliverable, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelDeliverable.getDeliverableBook().getDeliverableList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelDeliverable, MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different deliverable -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDeliverable(ModelDeliverable modelDeliverable) {
        modelDeliverable.updateFilteredDeliverableList(p -> false);

        assertTrue(modelDeliverable.getFilteredDeliverableList().isEmpty());
    }
}
