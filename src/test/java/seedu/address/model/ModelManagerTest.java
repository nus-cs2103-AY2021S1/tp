package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ANIMALS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.AHMENG;
import static seedu.address.testutil.TypicalAnimals.BUTTERCUP;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.animal.AnimalComparator;
import seedu.address.model.animal.AnimalContainsKeywordsPredicate;
import seedu.address.testutil.ZooKeepBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ZooKeepBook(), new ZooKeepBook(modelManager.getZooKeepBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setZooKeepBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setZooKeepBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setZooKeepBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setZooKeepBookFilePath(null));
    }

    @Test
    public void setZooKeepBookFilePath_validPath_setsZooKeepBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setZooKeepBookFilePath(path);
        assertEquals(path, modelManager.getZooKeepBookFilePath());
    }

    @Test
    public void hasAnimal_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAnimal(null));
    }

    @Test
    public void hasAnimal_animalNotInZooKeepBook_returnsFalse() {
        assertFalse(modelManager.hasAnimal(AHMENG));
    }

    @Test
    public void hasAnimal_animalInZooKeepBook_returnsTrue() {
        modelManager.addAnimal(AHMENG);
        assertTrue(modelManager.hasAnimal(AHMENG));
    }

    @Test
    public void getFilteredAnimalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAnimalList().remove(0));
    }

    @Test
    public void equals() {
        ZooKeepBook zooKeepBook = new ZooKeepBookBuilder().withAnimal(AHMENG).withAnimal(BUTTERCUP).build();
        ZooKeepBook differentZooKeepBook = new ZooKeepBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(zooKeepBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(zooKeepBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different zooKeepBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentZooKeepBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = AHMENG.getName().fullName.split("\\s+");
        modelManager.updateFilteredAnimalList(new AnimalContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(zooKeepBook, userPrefs)));

        // different after sorting by feedtimes -> return false
        modelManager.sortAnimals(AnimalComparator.createAnimalFeedTimeComparator());
        assertFalse(modelManager.equals(new ModelManager(zooKeepBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setZooKeepBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(zooKeepBook, differentUserPrefs)));
    }
}
