package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.tag.Tag;
import seedu.address.storage.Storage;

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags an order item with remark.\n"
            + "Format: tag INDEX REMARK\n"
            + "- The INDEX refers to the index number of the order item.\n"
            + "- INDEX must be positive number and must not exceed the size of the order list.\n"
            + "- REMARK is any non-empty string\n"
            + "Examples:\n"
            + "tag 2 1 no ice: tags order item INDEX 2, with tag \"1 no ice\"";
    public static final String MESSAGE_TAG_SUCCESS = "Tag \"%s\" has been added to order item %d.\n";

    private final Index tagIndex;
    private final Tag tag;

    /**
     * Creates a {@code tag} for the order item at index {@code tagIndex}.
     *
     * @param tagIndex
     * @param tag
     */
    public TagCommand(Index tagIndex, Tag tag) {
        requireAllNonNull(tagIndex, tag);
        this.tagIndex = tagIndex;
        this.tag = tag;
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
        if (orderItem.getTags().contains(tag)) {
            throw new CommandException(String.format(Messages.MESSAGE_EXISTING_TAG, tag.tagName));
        }
        model.tagOrderItem(orderItem, tag);
        return new CommandResult(String.format(MESSAGE_TAG_SUCCESS, tag.tagName, index + 1));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && tagIndex.equals(((TagCommand) other).tagIndex))
                && tag.equals(((TagCommand) other).tag);
    }
}
