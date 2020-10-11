package seedu.address.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_TAG_CLASSIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFoods.PRATA;
import static seedu.address.testutil.TypicalFoods.getTypicalMenuManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.Food;
import seedu.address.model.food.exceptions.DuplicateFoodException;
import seedu.address.testutil.FoodBuilder;

public class MenuManagerTest {

    private final MenuManager menuManager = new MenuManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), menuManager.getFoodList());
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
        Food editedPrata = new FoodBuilder(PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        List<Food> newFoods = Arrays.asList(PRATA, editedPrata);
        MenuManagerStub newData = new MenuManagerStub(newFoods);

        assertThrows(DuplicateFoodException.class, () -> menuManager.resetData(newData));
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

    /**
     * A stub ReadOnlyMenuManager whose foods list can violate interface constraints.
     */
    private static class MenuManagerStub implements ReadOnlyMenuManager {
        private final ObservableList<Food> foods = FXCollections.observableArrayList();
        //        private final ObservableList<Vendor> vendors = FXCollections.observableArrayList();

        MenuManagerStub(Collection<Food> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<Food> getFoodList() {
            return foods;
        }

        //        @Override
        //        public ObservableList<Vendor> getVendorList() {
        //            return vendors;
        //        }
    }

}
