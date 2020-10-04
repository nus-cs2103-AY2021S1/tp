package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.InvalidScopeException;
import seedu.address.model.project.Project;
import seedu.address.model.project.Status;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.testutil.ProjectBuilder;

public class MainCatalogueTest {

    private final MainCatalogue mainCatalogue = new MainCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mainCatalogue.getProjectList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMainCatalogue_replacesData() {
        MainCatalogue newData = getTypicalMainCatalogue();
        mainCatalogue.resetData(newData);
        assertEquals(newData, mainCatalogue);
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        // Two projects with the same identity fields
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .withTasks(VALID_TASK_DG)
                .build();
        List<Project> newProjects = Arrays.asList(ALICE, editedAlice);
        MainCatalogueStub newData = new MainCatalogueStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> mainCatalogue.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInMainCatalogue_returnsFalse() {
        assertFalse(mainCatalogue.hasProject(ALICE));
    }

    @Test
    public void hasProject_projectInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(ALICE);
        assertTrue(mainCatalogue.hasProject(ALICE));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(ALICE);
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .withTasks(VALID_TASK_DG)
                .build();
        assertTrue(mainCatalogue.hasProject(editedAlice));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mainCatalogue.getProjectList().remove(0));
    }

    @Test
    public void enterQuit_correctScope_success() {
        try {
            mainCatalogue.addProject(ALICE);
            mainCatalogue.enter(ALICE);
            mainCatalogue.quit();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void enterQuit_incorrectScope_throwInvalidScopeException() {
        mainCatalogue.addProject(ALICE);
        try {
            mainCatalogue.quit();
        } catch (InvalidScopeException e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.CATALOGUE), e);
        } catch (Exception e) {
            fail();
        }
        mainCatalogue.enter(ALICE);
        try {
            mainCatalogue.enter(ALICE);
        } catch (InvalidScopeException e) {
            assertEquals(new InvalidScopeException(Status.CATALOGUE, Status.PROJECT), e);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void enter_nonExistingProject_throwProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> mainCatalogue.enter(ALICE));
    }

    @Test
    public void enter_sameButNotEqualProject_success() {
        mainCatalogue.addProject(ALICE);
        Project adapted = new ProjectBuilder(ALICE).withTags().build();
        try {
            mainCatalogue.enter(adapted);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * A stub ReadOnlyMainCatalogue whose projects list can violate interface constraints.
     */
    private static class MainCatalogueStub implements ReadOnlyMainCatalogue {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private Status status = Status.CATALOGUE;

        MainCatalogueStub(Collection<Project> projects) {
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public Status getStatus() {
            return status;
        }

        @Override
        public void enter(Project project) {
            // TODO: Add content if test case need this.
        }

        @Override
        public void quit() {
            // TODO: Add content if test case need this.
        }
    }

}
