package seedu.address.logic.commands.deliverycommand;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Models;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.deliverymodel.DeliveryModel;

/**
 * Finds and lists all items in delivery book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class DeliveryFindCommand extends DeliveryCommand {

    public static final String COMMAND_WORD = "find-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all deliveries whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "PREFIX only limited to name, phone, address and order\n"
            + "Parameters: " + "PREFIX " + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/Sally";

    private final Predicate<Delivery> predicate;

    public DeliveryFindCommand(Predicate<Delivery> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Models models) {
        requireNonNull(models);
        requireNonNull(models.getDeliveryModel());
        DeliveryModel deliveryModel = models.getDeliveryModel();

        deliveryModel.updateFilteredDeliveryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DELIVERIES_LISTED_OVERVIEW,
                        deliveryModel.getFilteredAndSortedDeliveryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryFindCommand // instanceof handles nulls
                && predicate.equals(((DeliveryFindCommand) other).predicate)); // state check
    }
}
