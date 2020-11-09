package seedu.address.logic.commands.sellercommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindSellerCommand extends Command {

    public static final String COMMAND_WORD = "find-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all sellers whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "\n\nParameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "\n\nExample: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindSellerCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSellerList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SELLERS_LISTED_OVERVIEW, model.getFilteredSellerList().size()))
                .setEntity(EntityType.SELLER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSellerCommand // instanceof handles nulls
                && predicate.equals(((FindSellerCommand) other).predicate)); // state check
    }
}
