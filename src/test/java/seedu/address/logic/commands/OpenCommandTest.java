package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.address.testutil.TypicalFlashcards.getTypicalQuickCache;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.VERY_BIG_INDEX_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.Question;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class OpenCommandTest {

    private Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Flashcard flashcardToOpen = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Question question = flashcardToOpen.getQuestion();
        OpenCommand openCommand = new OpenCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(OpenCommand.MESSAGE_OPEN_FLASHCARD_SUCCESS,
                flashcardToOpen);

        assertCommandSuccess(openCommand, model, expectedMessage, model, question, null, true);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = VERY_BIG_INDEX_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertFalse(outOfBoundIndex.getZeroBased() < model.getQuickCache().getFlashcardList().size());

        OpenCommand openCommand = new OpenCommand(outOfBoundIndex);

        assertCommandFailure(openCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }
}
