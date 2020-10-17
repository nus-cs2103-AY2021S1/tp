package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_NOODLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Ingredient;

/**
 * A utility class containing a list of {@code Ingredient} objects to be used in tests.
 */
public class TypicalIngredients {

    public static final Ingredient POTATO = new IngredientBuilder().withValue("Potato").build();
    public static final Ingredient OLIVE = new IngredientBuilder().withValue("Olive").build();
    public static final Ingredient BREAD = new IngredientBuilder().withValue("White Bread").build();
    public static final Ingredient GRAPE = new IngredientBuilder().withValue("Grape").build();
    public static final Ingredient BANANA = new IngredientBuilder().withValue("Banana").build();
    public static final Ingredient OAT = new IngredientBuilder().withValue("Oat").build();
    public static final Ingredient CHOCOLATE = new IngredientBuilder().withValue("Chocolate").build();
    public static final Ingredient VALID_INGREDIENT1 =
            new IngredientBuilder().withValues("Potato", "2 whole").build();
    public static final Ingredient VALID_INGREDIENT2 =
            new IngredientBuilder().withValues("Banana", "200g").build();


    // Manually added
    public static final Ingredient CHICKEN = new IngredientBuilder().withValue("Chicken").build();
    public static final Ingredient MEAT = new IngredientBuilder().withValue("Meat").build();

    // Manually added - Ingredient's details found in {@code CommandTestUtil}
    public static final Ingredient PASTA = new IngredientBuilder()
            .withValues(VALID_INGREDIENT_NOODLE, VALID_QUANTITY_NOODLE).build();
    public static final Ingredient MANGO = new IngredientBuilder()
            .withValues(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code WishfulShrinking} with all the typical ingredients.
     */
    public static WishfulShrinking getTypicalWishfulShrinking() {
        WishfulShrinking ab = new WishfulShrinking();
        for (Ingredient ingredient : getTypicalIngredients()) {
            ab.addIngredient(ingredient);
        }
        return ab;
    }

    public static List<Ingredient> getTypicalIngredients() {
        return new ArrayList<>(Arrays.asList(POTATO, OLIVE, BREAD, GRAPE, BANANA, OAT, CHOCOLATE));
    }
}
