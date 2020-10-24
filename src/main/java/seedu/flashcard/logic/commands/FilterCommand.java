package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Filters and lists flashcards belonging to the category input by user.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters and lists flashcards belonging to the category input by user.\n"
            + "Parameters: " + PREFIX_CATEGORY + " CATEGORY\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + " SDLC";

    private final CategoryEqualsKeywordsPredicate categoryPredicate;
    private final RatingEqualsKeywordsPredicate ratingPredicate;
    private final FavouriteEqualsKeywordsPredicate favouritePredicate;
    private final MultipleFieldsEqualsKeywordsPredicate combinedPredicate;

    public FilterCommand(CategoryEqualsKeywordsPredicate categoryPredicate,
                         RatingEqualsKeywordsPredicate ratingPredicate,
                         FavouriteEqualsKeywordsPredicate favouritePredicate) {
        this.categoryPredicate = categoryPredicate;
        this.ratingPredicate = ratingPredicate;
        this.favouritePredicate = favouritePredicate;
        this.combinedPredicate = new MultipleFieldsEqualsKeywordsPredicate(categoryPredicate,
                ratingPredicate, favouritePredicate);
//        if (categoryPredicate.getCategory() != null) {
//            System.out.println("category added");
//            predicateList.add(categoryPredicate);
//        }
//        if (ratingPredicate.getRating() != null) {
//            System.out.println("rating added");
//            predicateList.add(ratingPredicate);
//        }
//        if (favouritePredicate.getIsFavourite() != null) {
//            System.out.println("favourite added");
//            predicateList.add(favouritePredicate);
//        }
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
                && categoryPredicate.equals(((FilterCommand) other).categoryPredicate)); // state check
    }
}
