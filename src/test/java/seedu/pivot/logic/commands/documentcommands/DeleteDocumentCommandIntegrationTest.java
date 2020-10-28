package seedu.pivot.logic.commands.documentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Document;


public class DeleteDocumentCommandIntegrationTest {
    private static final Index DEFAULT_DOC_INDEX = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseWithDoc = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        Document doc = caseWithDoc.getDocuments().get(0);
        DeleteCommand command = new DeleteDocumentCommand(INDEX_FIRST_PERSON, DEFAULT_DOC_INDEX);

        assertEquals(String.format(DeleteDocumentCommand.MESSAGE_DELETE_DOCUMENT_SUCCESS, doc),
                command.execute(model).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Index invalidDocIndex = Index.fromOneBased(model.getFilteredCaseList().get(0).getDocuments().size() + 1);
        DeleteCommand command = new DeleteDocumentCommand(INDEX_FIRST_PERSON, invalidDocIndex);

        assertCommandFailure(command, model, UserMessages.MESSAGE_INVALID_DOCUMENT_DISPLAYED_INDEX);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        showCaseAtIndex(model, INDEX_FIRST_PERSON); // filter the list
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseWithDoc = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        Document doc = caseWithDoc.getDocuments().get(0);
        DeleteCommand command = new DeleteDocumentCommand(INDEX_FIRST_PERSON, DEFAULT_DOC_INDEX);

        assertEquals(String.format(DeleteDocumentCommand.MESSAGE_DELETE_DOCUMENT_SUCCESS, doc),
                command.execute(model).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON); // filter the list
        StateManager.setState(INDEX_FIRST_PERSON);
        Index invalidDocIndex = Index.fromOneBased(model.getFilteredCaseList().get(0).getDocuments().size() + 1);
        DeleteCommand command = new DeleteDocumentCommand(INDEX_FIRST_PERSON, invalidDocIndex);

        assertCommandFailure(command, model, UserMessages.MESSAGE_INVALID_DOCUMENT_DISPLAYED_INDEX);
        StateManager.resetState();
    }

}
