package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.order.OrderItem;

//TODO change to fit vendor better
/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    //TODO: Update message to be closer to user guide
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a OrderItem to the OrderManager. "
            + "Parameters: "
            + " Index of FoodItem in Menu "
            + " [Quantity of Food to add]";

    public static final String MESSAGE_ADD_SUCCESS = "%1$s has been added to your Order";

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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Todo: This index value will be that of the chosen vendor. As of now the first menu on the list is chosen
        assert model != null;
        ObservableList<Food> menu = model.getFilteredFoodList();
        int index = addIndex.getZeroBased();

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }
        if (menu.size() <= index) {
            throw new CommandException(ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        }

        OrderItem orderItem = new OrderItem(menu.get(index), quantity);
        try {
            model.addOrderItem(orderItem);
        } catch (CommandException e) {
            throw e;
        }
        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, orderItem));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && addIndex.equals(((AddCommand) other).addIndex));
    }

}
