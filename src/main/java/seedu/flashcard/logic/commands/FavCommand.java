package seedu.flashcard.logic.commands;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Favourite a flashcard identified using it's displayed index from the list of flashcards.
 */
public class FavCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVOURITE_FLASHCARD_SUCCESS = "Favourite Flashcard: %1$s";

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
        assert flashcardToFavourite != null: "Index is invalid";

        Flashcard favouredFlashcard = createFavouriteFlashcard(flashcardToFavourite);
        model.setFlashcard(flashcardToFavourite, favouredFlashcard);
        return new CommandResult(String.format(MESSAGE_FAVOURITE_FLASHCARD_SUCCESS, favouredFlashcard));
    }

    public Flashcard createFavouriteFlashcard(Flashcard flashcardToFavourite) {
        if (flashcardToFavourite.isFavourite()) {
            return flashcardToFavourite;
        }

        Question question = flashcardToFavourite.getQuestion();
        Answer answer = flashcardToFavourite.getAnswer();
        Category category = flashcardToFavourite.getCategory();
        Note note = flashcardToFavourite.getNote();
        return new Flashcard(question, answer, category, note, true);
    }
}
