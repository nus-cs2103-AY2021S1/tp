package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.deliverable.TitleDescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all deliverables in deliverable book whose title and/or description
 * contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all deliverables whose titles and/or "
            + "descriptions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " homepage navigation";

    private final TitleDescriptionContainsKeywordsPredicate predicate;

    public FindCommand(TitleDescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(ModelDeliverable modelDeliverable) {
        requireNonNull(modelDeliverable);
        modelDeliverable.updateFilteredDeliverableList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DELIVERABLES_LISTED_OVERVIEW,
                        modelDeliverable.getFilteredDeliverableList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
