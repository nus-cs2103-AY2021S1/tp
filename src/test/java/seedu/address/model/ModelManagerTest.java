package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.SQUATS;
import static seedu.address.testutil.TypicalLessons.GEH1030;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.body.Height;
import seedu.address.model.body.Weight;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;
import seedu.address.model.routine.Routine;
import seedu.address.model.util.Name;
import seedu.address.testutil.FitNusBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FitNus(), new FitNus(modelManager.getFitNus()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFitNusFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFitNusFilePath(Paths.get("new/address/book/file/path"));
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
    public void setFitNusFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFitNusFilePath(null));
    }

    @Test
    public void setFitNusFilePath_validPath_setsFitNusFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFitNusFilePath(path);
        assertEquals(path, modelManager.getFitNusFilePath());
    }

    @Test
    public void hasLesson_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasLesson(null));
    }

    @Test
    public void hasLesson_lessonNotInFitNus_returnsFalse() {
        assertFalse(modelManager.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonInFitNus_returnsTrue() {
        modelManager.addLesson(GES1028);
        assertTrue(modelManager.hasLesson(GES1028));
    }

    @Test
    public void getFilteredLessonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredLessonList().remove(0));
    }

    @Test
    public void hasLesson_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRoutine(null));
    }

    @Test
    public void hasRoutine_routineNotInFitNus_returnsFalse() {
        assertFalse(modelManager.hasRoutine(LEG_DAY));
    }

    @Test
    public void hasLesson_routineInFitNus_returnsTrue() {
        modelManager.addRoutine(LEG_DAY);
        assertTrue(modelManager.hasRoutine(LEG_DAY));
    }

    @Test
    public void getFilteredRoutineList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRoutineList().remove(0));
    }

    @Test
    public void deleteRoutine_routineInFitNus_returnsFalse() {
        modelManager.addRoutine(LEG_DAY);
        modelManager.deleteRoutine(LEG_DAY);
        assertFalse(modelManager.hasRoutine(LEG_DAY));
    }

    @Test
    public void addExerciseToRoutine_routineInFitNus_returnsFalse() {
        Routine modelRoutine = new Routine(new Name("Leg Day"));
        modelManager.addRoutine(modelRoutine);
        modelManager.addExercise(SQUATS);
        modelManager.addExerciseToRoutine(modelRoutine, SQUATS);
        assertTrue(modelManager.getFilteredRoutineList().get(0).hasExercise(SQUATS));
    }

    @Test
    public void deleteExerciseFromRoutine_routineInFitNus_returnsFalse() {
        Routine modelRoutine = new Routine(new Name("Leg Day"));
        modelRoutine.addExercise(SQUATS);
        modelManager.addRoutine(modelRoutine);
        modelManager.addExercise(SQUATS);
        modelManager.deleteExerciseFromRoutine(modelRoutine, SQUATS);
        assertFalse(modelManager.getFilteredRoutineList().get(0).hasExercise(SQUATS));
    }

    @Test
    public void bodyTests() {
        assertEquals(String.format("%.2f", modelManager.getBmi()), "17.58");
        modelManager.addWeight(new Weight(80));
        assertEquals(String.format("%.2f", modelManager.getBmi()), "31.25");
        modelManager.addHeight(new Height(180));
        assertEquals(String.format("%.2f", modelManager.getBmi()), "24.69");

    }

    @Test
    public void calorieTests_success() {
        assertEquals(modelManager.getFilteredDailyCalorie().size(), 0);
        assertEquals(modelManager.getCalories(), 0);
        modelManager.addCalories(new Calorie(1500));
        assertEquals(modelManager.getCalories(), 1500);
        modelManager.minusCalories(new Calorie(1500));
        assertEquals(modelManager.getCalories(), 0);
        assertEquals(modelManager.getFilteredDailyCalorie().size(), 1);
    }

    @Test
    public void equals() {
        FitNus fitNus = new FitNusBuilder().withLesson(GES1028).withLesson(GEH1030).build();
        FitNus differentFitNus = new FitNus();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(fitNus, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(fitNus, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different fitNus -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFitNus, userPrefs)));

        // different filteredLessonList -> returns false
        String[] keywords = GES1028.getName().fullName.split("\\s+");
        modelManager.updateFilteredLessonList(new LessonNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(fitNus, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFitNusFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(fitNus, differentUserPrefs)));
    }
}
