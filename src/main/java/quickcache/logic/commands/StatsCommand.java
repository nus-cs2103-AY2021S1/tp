package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;

public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the statistics of the flashcard identified by the index number "
            + "used in the displayed flashcard list.\nParameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_STATISTICS_FLASHCARD_SUCCESS =
            "Displayed statistics of Flashcard:\n%1$s";

    private final Index targetIndex;

    public StatsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToDisplayStatistics = lastShownList.get(targetIndex.getZeroBased());
        Question question = flashcardToDisplayStatistics.getQuestion();
        Statistics statistics = flashcardToDisplayStatistics.getStatistics();
        return new CommandResult(String.format(MESSAGE_DISPLAY_STATISTICS_FLASHCARD_SUCCESS,
                flashcardToDisplayStatistics), question, statistics);
    }
}
