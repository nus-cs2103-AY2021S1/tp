package seedu.address.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_NAME_PRATA;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_PRICE_MILO;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_TAG_CLASSIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMenuItems.MILO;
import static seedu.address.testutil.TypicalMenuItems.PRATA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.food.MenuItem;
import seedu.address.model.food.exceptions.DuplicateFoodException;
import seedu.address.model.food.exceptions.FoodNotFoundException;
import seedu.address.testutil.MenuItemBuilder;

public class MenuTest {

    private final Menu menu = new Menu();

    @Test
    public void contains_nullMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.contains(null));
    }

    @Test
    public void contains_menuItemNotInList_returnsFalse() {
        assertFalse(menu.contains(PRATA));
    }

    @Test
    public void contains_menuItemInList_returnsTrue() {
        menu.add(PRATA);
        assertTrue(menu.contains(PRATA));
    }

    @Test
    public void contains_menuItemWithSameIdentityFieldsInList_returnsTrue() {
        menu.add(PRATA);
        MenuItem editedPrata = new MenuItemBuilder(PRATA).withName(VALID_NAME_PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        assertTrue(menu.contains(editedPrata));
    }

    @Test
    public void add_nullMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.add(null));
    }

    @Test
    public void add_duplicateMenuItem_throwsDuplicateFoodException() {
        menu.add(PRATA);
        assertThrows(DuplicateFoodException.class, () -> menu.add(PRATA));
    }

    @Test
    public void setMenuItem_nullTargetMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setMenuItem(null, PRATA));
    }

    @Test
    public void setMenuItem_nullEditedMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setMenuItem(PRATA, null));
    }

    @Test
    public void setMenuItem_targetMenuItemNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> menu.setMenuItem(PRATA, PRATA));
    }

    @Test
    public void setMenuItem_editedMenuItemIsSameMenuItem_success() {
        menu.add(PRATA);
        menu.setMenuItem(PRATA, PRATA);
        Menu expectedMenu = new Menu();
        expectedMenu.add(PRATA);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setMenuItem_editedMenuItemHasSameIdentity_success() {
        menu.add(PRATA);
        MenuItem editedPrata = new MenuItemBuilder(PRATA).withPrice(VALID_PRICE_MILO).withTags(VALID_TAG_CLASSIC)
                .build();
        menu.setMenuItem(PRATA, editedPrata);
        Menu expectedMenu = new Menu();
        expectedMenu.add(editedPrata);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setMenuItem_editedMenuItemHasDifferentIdentity_success() {
        menu.add(PRATA);
        menu.setMenuItem(PRATA, MILO);
        Menu expectedMenu = new Menu();
        expectedMenu.add(MILO);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setMenuItem_editedMenuItemHasNonUniqueIdentity_throwsDuplicateFoodException() {
        menu.add(PRATA);
        menu.add(MILO);
        assertThrows(DuplicateFoodException.class, () -> menu.setMenuItem(PRATA, MILO));
    }

    @Test
    public void remove_nullMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.remove(null));
    }

    @Test
    public void remove_menuItemDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> menu.remove(PRATA));
    }

    @Test
    public void remove_existingMenuItem_removesMenuItem() {
        menu.add(PRATA);
        menu.remove(PRATA);
        Menu expectedMenu = new Menu();
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setMenuItems_nullMenu_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setMenuItems((List<MenuItem>) null));
    }

    @Test
    public void setMenuItems_menu_replacesOwnListWithProvidedMenu() {
        menu.add(PRATA);
        Menu expectedMenu = new Menu();
        expectedMenu.add(MILO);
        menu.setMenuItems(expectedMenu);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setMenuItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setMenuItems((List<MenuItem>) null));
    }

    @Test
    public void setMenuItems_list_replacesOwnListWithProvidedList() {
        menu.add(PRATA);
        List<MenuItem> menuItemList = Collections.singletonList(MILO);
        menu.setMenuItems(menuItemList);
        Menu expectedMenu = new Menu();
        expectedMenu.add(MILO);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setMenuItems_listWithDuplicateMenuItems_throwsDuplicateFoodException() {
        List<MenuItem> listWithDuplicateMenuItems = Arrays.asList(PRATA, PRATA);
        assertThrows(DuplicateFoodException.class, () -> menu.setMenuItems(listWithDuplicateMenuItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> menu.asUnmodifiableObservableList().remove(0));
    }
}
