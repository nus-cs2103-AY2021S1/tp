package seedu.address.logic.commands.bidcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;
import seedu.address.model.bid.BidContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBidCommand extends Command {

    public static final String COMMAND_WORD = "find-bid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bids which contains "
            + "the specified keywords (case-insensitive) and displays "
            + "\nthem as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " P10 B12";

    private final BidContainsKeywordsPredicate predicate;

    public FindBidCommand(BidContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBidList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BIDS_LISTED_OVERVIEW, model.getFilteredBidList().size()))
                .setEntity(EntityType.BID);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBidCommand // instanceof handles nulls
                && predicate.equals(((FindBidCommand) other).predicate)); // state check
    }
}
