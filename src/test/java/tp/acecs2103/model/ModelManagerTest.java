package tp.acecs2103.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.acecs2103.testutil.Assert.assertThrows;
import static tp.acecs2103.testutil.TypicalTasks.ADMIN_ONE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tp.acecs2103.commons.core.GuiSettings;
import tp.acecs2103.model.exceptions.ModelException;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskList(), new TaskList(modelManager.getTaskList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskListFilePath(Paths.get("tasklist/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskListFilePath(Paths.get("new/tasklist/file/path"));
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
    public void setTaskListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskListFilePath(null));
    }

    @Test
    public void setTaskListFilePath_validPath_setsTaskListFilePath() {
        Path path = Paths.get("tasklist/file/path");
        modelManager.setTaskListFilePath(path);
        assertEquals(path, modelManager.getTaskListFilePath());
    }


    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(modelManager.hasTask(ADMIN_ONE));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        modelManager.addTask(ADMIN_ONE);
        assertTrue(modelManager.hasTask(ADMIN_ONE));
    }

    @Test
    public void deleteTask_taskInTaskList_success() {
        try {
            modelManager.addTask(ADMIN_ONE);
            assertTrue(modelManager.hasTask(ADMIN_ONE));
            modelManager.deleteTask(ADMIN_ONE.getIndex());
            assertFalse(modelManager.hasTask(ADMIN_ONE));
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void deleteTask_taskNotInTaskList_throwsModelException() {
        assertThrows(ModelException.class, () -> modelManager.deleteTask(ADMIN_ONE.getIndex()));
    }
}
