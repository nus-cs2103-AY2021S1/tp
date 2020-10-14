package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Question;
import seedu.address.model.flashcard.Statistics;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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
