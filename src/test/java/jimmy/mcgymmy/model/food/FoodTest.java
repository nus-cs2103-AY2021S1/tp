package jimmy.mcgymmy.model.food;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.testutil.Assert;
import jimmy.mcgymmy.testutil.FoodBuilder;

public class FoodTest {
    public static final Name VALID_FOOD_NAME = new Name("test food");
    public static final Name VALID_FOOD_NAME_2 = new Name("test food 2");
    public static final String INVALID_FOOD_NAME = "";

    public static final Protein PROTEIN = new Protein(2);
    public static final Protein PROTEIN_1 = new Protein(3);
    public static final Carbohydrate CARBOHYDRATE = new Carbohydrate(3);
    public static final Carbohydrate CARBOHYDRATE_1 = new Carbohydrate(4);
    public static final Fat FAT = new Fat(4);
    public static final Fat FAT_1 = new Fat(5);

    public static final Food COMPARED_FOOD = new Food(VALID_FOOD_NAME, PROTEIN, FAT, CARBOHYDRATE);
    public static final Food SAME_AS_COMPARED_FOOD = new Food(VALID_FOOD_NAME, PROTEIN, FAT, CARBOHYDRATE);
    public static final Food FOOD_W_DIFFERENT_NAME = new Food(VALID_FOOD_NAME_2, PROTEIN, FAT, CARBOHYDRATE);
    public static final Food FOOD_W_DIFFERENT_PROTEIN = new Food(VALID_FOOD_NAME, PROTEIN_1, FAT, CARBOHYDRATE);
    public static final Food FOOD_W_DIFFERENT_CARBS = new Food(VALID_FOOD_NAME, PROTEIN, FAT_1, CARBOHYDRATE);
    public static final Food FOOD_W_DIFFERENT_FAT = new Food(VALID_FOOD_NAME, PROTEIN, FAT, CARBOHYDRATE_1);

    @Test
    public void constructor_nullProtein_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Food(VALID_FOOD_NAME, null, FAT, CARBOHYDRATE));
    }

    @Test
    public void constructor_nullCarbohydrate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Food(VALID_FOOD_NAME, PROTEIN, FAT, null));
    }

    @Test
    public void constructor_nullFat_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Food(VALID_FOOD_NAME, PROTEIN, null, CARBOHYDRATE));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Food(new Name(INVALID_FOOD_NAME), PROTEIN, FAT, CARBOHYDRATE));
    }

    @Test
    public void equals() {
        // identical -> returns true
        assertEquals(COMPARED_FOOD, COMPARED_FOOD);

        // different object all field are the same -> returns true
        assertEquals(COMPARED_FOOD, SAME_AS_COMPARED_FOOD);

        // different name -> returns false
        assertFalse(COMPARED_FOOD.equals(FOOD_W_DIFFERENT_NAME));

        // different protein -> returns false
        assertFalse(COMPARED_FOOD.equals(FOOD_W_DIFFERENT_PROTEIN));

        // different carbohydrate -> returns false
        assertFalse(COMPARED_FOOD.equals(FOOD_W_DIFFERENT_CARBS));

        // different fat -> returns false
        assertFalse(COMPARED_FOOD.equals(FOOD_W_DIFFERENT_FAT));

        // different type -> returns false
        assertFalse(COMPARED_FOOD.equals(PROTEIN));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }


}
