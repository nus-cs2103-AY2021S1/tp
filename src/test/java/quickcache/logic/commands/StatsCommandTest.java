package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;
import quickcache.model.flashcard.Tag;
import quickcache.testutil.TypicalIndexes;
import quickcache.testutil.TypicalTags;

public class StatsCommandTest {

    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Flashcard flashcardToDisplayStatistics = model.getFilteredFlashcardList()
            .get(TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased());
        Question question = flashcardToDisplayStatistics.getQuestion();
        Statistics statistics = flashcardToDisplayStatistics.getStatistics();
        StatsCommand statsCommand = new StatsCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(StatsCommand.MESSAGE_DISPLAY_STATISTICS_FLASHCARD_SUCCESS,
            flashcardToDisplayStatistics);

        assertCommandSuccess(statsCommand, model, expectedMessage, model, question, statistics);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        showFlashcardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = TypicalIndexes.VERY_BIG_INDEX_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of QuickCache list
        assertFalse(outOfBoundIndex.getZeroBased() < model.getQuickCache().getFlashcardList().size());

        StatsCommand statsCommand = new StatsCommand(outOfBoundIndex);

        assertCommandFailure(statsCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validTag_success() {
        Set<Tag> tagsToMatch = new HashSet<>();
        tagsToMatch.add(TypicalTags.TEST_TAG);
        FlashcardPredicate flashcardPredicate = FlashcardPredicate.prepareOnlyTagsFlashcardPredicate(tagsToMatch);

        StatsCommand statsCommand = new StatsCommand(flashcardPredicate, tagsToMatch);

        String expectedMessage = String.format(StatsCommand.MESSAGE_DISPLAY_STATISTICS_FLASHCARDS_BY_TAG_SUCCESS,
            "with tags [test]");

        ModelManager expectedModel = new ModelManager(model.getQuickCache(), new UserPrefs());
        expectedModel.updateFilteredFlashcardList(flashcardPredicate);

        List<Flashcard> flashcardList = expectedModel.getFilteredFlashcardList();

        Statistics expectedStatistics = getStatisticsFromFlashcardList(flashcardList);

        expectedModel.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel, expectedStatistics);
    }

    @Test
    public void execute_invalidTag_success() {
        Set<Tag> tagsToMatch = new HashSet<>();
        tagsToMatch.add(TypicalTags.INVALID_TAG);
        FlashcardPredicate flashcardPredicate = FlashcardPredicate.prepareOnlyTagsFlashcardPredicate(tagsToMatch);

        StatsCommand statsCommand = new StatsCommand(flashcardPredicate, tagsToMatch);

        String expectedMessage = String.format(
            StatsCommand.MESSAGE_DISPLAY_STATISTICS_FLASHCARDS_BY_TAG_SUCCESS, "with tags [invalid]");

        ModelManager expectedModel = new ModelManager(model.getQuickCache(), new UserPrefs());
        expectedModel.updateFilteredFlashcardList(flashcardPredicate);

        List<Flashcard> flashcardList = expectedModel.getFilteredFlashcardList();

        Statistics expectedStatistics = getStatisticsFromFlashcardList(flashcardList);

        expectedModel.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel, expectedStatistics);
    }

    @Test
    public void equals() {
        StatsCommand statsFirstCommand = new StatsCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        StatsCommand statsSecondCommand = new StatsCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(statsFirstCommand.equals(statsFirstCommand));

        // same values -> returns true
        StatsCommand statsFirstCommandCopy = new StatsCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        assertTrue(statsFirstCommand.equals(statsFirstCommandCopy));

        // different types -> returns false
        assertFalse(statsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(statsFirstCommand.equals(null));

        // different stats command -> returns false
        assertFalse(statsFirstCommand.equals(statsSecondCommand));
    }

    private Statistics getStatisticsFromFlashcardList(List<Flashcard> flashcardList) {
        int timesTested = 0;
        int timesTestedCorrect = 0;

        for (Flashcard flashcard : flashcardList) {
            Statistics statistics = flashcard.getStatistics();
            timesTested += statistics.getTimesTested();
            timesTestedCorrect += statistics.getTimesTestedCorrect();
        }

        return new Statistics(timesTested, timesTestedCorrect);
    }
}
