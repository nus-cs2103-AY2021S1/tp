package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.deliverymodel.DeliveryModelManager;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;

/**
 * Represents command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which contains the model which the command operates on
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult execute(Model model) throws CommandException {
        if (model instanceof InventoryModel) {
            return execute((InventoryModel) model, new DeliveryModelManager());
        } else {
            return execute(new InventoryModelManager(), (DeliveryModelManager) model);
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param inventoryModel which contains the {@code InventoryModel} which the command should operate on.
     * @param deliveryModel which contains the {@code DeliveryModel} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(InventoryModel inventoryModel, DeliveryModel deliveryModel)
            throws CommandException;
}
