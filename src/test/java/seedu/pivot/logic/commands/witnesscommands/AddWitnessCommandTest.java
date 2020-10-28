package seedu.pivot.logic.commands.witnesscommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Address;
import seedu.pivot.model.investigationcase.caseperson.Email;
import seedu.pivot.model.investigationcase.caseperson.Gender;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Phone;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.testutil.CaseBuilder;

public class AddWitnessCommandTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Gender DEFAULT_GENDER = Gender.createGender("m");
    private static final Phone DEFAULT_PHONE = new Phone("91234567");
    private static final Address DEFAULT_ADDRESS = new Address("Blk 123");
    private static final Email DEFAULT_EMAIL = new Email("abc@gmail.com");
    private static final Witness DEFAULT_WITNESS = new Witness(DEFAULT_NAME, DEFAULT_GENDER,
            DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
    private static final Index DEFAULT_INDEX = Index.fromZeroBased(0);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddWitnessCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddWitnessCommand(null, DEFAULT_WITNESS));
        assertThrows(NullPointerException.class, () -> new AddWitnessCommand(DEFAULT_INDEX, null));
    }

    @Test
    public void equals() {
        Witness alternateWitness = new Witness(new Name("Alice"), Gender.F,
                new Phone("92345678"), new Email("alice@hello.com"), new Address("Blk 345"));
        Index alternateIndex = Index.fromZeroBased(1000);

        // same object -> returns true
        AddCommand testCommand = new AddWitnessCommand(DEFAULT_INDEX, DEFAULT_WITNESS);
        assertTrue(testCommand.equals(testCommand));

        // same values -> returns true
        assertTrue(testCommand.equals(new AddWitnessCommand(DEFAULT_INDEX, DEFAULT_WITNESS)));

        // different types -> returns false
        assertFalse(testCommand.equals(1));

        // null -> returns false
        assertFalse(testCommand.equals(null));

        // different person -> returns false
        assertFalse(testCommand.equals(new AddWitnessCommand(DEFAULT_INDEX, alternateWitness)));
        assertFalse(testCommand.equals(new AddWitnessCommand(alternateIndex, DEFAULT_WITNESS)));
        assertFalse(testCommand.equals(new AddWitnessCommand(alternateIndex, alternateWitness)));
    }

    @Test
    public void execute_validWitness_success() throws CommandException {
        StateManager.setState(DEFAULT_INDEX);
        Case testCase = new CaseBuilder().build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddWitnessCommand(DEFAULT_INDEX, DEFAULT_WITNESS);
        assertEquals(String.format(AddWitnessCommand.MESSAGE_ADD_WITNESS_SUCCESS, DEFAULT_WITNESS),
                command.execute(modelStub).getFeedbackToUser());

        StateManager.resetState();
    }

    @Test
    public void execute_sameWitness_throwsCommandException() {
        StateManager.setState(DEFAULT_INDEX);
        Case testCase = new CaseBuilder().withWitnesses(DEFAULT_WITNESS).build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddWitnessCommand(DEFAULT_INDEX, DEFAULT_WITNESS);
        assertThrows(CommandException.class,
                AddWitnessCommand.MESSAGE_DUPLICATE_WITNESS, () -> command.execute(modelStub));
        StateManager.resetState();
    }

    /**
     * A Model stub that holds a caseList.
     */
    private class ModelStubWithCaseList extends ModelStub {
        private final List<Case> caseList;

        private ModelStubWithCaseList(List<Case> caseList) {
            this.caseList = caseList;
        }

        @Override
        public ObservableList<Case> getFilteredCaseList() {
            return FXCollections.observableList(caseList);
        }

        @Override
        public void setCase(Case target, Case editedCase) {
            this.caseList.set(caseList.indexOf(target), editedCase);
        }

        @Override
        public void updateFilteredCaseList(Predicate<Case> predicate) {
            return;
        }

        @Override
        public void commitPivot(String command) {}
    }
}
