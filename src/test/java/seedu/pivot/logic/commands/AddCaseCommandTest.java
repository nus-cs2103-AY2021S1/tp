package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.logic.commands.casecommands.AddCaseCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.model.Model;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.ReadOnlyUserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

public class AddCaseCommandTest {

    @Test
    public void constructor_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCaseCommand(null));
    }

    @Test
    public void execute_caseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Case validCase = new CaseBuilder().build();

        CommandResult commandResult = new AddCaseCommand(validCase).execute(modelStub);

        assertEquals(String.format(AddCaseCommand.MESSAGE_ADD_CASE_SUCCESS, validCase),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCase), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateCase_throwsCommandException() {
        Case validCase = new CaseBuilder().build();
        AddCommand addCommand = new AddCaseCommand(validCase);
        ModelStub modelStub = new ModelStubWithPerson(validCase);

        assertThrows(CommandException.class,
                AddCaseCommand.MESSAGE_DUPLICATE_CASE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Case alice = new CaseBuilder().withTitle("Alice").build();
        Case bob = new CaseBuilder().withTitle("Bob").build();
        AddCommand addAliceCommand = new AddCaseCommand(alice);
        AddCommand addBobCommand = new AddCaseCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCaseCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
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
        public Path getPivotFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPivotFilePath(Path pivotFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCase(Case investigationCase) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPivot(ReadOnlyPivot newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPivot getPivot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCase(Case investigationCase) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCase(Case target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCase(Case target, Case editedCase) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Case> getFilteredCaseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCaseList(Predicate<Case> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitPivot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoPivot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoPivot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoPivot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoPivot() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Case investigationCase;

        ModelStubWithPerson(Case investigationCase) {
            requireNonNull(investigationCase);
            this.investigationCase = investigationCase;
        }

        @Override
        public boolean hasCase(Case investigationCase) {
            requireNonNull(investigationCase);
            return this.investigationCase.isSameCase(investigationCase);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Case> personsAdded = new ArrayList<>();

        @Override
        public boolean hasCase(Case investigationCase) {
            requireNonNull(investigationCase);
            return personsAdded.stream().anyMatch(investigationCase::isSameCase);
        }

        @Override
        public void addCase(Case investigationCase) {
            requireNonNull(investigationCase);
            personsAdded.add(investigationCase);
        }

        @Override
        public void commitPivot() {

        }

        @Override
        public ReadOnlyPivot getPivot() {
            return new Pivot();
        }
    }

}
