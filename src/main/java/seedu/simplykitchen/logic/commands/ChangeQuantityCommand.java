package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.Quantity;
import seedu.simplykitchen.model.tag.Tag;

/**
 * Changes the quantity of an existing food item in the food inventory.
 */
public class ChangeQuantityCommand extends Command {

    public static final String COMMAND_WORD = "changeqty";

    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " INDEX "
            + PREFIX_AMOUNT + "AMOUNT\n"
            + "  Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "+1";

    public static final String MESSAGE_SUCCESS = "The following food item has its quantity changed:\n  %1$s";
    public static final String MESSAGE_QUANTITY_ERROR = "The quantity of the food item cannot be updated to a value "
            + "less than or equal to zero or more than 100000.00.\n";

    public static final double MAX_AMOUNT = Quantity.MAX_VALUE;

    private static final Logger logger = LogsCenter.getLogger("ChangeQuantityCommand.class");

    private final Index index;
    private final double amount;

    /**
     * @param index of the food item in the filtered food list to edit
     * @param amount of the food item to change by
     */
    public ChangeQuantityCommand(Index index, double amount) {
        requireNonNull(index);
        assert amount != 0 && amount > -MAX_AMOUNT && amount < MAX_AMOUNT;

        this.index = index;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToEdit = lastShownList.get(index.getZeroBased());
        Food editedFood = updateFoodQuantity(foodToEdit, amount);

        model.setFood(foodToEdit, editedFood);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        model.commitFoodInventory();
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedFood));
    }

    /**
     * Updates the quantity of the {@code originalFood} by a certain {@code amount}.
     */
    private Food updateFoodQuantity(Food originalFood, double amount) throws CommandException {
        // update quantity value
        Quantity oldQuantity = originalFood.getQuantity();
        double newQuantityValue = oldQuantity.updateQuantityValue(amount);
        logger.log(Level.INFO, "User is trying to update the quantity to " + newQuantityValue);

        // check validity of new quantity value
        if (newQuantityValue <= 0.00 || newQuantityValue > Quantity.MAX_VALUE) {
            throw new CommandException(MESSAGE_QUANTITY_ERROR);
        }

        // update food with new quantity
        Description description = originalFood.getDescription();
        Priority priority = originalFood.getPriority();
        ExpiryDate expiryDate = originalFood.getExpiryDate();
        Quantity newQuantity = oldQuantity.updateQuantity(newQuantityValue);
        Set<Tag> tags = originalFood.getTags();

        return new Food(description, priority, expiryDate, newQuantity, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChangeQuantityCommand)) {
            return false;
        }

        // state checks
        ChangeQuantityCommand c = (ChangeQuantityCommand) other;
        return index.equals(c.index) && amount == c.amount;
    }
}
