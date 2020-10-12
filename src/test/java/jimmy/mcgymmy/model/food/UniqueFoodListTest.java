package jimmy.mcgymmy.model.food;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.model.food.exceptions.DuplicateFoodException;
import jimmy.mcgymmy.model.food.exceptions.FoodNotFoundException;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class UniqueFoodListTest {

    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(TypicalFoods.CHICKEN_RICE));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        assertTrue(uniqueFoodList.contains(TypicalFoods.CHICKEN_RICE));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateFoodException() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.add(TypicalFoods.CHICKEN_RICE));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(null, TypicalFoods.CHICKEN_RICE));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(TypicalFoods.CHICKEN_RICE, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(
                FoodNotFoundException.class, () -> uniqueFoodList.setFood(
                        TypicalFoods.CHICKEN_RICE, TypicalFoods.CHICKEN_RICE));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        uniqueFoodList.setFood(TypicalFoods.CHICKEN_RICE, TypicalFoods.CHICKEN_RICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        uniqueFoodList.setFood(TypicalFoods.CHICKEN_RICE, TypicalFoods.BEANS);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.BEANS);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        uniqueFoodList.add(TypicalFoods.BEANS);
        assertThrows(
                DuplicateFoodException.class, () -> uniqueFoodList.setFood(
                        TypicalFoods.CHICKEN_RICE, TypicalFoods.BEANS));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.remove(TypicalFoods.CHICKEN_RICE));
    }

    @Test
    public void remove_existingFood_removesFood() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        uniqueFoodList.remove(TypicalFoods.CHICKEN_RICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullUniqueFoodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFoodItems((UniqueFoodList) null));
    }

    @Test
    public void setFoods_uniqueFoodList_replacesOwnListWithProvidedUniqueFoodList() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.BEANS);
        uniqueFoodList.setFoodItems(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFoodItems((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(TypicalFoods.CHICKEN_RICE);
        List<Food> foodList = Collections.singletonList(TypicalFoods.BEANS);
        uniqueFoodList.setFoodItems(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.BEANS);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(TypicalFoods.CHICKEN_RICE, TypicalFoods.CHICKEN_RICE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.setFoodItems(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> uniqueFoodList
                        .asUnmodifiableObservableList().remove(0));
    }
}
