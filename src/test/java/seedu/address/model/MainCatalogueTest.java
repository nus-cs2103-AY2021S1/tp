package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_HANG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.APEAKAPP;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
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
        Project editedAlice = new ProjectBuilder(APEAKAPP).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_BOT).withTags(
            VALID_PROJECT_TAG_HANG)
                .withTasks(VALID_PROJECT_TAG_DG)
                .build();
        List<Project> newProjects = Arrays.asList(APEAKAPP, editedAlice);
        MainCatalogueStub newData = new MainCatalogueStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> mainCatalogue.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInMainCatalogue_returnsFalse() {
        assertFalse(
            mainCatalogue.hasProject(APEAKAPP));
    }

    @Test
    public void hasProject_projectInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(APEAKAPP);
        assertTrue(mainCatalogue.hasProject(APEAKAPP));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(APEAKAPP);
        Project editedAlice = new ProjectBuilder(APEAKAPP).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_BOT).withTags(
            VALID_PROJECT_TAG_HANG)
                .withTasks(VALID_PROJECT_TAG_DG)
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
            mainCatalogue.addProject(APEAKAPP);
            mainCatalogue.enter(APEAKAPP);
            mainCatalogue.quit();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void enter_nonExistingProject_throwProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> mainCatalogue.enter(APEAKAPP));
    }

    @Test
    public void enter_sameButNotEqualProject_success() {
        mainCatalogue.addProject(APEAKAPP);
        Project adapted = new ProjectBuilder(APEAKAPP).withTags().build();
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
