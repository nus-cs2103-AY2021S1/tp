package seedu.momentum.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_BOB;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_CREATED_DATE_AMY;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_CREATED_DATE_BOB;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_DEADLINE_DATE_AMY;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_DEADLINE_DATE_BOB;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_DEADLINE_TIME_AMY;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.momentum.testutil.Assert.assertThrows;
import static seedu.momentum.testutil.TypicalProjects.ALICE;
import static seedu.momentum.testutil.TypicalProjects.BOB;

import org.junit.jupiter.api.Test;

import seedu.momentum.testutil.ProjectBuilder;

public class ProjectTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> project.getTags().remove(0));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(ALICE.isSameAs(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameAs(null));

        // different name -> returns false
        Project editedAlice = new ProjectBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameAs(editedAlice));

        // different description -> returns false
        editedAlice = new ProjectBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.isSameAs(editedAlice));

        // different completion status -> returns false
        editedAlice = new ProjectBuilder(ALICE).withCompletionStatus(VALID_COMPLETION_STATUS_BOB).build();
        assertFalse(ALICE.isSameAs(editedAlice));

        // different deadline -> returns false
        editedAlice = new ProjectBuilder(ALICE).withDeadline(VALID_DEADLINE_DATE_BOB, VALID_CREATED_DATE_BOB).build();
        assertFalse(ALICE.isSameAs(editedAlice));
        Project editedBob = new ProjectBuilder(BOB)
                .withDeadline(VALID_DEADLINE_DATE_AMY, VALID_DEADLINE_TIME_AMY, VALID_CREATED_DATE_AMY).build();
        assertFalse(BOB.isSameAs(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project aliceCopy1 = new ProjectBuilder(ALICE).build();
        Project aliceCopy2 = new ProjectBuilder(ALICE).build();
        assertTrue(aliceCopy1.equals(aliceCopy2));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different project -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Project editedAlice = new ProjectBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> returns false
        editedAlice = new ProjectBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different completion status -> returns false
        editedAlice = new ProjectBuilder(ALICE).withCompletionStatus(VALID_COMPLETION_STATUS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new ProjectBuilder(ALICE).withDeadline(VALID_DEADLINE_DATE_BOB, VALID_CREATED_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ProjectBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
