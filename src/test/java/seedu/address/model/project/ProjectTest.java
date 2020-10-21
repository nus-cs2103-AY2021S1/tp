package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_B;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.APEAKAPP;
import static seedu.address.testutil.TypicalProjects.BOT;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.ProjectBuilder;

public class ProjectTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> project.getProjectTags().remove(0));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(APEAKAPP.isSameProject(APEAKAPP));

        // null -> returns false
        assertFalse(APEAKAPP.isSameProject(null));

        // different deadline and repoUrl -> returns false
        Project editedAlice = new ProjectBuilder(APEAKAPP)
                .withDeadline(VALID_DEADLINE_B).withRepoUrl(VALID_REPOURL_B).build();
        assertFalse(APEAKAPP.isSameProject(editedAlice));

        // different name -> returns false
        editedAlice = new ProjectBuilder(APEAKAPP).withProjectName(VALID_PROJECT_NAME_B).build();
        assertFalse(APEAKAPP.isSameProject(editedAlice));

        // same name, same deadline, different attributes -> returns true
        editedAlice = new ProjectBuilder(APEAKAPP).withRepoUrl(VALID_REPOURL_B).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_B)
                .withTags(VALID_PROJECT_TAG_A).withTasks(SampleDataUtil.getTask5()).build();
        assertTrue(APEAKAPP.isSameProject(editedAlice));

        // same name, same repoUrl, different attributes -> returns true
        editedAlice = new ProjectBuilder(APEAKAPP).withDeadline(VALID_DEADLINE_B)
                .withProjectDescription(VALID_PROJECT_DESCRIPTION_B)
                .withTags(VALID_PROJECT_TAG_A).withTasks(SampleDataUtil.getTask6()).build();
        assertTrue(APEAKAPP.isSameProject(editedAlice));

        // same name, same deadline, same repoUrl, different attributes -> returns true
        editedAlice = new ProjectBuilder(APEAKAPP).withProjectDescription(VALID_PROJECT_DESCRIPTION_B).withTags(
            VALID_PROJECT_TAG_A)
                .withTasks(SampleDataUtil.getTask2()).build();
        assertTrue(APEAKAPP.isSameProject(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project aliceCopy = new ProjectBuilder(APEAKAPP).build();
        assertTrue(APEAKAPP.equals(aliceCopy));

        // same object -> returns true
        assertTrue(APEAKAPP.equals(APEAKAPP));

        // null -> returns false
        assertFalse(APEAKAPP.equals(null));

        // different type -> returns false
        assertFalse(APEAKAPP.equals(5));

        // different project -> returns false
        assertFalse(APEAKAPP.equals(BOT));

        // different name -> returns false
        Project editedAlice = new ProjectBuilder(APEAKAPP).withProjectName(VALID_PROJECT_NAME_B).build();
        assertFalse(APEAKAPP.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new ProjectBuilder(APEAKAPP).withDeadline(VALID_DEADLINE_B).build();
        assertFalse(APEAKAPP.equals(editedAlice));

        // different repoUrl -> returns false
        editedAlice = new ProjectBuilder(APEAKAPP).withRepoUrl(VALID_REPOURL_B).build();
        assertFalse(APEAKAPP.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ProjectBuilder(APEAKAPP).withProjectDescription(VALID_PROJECT_DESCRIPTION_B).build();
        assertFalse(APEAKAPP.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ProjectBuilder(APEAKAPP).withTags(VALID_PROJECT_TAG_A).build();
        assertFalse(APEAKAPP.equals(editedAlice));

        // different tasks -> returns false
        editedAlice = new ProjectBuilder(APEAKAPP).withTasks(SampleDataUtil.getTask5()).build();
        assertFalse(APEAKAPP.equals(editedAlice));
    }
}
