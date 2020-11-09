package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StartAttemptCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
    }


    @Test
    public void start_attempt_success() {
        Model expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
        assertCommandSuccess(new StartAttemptCommand(), model,
            StartAttemptCommand.MESSAGE_ATTEMPT_ACKNOWLEDGEMENT, expectedModel);
    }

    @Test
    public void double_start_failure() {
        model.startAttempt();
        assertCommandFailure(new StartAttemptCommand(), model, StartAttemptCommand.MESSAGE_ATTEMPT_ALREADY_ONGOING);
    }
}
