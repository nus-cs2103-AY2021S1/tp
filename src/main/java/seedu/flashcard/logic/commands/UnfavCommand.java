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
 * Unfavourite a flashcard identified using it's displayed index from the list of flashcards.
 */
public class UnfavCommand extends Command {

    public static final String COMMAND_WORD = "unfav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unfavourites the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer greater than 0)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNFAVOURITE_FLASHCARD_SUCCESS = "Unfavourited Flashcard: %1$s";

    public static final String MESSAGE_FLASHCARD_NOT_FAVOURITED = "This flashcard is not favourited.";

    private final Index targetIndex;

    public UnfavCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToUnfavourite = lastShownList.get(targetIndex.getZeroBased());
        assert flashcardToUnfavourite != null : "Index is invalid";


        if (!flashcardToUnfavourite.isFavourite()) {
            throw new CommandException(MESSAGE_FLASHCARD_NOT_FAVOURITED);
        }

        Flashcard unfavouredFlashcard = createUnfavouriteFlashcard(flashcardToUnfavourite);
        model.setFlashcard(flashcardToUnfavourite, unfavouredFlashcard);
        return new CommandResult(String.format(MESSAGE_UNFAVOURITE_FLASHCARD_SUCCESS, unfavouredFlashcard));
    }

    private static Flashcard createUnfavouriteFlashcard(Flashcard flashcardToUnfavourite) {

        Question question = flashcardToUnfavourite.getQuestion();
        Answer answer = flashcardToUnfavourite.getAnswer();
        Category category = flashcardToUnfavourite.getCategory();
        Note note = flashcardToUnfavourite.getNote();
        Rating rating = flashcardToUnfavourite.getRating();
        Set<Tag> tags = flashcardToUnfavourite.getTags();
        Diagram diagram = flashcardToUnfavourite.getDiagram();
        Statistics statistics = flashcardToUnfavourite.getStatistics();
        return new Flashcard(question, answer, category, note, rating, tags, diagram, statistics, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnfavCommand // instanceof handles nulls
                && targetIndex.equals(((UnfavCommand) other).targetIndex)); // state check
    }
}
