package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoRedoLimitReachedException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryModel;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_ACKNOWLEDGEMENT = "Reverted to previous state";

    @Override
    public CommandResult execute(InventoryModel inventoryModel, DeliveryModel deliveryModel) {
        requireAllNonNull(inventoryModel, deliveryModel);

        String message = MESSAGE_UNDO_ACKNOWLEDGEMENT;

        try {
            inventoryModel.undo();
            deliveryModel.undo();
        } catch (UndoRedoLimitReachedException e) {
            message = e.getMessage();
        }

        return new CommandResult(message);
    }
}
