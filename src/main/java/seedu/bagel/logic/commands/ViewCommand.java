package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.bagel.commons.core.Messages;
import seedu.bagel.commons.core.index.Index;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * Displays the identified flashcard using it's displayed index from Bagel to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_FLASHCARD_SUCCESS = "Displaying flashcard";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToView = lastShownList.get(targetIndex.getZeroBased());
        Predicate<Flashcard> predicateViewFlashcard = flashcard -> flashcard.isSameFlashcard(flashcardToView);
        model.viewFlashcard(predicateViewFlashcard);

        return new CommandResult(String.format(MESSAGE_VIEW_FLASHCARD_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
