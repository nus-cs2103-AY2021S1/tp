package jimmy.mcgymmy.model;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.food.exceptions.DuplicateFoodException;
import jimmy.mcgymmy.testutil.FoodBuilder;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class McGymmyTest {

    private final McGymmy mcGymmy = new McGymmy();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mcGymmy.getFoodList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mcGymmy.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMcGymmy_replacesData() {
        McGymmy newData = TypicalFoods.getTypicalMcGymmy();
        mcGymmy.resetData(newData);
        assertEquals(newData, mcGymmy);
    }

    @Test
    public void resetData_withDuplicateFoods_throwsDuplicateFoodException() {
        // Two foods with the same identity fields
        Food editedAlice = new FoodBuilder(TypicalFoods.ALICE).build();
        List<Food> newFoods = Arrays.asList(TypicalFoods.ALICE, editedAlice);
        McGymmyStub newData = new McGymmyStub(newFoods);

        assertThrows(DuplicateFoodException.class, () -> mcGymmy.resetData(newData));
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mcGymmy.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInMcGymmy_returnsFalse() {
        assertFalse(mcGymmy.hasFood(TypicalFoods.ALICE));
    }

    @Test
    public void hasFood_foodInMcGymmy_returnsTrue() {
        mcGymmy.addFood(TypicalFoods.ALICE);
        assertTrue(mcGymmy.hasFood(TypicalFoods.ALICE));
    }

    @Test
    public void hasFood_foodWithSameIdentityFieldsInMcGymmy_returnsTrue() {
        mcGymmy.addFood(TypicalFoods.ALICE);
        Food editedAlice = new FoodBuilder(TypicalFoods.ALICE).build();
        assertTrue(mcGymmy.hasFood(editedAlice));
    }

    @Test
    public void getFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mcGymmy.getFoodList().remove(0));
    }

    /**
     * A stub ReadOnlyMcGymmy whose foods list can violate interface constraints.
     */
    private static class McGymmyStub implements ReadOnlyMcGymmy {
        private final ObservableList<Food> foods = FXCollections.observableArrayList();

        McGymmyStub(Collection<Food> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<Food> getFoodList() {
            return foods;
        }
    }

}
