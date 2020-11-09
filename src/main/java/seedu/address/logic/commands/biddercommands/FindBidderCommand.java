package seedu.address.logic.commands.biddercommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all bidders in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBidderCommand extends Command {

    public static final String COMMAND_WORD = "find-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bidders whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "\n\nParameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "\n\nExample: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindBidderCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBidderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BIDDERS_LISTED_OVERVIEW, model.getFilteredBidderList().size()))
                .setEntity(EntityType.BIDDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBidderCommand // instanceof handles nulls
                && predicate.equals(((FindBidderCommand) other).predicate)); // state check
    }
}
