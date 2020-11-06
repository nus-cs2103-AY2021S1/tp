package seedu.pivot.logic.commands.casecommands.descriptioncommands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.casecommands.descriptioncommands.AddDescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.Undoable;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Description;
import seedu.pivot.testutil.CaseBuilder;


public class AddDescriptionCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        Description description = new Description("Test Description");
        assertThrows(NullPointerException.class, () -> new AddDescriptionCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddDescriptionCommand(null, description));
        assertThrows(NullPointerException.class, () -> new AddDescriptionCommand(index, null));
    }

    @Test
    public void equals() {
        Index indexOne = Index.fromZeroBased(0);
        Description descriptionOne = new Description("Test Description 1");

        Index indexTwo = Index.fromZeroBased(1000);
        Description descriptionTwo = new Description("Test Description 2");

        // same object -> returns true
        AddCommand testCommand = new AddDescriptionCommand(indexOne, descriptionOne);
        assertTrue(testCommand.equals(testCommand));

        // same values -> returns true
        assertTrue(testCommand.equals(new AddDescriptionCommand(indexOne, descriptionOne)));

        // different types -> returns false
        assertFalse(testCommand.equals(1));

        // null -> returns false
        assertFalse(testCommand.equals(null));

        // different description -> returns false
        assertFalse(testCommand.equals(new AddDescriptionCommand(indexOne, descriptionTwo)));

        // different index -> returns false
        assertFalse(testCommand.equals(new AddDescriptionCommand(indexTwo, descriptionOne)));

        // different description and index -> returns false
        assertFalse(testCommand.equals(new AddDescriptionCommand(indexTwo, descriptionTwo)));
    }

    @Test
    public void execute_validDescription_success() throws CommandException {

        // Setup
        Description newDesc = new Description("New Description");
        Index index = Index.fromZeroBased(0);
        StateManager.setState(index);

        // Generate Case with no description
        Case testCase = new CaseBuilder().build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        // Add a description to a Case
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddDescriptionCommand(index, newDesc);
        assertEquals(String.format(MESSAGE_ADD_DESCRIPTION_SUCCESS, newDesc),
                command.execute(modelStub).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_descriptionAlreadyExists_throwsCommandException() {
        // Setup
        Description anotherDesc = new Description("Another Description");
        Index index = Index.fromZeroBased(0);
        StateManager.setState(index);

        // Generate Case with Existing Description
        Case testCase = new CaseBuilder().withDescription("Existing Description").build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        // Try to add Description to a case with a existing description
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddDescriptionCommand(index, anotherDesc);
        assertThrows(CommandException.class,
                AddDescriptionCommand.MESSAGE_DESCRIPTION_ALREADY_EXISTS, () -> command.execute(modelStub));
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
        public void commitPivot(String commandMessage, Undoable command) {}
    }

}
