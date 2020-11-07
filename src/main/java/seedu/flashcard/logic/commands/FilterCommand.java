package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_FAV;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.CategoryEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.FavouriteEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.MultipleFieldsEqualKeywordsPredicate;
import seedu.flashcard.model.flashcard.RatingEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.TagsEqualKeywordsPredicate;

/**
 * Filters and lists flashcards belonging to the category input by user.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters for flashcards based on the fields specified by user. "
            + "At least one field must be provided.\n"
            + "Parameters: [" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_RATING + "RATING] [" + PREFIX_FAV + "<yes|no>] "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + " SDLC " + PREFIX_RATING + " 2 "
            + PREFIX_FAV + " yes " + PREFIX_TAG + " exam";

    private final CategoryEqualsKeywordsPredicate categoryPredicate;
    private final RatingEqualsKeywordsPredicate ratingPredicate;
    private final FavouriteEqualsKeywordsPredicate favouritePredicate;
    private final TagsEqualKeywordsPredicate tagsPredicate;
    private final MultipleFieldsEqualKeywordsPredicate combinedPredicate;

    /**
     * Creates a FilterCommand to filter according to specified predicates
     */
    public FilterCommand(CategoryEqualsKeywordsPredicate categoryPredicate,
                         RatingEqualsKeywordsPredicate ratingPredicate,
                         FavouriteEqualsKeywordsPredicate favouritePredicate,
                         TagsEqualKeywordsPredicate tagsPredicate) {
        this.categoryPredicate = categoryPredicate;
        this.ratingPredicate = ratingPredicate;
        this.favouritePredicate = favouritePredicate;
        this.tagsPredicate = tagsPredicate;
        this.combinedPredicate = new MultipleFieldsEqualKeywordsPredicate(categoryPredicate,
                ratingPredicate, favouritePredicate, tagsPredicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && categoryPredicate.equals(((FilterCommand) other).categoryPredicate)
                && ratingPredicate.equals(((FilterCommand) other).ratingPredicate)
                && favouritePredicate.equals(((FilterCommand) other).favouritePredicate)); // state check
    }
}
