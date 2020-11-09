package seedu.flashcard.logic.commands;

import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import org.junit.jupiter.api.Test;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFlashcardDeck_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFlashcardDeck_success() {
        Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
        expectedModel.setFlashcardDeck(new FlashcardDeck());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
