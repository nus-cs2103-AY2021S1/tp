package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.COM_INFO;
import static seedu.address.testutil.TypicalModules.COM_ORG;
import static seedu.address.testutil.TypicalModules.EFF_COM;
import static seedu.address.testutil.TypicalModules.SWE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleNameContainsKeywordsPredicate;
import seedu.address.model.semester.Semester;
import seedu.address.model.semester.SemesterManager;
import seedu.address.testutil.GradeBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new GradeBook(), new GradeBook(modelManager.getGradeBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGradeBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setGradeBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setGradeBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGradeBookFilePath(null));
    }

    @Test
    public void setGradeBookFilePath_validPath_setsGradeBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setGradeBookFilePath(path);
        assertEquals(path, modelManager.getGradeBookFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInGradeBook_returnsFalse() {
        assertFalse(modelManager.hasModule(COM_ORG));
    }

    @Test
    public void hasModule_moduleInGradeBook_returnsTrue() {
        modelManager.addModule(COM_ORG);
        assertTrue(modelManager.hasModule(COM_ORG));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void filterModuleListBySem_success() {
        modelManager.addModule(COM_ORG); // Y2S1
        modelManager.addModule(SWE); // Y2S2
        SemesterManager.getInstance().setCurrentSemester(Semester.Y2S1);
        FilteredList<Module> y2s1Modules = modelManager.filterModuleListBySem();
        assertEquals(y2s1Modules.size(), 1);
        assertEquals(y2s1Modules.get(0), COM_ORG);
    }

    @Test
    public void sortModuleListBySem_success() {
        modelManager.addModule(COM_INFO); // Y3S1
        modelManager.addModule(COM_ORG); // Y2S1
        modelManager.addModule(SWE); // Y2S2
        FilteredList<Module> sortedModuleList = modelManager.sortModuleListBySem();
        assertEquals(sortedModuleList.get(0), COM_ORG); // Y2S1
        assertEquals(sortedModuleList.get(1), SWE); // Y2S2
        assertEquals(sortedModuleList.get(2), COM_INFO); // Y3S1
    }

    @Test
    public void resetFilteredList_success() {
        modelManager.addModule(COM_INFO); // Y3S1
        modelManager.addModule(COM_ORG); // Y2S1
        modelManager.addModule(SWE); // Y2S2

        modelManager.updateFilteredModuleList(module -> module.getSemester().equals(Semester.Y2S1));
        ObservableList<Module> filteredList = modelManager.getFilteredModuleList();
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0), COM_ORG);

        modelManager.resetFilteredList();
        ObservableList<Module> resetList = modelManager.getFilteredModuleList();
        assertEquals(resetList.get(0), COM_INFO);
        assertEquals(resetList.get(1), COM_ORG);
        assertEquals(resetList.get(2), SWE);
    }

    @Test
    public void noModule_generatesZeroCap() {
        String generatedCap = modelManager.generateCapAsString();
        String expectedCap = String.format("%.2f", (double) 0);
        assertEquals(generatedCap, expectedCap);
    }

    @Test
    public void generateSemester_success() {
        Semester currentSem = Semester.Y2S1;
        SemesterManager.getInstance().setCurrentSemester(currentSem);
        String generatedSem = modelManager.generateSem();
        assertEquals(currentSem.name(), generatedSem);
    }

    @Test
    public void equals() {
        GradeBook gradeBook = new GradeBookBuilder().withModule(COM_ORG).withModule(EFF_COM).build();
        GradeBook differentGradeBook = new GradeBook();
        UserPrefs userPrefs = new UserPrefs();
        GoalTarget goalTarget = new GoalTarget();

        // same values -> returns true
        modelManager = new ModelManager(gradeBook, userPrefs, goalTarget);
        ModelManager modelManagerCopy = new ModelManager(gradeBook, userPrefs, goalTarget);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different gradeBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentGradeBook, userPrefs, goalTarget)));

        // different filteredList -> returns false
        String[] keywords = COM_ORG.getModuleName().fullModName.split("\\s+");
        modelManager.updateFilteredModuleList(new ModuleNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(gradeBook, userPrefs, goalTarget)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setGradeBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(gradeBook, differentUserPrefs, goalTarget)));
    }
}
