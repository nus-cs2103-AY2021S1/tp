package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a OrderItem to the OrderManager. "
            + "Parameters: "
            + " Index of FoodItem in Menu "
            + " [Quantity of Food to add]";

    public static final String MESSAGE_ADD_FIRST_SUCCESS = "%1$s has been added to your Order";
    public static final String MESSAGE_ADD_QUANTITY_SUCCESS = "%1$s has been added to your Order";

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
        List<OrderItem> orderItemList = model.getFilteredOrderItemList(); //Order: list of orderitems
        // Todo: This index value will be that of the chosen vendor. As of now the first menu on the list is chosen
        List<Food> menu = model.getFilteredFoodList(0); // Menu: list of food
        int menuSize;

        try {
            menuSize = menu.size();
        } catch (NullPointerException ex) {
            throw new NullPointerException("Menu file has problems");
        }

        if (addIndex.getZeroBased() >= menuSize) {
            // index provided larger than size of menu
            throw new CommandException(ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
        }

        if (!OrderItem.isValidQuantity(quantity)) {
            throw new CommandException(ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_QUANTITY);
        }

        String addMessage;
        Food toAdd = menu.get(addIndex.getZeroBased()); //get the food from the menu
        OrderItem orderItem = new OrderItem(toAdd, quantity); //check if this orederitem is already in the order
        if (!orderItemList.contains(orderItem)) {
            // completely new addition to the order
            model.addOrderItem(orderItem);
            addMessage = String.format(MESSAGE_ADD_FIRST_SUCCESS, orderItem);
        } else {
            // increment the current order by the specified quantity
            int index = orderItemList.indexOf(orderItem);
            OrderItem original = orderItemList.get(index);
            int newQuantity = original.getQuantity() + this.quantity;
            original.setQuantity(newQuantity);
            model.setOrderItem(original, original);
            addMessage = String.format(MESSAGE_ADD_QUANTITY_SUCCESS, orderItem);
        }
        return new CommandResult(addMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && addIndex.equals(((AddCommand) other).addIndex));
    }

}
