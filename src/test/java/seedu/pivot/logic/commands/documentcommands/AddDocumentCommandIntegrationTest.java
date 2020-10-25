package seedu.pivot.logic.commands.documentcommands;

import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Document;
import seedu.pivot.model.investigationcase.Reference;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.testutil.CaseBuilder;


public class AddDocumentCommandIntegrationTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Reference DEFAULT_REFERENCE = new Reference("test1.txt");
    private static final Document DEFAULT_DOCUMENT = new Document(DEFAULT_NAME, DEFAULT_REFERENCE);
    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexAndValidDocumentUnfilteredList_success() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT DOCUMENT
        Case expectedCase = new CaseBuilder(caseToUpdate).withDocument(DEFAULT_NAME.getAlphaNum(),
                DEFAULT_REFERENCE.getFileName()).build();

        AddCommand command = new AddDocumentCommand(INDEX_FIRST_PERSON, DEFAULT_DOCUMENT);

        String expectedMessage = String.format(AddDocumentCommand.MESSAGE_ADD_DOCUMENT_SUCCESS, DEFAULT_DOCUMENT);
        ModelManager expectedModel = new ModelManager((model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToUpdate, expectedCase);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndDuplicateDocumentUnfilteredList_throwsCommandException() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT DESCRIPTIONS
        Document document = caseToUpdate.getDocuments().get(0);

        AddCommand command = new AddDocumentCommand(INDEX_FIRST_PERSON, document);

        assertCommandFailure(command, model, AddDocumentCommand.MESSAGE_DUPLICATE_DOCUMENT);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndValidDocumentFilteredList_success() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON);
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT DOCUMENT
        Case expectedCase = new CaseBuilder(caseToUpdate).withDocument(DEFAULT_NAME.getAlphaNum(),
                DEFAULT_REFERENCE.getFileName()).build();

        AddCommand command = new AddDocumentCommand(INDEX_FIRST_PERSON, DEFAULT_DOCUMENT);

        String expectedMessage = String.format(AddDocumentCommand.MESSAGE_ADD_DOCUMENT_SUCCESS, DEFAULT_DOCUMENT);
        ModelManager expectedModel = new ModelManager((model.getPivot()), new UserPrefs());
        showCaseAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setCase(caseToUpdate, expectedCase);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndDuplicateDocumentFilteredList_throwsCommandException() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON);
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT DESCRIPTIONS
        Document document = caseToUpdate.getDocuments().get(0);

        AddCommand command = new AddDocumentCommand(INDEX_FIRST_PERSON, document);

        assertCommandFailure(command, model, AddDocumentCommand.MESSAGE_DUPLICATE_DOCUMENT);
        StateManager.resetState();
    }
}
