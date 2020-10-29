package seedu.pivot.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

public class AddCaseCommandTest {

    @BeforeEach
    public void setUpMainPage() {
        StateManager.resetState();
        StateManager.setDefaultSection();
    }

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
        ModelStub modelStub = new ModelStubWithCase(validCase);

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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithCase extends ModelStub {
        private final Case investigationCase;

        ModelStubWithCase(Case investigationCase) {
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
        public void updateFilteredCaseList(Predicate<Case> predicate) {
            personsAdded.stream().filter(predicate);
        }

        @Override
        public void commitPivot(String command) {}

        @Override
        public ReadOnlyPivot getPivot() {
            return new Pivot();
        }
    }

}
