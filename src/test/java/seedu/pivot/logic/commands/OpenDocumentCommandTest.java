package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.documentcommands.OpenDocumentCommand.MESSAGE_OPEN_DOCUMENT_SUCCESS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.documentcommands.OpenDocumentCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Document;

public class OpenDocumentCommandTest {

    private static Index validDocIndex = Index.fromOneBased(1);
    private static Index invalidDocIndex = Index.fromOneBased(2);
    private static Index firstCaseIndex = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());
    private Model model;

    //OpenDocumentCommand assumes program to have a state
    @BeforeEach
    public void setUpCasePage() {
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
        StateManager.setState(firstCaseIndex);
    }

    @Test
    public void execute_invalidDocumentIndex_throwsCommandException() {

        //check if set up is correct; at case page
        assertTrue(StateManager.atCasePage());

        //command to execute
        OpenDocumentCommand openDoc = new OpenDocumentCommand(invalidDocIndex);

        //expected CommandResult
        CommandResult expectedResult =
                new CommandResult(String.format(MESSAGE_OPEN_DOCUMENT_SUCCESS, openDoc));

        assertCommandFailure(openDoc, model, UserMessages.MESSAGE_INVALID_DOCUMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_referenceDoesNotExist_throwsCommandException() {

        //get first valid case from typical pivot
        Case firstCase = model.getFilteredCaseList().get(firstCaseIndex.getZeroBased());

        //check if set up is correct; at case page
        assertTrue(StateManager.atCasePage());

        //get first document
        Document documentMissing = firstCase.getDocuments().get(validDocIndex.getZeroBased());


        //command to execute
        OpenDocumentCommand openDoc = new OpenDocumentCommand(validDocIndex);

        //expected message
        String expectedMessage = String.format(
                UserMessages.MESSAGE_REFERENCE_DOES_NOT_EXIST, documentMissing.getReference());
        assertCommandFailure(openDoc, model, expectedMessage);
    }

}
