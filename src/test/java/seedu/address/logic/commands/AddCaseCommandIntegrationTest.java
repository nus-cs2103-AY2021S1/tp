package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCases.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.casecommands.AddCaseCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.investigationcase.Case;
import seedu.address.testutil.CaseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCaseCommand}.
 */
public class AddCaseCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newCase_success() {
        Case validCase = new CaseBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCase(validCase);

        assertCommandSuccess(new AddCaseCommand(validCase), model,
                String.format(AddCaseCommand.MESSAGE_ADD_CASE_SUCCESS, validCase), expectedModel);
    }

    @Test
    public void execute_duplicateCase_throwsCommandException() {
        Case caseInList = model.getAddressBook().getCaseList().get(0);
        assertCommandFailure(new AddCaseCommand(caseInList), model, AddCaseCommand.MESSAGE_DUPLICATE_CASE);
    }

}
