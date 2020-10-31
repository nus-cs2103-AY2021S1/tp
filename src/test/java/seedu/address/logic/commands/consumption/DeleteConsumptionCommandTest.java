package seedu.address.logic.commands.consumption;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showConsumptionAtIndex;
import static seedu.address.testutil.TypicalConsumption.getTypicalWishfulShrinking;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONSUMPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONSUMPTION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.consumption.Consumption;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteConsumptionCommandTest {

    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Consumption consumptionToDelete = model.getFilteredConsumptionList().get(INDEX_FIRST_CONSUMPTION
                .getZeroBased());
        DeleteConsumptionCommand deleteConsumptionCommand = new DeleteConsumptionCommand(INDEX_FIRST_CONSUMPTION);

        String expectedMessage = String.format(DeleteConsumptionCommand.MESSAGE_DELETE_CONSUMPTION_SUCCESS,
                consumptionToDelete);

        ModelManager expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.deleteConsumption(consumptionToDelete);

        assertCommandSuccess(deleteConsumptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredConsumptionList().size() + 1);
        DeleteConsumptionCommand deleteConsumptionCommand = new DeleteConsumptionCommand(outOfBoundIndex);

        assertCommandFailure(deleteConsumptionCommand, model, Messages.MESSAGE_INVALID_CONSUMPTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showConsumptionAtIndex(model, INDEX_FIRST_CONSUMPTION);

        Consumption consumptionToDelete = model.getFilteredConsumptionList().get(INDEX_FIRST_CONSUMPTION
                .getZeroBased());
        DeleteConsumptionCommand deleteConsumptionCommand = new DeleteConsumptionCommand(INDEX_FIRST_CONSUMPTION);

        String expectedMessage = String.format(DeleteConsumptionCommand.MESSAGE_DELETE_CONSUMPTION_SUCCESS,
                consumptionToDelete);

        Model expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.deleteConsumption(consumptionToDelete);
        showNoConsumption(expectedModel);

        assertCommandSuccess(deleteConsumptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showConsumptionAtIndex(model, INDEX_FIRST_CONSUMPTION);

        Index outOfBoundIndex = INDEX_SECOND_CONSUMPTION;
        // ensures that outOfBoundIndex is still in bounds of consumption list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getConsumptionList().size());

        DeleteConsumptionCommand deleteConsumptionCommand = new DeleteConsumptionCommand(outOfBoundIndex);

        assertCommandFailure(deleteConsumptionCommand, model, Messages.MESSAGE_INVALID_CONSUMPTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteConsumptionCommand deleteFirstCommand = new DeleteConsumptionCommand(INDEX_FIRST_CONSUMPTION);
        DeleteConsumptionCommand deleteSecondCommand = new DeleteConsumptionCommand(INDEX_SECOND_CONSUMPTION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteConsumptionCommand deleteFirstCommandCopy = new DeleteConsumptionCommand(INDEX_FIRST_CONSUMPTION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different consumption -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoConsumption(Model model) {
        model.updateFilteredConsumptionList(p -> false);

        assertTrue(model.getFilteredConsumptionList().isEmpty());
    }
}
