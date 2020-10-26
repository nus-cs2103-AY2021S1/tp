package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.food.PriceWithinRangePredicate;

public class PriceCommand extends Command {

    public static final String COMMAND_WORD = "price";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists Food in within price range.\n"
        + "Parameters: INEQUALITY PRICE";

    private final PriceWithinRangePredicate predicate;

    public PriceCommand(PriceWithinRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        model.updateFilteredFoodList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FOOD_LISTED_PRICE_CONTEXT,
                        model.getFilteredFoodListSize(), predicate), false, false, true);
    }
}
