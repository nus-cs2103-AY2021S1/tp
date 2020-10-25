package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.pivot.commons.core.Messages;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.DetailsContainsKeywordsPredicate;

/**
 * Finds and lists all cases in PIVOT whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all cases whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final DetailsContainsKeywordsPredicate predicate;

    public FindCommand(DetailsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCaseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CASES_LISTED_OVERVIEW, model.getFilteredCaseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
