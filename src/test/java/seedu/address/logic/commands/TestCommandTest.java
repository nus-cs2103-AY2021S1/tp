package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalQuickCache;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MCQ_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.MultipleChoiceQuestion;
import seedu.address.flashcard.Option;
import seedu.address.flashcard.Question;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class TestCommandTest {

    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_answerSpecifiedOpenEndedUnfilteredListCorrect_success() {
        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Question question = flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Answer userAnswer = flashcard.getAnswer();
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        descriptor.setAnswer(answer);
        TestCommand testCommand = new TestCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
                answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, question, true);
    }

    @Test
    public void execute_answerSpecifiedOpenEndedUnfilteredListWrong_success() {
        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Question question = flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Answer userAnswer = model.getFilteredFlashcardList().get(1).getAnswer();
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        descriptor.setAnswer(userAnswer);
        TestCommand testCommand = new TestCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
                answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, question, false);
    }

    @Test
    public void execute_optionSpecifiedMcqUnfilteredListCorrect_success() {
        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_MCQ_FLASHCARD.getZeroBased());
        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Index correctIndex = Index.fromOneBased(3);
        Answer userAnswer = mcq.getAnswerFromIndex(correctIndex);
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        descriptor.setOption(new Option(String.valueOf(correctIndex.getOneBased())));
        TestCommand testCommand = new TestCommand(INDEX_FIRST_MCQ_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
                answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, mcq, true);
    }

    @Test
    public void execute_optionSpecifiedMcqUnfilteredListWrong_success() {
        Flashcard flashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_MCQ_FLASHCARD.getZeroBased());
        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) flashcard.getQuestion();
        Answer answer = flashcard.getAnswer();
        Index incorrectIndex = Index.fromOneBased(2);
        Answer userAnswer = mcq.getAnswerFromIndex(incorrectIndex);
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        descriptor.setOption(new Option(String.valueOf(incorrectIndex.getOneBased())));
        TestCommand testCommand = new TestCommand(INDEX_FIRST_MCQ_FLASHCARD, descriptor);

        String expectedMessage = String.format(TestCommand.MESSAGE_FORMAT,
                answer, userAnswer);

        assertCommandSuccess(testCommand, model, expectedMessage, model, mcq, false);
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
