package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalTravelPlans.AUSTRALIA_TRIP;
import static seedu.address.testutil.typicals.TypicalTravelPlans.BOSTON_TRIP;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.testutil.builders.TravelPlannerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TravelPlanner(), new TravelPlanner(modelManager.getTravelPlanner()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTravelPlannerFilePath(Paths.get("travel/planner/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTravelPlannerFilePath(Paths.get("new/travel/planner/file/path"));
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
    public void setTravelPlannerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTravelPlannerFilePath(null));
    }

    @Test
    public void setTravelPlannerFilePath_validPath_setsTravelPlannerFilePath() {
        Path path = Paths.get("travel/planner/file/path");
        modelManager.setTravelPlannerFilePath(path);
        assertEquals(path, modelManager.getTravelPlannerFilePath());
    }

    @Test
    public void hasTravelPlan_nullTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTravelPlan(null));
    }

    @Test
    public void hasTravelPlan_travelPlanNotInTravelPlanner_returnsFalse() {
        assertFalse(modelManager.hasTravelPlan(AUSTRALIA_TRIP));
    }

    @Test
    public void hasTravelPlan_travelPLanInTravelPlanner_returnsTrue() {
        modelManager.addTravelPlan(AUSTRALIA_TRIP);
        assertTrue(modelManager.hasTravelPlan(AUSTRALIA_TRIP));
    }

    @Test
    public void getFilteredTravelPlanList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredTravelPlanList().remove(0));
    }

    @Test
    public void equals() {
        TravelPlanner travelPlanner = new TravelPlannerBuilder().withTravelPlan(AUSTRALIA_TRIP)
                .withTravelPlan(BOSTON_TRIP)
                .build();
        TravelPlanner differentTravelPlanner = new TravelPlanner();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(travelPlanner, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(travelPlanner, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different travelPlanner -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTravelPlanner, userPrefs)));

        // different filteredList -> returns false
        modelManager.deleteTravelPlan(AUSTRALIA_TRIP);
        assertFalse(modelManager.equals(new ModelManager(travelPlanner, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.addTravelPlan(AUSTRALIA_TRIP);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTravelPlannerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(travelPlanner, differentUserPrefs)));
    }
}
