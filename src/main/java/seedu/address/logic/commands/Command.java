package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INCORRECT_MODEL;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryModel;

/**
 * Represents command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param models {@code List<Model>} which contains the models which the command should operate on. the 1st
     *                                  element must be an {@code InventoryModel},
     *                                  the 2nd must be a {@code DeliveryModel}
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(List<Model> models) throws CommandException;

    protected DeliveryModel getDeliveryModel(List<Model> models) throws CommandException {
        requireNonNull(models);
        requireNonNull(models.get(1));
        requireDeliveryModel(models.get(1));
        return (DeliveryModel) models.get(1);
    }

    protected InventoryModel getInventoryModel(List<Model> models) throws CommandException {
        requireNonNull(models);
        requireNonNull(models.get(0));
        requireInventoryModel(models.get(0));
        return (InventoryModel) models.get(0);
    }

    private void requireDeliveryModel(Model model) throws CommandException {
        if (!(model instanceof DeliveryModel)) {
            throw new CommandException(MESSAGE_INCORRECT_MODEL);
        }
    }

    private void requireInventoryModel(Model model) throws CommandException {
        if (!(model instanceof InventoryModel)) {
            throw new CommandException(MESSAGE_INCORRECT_MODEL);
        }
    }
}
