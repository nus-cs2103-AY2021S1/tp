package seedu.address.logic.commands.deliverycommand;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.itemcommand.ItemCommand;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryModel;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.deliverymodel.DeliveryModel.PREDICATE_SHOW_ALL_DELIVERIES;
import static seedu.address.model.inventorymodel.InventoryModel.PREDICATE_SHOW_ALL_ITEMS;

/**
 * Lists all items in the delivery book to the user.
 */
public class DeliveryListCommand extends DeliveryCommand {

    public static final String COMMAND_WORD = "list-d";

    public static final String MESSAGE_SUCCESS = "Listed all items";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireDeliveryModel(model);
        DeliveryModel deliveryModel = (DeliveryModel) model;
        deliveryModel.updateFilteredDeliveryList(PREDICATE_SHOW_ALL_DELIVERIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
