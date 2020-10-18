package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;

/**
 * Deletes a vendor identified using it's displayed index from the address book.
 */
public class RemoveCommand extends Command {

    // TODO: Update delete to remove.
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the order item identified by the index number used in the displayed menu list.\n"
            + "Parameters: \n"
            + "1. INDEX (must be a positive integer)\n"
            + "2. INDEX (must be a positive integer) QUANTITY (must be a positive integer)\n"
            + "Example: \n"
            + "1. " + COMMAND_WORD + " 1\n"
            + "2. " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_ORDERITEM_SUCCESS = "Removed order item: %1$s";
    public static final String MESSAGE_CUT_ORDERITEM_SUCCESS = "Reduced order item: %1$s";

    private final Index targetIndex;
    private final int quantity;

    /**
     * Creates a DeleteCommand to delete the OrderItem at the specified {@code targetIndex} and remove all its quantity
     */
    public RemoveCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.quantity = Integer.MAX_VALUE;
    }

    /**
     * Creates a DeleteCommand to delete the OrderItem at the specified {@code targetIndex} and remove its quantity by
     * the specified {@code quantity}
     */
    public RemoveCommand(Index targetIndex, int quantity) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.quantity = quantity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<OrderItem> lastShownList = model.getFilteredOrderItemList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        }

        if (!OrderItem.isValidQuantity(quantity)) {
            throw new CommandException(ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_QUANTITY);
        }

        OrderItem orderItemToDelete = lastShownList.get(targetIndex.getZeroBased());

        String successMessage;
        int currQty = orderItemToDelete.getQuantity();
        int newQty = currQty - quantity;
        if (!OrderItem.isValidQuantity(newQty)) {
            model.deleteOrderItem(orderItemToDelete);
            successMessage = String.format(MESSAGE_DELETE_ORDERITEM_SUCCESS, orderItemToDelete);
        } else {
            orderItemToDelete.setQuantity(newQty);
            model.setOrderItem(orderItemToDelete, orderItemToDelete);
            successMessage = String.format(MESSAGE_CUT_ORDERITEM_SUCCESS, orderItemToDelete);
        }

        // TODO: Refine successMessage
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)); // state check
    }
}
