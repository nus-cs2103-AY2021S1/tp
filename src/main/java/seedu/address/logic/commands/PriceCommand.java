package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.PriceWithinRangePredicate;
import seedu.address.storage.Storage;

public class PriceCommand extends Command {

    public static final String COMMAND_WORD = "price";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters all food item within a specified price range.\n"
            + "Format: price INEQUALITY PRICE\n"
            + "- INEQUALITY is an inequality sign, of the below formats:\n"
            + "   <: Strictly less than\n"
            + "   <=: Less than or Equal to\n"
            + "   >: Greater than\n"
            + "   >=: Greater than or Equal to\n"
            + "- PRICE must be a non-negative real number.\n"
            + "Examples:\n"
            + "price < 3: lists all food item with price less than $3.\n"
            + "price >= 2: lists all food item with price from $2.";

    private final PriceWithinRangePredicate predicate;

    public PriceCommand(PriceWithinRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }

        model.updateFilteredMenuItemList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FOOD_LISTED_PRICE_CONTEXT,
                        model.getFilteredMenuItemListSize(), predicate), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceCommand // instanceof handles nulls
                && predicate.equals(((PriceCommand) other).predicate)); // state check
    }
}
