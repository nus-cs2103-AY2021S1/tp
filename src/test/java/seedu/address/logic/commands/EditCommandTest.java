package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.FlashcardBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Flashcard;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;
import seedu.address.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Flashcard editedFlashcard = new FlashcardBuilder().build();
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardBook(model.getFlashcardBook()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFlashcard = Index.fromOneBased(model.getFilteredFlashcardList().size());
        Flashcard lastFlashcard = model.getFilteredFlashcardList().get(indexLastFlashcard.getZeroBased());

        FlashcardBuilder flashcardInList = new FlashcardBuilder(lastFlashcard);
        Flashcard editedFlashcard = flashcardInList.withTitle(VALID_NAME_HEAPING)
            .withTags(VALID_TAG_FINAL).build();

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withName(VALID_NAME_HEAPING)
            .withTags(VALID_TAG_FINAL).build();
        EditCommand editCommand = new EditCommand(indexLastFlashcard, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardBook(model.getFlashcardBook()), new UserPrefs());
        expectedModel.setFlashcard(lastFlashcard, editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, new EditFlashcardDescriptor());
        Flashcard editedFlashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardBook(model.getFlashcardBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_FLASHCARD);

        Flashcard flashcardInFilteredList = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard editedFlashcard = new FlashcardBuilder(flashcardInFilteredList)
            .withTitle(VALID_NAME_BUBBLE_SORT).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                new EditFlashcardDescriptorBuilder().withName(VALID_NAME_BUBBLE_SORT).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new FlashcardBook(model.getFlashcardBook()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Flashcard firstFlashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(firstFlashcard).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_FLASHCARD, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_FLASHCARD);

        // edit flashcard in filtered list into a duplicate in address book
        Flashcard flashcardInList = model.getFlashcardBook().getFlashcardList()
            .get(INDEX_SECOND_FLASHCARD.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                new EditFlashcardDescriptorBuilder(flashcardInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withName(VALID_NAME_HEAPING).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashcardBook().getFlashcardList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFlashcardDescriptorBuilder().withName(VALID_NAME_HEAPING).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_FLASHCARD, DESC_BUBBLE_SORT);

        // same values -> returns true
        EditFlashcardDescriptor copyDescriptor = new EditFlashcardDescriptor(DESC_BUBBLE_SORT);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_FLASHCARD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_FLASHCARD, DESC_BUBBLE_SORT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_FLASHCARD, DESC_HEAPING)));
    }

}
