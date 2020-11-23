package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FilterEntityCommand;
import seedu.address.model.Model;
import seedu.address.model.person.client.ClientNameContainsKeywordsPredicate;

/**
 * Filters and lists all clients whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterClientCommand extends FilterEntityCommand<ClientNameContainsKeywordsPredicate> {

    public static final String COMMAND_WORD = "filter_client";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all clients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public FilterClientCommand(ClientNameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);

        if (model.getFilteredClientList().size() == 1) {
            return new CommandResult(Messages.MESSAGE_CLIENT_LISTED_OVERVIEW_SINGULAR);
        } else {
            return new CommandResult(
                String.format(Messages.MESSAGE_CLIENT_LISTED_OVERVIEW,
                    model.getFilteredClientList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterClientCommand // instanceof handles nulls
                && predicate.equals(((FilterClientCommand) other).predicate)); // state check
    }
}
