package seedu.address.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_TAG_CLASSIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMenuItems.CHEESE_PRATA;
import static seedu.address.testutil.TypicalMenuItems.MILO;
import static seedu.address.testutil.TypicalMenuItems.PRATA;
import static seedu.address.testutil.TypicalMenuItems.getTypicalMenuManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.food.MenuItem;
import seedu.address.model.food.exceptions.DuplicateFoodException;
import seedu.address.testutil.MenuItemBuilder;
import seedu.address.testutil.TypicalMenus;

public class MenuManagerTest {

    private final MenuManager menuManager = new MenuManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), menuManager.getMenuItemList());
    }

    @Test
    public void constructor_menuToBeCopied() {
        MenuManager menuManager2 = new MenuManager(TypicalMenus.MENU);
        assertEquals(TypicalMenus.MENU.asUnmodifiableObservableList(),
                menuManager2.getMenuItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menuManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMenuManager_replacesData() {
        MenuManager newData = getTypicalMenuManager();
        menuManager.resetData(newData);
        assertEquals(newData, menuManager);
    }

    @Test
    public void resetData_withDuplicateMenuItems_throwsDuplicateFoodException() {
        // Two MenuItems with the same identity fields
        MenuItem editedPrata = new MenuItemBuilder(PRATA)
                .build();
        List<MenuItem> newMenuItems = Arrays.asList(PRATA, editedPrata);
        MenuManager newData = new MenuManager();

        assertThrows(DuplicateFoodException.class, () -> {
            newData.setMenu(newMenuItems);
            menuManager.resetData(newData);
        });
    }

    @Test
    public void hasMenuItem_nullMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menuManager.hasMenuItem(null));
    }

    @Test
    public void hasMenuItem_menuItemNotInMenuManager_returnsFalse() {
        assertFalse(menuManager.hasMenuItem(PRATA));
    }

    @Test
    public void hasMenuItem_menuItemInMenuManager_returnsTrue() {
        menuManager.addMenuItem(PRATA);
        assertTrue(menuManager.hasMenuItem(PRATA));
    }

    @Test
    public void hasMenuItem_menuItemWithSameIdentityFieldsInMenuManager_returnsTrue() {
        menuManager.addMenuItem(PRATA);
        MenuItem editedPrata = new MenuItemBuilder(PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        assertTrue(menuManager.hasMenuItem(editedPrata));
    }

    @Test
    public void getMenuItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> menuManager.getMenuItemList().remove(0));
    }

    @Test
    public void removeMenuItem_hasMenuItemInMenuManager_returnsFalse() {
        menuManager.addMenuItem(PRATA);
        menuManager.removeMenuItem(PRATA);
        assertFalse(menuManager.hasMenuItem(PRATA));
    }

    @Test
    public void setMenuItem_hasMenuItemInMenuManager_returnsTrue() {
        menuManager.addMenuItem(PRATA);
        menuManager.setMenuItem(PRATA, MILO);
        assertTrue(menuManager.hasMenuItem(MILO));
    }

    @Test
    public void sortMenuItemInAscendingByName_sortsMenu() {
        menuManager.addMenuItem(PRATA);
        menuManager.addMenuItem(MILO);
        menuManager.addMenuItem(CHEESE_PRATA);
        // Added in wrong order
        menuManager.sortMenuItemByName(true);
        MenuManager sortedMenu = new MenuManager();
        sortedMenu.addMenuItem(CHEESE_PRATA);
        sortedMenu.addMenuItem(MILO);
        sortedMenu.addMenuItem(PRATA);
        assertEquals(menuManager.getMenuItemList(), sortedMenu.getMenuItemList());
    }

    @Test
    public void sortMenuItemInDescendingByName_sortsMenu() {
        menuManager.addMenuItem(PRATA);
        menuManager.addMenuItem(CHEESE_PRATA);
        menuManager.addMenuItem(MILO);
        menuManager.sortMenuItemByName(false);
        MenuManager sortedMenu = new MenuManager();
        sortedMenu.addMenuItem(PRATA);
        sortedMenu.addMenuItem(MILO);
        sortedMenu.addMenuItem(CHEESE_PRATA);
        assertEquals(menuManager.getMenuItemList(), sortedMenu.getMenuItemList());
    }

    @Test
    public void sortMenuItemInAscendingByPrice_sortsMenu() {
        menuManager.addMenuItem(PRATA);
        menuManager.addMenuItem(MILO);
        menuManager.addMenuItem(CHEESE_PRATA);
        //Added in wrong order
        menuManager.sortMenuItemByPrice(true);
        MenuManager sortedMenu = new MenuManager();
        sortedMenu.addMenuItem(PRATA);
        sortedMenu.addMenuItem(MILO);
        sortedMenu.addMenuItem(CHEESE_PRATA);
        assertEquals(menuManager.getMenuItemList(), sortedMenu.getMenuItemList());
    }

    @Test
    public void sortMenuItemInDescendingByPrice_sortsMenu() {
        menuManager.addMenuItem(PRATA);
        menuManager.addMenuItem(CHEESE_PRATA);
        menuManager.addMenuItem(MILO);
        menuManager.sortMenuItemByPrice(false);
        MenuManager sortedMenu = new MenuManager();
        sortedMenu.addMenuItem(CHEESE_PRATA);
        sortedMenu.addMenuItem(MILO);
        sortedMenu.addMenuItem(PRATA);
        assertEquals(menuManager.getMenuItemList(), sortedMenu.getMenuItemList());
    }

}
