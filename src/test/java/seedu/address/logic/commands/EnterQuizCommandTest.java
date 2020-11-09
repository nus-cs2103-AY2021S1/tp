package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class EnterQuizCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
    }

    @Test
    public void enterQuiz_from_flashcardMode() {
        Model expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
        assertCommandSuccess(new EnterQuizCommand(), model,
            EnterQuizCommand.MESSAGE_ENTER_ACKNOWLEDGEMENT, expectedModel);
    }
}
