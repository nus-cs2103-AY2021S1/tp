package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_INVALID_TEAMMATE;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

public class DeleteTeammateParticipationTest {

    @Test
    public void execute_invalidGitUserIndex_throwsNullPointerException() {

        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);

        DeleteTeammateParticipationCommand deleteTeammateParticipationCommand =
            new DeleteTeammateParticipationCommand(GIT_USERINDEX_INVALID_TEAMMATE);

        assertThrows(NullPointerException.class, () -> deleteTeammateParticipationCommand.execute(model));
    }

    @Test
    public void equals() {
        DeleteTeammateParticipationCommand deleteFirst = new DeleteTeammateParticipationCommand(
            GIT_USERINDEX_FIRST_TEAMMATE);
        DeleteTeammateParticipationCommand deleteSecond = new DeleteTeammateParticipationCommand(
            GIT_USERINDEX_FIRST_TEAMMATE);
        DeleteTeammateParticipationCommand deleteThird = new DeleteTeammateParticipationCommand(
            GIT_USERINDEX_SECOND_TEAMMATE);

        // same object -> returns true
        assertTrue(deleteFirst.equals(deleteFirst));

        // same values -> returns true
        assertTrue(deleteFirst.equals(deleteSecond));

        // different types -> returns false
        assertFalse(deleteFirst.equals(1));

        // null -> returns false
        assertFalse(deleteFirst.equals(null));

        // different gitUserName -> returns false
        assertFalse(deleteFirst.equals(deleteThird));
    }
}
