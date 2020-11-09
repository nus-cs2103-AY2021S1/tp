package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.QuickCache;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Flashcard;
import quickcache.testutil.EditFlashcardDescriptorBuilder;
import quickcache.testutil.FlashcardBuilder;
import quickcache.testutil.TypicalFlashcards;
import quickcache.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model)
 * and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(TypicalFlashcards.getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedForOpenEndedUnfilteredList_success() {
        Flashcard editedFlashcard = new FlashcardBuilder().build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard)
                .buildWithNoChoices();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_choicesInOpenEndedUnfilteredList_throwsCommandException() {
        Flashcard editedFlashcard = new FlashcardBuilder().build();
        Choice[] choices = new Choice[1];
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard)
                .withChoices(choices).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = "Choices should not be provided for open ended question";

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());

        assertThrows(CommandException.class, expectedMessage, () -> editCommand.execute(expectedModel));
    }

    @Test
    public void execute_allFieldsSpecifiedForMultipleChoiceUnfilteredList_success() {
        String[] choices = {"First", "Second", "Third", "Fourth"};
        Flashcard editedFlashcard = new FlashcardBuilder()
            .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer("2").build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        Flashcard editedMultipleChoice = new FlashcardBuilder()
            .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer(choices[1]).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SEVENTH_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedMultipleChoice);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(6), editedMultipleChoice);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_answerLargerThanChoicesForMultipleChoiceUnfilteredList_throwsCommandException() {
        String[] choices = {"First", "Second", "Third", "Fourth"};
        Flashcard editedFlashcard = new FlashcardBuilder()
                .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer("5").build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        Flashcard editedMultipleChoice = new FlashcardBuilder()
                .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer(choices[1]).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SEVENTH_FLASHCARD, descriptor);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(6), editedMultipleChoice);

        assertThrows(CommandException.class, "Answer must be smaller than number of choices", () ->
                editCommand.execute(expectedModel));
    }

    @Test
    public void execute_answerIsNotNumberMultipleChoiceUnfilteredList_throwsCommandException() {
        String[] choices = {"First", "Second", "Third", "Fourth"};
        Flashcard editedFlashcard = new FlashcardBuilder()
                .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer("String").build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        Flashcard editedMultipleChoice = new FlashcardBuilder()
                .withMultipleChoiceQuestion("Multiple Choice Question", choices).withAnswer(choices[1]).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SEVENTH_FLASHCARD, descriptor);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(6), editedMultipleChoice);

        assertThrows(CommandException.class, "Answer must be integer", () ->
                editCommand.execute(expectedModel));
    }

    @Test
    public void execute_sameFlashcardUnfilteredList_throwsCommandException() {
        Flashcard editedFlashcard = model.getFilteredFlashcardList().get(0);
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard)
                .buildWithNoChoices();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD, descriptor);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertThrows(CommandException.class, EditCommand.MESSAGE_DUPLICATE_FLASHCARD, () ->
                editCommand.execute(expectedModel));
    }

    @Test
    public void execute_largeIndexUnfilteredList_throwsCommandException() {
        Flashcard editedFlashcard = model.getFilteredFlashcardList().get(0);
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard)
                .buildWithNoChoices();
        EditCommand editCommand = new EditCommand(TypicalIndexes.VERY_BIG_INDEX_FLASHCARD, descriptor);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX, () ->
                editCommand.execute(expectedModel));
    }

    @Test
    public void execute_filteredList_success() {

        Flashcard flashcardInFilteredList =
            model.getFilteredFlashcardList().get(TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard editedFlashcard =
            new FlashcardBuilder(flashcardInFilteredList).withQuestion(CommandTestUtil.VALID_QUESTION_THREE)
                .build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
            new EditFlashcardDescriptorBuilder().withQuestion(CommandTestUtil.VALID_QUESTION_THREE)
                    .buildWithNoChoices());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new QuickCache(model.getQuickCache()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
            CommandTestUtil.DESC_TWO);

        // same values -> returns true
        EditCommand.EditFlashcardDescriptor copyDescriptor =
            new EditCommand.EditFlashcardDescriptor(CommandTestUtil.DESC_TWO);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD,
            CommandTestUtil.DESC_TWO)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
            CommandTestUtil.DESC_THREE)));
    }

}
