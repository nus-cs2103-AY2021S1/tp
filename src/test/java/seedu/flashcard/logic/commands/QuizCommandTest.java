package seedu.flashcard.logic.commands;

import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.logic.commands.QuizCommand.MESSAGE_INITIATE_QUIZ_SUCCESS;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import org.junit.jupiter.api.Test;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;

public class QuizCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());

    @Test
    public void execute_quiz_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_INITIATE_QUIZ_SUCCESS, false, false, false,
                true);
        assertCommandSuccess(new QuizCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_emptyFlashcardList_throwsCommandException() {
        Model model = new ModelManager();
        QuizCommand quizCommand = new QuizCommand();
        assertCommandFailure(quizCommand, model, QuizCommand.MESSAGE_INITIATE_QUIZ_ERROR);
    }

}
