package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.ReadOnlyUserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.testutil.FlashcardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_flashcardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFlashcardAdded modelStub = new ModelStubAcceptingFlashcardAdded();
        Flashcard validFlashcard = new FlashcardBuilder().build();

        CommandResult commandResult = new AddCommand(validFlashcard).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFlashcard), modelStub.flashcardsAdded);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {
        Flashcard validFlashcard = new FlashcardBuilder().build();
        AddCommand addCommand = new AddCommand(validFlashcard);
        ModelStub modelStub = new ModelStubWithFlashcard(validFlashcard);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_FLASHCARD, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Flashcard oop = new FlashcardBuilder().withQuestion("What is OOP?")
                .withAnswer("Object-Oriented Programming").build();
        Flashcard simple = new FlashcardBuilder().withQuestion("A simple question?").withAnswer("Yes").build();
        AddCommand addOopCommand = new AddCommand(oop);
        AddCommand addSimpleCommand = new AddCommand(simple);

        // same object -> returns true
        assertTrue(addOopCommand.equals(addOopCommand));

        // same values -> returns true
        AddCommand addOopCommandcopy = new AddCommand(oop);
        assertTrue(addOopCommand.equals(addOopCommandcopy));

        // different types -> returns false
        assertFalse(addOopCommand.equals(1));

        // null -> returns false
        assertFalse(addOopCommand.equals(null));

        // different person -> returns false
        assertFalse(addOopCommand.equals(addSimpleCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFlashcardDeckFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardDeckFilePath(Path flashcardDeckFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashcardDeck getFlashcardDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Flashcard> getFilteredFlashcardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single flashcard.
     */
    private class ModelStubWithFlashcard extends ModelStub {
        private final Flashcard flashcard;

        ModelStubWithFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            this.flashcard = flashcard;
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return this.flashcard.isSameQuestion(flashcard);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingFlashcardAdded extends ModelStub {
        final ArrayList<Flashcard> flashcardsAdded = new ArrayList<>();

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return flashcardsAdded.stream().anyMatch(flashcard::isSameQuestion);
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            flashcardsAdded.add(flashcard);
        }

        @Override
        public ReadOnlyFlashcardDeck getFlashcardDeck() {
            return new FlashcardDeck();
        }
    }

}
