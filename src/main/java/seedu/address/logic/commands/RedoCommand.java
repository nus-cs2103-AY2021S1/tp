package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoRedoLimitReachedException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryModel;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_ACKNOWLEDGEMENT = "Redone command";

    @Override
    public CommandResult execute(InventoryModel inventoryModel, DeliveryModel deliveryModel) {
        requireAllNonNull(inventoryModel, deliveryModel);

        String message = MESSAGE_REDO_ACKNOWLEDGEMENT;

        try {
            inventoryModel.redo();
            deliveryModel.redo();
        } catch (UndoRedoLimitReachedException e) {
            message = e.getMessage();
        }

        return new CommandResult(message);
    }
}
