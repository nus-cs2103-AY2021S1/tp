package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.problem.Problem;
import seedu.address.testutil.ProblemBuilder;
import seedu.address.ui.Mode;


class AddProblemCommandTest {
    @Test
    public void constructor_nullProblem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProblemCommand(null));
    }

    @Test
    public void execute_problemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProblemAdded modelStub = new ModelStubAcceptingProblemAdded();
        Problem validProblem = new ProblemBuilder().build();

        CommandResult commandResult = new AddProblemCommand(validProblem).execute(modelStub);

        assertEquals(String.format(AddProblemCommand.MESSAGE_SUCCESS, validProblem), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProblem), modelStub.problemsAdded);
    }

    @Test
    public void execute_duplicateProblem_throwsCommandException() {
        Problem validProblem = new ProblemBuilder().build();
        AddProblemCommand addProblemCommand = new AddProblemCommand(validProblem);
        ModelStub modelStub = new ModelStubWithProblem(validProblem);

        assertThrows(CommandException.class,
                AddProblemCommand.MESSAGE_DUPLICATE_PROB, () -> addProblemCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Problem p1 = new ProblemBuilder().withSeverity("high").build();
        Problem p2 = new ProblemBuilder().withSeverity("medium").build();
        AddProblemCommand addP1Command = new AddProblemCommand(p1);
        AddProblemCommand addP2Command = new AddProblemCommand(p2);

        // same object -> returns true
        assertTrue(addP1Command.equals(addP1Command));

        // same values -> returns true
        AddProblemCommand addP1CommandCopy = new AddProblemCommand(p1);
        assertTrue(addP1Command.equals(addP1CommandCopy));

        // different types -> returns false
        assertFalse(addP1Command.equals(1));

        // null -> returns false
        assertFalse(addP1Command.equals(null));

        // different problems -> returns false
        assertFalse(addP1Command.equals(addP2Command));
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
        public Path getLibraryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibraryFilePath(Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibrary(ReadOnlyLibrary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBook(Book target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBook(Book target, Book editedBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Book> getFilteredBookList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookList(Predicate<Book> predicate, Mode mode) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a book with the same identity as {@code book} exists in the library.
         *
         * @param problem
         */
        @Override
        public boolean hasProblem(Problem problem) {
            return false;
        }

        /**
         * Deletes the given book.
         * The book must exist in the library.
         *
         * @param problem
         */
        @Override
        public void deleteProblem(Problem problem) {

        }

        @Override
        public void addProblem(Problem problem) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given book {@code target} with {@code editedBook}.
         * {@code target} must exist in the library.
         * The book identity of {@code editedBook} must not be the same as another existing book in the library.
         *
         * @param target
         * @param problem
         */
        @Override
        public void setProblem(Problem target, Problem problem) {

        }

        /**
         * Returns an unmodifiable view of the filtered book list
         */
        @Override
        public ObservableList<Problem> getFilteredProblemList() {
            return null;
        }

        /**
         * Updates the filter of the filtered book list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @param mode
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredProblemList(Predicate<Problem> predicate, Mode mode) {

        }

        @Override
        public String getProblemString() {
            // TODO Auto-generated method stub
            return null;
        }

    }

    /**
     * A Model stub that contains a single problem.
     */
    private class ModelStubWithProblem extends ModelStub {
        private final Problem problem;

        ModelStubWithProblem(Problem problem) {
            requireNonNull(problem);
            this.problem = problem;
        }

        @Override
        public boolean hasProblem(Problem problem) {
            requireNonNull(problem);
            return this.problem.isSameProblem(problem);
        }
    }

    /**
     * A Model stub that always accept the problem being added.
     */
    private class ModelStubAcceptingProblemAdded extends ModelStub {
        final ArrayList<Problem> problemsAdded = new ArrayList<>();

        @Override
        public boolean hasProblem(Problem problem) {
            requireNonNull(problem);
            return problemsAdded.stream().anyMatch(problem::isSameProblem);
        }

        @Override
        public void addProblem(Problem problem) {
            requireNonNull(problem);
            problemsAdded.add(problem);
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            return new Library();
        }
    }
}
