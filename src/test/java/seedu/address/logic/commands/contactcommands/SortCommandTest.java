package seedu.address.logic.commands.contactcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.contactcommands.SortCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonNameComparator;

class SortCommandTest {
    private Model model = new ModelManager(getTypicalProjact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProjact(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand commandOne = new SortCommand();
        SortCommand commandTwo = new SortCommand();

        // same object -> returns true
        assertTrue(commandOne.equals(commandOne));

        // different types -> returns false
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different tag -> returns false
        assertFalse(commandOne.equals(commandTwo));
    }


    @Test
    public void execute_sortContactCommand_success() {
        String expectedMessage = MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(
                expectedMessage, false, false, false, true, false, true);
        PersonNameComparator comparator = new PersonNameComparator();
        SortCommand command = new SortCommand();
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(expectedModel.getSortedPersonList(), model.getSortedPersonList());
    }
}
