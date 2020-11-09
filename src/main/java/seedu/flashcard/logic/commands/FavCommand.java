package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.flashcard.Statistics;
import seedu.flashcard.model.tag.Tag;


/**
 * Favourites a flashcard identified using it's displayed index from the list of flashcards.
 */
public class FavCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer greater than 0)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVOURITE_FLASHCARD_SUCCESS = "Favourited Flashcard: %1$s";

    public static final String MESSAGE_FLASHCARD_IS_ALREADY_FAVOURITED_ERROR = "This flashcard is already favourited.";

    private final Index targetIndex;

    public FavCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToFavourite = lastShownList.get(targetIndex.getZeroBased());
        assert flashcardToFavourite != null : "Index is invalid";

        if (flashcardToFavourite.isFavourite()) {
            throw new CommandException(MESSAGE_FLASHCARD_IS_ALREADY_FAVOURITED_ERROR);
        }

        Flashcard favouritedFlashcard = createFavouriteFlashcard(flashcardToFavourite);

        model.setFlashcard(flashcardToFavourite, favouritedFlashcard);
        return new CommandResult(String.format(MESSAGE_FAVOURITE_FLASHCARD_SUCCESS, favouritedFlashcard));
    }

    private static Flashcard createFavouriteFlashcard(Flashcard flashcardToFavourite) {
        Question question = flashcardToFavourite.getQuestion();
        Answer answer = flashcardToFavourite.getAnswer();
        Category category = flashcardToFavourite.getCategory();
        Note note = flashcardToFavourite.getNote();
        Rating rating = flashcardToFavourite.getRating();
        Set<Tag> tags = flashcardToFavourite.getTags();
        Diagram diagram = flashcardToFavourite.getDiagram();
        Statistics statistics = flashcardToFavourite.getStatistics();
        return new Flashcard(question, answer, category, note, rating, tags, diagram, statistics, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavCommand // instanceof handles nulls
                && targetIndex.equals(((FavCommand) other).targetIndex)); // state check
    }
}
