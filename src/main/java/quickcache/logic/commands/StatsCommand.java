package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;
import quickcache.model.flashcard.Tag;

public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Displays the statistics of the flashcard identified by the index number "
        + "used in the displayed flashcard list or a set of flashcards identified by their tags.\n"
        + "Parameters (either but not both):\n"
        + "1. INDEX (must be a positive integer)\n"
        + "2. [TAGS]\n"
        + "Example: \n"
        + "1. " + COMMAND_WORD + " 1\n"
        + "2. " + COMMAND_WORD + " " + PREFIX_TAG + "MCQ";

    public static final String MESSAGE_DISPLAY_STATISTICS_FLASHCARD_SUCCESS =
        "Displayed statistics of Flashcard:\n\n%1$s";

    public static final String MESSAGE_DISPLAY_STATISTICS_FLASHCARDS_BY_TAG_SUCCESS =
        "Displayed statistics of Flashcards:\n\n%1$s";

    private final Index targetIndex;
    private final FlashcardPredicate predicate;
    private final Set<Tag> tagsToMatch;
    private final boolean isStatsByTag;

    /**
     * Instantiates a stats command.
     *
     * @param targetIndex of the question in the filtered question list to test.
     */
    public StatsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.predicate = null;
        this.tagsToMatch = null;
        this.isStatsByTag = false;
    }

    /**
     * Instantiates a stats command.
     *
     * @param predicate To filter on model.
     * @param tagsToMatch To generate the output message.
     */
    public StatsCommand(FlashcardPredicate predicate, Set<Tag> tagsToMatch) {
        this.targetIndex = null;
        this.predicate = predicate;
        this.tagsToMatch = tagsToMatch;
        this.isStatsByTag = true;
    }

    private String createStatsWithTagsMessage() {
        assert isStatsByTag && tagsToMatch != null;
        return tagsToMatch.stream()
            .reduce("with tags ", (
                    curr, next) -> curr + next.toString() + " ",
                String::concat)
            .trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isStatsByTag) {
            model.updateFilteredFlashcardList(predicate);
            List<Flashcard> filteredList = model.getFilteredFlashcardList();
            Statistics aggregatedStatistics = getAggregatedStatistics(filteredList);
            String statsWithTagsMessage = createStatsWithTagsMessage();

            // necessary to display all the remaining flashcards in the list
            model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);

            return new CommandResult(
                String.format(MESSAGE_DISPLAY_STATISTICS_FLASHCARDS_BY_TAG_SUCCESS,
                    statsWithTagsMessage), aggregatedStatistics);
        } else {
            requireNonNull(targetIndex);
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

    private Statistics getAggregatedStatistics(List<Flashcard> filteredList) {
        int timesTested = 0;
        int timesTestedCorrect = 0;
        for (Flashcard flashcard : filteredList) {
            timesTested += flashcard.getStatistics().getTimesTested();
            timesTestedCorrect += flashcard.getStatistics().getTimesTestedCorrect();
        }
        return new Statistics(timesTested, timesTestedCorrect);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        StatsCommand that = (StatsCommand) object;
        return isStatsByTag == that.isStatsByTag
            && Objects.equals(targetIndex, that.targetIndex)
            && Objects.equals(predicate, that.predicate)
            && Objects.equals(tagsToMatch, that.tagsToMatch);
    }

}
