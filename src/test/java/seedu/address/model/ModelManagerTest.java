package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.APEAKAPP;
import static seedu.address.testutil.TypicalProjects.BRICK;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.project.NameContainsKeywordsPredicate;
import seedu.address.testutil.MainCatalogueBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MainCatalogue(), new MainCatalogue(modelManager.getProjectCatalogue()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMainCatalogueFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMainCatalogueFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setMainCatalogueFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setProjectCatalogueFilePath(null));
    }

    @Test
    public void setMainCatalogueFilePath_validPath_setsMainCatalogueFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setProjectCatalogueFilePath(path);
        assertEquals(path, modelManager.getProjectCatalogueFilePath());
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInMainCatalogue_returnsFalse() {
        assertFalse(modelManager
            .hasProject(APEAKAPP));
    }

    @Test
    public void hasProject_projectInMainCatalogue_returnsTrue() {
        modelManager.addProject(APEAKAPP);
        assertTrue(modelManager.hasProject(APEAKAPP));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
            .getFilteredProjectList().remove(0));
    }

    @Test
    public void getFilteredProjectList_enterProject_singleProject() {
        modelManager.addProject(APEAKAPP);
        modelManager.enter(APEAKAPP);
        assertTrue(modelManager.getFilteredProjectList().contains(APEAKAPP));
        assertEquals(1, modelManager.getFilteredProjectList().size());
    }

    @Test
    public void getFilteredProjectList_quit_fullList() {
        modelManager.addProject(APEAKAPP);
        modelManager.addProject(BRICK);
        modelManager.enter(APEAKAPP);
        modelManager.quit();
        assertEquals(modelManager.getProjectCatalogue().getProjectList(), modelManager.getFilteredProjectList());
    }

    @Test
    public void equals() {
        MainCatalogue mainCatalogue = new MainCatalogueBuilder().withProject(APEAKAPP).withProject(BRICK).build();
        MainCatalogue differentMainCatalogue = new MainCatalogue();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(mainCatalogue, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(mainCatalogue, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different mainCatalogue -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentMainCatalogue, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = APEAKAPP.getProjectName().fullProjectName.split("\\s+");
        modelManager.updateFilteredProjectList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(mainCatalogue, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMainCatalogueFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(mainCatalogue, differentUserPrefs)));
    }
}
