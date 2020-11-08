package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FlashcardBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFlashcardBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Flashcard;
import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.testutil.FlashcardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Flashcard validFlashcard = new FlashcardBuilder().build();

        CommandResult commandResult = new AddCommand(validFlashcard).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFlashcard), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Flashcard validFlashcard = new FlashcardBuilder().build();
        AddCommand addCommand = new AddCommand(validFlashcard);
        ModelStub modelStub = new ModelStubWithPerson(validFlashcard);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_FLASHCARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Flashcard alice = new FlashcardBuilder().withTitle("Alice").build();
        Flashcard bob = new FlashcardBuilder().withTitle("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getFlashcardBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBookFilePath(Path flashcardBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBook(ReadOnlyFlashcardBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredFlashcardList(String sortOrder) {
            throw new AssertionError("This method could not be called.");
        }

        @Override
        public boolean getIsQuizMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void flipQuizMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showAttempt(Attempt attempt) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedIndex(Question target, String response) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAllSelectedIndex(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCurrentAttempt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void startAttempt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void recordResponse(Response response) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Question> getQuizList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attempt> getAttemptList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Response> getResponseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashcardBook getFlashcardBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void flipFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
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

        @Override
        public void savePerformance() throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean endAttempt() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single flashcard.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Flashcard flashcard;

        ModelStubWithPerson(Flashcard flashcard) {
            requireNonNull(flashcard);
            this.flashcard = flashcard;
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return this.flashcard.isSameFlashcard(flashcard);
        }
    }

    /**
     * A Model stub that always accept the flashcard being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Flashcard> personsAdded = new ArrayList<>();

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return personsAdded.stream().anyMatch(flashcard::isSameFlashcard);
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            personsAdded.add(flashcard);
        }

        @Override
        public ReadOnlyFlashcardBook getFlashcardBook() {
            return new FlashcardBook();
        }
    }

}
