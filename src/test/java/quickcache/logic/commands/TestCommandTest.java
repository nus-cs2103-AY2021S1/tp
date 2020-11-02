package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_MCQ_FLASHCARD;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_OPEN_ENDED_FLASHCARD;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.TestCommand.TestAnswerDescriptor;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.Option;
import quickcache.model.flashcard.Question;
import quickcache.testutil.TypicalIndexes;

class TestCommandTest {

    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_answerSpecifiedOpenEndedUnfilteredListCorrect_success() {
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());

        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard expectedFlashcard = flashcard.getFlashcardAfterTestSuccess();

        // updates tested flashcard with new flashcard state
        expectedModel.setFlashcard(flashcard, expectedFlashcard);

        Question question = flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Answer userAnswer = flashcard.getAnswer();
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        descriptor.setAnswer(answer);
        TestCommand testCommand = new TestCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
            answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, expectedModel, question, true, true);
    }

    @Test
    public void execute_answerSpecifiedOpenEndedUnfilteredListWrong_success() {
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());

        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard expectedFlashcard = flashcard.getFlashcardAfterTestFailure();

        // updates tested flashcard with new flashcard state
        expectedModel.setFlashcard(flashcard, expectedFlashcard);

        Question question = flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Answer userAnswer = model.getFilteredFlashcardList().get(1).getAnswer();
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        descriptor.setAnswer(userAnswer);
        TestCommand testCommand = new TestCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
            answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, question, false, true);
    }

    @Test
    public void execute_optionSpecifiedMcqUnfilteredListCorrect_success() {
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());

        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_MCQ_FLASHCARD.getZeroBased());
        Flashcard expectedFlashcard = flashcard.getFlashcardAfterTestSuccess();

        // updates tested flashcard with new flashcard state
        expectedModel.setFlashcard(flashcard, expectedFlashcard);

        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Index correctIndex = Index.fromOneBased(3);
        Answer userAnswer = mcq.getAnswerFromIndex(correctIndex);
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        descriptor.setOption(new Option(String.valueOf(correctIndex.getOneBased())));
        TestCommand testCommand = new TestCommand(INDEX_FIRST_MCQ_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
            answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, mcq, true, true);
    }

    @Test
    public void execute_optionSpecifiedMcqUnfilteredListWrong_success() {
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());

        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_MCQ_FLASHCARD.getZeroBased());
        Flashcard expectedFlashcard = flashcard.getFlashcardAfterTestFailure();

        // updates tested flashcard with new flashcard state
        expectedModel.setFlashcard(flashcard, expectedFlashcard);

        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Index incorrectIndex = Index.fromOneBased(2);
        Answer userAnswer = mcq.getAnswerFromIndex(incorrectIndex);
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        descriptor.setOption(new Option(String.valueOf(incorrectIndex.getOneBased())));
        TestCommand testCommand = new TestCommand(INDEX_FIRST_MCQ_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
            answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, mcq, false, true);
    }

    @Test
    public void execute_answerUnspecifiedOpenEndedUnfilteredListCorrect_failure() {
        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Question question = flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Answer userAnswer = flashcard.getAnswer();
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        TestCommand testCommand = new TestCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = TestCommand.MESSAGE_NO_ANSWER_PROVIDED;

        assertCommandFailure(testCommand, model, expectedMessage);
    }

    @Test
    public void execute_optionUnspecifiedMcqUnfilteredListCorrect_failure() {
        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_MCQ_FLASHCARD.getZeroBased());
        Question question = flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Answer userAnswer = flashcard.getAnswer();
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        TestCommand testCommand = new TestCommand(INDEX_FIRST_MCQ_FLASHCARD, descriptor);

        String expectedMessage = TestCommand.MESSAGE_NO_OPTION_PROVIDED;

        assertCommandFailure(testCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        showFlashcardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = TypicalIndexes.VERY_BIG_INDEX_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertFalse(outOfBoundIndex.getZeroBased() < model.getQuickCache().getFlashcardList().size());

        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        TestCommand testCommand = new TestCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(testCommand , model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TestAnswerDescriptor descriptor = new TestAnswerDescriptor();
        TestCommand testFirstCommand = new TestCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD, descriptor);
        TestCommand testSecondCommand = new TestCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD, descriptor);

        // same object -> returns true
        assertTrue(testFirstCommand.equals(testFirstCommand));

        // same values -> returns true
        TestCommand testFirstCommandCopy = new TestCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD, descriptor);
        assertTrue(testFirstCommand.equals(testFirstCommandCopy));

        // different types -> returns false
        assertFalse(testFirstCommand.equals(1));

        // null -> returns false
        assertFalse(testFirstCommand.equals(null));

        // different test command -> returns false
        assertFalse(testFirstCommand.equals(testSecondCommand));
    }

    @Test
    public void testAnswerDescriptor_equals() {
        TestAnswerDescriptor descriptorMcq = new TestAnswerDescriptor();
        Flashcard flashcardMcq = model.getFilteredFlashcardList().get(INDEX_FIRST_MCQ_FLASHCARD.getZeroBased());
        descriptorMcq.setAnswer((flashcardMcq.getAnswer()));

        TestAnswerDescriptor descriptorMcqCopy = new TestAnswerDescriptor();
        descriptorMcqCopy.setAnswer((flashcardMcq.getAnswer()));
        TestAnswerDescriptor descriptorOpenEnded = new TestCommand.TestAnswerDescriptor();
        Flashcard flashcardOpenEnded = model.getFilteredFlashcardList().get(
            INDEX_FIRST_OPEN_ENDED_FLASHCARD.getZeroBased());
        descriptorOpenEnded.setAnswer((flashcardOpenEnded.getAnswer()));

        // same object -> returns true
        assertTrue(descriptorMcq.equals(descriptorMcq));

        // same values -> returns true
        assertTrue(descriptorMcq.equals(descriptorMcqCopy));

        // different types -> returns false
        assertFalse(descriptorMcq.equals(1));

        // null -> returns false
        assertFalse(descriptorMcq.equals(null));

        // different descriptor -> returns false
        assertFalse(descriptorMcq.equals(descriptorOpenEnded));

    }

}
