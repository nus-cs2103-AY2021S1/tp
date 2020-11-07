package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalPersons.DESC_B;
import static seedu.address.testutil.TypicalPersons.DESC_C;
import static seedu.address.testutil.TypicalProjects.AI;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains tests regarding AddTeammateCommand
 */
public class AddTeammateCommandTest {
    @Test
    public void execute_invalidPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new AddTeammateCommand(null);
        });
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        AddTeammateCommand addTeammateCommand = new AddTeammateCommand(DESC_A);
        Model model = null;
        assertThrows(NullPointerException.class, () -> {
            addTeammateCommand.execute(model);
        });
    }

    @Test
    public void execute_validModel() {
        AddTeammateCommand addTeammateCommand = new AddTeammateCommand(DESC_A);
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        model.updateProjectToBeDisplayedOnDashboard(AI);
        String expectedResult = String.format(AddTeammateCommand.MESSAGE_NEW_TEAMMATE_SUCCESS,
            DESC_A.getGitUserNameString());

        try {
            CommandResult commandResult = addTeammateCommand.execute(model);
            assertEquals(expectedResult, commandResult.getFeedbackToUser());
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void execute_equals() {

        AddTeammateCommand addTeammateCommand1 = new AddTeammateCommand(DESC_A);
        AddTeammateCommand addTeammateCommand2 = new AddTeammateCommand(DESC_B);
        AddTeammateCommand addTeammateCommand3 = new AddTeammateCommand(DESC_C);

        // same object -> returns true
        assertEquals(addTeammateCommand1, addTeammateCommand1);

        // same values -> returns true
        AddTeammateCommand addTeammateCommand1Copy = new AddTeammateCommand(DESC_A);
        assertEquals(addTeammateCommand1Copy, addTeammateCommand1);

        // different types -> returns false
        assertNotEquals(addTeammateCommand1, "this test will return false");

        // null -> returns false
        assertNotEquals(addTeammateCommand1, null);

        // different tasks -> returns false
        assertNotEquals(addTeammateCommand2, addTeammateCommand1);
        assertNotEquals(addTeammateCommand3, addTeammateCommand2);
    }




}
