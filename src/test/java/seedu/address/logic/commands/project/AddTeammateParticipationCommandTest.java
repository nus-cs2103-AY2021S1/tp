package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_INVALID_TEAMMATE;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

public class AddTeammateParticipationCommandTest {

    /**
    @Test
    public void execute_validGitUserIndex_success() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);

        Model expectedModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        expectedModel.enter(project);
        Project expectedProject = expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        expectedProject.addParticipation(DESC_A);
        expectedModel.addParticipation(expectedProject.getParticipation(DESC_A.getGitUserNameString()));

        String expectedMessage =
            String.format(AddTeammateParticipationCommand.MESSAGE_ADD_TEAMMATE_PARTICIPATION_SUCCESS,
                VALID_TEAMMATE_GIT_USERNAME_A);
        AddTeammateParticipationCommand addTeammateParticipationCommand =
            new AddTeammateParticipationCommand(GIT_USERINDEX_FIRST_TEAMMATE);

        assertCommandSuccess(addTeammateParticipationCommand, model, expectedMessage, expectedModel);
    }
     */

    @Test
    public void execute_validGitUserDuplicateParticipation_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(DESC_A);
        model.addParticipation(project.getParticipation(DESC_A.getGitUserNameString()));

        AddTeammateParticipationCommand addTeammateParticipationCommand =
            new AddTeammateParticipationCommand(GIT_USERINDEX_FIRST_TEAMMATE);

        assertThrows(CommandException.class, () -> addTeammateParticipationCommand.execute(model));
    }

    @Test
    public void execute_invalidGitUserIndex_success() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);

        AddTeammateParticipationCommand addTeammateParticipationCommand =
            new AddTeammateParticipationCommand(GIT_USERINDEX_INVALID_TEAMMATE);

        assertThrows(NullPointerException.class, () -> addTeammateParticipationCommand.execute(model));
    }

    @Test
    public void equals() {
        AddTeammateParticipationCommand addFirst = new AddTeammateParticipationCommand(GIT_USERINDEX_FIRST_TEAMMATE);
        AddTeammateParticipationCommand addSecond = new AddTeammateParticipationCommand(GIT_USERINDEX_FIRST_TEAMMATE);
        AddTeammateParticipationCommand addThird = new AddTeammateParticipationCommand(GIT_USERINDEX_SECOND_TEAMMATE);

        // same object -> returns true
        assertTrue(addFirst.equals(addFirst));

        // same values -> returns true
        assertTrue(addFirst.equals(addSecond));

        // different types -> returns false
        assertFalse(addFirst.equals(1));

        // null -> returns false
        assertFalse(addFirst.equals(null));

        // different gitUserName -> returns false
        assertFalse(addFirst.equals(addThird));
    }
}
