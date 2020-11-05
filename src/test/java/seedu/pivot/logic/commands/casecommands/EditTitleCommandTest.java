package seedu.pivot.logic.commands.casecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.casecommands.EditTitleCommand.MESSAGE_EDIT_TITLE_SUCCESS;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.CaseBuilder.DEFAULT_TITLE;
import static seedu.pivot.testutil.TypicalCases.getTypicalCases;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.pivot.testutil.TypicalIndexes.SECOND_INDEX;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.logic.commands.Undoable;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Title;
import seedu.pivot.testutil.CaseBuilder;

public class EditTitleCommandTest {

    private static final Title FIRST_TEST_TITLE = new Title(DEFAULT_TITLE);
    private static final Title SECOND_TEST_TITLE = new Title("EditTitleCommandTest");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTitleCommand(null, FIRST_TEST_TITLE));
        assertThrows(NullPointerException.class, () -> new EditTitleCommand(FIRST_INDEX, null));
    }

    @Test
    public void equals() {
        EditTitleCommand command = new EditTitleCommand(FIRST_INDEX, FIRST_TEST_TITLE);

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new EditTitleCommand(FIRST_INDEX, FIRST_TEST_TITLE)));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different index -> returns false
        assertFalse(command.equals(new EditTitleCommand(SECOND_INDEX, FIRST_TEST_TITLE)));

        // different title -> returns false
        assertFalse(command.equals(new EditTitleCommand(FIRST_INDEX, SECOND_TEST_TITLE)));

    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        //initialise valid edit command
        EditTitleCommand command = new EditTitleCommand(FIRST_INDEX, FIRST_TEST_TITLE);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_allValidFields_success() throws CommandException {
        //set up case page
        StateManager.setState(FIRST_INDEX);

        //set up model with case list
        ModelStub modelStub = new ModelStubWithCaseList(getTypicalCases());

        //initialise valid edit command
        EditTitleCommand command = new EditTitleCommand(FIRST_INDEX, FIRST_TEST_TITLE);

        assertEquals(String.format(MESSAGE_EDIT_TITLE_SUCCESS, FIRST_TEST_TITLE),
                command.execute(modelStub).getFeedbackToUser());

        StateManager.resetState();
    }

    @Test
    public void execute_duplicateTitles_throwsCommandException() {
        //set up case page
        StateManager.setState(FIRST_INDEX);

        //set up model with case list
        Case caseTitle = new CaseBuilder()
                .withTitle(DEFAULT_TITLE)
                .build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(caseTitle);
        ModelStub modelStub = new ModelStubWithCaseList(caseList);

        //initialise invalid edit command
        //edit title to itself (already existing in PIVOT)
        EditTitleCommand command = new EditTitleCommand(FIRST_INDEX, FIRST_TEST_TITLE);

        assertThrows(CommandException.class,
                UserMessages.MESSAGE_DUPLICATE_TITLE, () -> command.execute(modelStub));

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
        public boolean hasCase(Case investigationCase) {
            return caseList.contains(investigationCase);
        }

        @Override
        public void commitPivot(String commandMessage, Undoable command) {}
    }
}
