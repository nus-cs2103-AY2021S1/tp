package seedu.pivot.logic.commands.suspectcommands;


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
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.testutil.CaseBuilder;


public class AddSuspectCommandTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Gender DEFAULT_GENDER = Gender.createGender("m");
    private static final Phone DEFAULT_PHONE = new Phone("91234567");
    private static final Address DEFAULT_ADDRESS = new Address("Blk 123");
    private static final Email DEFAULT_EMAIL = new Email("abc@gmail.com");
    private static final Suspect DEFAULT_SUSPECT = new Suspect(DEFAULT_NAME, DEFAULT_GENDER,
            DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
    private static final Index DEFAULT_INDEX = Index.fromZeroBased(0);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSuspectCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddSuspectCommand(null, DEFAULT_SUSPECT));
        assertThrows(NullPointerException.class, () -> new AddSuspectCommand(DEFAULT_INDEX, null));
    }

    @Test
    public void equals() {
        Suspect alternateSuspect = new Suspect(new Name("Alice"), Gender.F,
                new Phone("92345678"), new Email("alice@hello.com"), new Address("Blk 345"));
        Index alternateIndex = Index.fromZeroBased(1000);

        // same object -> returns true
        AddCommand testCommand = new AddSuspectCommand(DEFAULT_INDEX, DEFAULT_SUSPECT);
        assertTrue(testCommand.equals(testCommand));

        // same values -> returns true
        assertTrue(testCommand.equals(new AddSuspectCommand(DEFAULT_INDEX, DEFAULT_SUSPECT)));

        // different types -> returns false
        assertFalse(testCommand.equals(1));

        // null -> returns false
        assertFalse(testCommand.equals(null));

        // different person -> returns false
        assertFalse(testCommand.equals(new AddSuspectCommand(DEFAULT_INDEX, alternateSuspect)));
        assertFalse(testCommand.equals(new AddSuspectCommand(alternateIndex, DEFAULT_SUSPECT)));
        assertFalse(testCommand.equals(new AddSuspectCommand(alternateIndex, alternateSuspect)));
    }

    @Test
    public void execute_validSuspect_success() throws CommandException {
        StateManager.setState(DEFAULT_INDEX);
        Case testCase = new CaseBuilder().build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddSuspectCommand(DEFAULT_INDEX, DEFAULT_SUSPECT);
        assertEquals(String.format(AddSuspectCommand.MESSAGE_ADD_SUSPECT_SUCCESS, DEFAULT_SUSPECT),
                command.execute(modelStub).getFeedbackToUser());

        StateManager.resetState();
    }

    @Test
    public void execute_sameSuspect_throwsCommandException() {
        StateManager.setState(DEFAULT_INDEX);
        Case testCase = new CaseBuilder().withSuspects(DEFAULT_SUSPECT).build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddSuspectCommand(DEFAULT_INDEX, DEFAULT_SUSPECT);
        assertThrows(CommandException.class,
                AddSuspectCommand.MESSAGE_DUPLICATE_SUSPECT, () -> command.execute(modelStub));
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
