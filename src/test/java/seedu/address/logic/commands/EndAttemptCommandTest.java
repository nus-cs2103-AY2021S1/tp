package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.quiz.Response;

//author zeying99

public class EndAttemptCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
    }

    @Test
    public void no_currentAttempt_failure() {
        assertCommandFailure(new EndAttemptCommand(), model, EndAttemptCommand.NO_ATTEMPT_IN_PROGRESS);
    }

    @Test
    public void empty_attempt_success() {
        Model expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
        model.startAttempt();
        expectedModel.startAttempt();
        assertCommandSuccess(new EndAttemptCommand(), model, EndAttemptCommand.MESSAGE_EMPTY_ATTEMPT, expectedModel);
    }

    @Test
    public void nonEmpty_attempt_success() {
        Model expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
        model.startAttempt();
        expectedModel.startAttempt();
        model.recordResponse(new Response("true", model.getQuizBook().getQuestionList().get(1)));
        expectedModel.recordResponse(new Response("true", model.getQuizBook().getQuestionList().get(1)));
        assertCommandSuccess(new EndAttemptCommand(), model,
            EndAttemptCommand.MESSAGE_ATTEMPT_ACKNOWLEDGEMENT, expectedModel);
    }
}
