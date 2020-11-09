package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleNameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all module codes which contain any of "
            + "the specified keywords (non-case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2100";

    private final ModuleNameContainsKeywordsPredicate predicate;

    public FindCommand(ModuleNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        int searchResultsListSize = model.getFilteredModuleList().size();
        if (searchResultsListSize == 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_NO_MODULES_FOUND),
                    false, false, false, false, true);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, searchResultsListSize),
                    false, false, false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
