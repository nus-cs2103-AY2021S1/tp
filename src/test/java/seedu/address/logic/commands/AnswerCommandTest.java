package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.AnswerCommand.MESSAGE_CURRENTLY_NOT_ATTEMPTING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MCQ_ANSWER_OPTION_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TF_ANSWER_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TF_ANSWER_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureQuiz;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.address.testutil.TypicalQuestions.getTypicalQuizBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.QuizBook;
import seedu.address.model.ReadOnlyFlashcardBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Flashcard;
import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.UniqueResponseList;

/**
 * Contains integration tests (interaction with the Response and Question) and unit tests for
 * {@code AnswerCommand}.
 */
class AnswerCommandTest {

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AnswerCommand(null, VALID_TF_ANSWER_TRUE));
        assertThrows(NullPointerException.class, () -> new AnswerCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_noAttemptedStarted_addFailure() throws CommandException {
        ModelStubNoOngoingAttempt modelStub = new ModelStubNoOngoingAttempt();
        CommandResult commandResult = new AnswerCommand(Index.fromOneBased(1), VALID_TF_ANSWER_TRUE).execute(modelStub);
        assertEquals(MESSAGE_CURRENTLY_NOT_ATTEMPTING, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        ModelStubQuizBook modelStub = new ModelStubQuizBook();
        Index outOfBoundIndex = Index.fromOneBased(modelStub.getQuizList().size() + 1);
        AnswerCommand answerCommand = new AnswerCommand(outOfBoundIndex, VALID_TF_ANSWER_TRUE);

        assertCommandFailureQuiz(answerCommand, modelStub, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTrueFalseAnswerOption_throwsCommandException() {
        ModelStubQuizBook modelStub = new ModelStubQuizBook();
        Index validIndex = Index.fromOneBased(5);
        AnswerCommand randomAnswerCommand = new AnswerCommand(validIndex, "random");
        AnswerCommand incompleteAnswerCommand = new AnswerCommand(validIndex, "t");
        AnswerCommand trailingAnswerCommand = new AnswerCommand(validIndex, "true random");

        assertCommandFailureQuiz(randomAnswerCommand, modelStub, Messages.MESSAGE_INVALID_TF_ANSWER);
        assertCommandFailureQuiz(incompleteAnswerCommand, modelStub, Messages.MESSAGE_INVALID_TF_ANSWER);
        assertCommandFailureQuiz(trailingAnswerCommand, modelStub, Messages.MESSAGE_INVALID_TF_ANSWER);
    }

    @Test
    public void execute_invalidMultipleChoiceAnswerOption_throwsCommandException() {
        ModelStubQuizBook modelStub = new ModelStubQuizBook();
        Index validIndex = Index.fromOneBased(1);
        AnswerCommand negativeAnswerCommand = new AnswerCommand(validIndex, "-1");
        AnswerCommand zeroAnswerCommand = new AnswerCommand(validIndex, "0");
        AnswerCommand outOfBoundsAnswerCommand = new AnswerCommand(validIndex, "5");
        AnswerCommand trailingAnswerCommand = new AnswerCommand(validIndex, "1 random");

        assertCommandFailureQuiz(negativeAnswerCommand, modelStub, Messages.MESSAGE_INVALID_MCQ_ANSWER);
        assertCommandFailureQuiz(zeroAnswerCommand, modelStub, Messages.MESSAGE_INVALID_MCQ_ANSWER);
        assertCommandFailureQuiz(outOfBoundsAnswerCommand, modelStub, Messages.MESSAGE_INVALID_MCQ_ANSWER);
        assertCommandFailureQuiz(trailingAnswerCommand, modelStub, Messages.MESSAGE_INVALID_MCQ_ANSWER);
    }

    @Test
    public void execute_trueFalseAnswer_successful() throws CommandException {
        ModelStubQuizBook modelStub = new ModelStubQuizBook();
        Index validIndex = Index.fromOneBased(5);

        CommandResult commandResultTrue = new AnswerCommand(validIndex, VALID_TF_ANSWER_TRUE).execute(modelStub);
        CommandResult commandResultFalse = new AnswerCommand(validIndex, VALID_TF_ANSWER_FALSE).execute(modelStub);

        assertEquals(String.format(AnswerCommand.MESSAGE_ANSWER_SUCCESS, validIndex.getZeroBased(),
                VALID_TF_ANSWER_TRUE), commandResultTrue.getFeedbackToUser());
        assertEquals(String.format(AnswerCommand.MESSAGE_ANSWER_SUCCESS, validIndex.getZeroBased(),
                VALID_TF_ANSWER_FALSE), commandResultFalse.getFeedbackToUser());
    }

    @Test
    public void execute_multipleChoiceAnswer_successful() throws CommandException {
        ModelStubQuizBook modelStub = new ModelStubQuizBook();
        Index validIndex = Index.fromOneBased(1);

        CommandResult commandResult = new AnswerCommand(validIndex, VALID_MCQ_ANSWER_OPTION_ONE).execute(modelStub);

        assertEquals(String.format(AnswerCommand.MESSAGE_ANSWER_SUCCESS, validIndex.getZeroBased(),
                VALID_MCQ_ANSWER_OPTION_ONE), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        AnswerCommand answerFirstCommand = new AnswerCommand(INDEX_FIRST_FLASHCARD, VALID_TF_ANSWER_TRUE);
        AnswerCommand answerSecondCommandOld = new AnswerCommand(INDEX_SECOND_FLASHCARD, VALID_TF_ANSWER_TRUE);
        AnswerCommand answerSecondCommandNew = new AnswerCommand(INDEX_SECOND_FLASHCARD, VALID_TF_ANSWER_FALSE);

        // same object -> returns true
        assertTrue(answerFirstCommand.equals(answerFirstCommand));

        // same values -> returns true
        AnswerCommand deleteFirstCommandCopy = new AnswerCommand(INDEX_FIRST_FLASHCARD, VALID_TF_ANSWER_TRUE);
        assertTrue(answerFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(answerFirstCommand.equals(1));

        // null -> returns false
        assertFalse(answerFirstCommand.equals(null));

        // different answer -> returns false
        assertFalse(answerFirstCommand.equals(answerSecondCommandOld));
        assertFalse(answerSecondCommandOld.equals(answerSecondCommandNew));
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
        public Path getFlashcardBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBookFilePath(Path flashcardBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBook(ReadOnlyFlashcardBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredFlashcardList(String sortOrder) {
            throw new AssertionError("This method could not be called.");
        }

        @Override
        public boolean getIsQuizMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void flipQuizMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showAttempt(Attempt attempt) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedIndex(Question target, String response) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAllSelectedIndex(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCurrentAttempt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void startAttempt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void recordResponse(Response response) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Question> getQuizList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attempt> getAttemptList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Response> getResponseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public QuizBook getQuizBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashcardBook getFlashcardBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void flipFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
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

        @Override
        public void savePerformance() throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean endAttempt() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that does not have an ongoing attempt and always rejects all answers.
     */
    private class ModelStubNoOngoingAttempt extends AnswerCommandTest.ModelStub {
        @Override
        public boolean hasCurrentAttempt() {
            return false;
        }
    }

    /**
     * A Model stub that have an ongoing attempt and typical QuizBook.
     */
    private class ModelStubQuizBook extends AnswerCommandTest.ModelStub {
        private final QuizBook quizBook = getTypicalQuizBook();
        private final ObservableList<Question> filteredQuizList = this.quizBook.getQuestionList();
        private ObservableList<Response> responseList = new UniqueResponseList().asUnmodifiableObservableList();

        @Override
        public boolean hasCurrentAttempt() {
            return true;
        }

        @Override
        public void setSelectedIndex(Question question, String response) {
            requireAllNonNull(question, response);
        }

        @Override
        public void recordResponse(Response response) {
            requireNonNull(response);
        }

        @Override
        public QuizBook getQuizBook() {
            return this.quizBook;
        }

        @Override
        public ObservableList<Response> getResponseList() {
            return this.responseList;
        }

        @Override
        public ObservableList<Question> getQuizList() {
            return this.filteredQuizList;
        }
    }

}
