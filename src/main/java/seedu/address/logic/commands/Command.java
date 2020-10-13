package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INCORRECT_MODEL;

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
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    protected void requireDeliveryModel(Model model) throws CommandException {
        if (!(model instanceof DeliveryModel)) {
            throw new CommandException(MESSAGE_INCORRECT_MODEL);
        }
    }

    protected void requireInventoryModel(Model model) throws CommandException {
        if (!(model instanceof InventoryModel)) {
            throw new CommandException(MESSAGE_INCORRECT_MODEL);
        }
    }
}
