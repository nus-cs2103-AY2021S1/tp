package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.Question;

public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_OPEN_FLASHCARD_SUCCESS = "Opened Flashcard:\n\n%1$s";

    private final Index targetIndex;

    public OpenCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToOpen = lastShownList.get(targetIndex.getZeroBased());
        Question question = flashcardToOpen.getQuestion();
        return new CommandResult(String.format(MESSAGE_OPEN_FLASHCARD_SUCCESS, flashcardToOpen), question,
                null, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenCommand // instanceof handles nulls
                && targetIndex.equals(((OpenCommand) other).targetIndex)); // state check
    }
}
