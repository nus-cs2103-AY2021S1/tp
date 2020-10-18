package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;

/**
 * Removes a order item identified using it's displayed index from the address book.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the order item identified by the index number used in the displayed menu list.\n"
            + "Parameters: \n"
            + "1. INDEX (must be a positive integer)\n"
            + "2. INDEX (must be a positive integer) QUANTITY (must be a positive integer)\n"
            + "Example: \n"
            + "1. " + COMMAND_WORD + " 1\n"
            + "2. " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_REMOVE_ORDERITEM_SUCCESS = "Removed order item: %1$s";

    private final Index targetIndex;
    private final int quantity;

    /**
     * Creates a RemoveCommand to remove the OrderItem at the specified {@code targetIndex} and remove all its quantity
     */
    public RemoveCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.quantity = Integer.MAX_VALUE;
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
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<OrderItem> menu = model.getFilteredOrderItemList();
        int index = targetIndex.getZeroBased();

        if (menu.size() <= index) {
            throw new CommandException(ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        }
        OrderItem oldItem = menu.get(index);

        OrderItem orderItem = new OrderItem(menu.get(index), quantity);
        model.removeOrderItem(orderItem);

        if (menu.size() == index || !menu.get(index).isSameOrderItem(orderItem)) {
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
