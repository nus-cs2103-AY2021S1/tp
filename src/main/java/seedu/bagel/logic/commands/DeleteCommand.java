package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.bagel.commons.core.Messages;
import seedu.bagel.commons.core.index.Index;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;

/**
 * Deletes a flashcard identified using it's displayed index from Bagel.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FLASHCARD_SUCCESS = "Deleted Flashcard: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard toDelete = lastShownList.get(targetIndex.getZeroBased());
        FlashcardSet toDeleteSet = toDelete.getFlashcardSet();

        model.deleteFlashcard(toDelete);

        if (model.hasFlashcardSet(toDeleteSet)) {
            // System.out.println("Set of deleted flashcard is not empty");
            return new CommandResult(String.format(MESSAGE_DELETE_FLASHCARD_SUCCESS, toDelete));
        }
        // System.out.println("No more flashcards left in current set");
        return new CommandResult(String.format(MESSAGE_DELETE_FLASHCARD_SUCCESS, toDelete), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
