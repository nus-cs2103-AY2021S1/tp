package seedu.pivot.logic.commands;

import static seedu.pivot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalAddressBook;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.casecommands.ListCaseCommand;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCaseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCaseCommand(), model, ListCaseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCaseCommand(), model, ListCaseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
