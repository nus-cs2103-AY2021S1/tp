package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.flashcard.Flashcard;

public class ClearStatsCommand extends Command {

    public static final String COMMAND_WORD = "clearstats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the statistics of the flashcard identified by the index number "
            + "used in the displayed flashcard list.\nParameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLEAR_STATISTICS_FLASHCARD_SUCCESS =
            "Cleared statistics of Flashcard %d";

    private final Index targetIndex;

    public ClearStatsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToClearStatistics = lastShownList.get(targetIndex.getZeroBased());
        Flashcard updatedFlashcard = flashcardToClearStatistics.getFlashcardAfterClearStatistics();

        model.setFlashcard(flashcardToClearStatistics, updatedFlashcard);
        return new CommandResult(String.format(MESSAGE_CLEAR_STATISTICS_FLASHCARD_SUCCESS,
                targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearStatsCommand // instanceof handles nulls
                && targetIndex.equals(((ClearStatsCommand) other).targetIndex)); // state check
    }
}
