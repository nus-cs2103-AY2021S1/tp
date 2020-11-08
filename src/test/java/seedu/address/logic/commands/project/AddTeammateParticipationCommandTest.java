package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import seedu.address.model.project.Project;

public class AddTeammateParticipationCommandTest {

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
