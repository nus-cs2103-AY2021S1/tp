package quickcache.logic.commands;

import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddOpenEndedQuestionCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalQuickCache(), new UserPrefs());
    }

    @Test
    public void execute_newFlashcard_success() {
        Flashcard validFlashcard = new FlashcardBuilder().build();

        Model expectedModel = new ModelManager(model.getQuickCache(), new UserPrefs());
        expectedModel.addFlashcard(validFlashcard);

        assertCommandSuccess(new AddOpenEndedQuestionCommand(validFlashcard), model,
            String.format(AddOpenEndedQuestionCommand.MESSAGE_SUCCESS, validFlashcard), expectedModel);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {
        Flashcard flashcardInList = model.getQuickCache().getFlashcardList().get(0);
        assertCommandFailure(new AddOpenEndedQuestionCommand(flashcardInList),
            model, AddOpenEndedQuestionCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

}
