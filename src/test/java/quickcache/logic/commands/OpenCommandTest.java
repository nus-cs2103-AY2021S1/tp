package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static quickcache.testutil.TypicalIndexes.VERY_BIG_INDEX_FLASHCARD;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.Question;
import quickcache.testutil.TypicalIndexes;

public class OpenCommandTest {

    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void equals() {
        OpenCommand firstOpenCommand = new OpenCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        OpenCommand secondOpenCommand = new OpenCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(firstOpenCommand.equals(firstOpenCommand));

        // same values -> returns true
        OpenCommand firstOpenCommandCopy = new OpenCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD);
        assertTrue(firstOpenCommand.equals(firstOpenCommandCopy));

        // different types -> returns false
        assertFalse(firstOpenCommand.equals(1));

        // null -> returns false
        assertFalse(firstOpenCommand.equals(null));

        // different index -> returns false
        assertFalse(firstOpenCommand.equals(secondOpenCommand));
    }

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
        // ensures that outOfBoundIndex is still in bounds of QuickCache list
        assertFalse(outOfBoundIndex.getZeroBased() < model.getQuickCache().getFlashcardList().size());

        OpenCommand openCommand = new OpenCommand(outOfBoundIndex);

        assertCommandFailure(openCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }
}
