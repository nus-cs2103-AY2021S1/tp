package seedu.address.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_TAG_CLASSIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFoods.PRATA;
import static seedu.address.testutil.TypicalFoods.getTypicalMenuManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.food.Food;
import seedu.address.model.food.exceptions.DuplicateFoodException;
import seedu.address.testutil.FoodBuilder;
import seedu.address.testutil.TypicalMenus;

public class MenuManagerTest {

    private final MenuManager menuManager = new MenuManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), menuManager.getFoodList());
    }

    @Test
    public void constructor_menuToBeCopied() {
        MenuManager menuManager2 = new MenuManager(TypicalMenus.MENU);
        assertEquals(TypicalMenus.MENU.asUnmodifiableObservableList(),
                menuManager2.getFoodList());
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
    public void resetData_withDuplicateFoods_throwsDuplicateFoodException() {
        // Two foods with the same identity fields
        Food editedPrata = new FoodBuilder(PRATA)
                .build();
        List<Food> newFoods = Arrays.asList(PRATA, editedPrata);
        MenuManager newData = new MenuManager();

        assertThrows(DuplicateFoodException.class, () -> {
            newData.setMenu(newFoods);
            menuManager.resetData(newData);
        });
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menuManager.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInMenuManager_returnsFalse() {
        assertFalse(menuManager.hasFood(PRATA));
    }

    @Test
    public void hasFood_foodInMenuManager_returnsTrue() {
        menuManager.addFood(PRATA);
        assertTrue(menuManager.hasFood(PRATA));
    }

    @Test
    public void hasFood_foodWithSameIdentityFieldsInMenuManager_returnsTrue() {
        menuManager.addFood(PRATA);
        Food editedPrata = new FoodBuilder(PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        assertTrue(menuManager.hasFood(editedPrata));
    }

    @Test
    public void getFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> menuManager.getFoodList().remove(0));
    }

    @Test
    public void removeFood_hasFoodInMenuManager_returnsFalse() {
        menuManager.addFood(PRATA);
        menuManager.removeFood(PRATA);
        assertFalse(menuManager.hasFood(PRATA));
    }

}
