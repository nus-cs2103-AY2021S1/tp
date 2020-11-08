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
 * Contains tests regarding AddPersonCommand
 */
public class AddPersonCommandTest {
    @Test
    public void execute_invalidPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new AddPersonCommand(null);
        });
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        AddPersonCommand addPersonCommand = new AddPersonCommand(DESC_A);
        Model model = null;
        assertThrows(NullPointerException.class, () -> {
            addPersonCommand.execute(model);
        });
    }

    @Test
    public void execute_validModel() {
        AddPersonCommand addPersonCommand = new AddPersonCommand(DESC_A);
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        model.updateProjectToBeDisplayedOnDashboard(AI);
        String expectedResult = String.format(AddPersonCommand.MESSAGE_NEW_TEAMMATE_SUCCESS,
            DESC_A.getGitUserNameString());

        try {
            CommandResult commandResult = addPersonCommand.execute(model);
            assertEquals(expectedResult, commandResult.getFeedbackToUser());
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void execute_equals() {

        AddPersonCommand addPersonCommand1 = new AddPersonCommand(DESC_A);
        AddPersonCommand addPersonCommand2 = new AddPersonCommand(DESC_B);
        AddPersonCommand addPersonCommand3 = new AddPersonCommand(DESC_C);

        // same object -> returns true
        assertEquals(addPersonCommand1, addPersonCommand1);

        // same values -> returns true
        AddPersonCommand addPersonCommand1Copy = new AddPersonCommand(DESC_A);
        assertEquals(addPersonCommand1Copy, addPersonCommand1);

        // different types -> returns false
        assertNotEquals(addPersonCommand1, "this test will return false");

        // null -> returns false
        assertNotEquals(addPersonCommand1, null);

        // different tasks -> returns false
        assertNotEquals(addPersonCommand2, addPersonCommand1);
        assertNotEquals(addPersonCommand3, addPersonCommand2);
    }




}
