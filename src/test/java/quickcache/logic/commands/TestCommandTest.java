package quickcache.logic.commands;

import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_MCQ_FLASHCARD;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.index.Index;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.Option;
import quickcache.model.flashcard.Question;

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
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
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
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
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
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
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
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
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
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
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
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        TestCommand testCommand = new TestCommand(INDEX_FIRST_MCQ_FLASHCARD, descriptor);

        String expectedMessage = TestCommand.MESSAGE_NO_OPTION_PROVIDED;

        assertCommandFailure(testCommand, model, expectedMessage);
    }

}
