package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Finds and lists all active(unarchived) persons in address book whose tag(s) contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindByTagCommand extends Command {

    public static final String COMMAND_WORD = "c-tag-find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all employees whose tag(s) contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers"
            + " in the Employee Directory.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " friday monday PartTime";

    private final TagContainsKeywordsPredicate predicate;

    public FindByTagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByTagCommand // instanceof handles nulls
                && predicate.equals(((FindByTagCommand) other).predicate)); // state check
    }
}
