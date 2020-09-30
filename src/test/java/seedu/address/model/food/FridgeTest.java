package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.food.exceptions.FoodNotFoundException;

class FridgeTest {
    private static final Food CHIMKEN = new Food("Chimken", 1, 2, 3);
    private static final Food RAMEN = new Food("Ramen", 2, 3, 4);

    private final Fridge fridge = new Fridge();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(fridge.contains(CHIMKEN));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        fridge.add(CHIMKEN);
        assertTrue(fridge.contains(CHIMKEN));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.add(null));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.setFood(null, CHIMKEN));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.setFood(CHIMKEN, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> fridge.setFood(CHIMKEN, CHIMKEN));
    }

    @Test
    public void setFood_validFood_success() {
        fridge.add(CHIMKEN);
        fridge.setFood(CHIMKEN, RAMEN);
        Fridge expectedFridge = new Fridge();
        expectedFridge.add(RAMEN);
        assertEquals(expectedFridge, this.fridge);
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> fridge.remove(CHIMKEN));
    }

    @Test
    public void remove_existingFood_removesFood() {
        fridge.add(CHIMKEN);
        fridge.remove(CHIMKEN);
        Fridge expectedFridge = new Fridge();
        assertEquals(expectedFridge, fridge);
    }

    @Test
    public void setFoods_nullFridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.setFoods((Fridge) null));
    }

    @Test
    public void setFoods_fridge_replacesOwnListWithProvidedFridge() {
        fridge.add(CHIMKEN);
        Fridge expectedFridge = new Fridge();
        expectedFridge.add(RAMEN);
        fridge.setFoods(expectedFridge);
        assertEquals(expectedFridge, fridge);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fridge.setFoods((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        fridge.add(CHIMKEN);
        List<Food> foodList = Collections.singletonList(RAMEN);
        fridge.setFoods(foodList);
        Fridge expectedFridge = new Fridge();
        expectedFridge.add(RAMEN);
        assertEquals(expectedFridge, fridge);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> fridge.asUnmodifiableObservableList().remove(0));
    }
}
