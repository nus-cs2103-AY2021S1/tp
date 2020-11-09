package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.storage.Storage;

public class UntagCommand extends Command {
    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all the tag of an order item.\n"
            + "Format: untag INDEX\n";
    public static final String MESSAGE_UNTAG_SUCCESS = "Cleared tag(s) of order item %d.\n";

    private final Index tagIndex;

    /**
     * Creates a {@code tag} for the order item at index {@code tagIndex}.
     *
     * @param tagIndex
     */
    public UntagCommand(Index tagIndex) {
        requireNonNull(tagIndex);
        this.tagIndex = tagIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        assert model != null;
        ObservableList<OrderItem> order = model.getObservableOrderItemList();
        int index = tagIndex.getZeroBased();
        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }
        if (order.size() <= index) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        }
        OrderItem orderItem = order.get(index);
        model.untagOrderItem(orderItem);
        return new CommandResult(String.format(MESSAGE_UNTAG_SUCCESS, index + 1));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UntagCommand // instanceof handles nulls
                && tagIndex.equals(((UntagCommand) other).tagIndex));
    }
}
