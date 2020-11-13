package seedu.bagel.logic.commands;

import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code FlipCommand}.
 */
public class FlipCommandTest {

    private Model model = new ModelManager(getTypicalBagel(), new UserPrefs());

    @Test
    public void execute_unfilteredListOfMultipleFlashcards_success() {
        // test flip when multiple flashcard is on screen, expected to show top of flashcard list
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        ObservableList<Flashcard> flashcards = model.getBagel().getFlashcardList();
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0);

        FlipCommand flipCommand = new FlipCommand();
        String expectedMessage = String.format(FlipCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_firstFlashcardInList_success() {
        // test flip flashcard from index 0 to 1
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        ObservableList<Flashcard> flashcards = model.getBagel().getFlashcardList();
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 1);

        model.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0);
        FlipCommand flipCommand = new FlipCommand();
        String expectedMessage = String.format(FlipCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_lastFlashcardInList_success() {
        // test flip at end of flashcard list, expected to go back to start of the list
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        ObservableList<Flashcard> flashcards = model.getBagel().getFlashcardList();
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0);

        int lastIndex = flashcards.size() - 1;
        model.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == lastIndex);
        FlipCommand flipCommand = new FlipCommand();
        String expectedMessage = String.format(FlipCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterDeleteFirstFlashcardInList_success() {
        // test flip after delete
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        ObservableList<Flashcard> flashcards = model.getBagel().getFlashcardList();
        expectedModel.deleteFlashcard(flashcards.get(0));
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 1);

        model.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0);
        model.deleteFlashcard(flashcards.get(0));
        FlipCommand flipCommand = new FlipCommand();
        String expectedMessage = String.format(FlipCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterDeleteLastFlashcardInList_success() {
        // test flip after delete last flashcard in list, expect to go back to first flashcard in list
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
        ObservableList<Flashcard> flashcards = model.getBagel().getFlashcardList();
        int lastIndex = flashcards.size() - 1;
        expectedModel.deleteFlashcard(flashcards.get(lastIndex));
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0);

        model.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == lastIndex);
        model.deleteFlashcard(flashcards.get(lastIndex));
        FlipCommand flipCommand = new FlipCommand();
        String expectedMessage = String.format(FlipCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyBagel_success() {
        // test flip at empty list, expect no error and nothing happens
        Model expectedModel = new ModelManager();
        model = new ModelManager();
        FlipCommand flipCommand = new FlipCommand();
        String expectedMessage = String.format(FlipCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

}
