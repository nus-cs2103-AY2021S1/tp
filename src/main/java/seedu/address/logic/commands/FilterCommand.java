package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.flashcard.CategoryEqualsKeywordsPredicate;

/**
 * Filters and lists flashcards belonging to the category input by user.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters and lists flashcards belonging to the category input by user.\n"
            + "Parameters: CATEGORY\n"
            + "Example: " + COMMAND_WORD + " SDLC";

    public static final String MESSAGE_FILTER_FLASHCARD_SUCCESS = "Filtered flashcards";

    private final CategoryEqualsKeywordsPredicate predicate;

    public FilterCommand(CategoryEqualsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
