package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnassignallCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.unassignAllInstructors();
    }

    @Test
    public void execute_listIsFiltered_unassignedAllInstructors() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new UnassignallCommand(), model, UnassignallCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsNotFiltered_unassignedAllInstructors() {
        assertCommandSuccess(new UnassignallCommand(), model, UnassignallCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
