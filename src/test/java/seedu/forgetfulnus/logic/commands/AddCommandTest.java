package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.ReadOnlyUserPrefs;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_flashCardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFlashCardAdded modelStub = new ModelStubAcceptingFlashCardAdded();
        FlashCard validFlashCard = new FlashCardBuilder().build();

        CommandResult commandResult = new AddCommand(validFlashCard).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFlashCard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFlashCard), modelStub.flashCardsAdded);
    }

    @Test
    public void execute_duplicateFlashCard_throwsCommandException() {
        FlashCard validFlashCard = new FlashCardBuilder().build();
        AddCommand addCommand = new AddCommand(validFlashCard);
        ModelStub modelStub = new ModelStubWithFlashCard(validFlashCard);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHRASE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        FlashCard man = new FlashCardBuilder().withGermanPhrase("Mann").build();
        FlashCard woman = new FlashCardBuilder().withGermanPhrase("Frau").build();
        AddCommand addManCommand = new AddCommand(man);
        AddCommand addWomanCommand = new AddCommand(woman);

        // same object -> returns true
        assertTrue(addManCommand.equals(addManCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(man);
        assertTrue(addManCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addManCommand.equals(1));

        // null -> returns false
        assertFalse(addManCommand.equals(null));

        // different flashCard -> returns false
        assertFalse(addManCommand.equals(addWomanCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public Path getGlossaryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGlossaryFilePath(Path glossaryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashCard(FlashCard flashCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGlossary(ReadOnlyGlossary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGlossary getGlossary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getScoreFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScoreFilePath(Path scoreFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScoreList(ScoreList scoreList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ScoreList getScoreList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashCard(FlashCard flashCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashCard(FlashCard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<FlashCard> getFilteredFlashCardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPhraseList(Predicate<FlashCard> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered flashcard list to filter by the predicate in the class.
         */
        @Override
        public void updateFilteredPhraseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCardToScore(FlashCard next) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getQuizModeIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveScore() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setQuizMode(boolean quizMode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isQuizMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRandomQuizMode(boolean isRandomQuiz) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isRandomQuizMode() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Increments the number of correct attempts in current quiz.
         */
        @Override
        public void updateWithCorrectAttempt() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the number of correct attempts in this quiz.
         *
         * @return quizScore
         */
        @Override
        public int getQuizScore() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the total questions in this quiz.
         *
         * @return quizTotalQuestions
         */
        @Override
        public int getQuizTotalQuestions() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Resets the program at the end of a quiz.
         */
        @Override
        public void resetQuiz() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single flashCard.
     */
    private class ModelStubWithFlashCard extends ModelStub {
        private final FlashCard flashCard;

        ModelStubWithFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            this.flashCard = flashCard;
        }

        @Override
        public boolean hasFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            return this.flashCard.isSameFlashCard(flashCard);
        }
    }

    /**
     * A Model stub that always accept the flashCard being added.
     */
    private class ModelStubAcceptingFlashCardAdded extends ModelStub {
        final ArrayList<FlashCard> flashCardsAdded = new ArrayList<>();

        @Override
        public boolean hasFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            return flashCardsAdded.stream().anyMatch(flashCard::isSameFlashCard);
        }

        @Override
        public void addFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            flashCardsAdded.add(flashCard);
        }

        @Override
        public ReadOnlyGlossary getGlossary() {
            return new Glossary();
        }
    }

}
