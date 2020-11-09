package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_FLAG;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.SortCriteria;

/**
 * Sorts and lists flashcards according to criteria given by user.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts and lists flashcards according to criteria given by user.\n"
            + "Can sort according to review frequency or success rate of the flashcards in ascending or "
            + "descending order.\n"
            + "Parameters: <success|reviewed> <-a|-d>\n"
            + "Example: " + COMMAND_WORD + " reviewed " + PREFIX_FLAG + "d";

    public static final String MESSAGE_SORTED_REVIEWED_ASCENDING = "Sorted flashcards by review frequency "
            + "in ascending order!";
    public static final String MESSAGE_SORTED_REVIEWED_DESCENDING = "Sorted flashcards by review frequency "
            + "in descending order!";
    public static final String MESSAGE_SORTED_SUCCESS_ASCENDING = "Sorted flashcards by success rate in "
            + "ascending order!";
    public static final String MESSAGE_SORTED_SUCCESS_DESCENDING = "Sorted flashcards by success rate in "
            + "descending order!";
    public static final String MESSAGE_SORTED_INVALID = "Flashcards could not be sorted!";

    private final SortCriteria criteria;

    public SortCommand(SortCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredFlashcardList(criteria);
        String message;
        switch(criteria) {
        case REVIEWED_ASCENDING:
            message = MESSAGE_SORTED_REVIEWED_ASCENDING;
            break;
        case REVIEWED_DESCENDING:
            message = MESSAGE_SORTED_REVIEWED_DESCENDING;
            break;
        case SUCCESS_RATE_ASCENDING:
            message = MESSAGE_SORTED_SUCCESS_ASCENDING;
            break;
        case SUCCESS_RATE_DESCENDING:
            message = MESSAGE_SORTED_SUCCESS_DESCENDING;
            break;
        default:
            message = MESSAGE_SORTED_INVALID;
            break;
        }
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && criteria.equals(((SortCommand) other).criteria)); // state check
    }
}
