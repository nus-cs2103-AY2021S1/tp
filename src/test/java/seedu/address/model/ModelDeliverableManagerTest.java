package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.deliverable.ModelDeliverable.PREDICATE_SHOW_ALL_DELIVERABLES;
import static seedu.address.testutil.TypicalDeliverables.HOME_PAGE;
import static seedu.address.testutil.TypicalDeliverables.NAVIGATION;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ModelDeliverableManager;
import seedu.address.model.deliverable.deliverable.TitleDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.DeliverableBookBuilder;

public class ModelDeliverableManagerTest {
    private ModelDeliverableManager modelManager = new ModelDeliverableManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new DeliverableBook(), new DeliverableBook(modelManager.getDeliverableBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDeliverableBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setDeliverableBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setDeliverableBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDeliverableBookFilePath(null));
    }

    @Test
    public void setDeliverableBookFilePath_validPath_setsDeliverableBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDeliverableBookFilePath(path);
        assertEquals(path, modelManager.getDeliverableBookFilePath());
    }

    @Test
    public void hasDeliverable_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDeliverable(null));
    }

    @Test
    public void hasDeliverable_deliverableNotInDeliverableBook_returnsFalse() {
        assertFalse(modelManager.hasDeliverable(HOME_PAGE));
    }

    @Test
    public void hasDeliverable_deliverableInDeliverableBook_returnsTrue() {
        modelManager.addDeliverable(HOME_PAGE);
        assertTrue(modelManager.hasDeliverable(HOME_PAGE));
    }

    @Test
    public void addDeliverable_success_placedInView() {
        modelManager = new ModelDeliverableManager();
        modelManager.addDeliverable(HOME_PAGE);
        assertEquals(modelManager.getDeliverableInView(), HOME_PAGE);
    }

    @Test
    public void deleteViewingDeliverable_success_noDeliverableViewed() {
        modelManager = new ModelDeliverableManager();
        modelManager.addDeliverable(HOME_PAGE);
        modelManager.deleteDeliverable(HOME_PAGE);
        assertNull(modelManager.getDeliverableInView());
    }

    @Test
    public void setDeliverable_success_placedInView() {
        modelManager = new ModelDeliverableManager();
        modelManager.addDeliverable(HOME_PAGE);
        modelManager.setDeliverable(HOME_PAGE, NAVIGATION);
        assertEquals(modelManager.getDeliverableInView(), NAVIGATION);
    }

    @Test
    public void getFilteredDeliverableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> modelManager.getFilteredDeliverableList().remove(0));
    }

    @Test
    public void equals() {
        DeliverableBook deliverableBook = new DeliverableBookBuilder().withDeliverable(HOME_PAGE)
                .withDeliverable(NAVIGATION).build();
        DeliverableBook differentDeliverableBook = new DeliverableBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelDeliverableManager(deliverableBook, userPrefs);
        ModelDeliverableManager modelManagerCopy = new ModelDeliverableManager(deliverableBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different deliverableBook -> returns false
        assertFalse(modelManager.equals(new ModelDeliverableManager(differentDeliverableBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = HOME_PAGE.getTitle().value.split("\\s+");
        modelManager.updateFilteredDeliverableList(
                new TitleDescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelDeliverableManager(deliverableBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredDeliverableList(PREDICATE_SHOW_ALL_DELIVERABLES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setDeliverableBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelDeliverableManager(deliverableBook, differentUserPrefs)));
    }
}
