package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import quickcache.commons.core.GuiSettings;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.QuickCache;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.ReadOnlyUserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.testutil.FlashcardBuilder;

public class AddMultipleChoiceQuestionCommandTest {

    @Test
    public void constructor_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMultipleChoiceQuestionCommand(null));
    }

    @Test
    public void execute_flashcardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFlashcardAdded modelStub = new ModelStubAcceptingFlashcardAdded();
        String[] choices = {"First, Second, Third, Fourth"};
        Flashcard validFlashcard = new FlashcardBuilder().withMultipleChoiceQuestion("Sample Question", choices)
            .withAnswer("2").build();

        CommandResult commandResult = new AddMultipleChoiceQuestionCommand(validFlashcard).execute(modelStub);

        assertEquals(String.format(AddMultipleChoiceQuestionCommand.MESSAGE_SUCCESS, validFlashcard),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFlashcard), modelStub.flashcardsAdded);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {
        String[] choices = {"First, Second, Third, Fourth"};
        Flashcard validFlashcard = new FlashcardBuilder().withMultipleChoiceQuestion("Sample Question", choices)
            .withAnswer("2").build();

        AddMultipleChoiceQuestionCommand addMultipleChoiceQuestionCommand =
            new AddMultipleChoiceQuestionCommand(validFlashcard);
        ModelStub modelStub = new ModelStubWithFlashcard(validFlashcard);

        assertThrows(CommandException.class, AddOpenEndedQuestionCommand.MESSAGE_DUPLICATE_FLASHCARD, () ->
            addMultipleChoiceQuestionCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        String[] choices = {"First, Second, Third, Fourth"};
        Flashcard first = new FlashcardBuilder().withMultipleChoiceQuestion("Sample one", choices)
            .withAnswer("2").build();
        Flashcard second = new FlashcardBuilder().withMultipleChoiceQuestion("Sample two", choices)
            .withAnswer("2").build();
        AddMultipleChoiceQuestionCommand addSampleMcqQuestionOne = new AddMultipleChoiceQuestionCommand(first);
        AddMultipleChoiceQuestionCommand addSampleMcqQuestionTwo = new AddMultipleChoiceQuestionCommand(second);

        // same object -> returns true
        assertTrue(addSampleMcqQuestionOne.equals(addSampleMcqQuestionOne));

        // same values -> returns true
        AddMultipleChoiceQuestionCommand addSampleMcqQuestionOneCopy = new AddMultipleChoiceQuestionCommand(first);
        assertTrue(addSampleMcqQuestionOne.equals(addSampleMcqQuestionOneCopy));

        // different types -> returns false
        assertFalse(addSampleMcqQuestionOne.equals(1));

        // null -> returns false
        assertFalse(addSampleMcqQuestionOne.equals(null));

        // different flashcard -> returns false
        assertFalse(addSampleMcqQuestionOne.equals(addSampleMcqQuestionTwo));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getQuickCacheFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setQuickCacheFilePath(Path quickCacheFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyQuickCache getQuickCache() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setQuickCache(ReadOnlyQuickCache newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Flashcard> getFilteredFlashcardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single flashcard.
     */
    private class ModelStubWithFlashcard extends ModelStub {
        private final Flashcard flashcard;

        ModelStubWithFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            this.flashcard = flashcard;
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return this.flashcard.isSameFlashcard(flashcard);
        }
    }

    /**
     * A Model stub that always accept the flashcard being added.
     */
    private class ModelStubAcceptingFlashcardAdded extends ModelStub {
        final ArrayList<Flashcard> flashcardsAdded = new ArrayList<>();

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return flashcardsAdded.stream().anyMatch(flashcard::isSameFlashcard);
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            flashcardsAdded.add(flashcard);
        }

        @Override
        public ReadOnlyQuickCache getQuickCache() {
            return new QuickCache();
        }
    }

}
