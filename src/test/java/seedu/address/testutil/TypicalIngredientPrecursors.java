package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.recipe.IngredientPrecursor;

public class TypicalIngredientPrecursors {
    public static final IngredientPrecursor INGREDIENT_PRECURSOR_1 = new IngredientPrecursor("Apple", 1);
    public static final IngredientPrecursor INGREDIENT_PRECURSOR_2 = new IngredientPrecursor("Banana", 2);

    private TypicalIngredientPrecursors() {} // prevents instantiation

    /**
     * Returns an IngredientPrecursor list with all the typical ingredient precursors.
     */
    public static List<IngredientPrecursor> getTypicalIngredientList() {
        List<IngredientPrecursor> ab = new ArrayList<>();
        ab.add((INGREDIENT_PRECURSOR_1));
        ab.add(INGREDIENT_PRECURSOR_2);
        return ab;
    }
}
