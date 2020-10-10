package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalQuickCache;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.flashcard.Flashcard;
import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.QuickCache;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;
import seedu.address.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedForOpenEndedUnfilteredList_success() {
        Flashcard editedFlashcard = new FlashcardBuilder().build();
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedForMultipleChoiceUnfilteredList_success() {
        String[] choices = {"First", "Second", "Third", "Fourth"};
        Flashcard editedFlashcard = new FlashcardBuilder()
                .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer("2").build();
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        Flashcard editedMultipleChoice = new FlashcardBuilder()
                .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer(choices[1]).build();
        EditCommand editCommand = new EditCommand(INDEX_SEVENTH_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedMultipleChoice);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(6), editedMultipleChoice);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        Flashcard flashcardInFilteredList = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard editedFlashcard = new FlashcardBuilder(flashcardInFilteredList).withQuestion(VALID_QUESTION_BOB)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_FLASHCARD, DESC_AMY);

        // same values -> returns true
        EditFlashcardDescriptor copyDescriptor = new EditFlashcardDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_FLASHCARD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_FLASHCARD, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_FLASHCARD, DESC_BOB)));
    }

}
