package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.order.OrderItem;
//TODO change to fit vendor better
/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a OrderItem to the OrderManager. "
            + "Parameters: "
            + " Index of FoodItem in Menu "
            + " [Quantity of Food to add]";
    // To remove
    public static final String MESSAGE_DUPLICATE_VENDOR = "This vendor already exists in the address book";
    public static final String MESSAGE_SUCCESS = "%1$s has been added to your Order";
    private final OrderItem toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Food food) {
        requireNonNull(food);
        toAdd = new OrderItem(food, 1);
    }

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Food food, int quantity) {
        requireNonNull(food);
        toAdd = new OrderItem(food, quantity);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addOrderItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, getAddMessage()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

    public String getAddMessage() {
        final StringBuilder builder = new StringBuilder();
        builder.append(toAdd.getName()).append(" X ").append(toAdd.getQuantity());
        return builder.toString();
    }
}
