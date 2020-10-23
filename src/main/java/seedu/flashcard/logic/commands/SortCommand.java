package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.SortCriteria;

/**
 * Sorts and lists flashcards according to criteria given by user.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts and lists flashcards according to criteria given by user.\n"
            + "Parameters: Sort Criteria (leastReviewed/mostReviewed/successRate)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + " SDLC";

    private final SortCriteria criteria;

    public SortCommand(SortCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredFlashcardList(criteria);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && criteria.equals(((SortCommand) other).criteria)); // state check
    }
}
