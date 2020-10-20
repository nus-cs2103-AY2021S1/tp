package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FLASHCARD_SUCCESS = "Deleted Flashcard: %1$s";

    private final Index targetIndex;
    private final FlashcardPredicate predicate;
    private final boolean isDeleteByTag;

    private DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.predicate = null;
        this.isDeleteByTag = false;
    }

    private DeleteCommand(FlashcardPredicate predicate) {
        this.targetIndex = null;
        this.predicate = predicate;
        this.isDeleteByTag = true;
    }

    public static DeleteCommand withIndex(Index targetIndex) {
        return new DeleteCommand(targetIndex);
    }

    public static DeleteCommand withPredicate(FlashcardPredicate predicate) {
        return new DeleteCommand(predicate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteFlashcard(flashcardToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
