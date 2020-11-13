package seedu.bagel.logic.commands;

import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.testutil.FlashcardBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBagel(), new UserPrefs());
    }

    @Test
    public void execute_newFlashcard_success() {
        Flashcard validFlashcard = new FlashcardBuilder().withTitle("pvalue").build();

        Model expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        expectedModel.addFlashcard(validFlashcard);

        assertCommandSuccess(new AddCommand(validFlashcard), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), expectedModel);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {
        Flashcard flashcardInList = model.getBagel().getFlashcardList().get(0);
        assertCommandFailure(new AddCommand(flashcardInList), model, AddCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

}
