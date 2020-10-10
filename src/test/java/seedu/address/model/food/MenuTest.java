package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_NAME_PRATA;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_PRICE_MILO;
import static seedu.address.logic.commands.FoodCommandTestUtil.VALID_TAG_CLASSIC;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFoods.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.model.food.exceptions.DuplicateFoodException;
import seedu.address.model.food.exceptions.FoodNotFoundException;
import seedu.address.model.menu.Menu;
import seedu.address.testutil.FoodBuilder;

public class MenuTest {

    private final Menu menu = new Menu();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(menu.contains(PRATA));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        menu.add(PRATA);
        assertTrue(menu.contains(PRATA));
    }

    @Test
    public void contains_foodWithSameIdentityFieldsInList_returnsTrue() {
        menu.add(PRATA);
        Food editedPrata = new FoodBuilder(PRATA).withName(VALID_NAME_PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        assertTrue(menu.contains(editedPrata));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateFoodException() {
        menu.add(PRATA);
        assertThrows(DuplicateFoodException.class, () -> menu.add(PRATA));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setFood(null, PRATA));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setFood(PRATA, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> menu.setFood(PRATA, PRATA));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        menu.add(PRATA);
        menu.setFood(PRATA, PRATA);
        Menu expectedMenu = new Menu();
        expectedMenu.add(PRATA);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        menu.add(PRATA);
        Food editedPrata = new FoodBuilder(PRATA).withPrice(VALID_PRICE_MILO).withTags(VALID_TAG_CLASSIC)
                .build();
        menu.setFood(PRATA, editedPrata);
        Menu expectedMenu = new Menu();
        expectedMenu.add(editedPrata);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        menu.add(PRATA);
        menu.setFood(PRATA, MILO);
        Menu expectedMenu = new Menu();
        expectedMenu.add(MILO);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        menu.add(PRATA);
        menu.add(MILO);
        assertThrows(DuplicateFoodException.class, () -> menu.setFood(PRATA, MILO));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> menu.remove(PRATA));
    }

    @Test
    public void remove_existingFood_removesFood() {
        menu.add(PRATA);
        menu.remove(PRATA);
        Menu expectedMenu = new Menu();
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setFoods_nullMenu_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setFoods((List<Food>) null));
    }

    @Test
    public void setFoods_menu_replacesOwnListWithProvidedMenu() {
        menu.add(PRATA);
        Menu expectedMenu = new Menu();
        expectedMenu.add(MILO);
        menu.setFoods(expectedMenu);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.setFoods((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        menu.add(PRATA);
        List<Food> foodList = Collections.singletonList(MILO);
        menu.setFoods(foodList);
        Menu expectedMenu = new Menu();
        expectedMenu.add(MILO);
        assertEquals(expectedMenu, menu);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(PRATA, PRATA);
        assertThrows(DuplicateFoodException.class, () -> menu.setFoods(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> menu.asUnmodifiableObservableList().remove(0));
    }
}
