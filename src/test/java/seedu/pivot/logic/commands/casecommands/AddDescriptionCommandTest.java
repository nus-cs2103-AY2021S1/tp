package seedu.pivot.logic.commands.casecommands;

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

        // different person -> returns false
        assertFalse(testCommand.equals(new AddDescriptionCommand(indexOne, descriptionTwo)));
        assertFalse(testCommand.equals(new AddDescriptionCommand(indexTwo, descriptionOne)));
        assertFalse(testCommand.equals(new AddDescriptionCommand(indexTwo, descriptionTwo)));
    }

    @Test
    public void execute_validDescription_success() throws CommandException {
        // Setup
        Description newDesc = new Description("New Description");
        Description blankDesc = new Description(""); // Allows blank
        Index index = Index.fromZeroBased(0);
        StateManager.setState(index);

        // Generate Case with Old Description
        Case testCase = new CaseBuilder().withDescription("Old Description").build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        // Update Case
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand descCommand = new AddDescriptionCommand(index, newDesc);
        assertEquals(String.format(AddDescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, newDesc),
                descCommand.execute(modelStub).getFeedbackToUser());
        AddCommand blankDescCommand = new AddDescriptionCommand(index, blankDesc);
        assertEquals(String.format(AddDescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, blankDesc),
                blankDescCommand.execute(modelStub).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_sameDescription_throwsCommandException() {
        // Setup
        Description newDesc = new Description("Old Description");
        Index index = Index.fromZeroBased(0);
        StateManager.setState(index);

        // Generate Case with Old Description
        Case testCase = new CaseBuilder().withDescription("Old Description").build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        // Update Case
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        AddCommand command = new AddDescriptionCommand(index, newDesc);
        assertThrows(CommandException.class,
                AddDescriptionCommand.MESSAGE_DUPLICATE_DESCRIPTION, () -> command.execute(modelStub));
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
