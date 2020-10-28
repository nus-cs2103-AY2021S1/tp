package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ALL_CASES;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ARCHIVED_CASES;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.DetailsContainsKeywordsPredicate;

/**
 * Finds and lists all cases in PIVOT whose details contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all cases whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final DetailsContainsKeywordsPredicate predicate;

    /**
     * Creates a FindCommand to find cases in the current section based on the given predicate.
     *
     * @param predicate The predicate used to filter cases.
     */
    public FindCommand(DetailsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (StateManager.atArchivedSection()) {
            model.updateFilteredCaseList(predicate.and(PREDICATE_SHOW_ARCHIVED_CASES));
        }

        if (StateManager.atDefaultSection()) {
            model.updateFilteredCaseList(predicate.and(PREDICATE_SHOW_ALL_CASES));
        }

        return new CommandResult(
                String.format(UserMessages.MESSAGE_CASES_LISTED_OVERVIEW, model.getFilteredCaseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
