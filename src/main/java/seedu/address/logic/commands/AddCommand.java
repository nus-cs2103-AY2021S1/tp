package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.storage.Storage;

//TODO change to fit vendor better
/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    //TODO: Update message to be closer to user guide
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an OrderItem to the OrderManager.\n"
            + "Format: add INDEX [QUANTITY]\n"
            + "- The INDEX refers to the index number shown on the displayed menu list.\n"
            + "- INDEX must be a positive integer and must not exceed the size of the menu list.\n"
            + "- QUANTITY can be specified to indicate the number of item to be added.\n"
                + "     Otherwise, it adds one quantity of the item at the specified index.\n"
            + "Examples:\n"
            + "add 1 1: add item at INDEX 1, of QUANTITY 1\n"
            + "add 2 3: add item at INDEX 2, of QUANTITY 3\n"
            + "add 1: add item at INDEX 1, of default QUANTITY 1";

    public static final String MESSAGE_ADD_SUCCESS = "%s x%d has been added to your Order";

    private final Index addIndex;
    private final int quantity;

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Index index) {
        requireNonNull(index);
        this.addIndex = index;
        this.quantity = 1;
    }

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Index index, int quantity) {
        requireNonNull(index);
        this.addIndex = index;
        this.quantity = quantity;
    }


    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        // Todo: This index value will be that of the chosen vendor. As of now the first menu on the list is chosen
        assert model != null;
        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();
        int index = addIndex.getZeroBased();

        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }
        if (menu.size() <= index) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        }

        OrderItem orderItem = new OrderItem(menu.get(index), quantity);
        model.addOrderItem(orderItem);

        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, orderItem.getName(), this.quantity));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && addIndex.equals(((AddCommand) other).addIndex));
    }

}
