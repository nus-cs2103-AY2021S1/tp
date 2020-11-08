package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.storage.Storage;

/**
 * Removes a order item identified using it's displayed index from the address book.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the specified item from the supper order.\n"
            + "Format: remove INDEX [QUANTITY]\n"
            + "- INDEX refers to the index number shown in the displayed supper order list.\n"
            + "- INDEX must be a positive integer and must not exceed the size of the supper order list.\n"
            + "- QUANTITY can be specified to indicate the number of item to be deleted."
                + " Otherwise, it deletes all quantities of the item at the specified index.\n"
            + "Examples:\n"
            + "remove 2: remove item at INDEX 2.\n"
            + "remove 1 2: remove item at INDEX 1, of quantity 2.\n";

    public static final String MESSAGE_REMOVE_ORDERITEM_SUCCESS = "Removed order item: %1$s";

    private final Index targetIndex;
    private final int quantity;
    private final boolean specified;

    /**
     * Creates a RemoveCommand to remove the OrderItem at the specified {@code targetIndex} and remove all its quantity
     */
    public RemoveCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.quantity = Integer.MAX_VALUE;
        specified = false;
    }

    /**
     * Creates a RemoveCommand to remove the OrderItem at the specified {@code targetIndex} and remove its quantity by
     * the specified {@code quantity}
     */
    public RemoveCommand(Index targetIndex, int quantity) {
        requireNonNull(targetIndex);
        assert quantity > 0;
        this.targetIndex = targetIndex;
        this.quantity = quantity;
        specified = true;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }

        ObservableList<OrderItem> order = model.getObservableOrderItemList();
        int index = targetIndex.getZeroBased();

        if (order.size() <= index) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        } else if (model.getOrderItemQuantity(index) < quantity && specified) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_QUANTITY);
        }
        OrderItem oldItem = order.get(index);

        OrderItem orderItem = new OrderItem(order.get(index), quantity);
        model.removeOrderItem(orderItem);

        if (order.size() == index || !order.get(index).isSameOrderItemDescription(orderItem)) {
            orderItem = oldItem;
        }

        return new CommandResult(String.format(MESSAGE_REMOVE_ORDERITEM_SUCCESS, orderItem));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)); // state check
    }
}
